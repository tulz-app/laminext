const path = require('path');
const _ = require('lodash');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin');
const scalaVersion = require('./scala-version')

const scalaOutputPath = path.resolve(__dirname, `./target/scala-${scalaVersion}`);

const devServerHost = '127.0.0.1';
const devServerPort = 30088;

const devServer = _.mergeWith(
  {
    hot: true,
    injectHot: true,
    injectClient: true,
    transportMode: 'ws',
    port: devServerPort,
    host: devServerHost,
    historyApiFallback: {
      index: ''
    }
  },
  require('./devserver.config.js')
)


function common(mode) {
  return {
    mode,
    entry: [
      path.resolve(__dirname, './src/main/static/stylesheets/main.css'),
    ],
    output: {
      publicPath: '/',
      path: path.resolve(__dirname, 'dist'),
      filename: '[name].bundle.[contenthash].js'
    },
    module: {
      rules: [
        {
          test: /\.css$/,
          use: [MiniCssExtractPlugin.loader, 'css-loader', 'postcss-loader'],
        },
        {
          test: /\.(png|jpg|woff(2)?|ttf|eot|svg)$/,
          type: 'asset/resource'
        },
      ]
    },
    plugins: [
      new MiniCssExtractPlugin({
        filename: '[name].[contenthash].css',
      }),
      new HtmlWebpackPlugin({
        template: './src/main/static/html/index.html.ejs',
        minify: false,
        inject: 'head',
        mode: mode,
        scriptLoading: 'defer'
      }),
      new CopyWebpackPlugin({
        patterns: [
          {from: './src/main/public', to: ''},
        ]
      })
    ]
  }
}

const prod = {
  entry: [
    path.resolve(__dirname, `${scalaOutputPath}/website-opt/main.js`),
  ],
  optimization: {
    minimize: true,
    minimizer: [new TerserPlugin()],
  },
  plugins: [
    new CleanWebpackPlugin(),
    new CompressionPlugin({
      test: /\.(js|css|html|svg|json|woff|woff2)$/,
      deleteOriginalAssets: false,
    }),
    new CompressionPlugin({
      test: /\.(js|css|html|svg|woff|woff2)$/,
      filename: '[path][base].br',
      algorithm: 'brotliCompress',
      compressionOptions: {
        // zlib’s `level` option matches Brotli’s `BROTLI_PARAM_QUALITY` option.
        level: 11,
      },
      minRatio: 0.8,
      deleteOriginalAssets: false,
    })
  ]
}

const dev = {
  entry: [
    path.resolve(__dirname, `${scalaOutputPath}/website-fastopt/main.js`),
  ],
  devtool: 'cheap-module-source-map',
}

function customizer(objValue, srcValue) {
  if (_.isArray(objValue)) {
    return objValue.concat(srcValue);
  }
}

function getConfig() {
  switch (process.env.npm_lifecycle_event) {
    case 'build:prod':
    case 'build':
      return _.mergeWith(common('production'), prod, customizer);

    case 'build:dev':
      return _.mergeWith(common('development'), dev, customizer);

    case 'start:prod':
      return _.mergeWith(common('production'), prod, {devServer}, customizer);

    case 'start':
    case 'start:dev':
    default:
      return _.mergeWith(common('development'), dev, {devServer}, customizer);
  }
}

const config = getConfig()
module.exports = config
