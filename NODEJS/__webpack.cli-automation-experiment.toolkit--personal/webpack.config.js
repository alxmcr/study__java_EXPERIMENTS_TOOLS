const path = require('path');

module.exports = {
    entry: [
      './src/app.js', './src/functions.js'
    ],
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'my-first-webpack.bundle.js'
    }
  }