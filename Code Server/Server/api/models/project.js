const mongoose = require('mongoose');

const projectSchema = mongoose.Schema({

    _id: mongoose.Schema.Types.ObjectId,
    projektleiter: mongoose.Schema.Types.ObjectId,
    name: {type: String, required: true},
    gewuenschteTeamgroesse: Int,
    gewuenschteRollen: Sring,
    beschreibung: String,
    kategorie: [String],   // Tag
    ausfuehrungsort: String,
    ausfuehrungsort_sichtbar: Boolean,  //wenn true dann nicht nur innerhalb des Teams sondern auch global sichtbar
    zweck: [String],  //Tag
    startzeitpunkt: Date,
    dauer: Int,
    anforderung: [String],    //Tag
    aktuelleTeilnehmerzahl: Int,
    teilnehmer: [userID],
    teilnehmer_sichtbar: Boolean        //wenn true dann nicht nur innerhalb des Teams sondern auch global sichtbar


});

module.exports = mongoose.model('Projects', projectSchema);