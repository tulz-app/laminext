const _ = require('lodash');
const base = require('./tailwind.base.config');

const config = _.mergeWith({}, base);

module.exports = config;


