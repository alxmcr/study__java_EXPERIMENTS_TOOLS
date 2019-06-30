const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

// CSS
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = {
	entry: {
		app: './src/js/app.js'
	},
	output: {
		path: path.resolve(__dirname, 'dist'),
		filename: '[name].bundle.js'
	},
	module: {
		rules: [
			{
				test: /\.(html)$/,
				use: {
				  loader: 'html-loader',
				}
			  },
			{
				test: /\.css$/,
				use: [{
						loader: 'style-loader',
					},{
						loader: MiniCssExtractPlugin.loader,
						options: {
							publicPath: '../'
						}
					},
					{
						loader: 'css-loader'
					}
				]
			},
			{
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
				test: /\.(png|jpg|gif)$/i,
				use: [{
					loader: 'file-loader',
					options: {
						outputPath: 'assets/images'
					}
				}, ]
			}
		]
	},
	plugins: [
		new CleanWebpackPlugin(['dist']),

		new HtmlWebpackPlugin({
			filename: 'index.html',
			template: './src/index.html'
		}),

		new MiniCssExtractPlugin({
			// Options similar to the same options in webpackOptions.output
			// both options are optional
			filename: 'css/[name].css',
			chunkFilename: '[id].css'
		}),
	],
	devServer: {
		contentBase: __dirname + '/dist',
		compress: true, // Enable gzip compression for everything served
		open: true, // browser
		port: 9000,
		watchContentBase: true,
		stats: 'errors-only',
		overlay: {
			warnings: true,
			errors: true
		}
	},
	devtool: 'inline-source-map',
}