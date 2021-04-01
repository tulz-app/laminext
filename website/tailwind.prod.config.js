const _ = require('lodash');
const base = require('./tailwind.base.config');

module.exports = _.mergeWith({}, base, {
  purge: [
    './target/scala-2.13/website-opt/*.js',
    './src/main/resources/**/*.html',
    './src/main/resources/**/*.md',
    './src/main/scala/**/*.html',
    './src/main/scala/**/*.md',
    './src/main/static/html/*',
  ]
});

