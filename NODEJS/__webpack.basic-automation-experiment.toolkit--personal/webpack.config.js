const path = require('path');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
  mode: 'development',
  entry: ['./src/app.js', './src/functions.js'],
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'my-first-webpack.bundle.js'
  },
  // Loaders
  module: {
    rules: [{
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.scss$/,
        use: [
          // Information

          // "style-loader" creates style nodes from JS strings
          // "css-loader" translates CSS into CommonJS
          // "sass-loader" compiles Sass to CSS, using Node Sass by default


          // fallback to style-loader in development
          process.env.NODE_ENV !== 'production' 
                                ? 'style-loader' 
                                : MiniCssExtractPlugin.loader,
                                  "css-loader",
                                  "sass-loader"
        ]
      }
    ]
  },
  plugins: [
    new MiniCssExtractPlugin({
      // Options similar to the same options in webpackOptions.output
      // both options are optional
      filename: "[name].css",
      chunkFilename: "[id].css"
    })
  ],
  // dev-server
  devServer: {
    contentBase: __dirname + '/',
    compress: true,
    open: true, // browser
    port: 9000,
    watchContentBase: true,
    overlay: {
      warnings: true,
      errors: true
    }
  }
};