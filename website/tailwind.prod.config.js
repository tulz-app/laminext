const _ = require('lodash');
const base = require('./tailwind.base.config');

const config = _.mergeWith({}, base, {
  purge: {
    enabled: true,
    content: [
      './target/scala-2.13/website-opt/*.js',
      './src/main/static/html/*.ejs',
    ]
  },
});

module.exports = config;

