const fs = require('fs')
const build = fs.readFileSync('../build.sbt', 'utf8')
const isScala3 = /^\s*scalaVersion\s*:=\s*ScalaVersions.v3,\s*$/m

const scala2 = '2.13'
const scala3 = '3.0.1'

// const scalaVersion = isScala3.test(build) ? scala3 : scala2;
const scalaVersion = scala3

console.log('detected scala version', scalaVersion)
module.exports = scalaVersion
