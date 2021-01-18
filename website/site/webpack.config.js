const path = require('path');
const _ = require('lodash');

const compression = require('compression');
const ExtractCssChunks = require('extract-css-chunks-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const {CleanWebpackPlugin} = require('clean-webpack-plugin');
const TerserPlugin = require('terser-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin');

// const scalaOutputPath = path.resolve(__dirname, './target/scala-2.13');
const scalaOutputPath = path.resolve(__dirname, './target/scala-3.0.0-RC1');

const devServerHost = '127.0.0.1';
const devServerPort = 30088;

const devServer = {
  // hot: true,
  // inline: true,
  disableHostCheck: true,
  clientLogLevel: 'info',
  public: 'https://dev.laminext.tulz.app:443',
  port: devServerPort,
  host: devServerHost,
  historyApiFallback: {
    index: ''
  }
};

function common(mode) {
  return {
    mode,
    resolve: {
      modules: [
        "node_modules",
        path.resolve(__dirname, "node_modules")
      ],
    },
    output: {
      publicPath: '/',
      filename: '[name].[hash].js',
      library: 'laminext',
      libraryTarget: 'var'
    },
    entry: [
      path.resolve(__dirname, './src/main/static/stylesheets/main.css')
    ],
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
          use: [
            {
              loader: ExtractCssChunks.loader,
              options: {
                filename: '[name].[contenthash:8].[ext]'
              }
            },
            {
              loader: 'css-loader'
            },
            {
              loader: 'postcss-loader',
            },
          ]
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
        },
        {
          test: /\.(png|jpg)(\?v=\d+\.\d+\.\d+)?$/,
          use: [{
            loader: 'file-loader',
            options: {
              name: '[name].[ext]',
              outputPath: 'images/'
            }
          }]
        }
      ]
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: './src/main/static/html/index.html.ejs',
        minify: false,
        inject: 'head',
        mode: mode,
        scriptLoading: 'defer'
      }),
      new CopyWebpackPlugin({
        patterns: [
          // {from: './src/main/static/images', to: 'images'},
          {from: './src/main/static/robots.txt', to: '[name].[ext]'},
          {from: './src/main/static/manifest.json', to: '[name].[ext]'},
          {from: './node_modules/highlight.js/styles/*', to: 'stylesheets/highlightjs/[name].[ext]'},
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
    devtool: 'source-map',
    optimization: {
      minimize: true
      // minimizer: [new TerserPlugin()],
    },
    plugins: [
      new CleanWebpackPlugin(),
      new ExtractCssChunks(
        {
          filename: '[name].[contenthash:8].css',
          chunkFilename: '[id].css'
          // hot: true // optional as the plugin cannot automatically detect if you are using HOT, not for production use
        }
      ),
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
      new ExtractCssChunks(
        {
          filename: '[name].[contenthash:8].css',
          chunkFilename: '[id].css'
          // hot: true
        }
      ),
    ]
  };
}

function customizer(objValue, srcValue) {
  if (_.isArray(objValue)) {
    return objValue.concat(srcValue);
  }
}

module.exports = function () {
  switch (process.env.npm_lifecycle_event) {
    case 'build:prod':
    case 'build':
      console.log('production build');
      return _.mergeWith(
        {},
        common('production'),
        prod(),
        customizer
      );

    case 'build:dev':
      console.log('development build');
      return _.mergeWith(
        {},
        common('development'),
        dev(),
        customizer
      );

    case 'start:prod':
      console.log('production dev server');
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
      console.log('development dev server');
      return _.mergeWith(
        {},
        common('development'),
        dev(),
        {devServer},
        customizer
      );
  }
};
