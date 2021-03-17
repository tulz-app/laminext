const webpack = require('webpack');
const path = require('path');
const _ = require('lodash');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const ExtractCssChunks = require('extract-css-chunks-webpack-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin');

// const scalaOutputPath = path.resolve(__dirname, './target/scala-2.13');
const scalaOutputPath = path.resolve(__dirname, './target/scala-3.0.0-RC1');

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
    entry: [
      path.resolve(__dirname, './src/main/static/stylesheets/main.css'),
    ],
    mode,
    resolve: {
      modules: [
        'node_modules',
        path.resolve(__dirname, 'node_modules'),
        path.resolve(__dirname, 'src/main/static/stylesheets')
      ],
    },
    output: {
      publicPath: '/',
      path: path.resolve(__dirname, 'dist'),
      filename: '[name].bundle.[contenthash].js'
    },
    module: {
      rules: [
        {
          test: /\.js$/,
          enforce: 'pre',
          use: [{
            loader: 'scalajs-friendly-source-map-loader',
            options: {
              name: '[name].[contenthash:8].[ext]',
              bundleHttp: false
            }
          }]
        },
        {
          test: /\.css$/,
          use: [ExtractCssChunks.loader, 'css-loader', 'postcss-loader'],
        },
        {
          test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
          use: [{
            loader: 'file-loader',
            options: {
              name: '[name].[ext]',
              outputPath: 'fonts/'
            }
          }]
        }
      ]
    },
    plugins: [
      new ExtractCssChunks({
        filename: '[name].[contenthash].css',
        chunkFilename: '[id].[contenthash].css',
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
          {from: './src/main/static/images', to: 'images'},
          {from: './src/main/static/robots.txt', to: '[name].[ext]'},
          {from: './src/main/static/favicon/*', to: '[name].[ext]'},
          {from: './src/main/static/theme/*', to: 'stylesheets/highlightjs/[name].[ext]'},
        ]
      })
    ]
  }
}

function prod() {
  return {
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
}

function dev() {
  return {
    devtool: 'cheap-module-source-map',
    entry: [
      path.resolve(__dirname, `${scalaOutputPath}/website-fastopt/main.js`),
    ],
    plugins: [
      new webpack.HotModuleReplacementPlugin({
        // Options...
      })
    ]
  };
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
      console.info('production build');
      return _.mergeWith(
        {},
        common('production'),
        prod(),
        customizer
      );

    case 'build:dev':
      console.info('development build');
      return _.mergeWith(
        {},
        common('development'),
        dev(),
        customizer
      );

    case 'start:prod':
      console.info('production dev server');
      return _.mergeWith(
        {},
        common('production'),
        prod(),
        {devServer},
        customizer
      );

    case 'start':
    case 'start:dev':
    default:
      console.info('development dev server');
      return _.mergeWith(
        {},
        common('development'),
        dev(),
        {devServer},
        customizer
      );
  }
}

const config = getConfig()
module.exports = config
