module.exports = (api) => {
  return {
    plugins: [
      require('postcss-import')({}),
      api.mode === 'production' ?
        require('tailwindcss')('./tailwind.prod.config.js') :
        require('tailwindcss')('./tailwind.dev.config.js'),
      require('postcss-preset-env')({}),
      api.mode === 'production' ?
        require('cssnano')() :
        false
    ]
  };
}
