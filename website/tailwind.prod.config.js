const _ = require('lodash');
const base = require('./tailwind.base.config');

const config = _.mergeWith({}, base, {
  purge: {
    enabled: true,
    content: [
      './modules/frontend/.js/target/scala-2.13/*-fastopt.js',
      './modules/frontend/src/static/**/*.html',
    ]
  },
});

module.exports = config;

