const _ = require('lodash');
const base = require('./tailwind.base.config');

const config = _.mergeWith({}, base, {
  purge: {
    enabled: true,
    content: [
      './website/target/scala-2.13/website-fullopt/*.js',
      './website/src/main/static/html/*.ejs',
    ]
  },
});

module.exports = config;

