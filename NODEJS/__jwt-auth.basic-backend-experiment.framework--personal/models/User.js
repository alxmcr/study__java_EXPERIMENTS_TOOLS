const mongoose = require('mongoose');
const Schema = mongoose.Schema;

// User Schema
const UserSchema = new Schema({
    username: {
        type: String,
        required: [true, 'Name field is required']
    },
    password: {
        type: String
    }
});

UserSchema.methods.validPassword = function(password) {
    if (password === this.password) {
        return true;
    } else {
        return false;
    }
}

const User = mongoose.model('user', UserSchema);

module.exports = User;