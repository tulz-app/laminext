const fs = require('fs')
const build = fs.readFileSync('../build.sbt', 'utf8')
const scala3 = /^\s*scalaVersion\s*:=\s*ScalaVersions.v3,\s*$/m
const scalaVersion = scala3.test(build) ? '3.0.0' : '2.13';

console.log('detected scala version', scalaVersion)
module.exports = scalaVersion
