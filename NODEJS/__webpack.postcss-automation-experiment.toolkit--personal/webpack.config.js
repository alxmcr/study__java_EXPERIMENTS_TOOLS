const path = require("path");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

module.exports = {
  entry: {
    home: './src/js/home.js',
    about: './src/js/about.js',
    funciones: './src/js/funciones.js'
  },
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].bundle.js'
  },
  devServer: {
    contentBase: __dirname + '/dist',
    open: true, // browser
    watchContentBase: true,
    overlay: {
      warnings: true,
      errors: true
    }
  },
  module: {
    rules: [{
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env'],
            plugins: [require('@babel/plugin-proposal-object-rest-spread')]
          }
        }
      },
      {
        test: /\.css$/,
        use: [{
            // 2nd loader in running
            // style-loader inyect <style>
            loader: 'style-loader'
          },
          {
            // 1st loader in running
            // css-loader intepreta el CSS
            loader: 'css-loader'
          }
        ]
      },
      {
        test: /\.html$/,
        use: [{
            loader: 'file-loader',
            options: {
              name: "[name].html"
            }
          },
          {
            loader: 'extract-loader'
          },
          {
            loader: 'html-loader',
            // attrs: [ <tag>:<attribute> ]
            // For example <img src="image.ext" />
            options: {
              attrs: ['img:src']
            }
          }
        ]
      },
      {
        test: /\.pug$/,
        loader: 'pug-loader',
      },
      {
        test: /\.(png|jpg|gif)$/,
        use: [{
          loader: 'file-loader',
          options: {
            name: "./images/[name].[ext]"
          }
        }]
      },
      {
        test: /\.scss$/,
        use: [
          {
            loader: "style-loader", // creates style nodes from JS strings
          },
          MiniCssExtractPlugin.loader,
          { 
            loader: 'css-loader',  // translates CSS into CommonJS
            options: { 
              importLoaders: 1 
            } 
          },
          {
            loader: 'postcss-loader'
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
      title: 'Home | Webpack',
      filename: 'index.html',
      template: './src/views/index.pug',
      chunks: ['funciones', 'home']
    }),

    // About
    new HtmlWebpackPlugin({
      title: 'About | Webpack',
      filename: 'about.html',
      template: './src/views/about.pug',
      chunks: ['funciones', 'about']
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
  devServer: {
    contentBase: __dirname + '/dist',
    open: true, // browser
    watchContentBase: true,
    overlay: {
      warnings: true,
      errors: true
    }
  },
}