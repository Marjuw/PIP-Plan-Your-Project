const express = require('express');
const router = express.Router();
const mongoose = require("mongoose");
const request = require("request");

const { v4: uuidv4 } = require('uuid');

const Project = require("../models/project");
const { ResourceNotFoundError, InternalError } = require('./errors.js');

//Erhalte alle Proejkte       //Brauchen wir  zum überprüfen (als programmierer) => Noch in Rest hinzufügen
router.get("/", (req, res, next) => {

    const query = req.query;   // Falls Qury parameter angegeben werden wird gefiltert

    Project.find(query)   // Direkt mit Query da wenn keine Query parameter eingegeben ist der "Filter auf null"
        .exec()
        .then(docs => {
                console.log(docs);
                res.status(200).json(docs);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });

});

//erstelle ein Projekt
router.post("/", (req, res, next) => {

    const project = new Project({

        _id: uuidv4(),
        projektleiter: req.body.projektleiter,
        name: req.body.name,
        gewuenschteTeamgroesse: req.body.gewuenschteTeamgroesse,
        gewuenschteRollen: req.body.gewuenschteRollen,
        beschreibung: req.body.beschreibung,
        kategorie: req.body.kategorie,
        ausfuehrungsort: req.body.ausfuehrungsort,
        ausfuehrungsort_sichtbar: req.body.ausfuehrungsort_sichtbar,
        plz: req.body.plz,
        plz_sichtbar: req.body.plz_sichtbar,
        ort: req.body.ort,
        ort_sichtbar: req.body.ort_sichtbar,
        zweck: req.body.zweck,
        startzeitpunkt: req.body.startzeitpunkt,
        dauer: req.body.dauer,
        anforderung: req.body.anforderung,
        aktuelleTeilnehmerzahl: req.body.aktuelleTeilnehmerzahl,
        teilnehmer: req.body.teilnehmer,
        teilnehmer_sichtbar: req.body.teilnehmer_sichtbar,
        avatarPicture: req.body.avatarPicture

    });

    project.save()
        .then(result => {
            console.log(result);
            res.status(201).json({
                message: "Handling POST requests to /projects",
                createdProject: result
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});

//Erhalte bestimmtes Projekt
router.get("/:projectId", (req, res, next) => {

    const id = req.params.projectId;
    Project.findById(id)
        .exec()
        .then(doc => {
            console.log("From database", doc);
            if (doc) {
                res.status(200).json(doc);
            } else {
                res
                    .status(404)
                    .json({ message: "No valid entry found for provided ID" });
            }
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({ error: err });
        });
});

//Lösche bestimmtes Projekt
router.delete("/:projectId", (req, res, next) => {

    const id = req.params.projectId;
    Project.remove({_id: id})
    //Hier muss noch hin was mit dem Projekt passiert, wenn dieses Projekt eins erstellt hat
        .exec()
        .then(result => {
            res.status(200).json(result);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });

        });
});

//Bearbeite/aktualisiere bestimmtes Projekt
//Zum Testen: [{"propName": <Attributname>, "value": <neuer Attributwert>}]
/*router.put("/:projectId", (req, res, next) => {
    const id = req.params.projectId;
    const updateOps = {};
    for (const ops of req.body) {
        updateOps[ops.propName] = ops.value;
    }
    Project.update({ _id: id }, { $set: updateOps })
        .exec()
        .then(result => {
            console.log(result);
            res.status(200).json(result);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});*/

// ZB: {"name": "NachUpdate"}
router.put("/:projectId", (req, res, next) => {
    const id = req.params.projectId;
    // const updateOps = {};
    // for (const ops of req.body) {
    //     updateOps[ops.propName] = ops.value;
    // }
    Project.update({ _id: id }, { $set: req.body })
        .exec()
        .then(result => {
            console.log(result);
            res.status(200).json(result);
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({
                error: err
            });
        });
});


module.exports = router;