const _ = require('lodash');
const base = require('./tailwind.base.config');

module.exports = _.mergeWith({}, base, {
  purge: [
    './target/scala-2.13/website-opt/*.js',
    './src/main/static/html/*.ejs',
  ]
});

