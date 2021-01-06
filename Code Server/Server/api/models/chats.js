const mongoose = require('mongoose');

const chatSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    bezeichnung: String,
    erstelldatum: String, //Schauen ob Datum als Typ ?
    userID: [String]
});

module.exports = mongoose.model('Chats', chatSchema);