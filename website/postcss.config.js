module.exports = (api) => {
  const tailwindcss = require('./tailwind.config')(api)
  const plugins = {
    'postcss-import': {},
    tailwindcss,
    autoprefixer: {}
  }
  if (api.mode === 'production') {
    plugins.cssnano = {}
  }

  return {
    plugins
  }
}
