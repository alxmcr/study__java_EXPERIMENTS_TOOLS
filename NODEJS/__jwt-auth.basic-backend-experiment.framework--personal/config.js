module.exports = {
    db: process.env.MONGODB_URI || 'mongodb://test:test123@ds231588.mlab.com:31588/passportjs-002',
    SECRET_TOKEN: 'MY_KEY_SECRET'
}