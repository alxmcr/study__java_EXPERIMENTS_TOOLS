const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = {
  entry:
  {
    home: './src/js/home.js',
    about: './src/js/about.js',
    functions: './src/js/utils/functions.js',
  },
  output:
  {
    filename: '[name].js',
    path: path.resolve(__dirname, 'dist')
  },
  module:
  {
    rules: [
        {
            test: /\.js$/,
            use: [
            {
                loader: 'babel-loader',
                options:
                {
                presets: ['@babel/preset-env']
                }
            }]
        },
        {
            test: /\.pug$/,
            loader: 'pug-loader',
            options: {
                pretty: true
            }
        },
        {
            test: /\.(png|jpg|gif)$/,
            use: [{
                loader: 'file-loader',
                options: {
                name: '[name].[ext]',
                    outputPath: 'assets/img/',
                }
            }]
        },
        {
          test: /\.scss$/,
          use: [{
              loader: "style-loader", // creates style nodes from JS strings
            },
            MiniCssExtractPlugin.loader,
            {
              loader: "css-loader", // translates CSS into CommonJS
            },
            {
              loader: "sass-loader", // compiles Sass to CSS, using Node Sass by default
              options: {
                includePaths: [path.resolve(__dirname, '/partials')]
              }
            },
          ]
        }
    ]
  },
  plugins: [
    new CleanWebpackPlugin(['dist']),

    new HtmlWebpackPlugin({
      title: 'Home',
      filename: 'index.html',
      template: './src/templates/index.pug',
      chunks: ['functions', 'home']
    }),

    // About
    new HtmlWebpackPlugin({
      title: 'About',
      filename: 'about.html',
      template: './src/templates/about.pug',
      chunks: ['functions', 'about']
    }),

    new MiniCssExtractPlugin({
      // Options similar to the same options in webpackOptions.output
      // both options are optional
      filename: 'css/[name].css',
      chunkFilename: '[id].css'
    })
  ],
  // JS sourcemaps
  devtool: 'inline-sourcemap',
  devServer:
  {
    contentBase: __dirname + '/dist',
    open: true, // browser
    watchContentBase: true,
    overlay:
    {
      warnings: true,
      errors: true
    }
  },
}