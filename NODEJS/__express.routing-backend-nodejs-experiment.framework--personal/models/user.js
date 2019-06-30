'use strict';

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const bcrypt = require('bcrypt-nodejs');
const crypto = require('crypto');

const UserSchema = new Schema({
	email: {
		type: String,
		unique: true,
		lowercase: true
	},
	displayName: String,
	avatar: String,
	password: {
		type: String,
		select: false
	},
	signupDate: {
		type: Date,
		default: Date.now()
	},
	lastLogin: Date
});

UserSchema.pre('save', function(done){
	if (!this.isModified('password')) {
		return done();
	}

	brypt.genSalt(10, (err, salt) => {
		if (err) {
			return done();
		}

		brypt.hash(this.password, salt, null, (err, hash) => {

			if (err) {
				return done();
			}

			this.password = hash;
			done();

		})
	})
});

UserSchema.methods.gravatar = function(){
	if(!this.email){
		return `https://gravatar.com/avatar/?s=200&d=retro`
	}

	const md5 = crypto.createHash('md5').update(this.email).digest('hex');

	return `https://gravatar.com/avatar/${md5}?s=200&d=retro`
}

module.exports = mongoose.model('User', UserSchema)