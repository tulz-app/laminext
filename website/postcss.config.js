module.exports = (api) => {
  return {
    plugins: [
      require('postcss-import')({}),
      api.mode === 'production' ?
        require('tailwindcss')('./tailwind.prod.config.js') :
        require('tailwindcss')('./tailwind.dev.config.js'),
      require('postcss-nested')({}),
      require('autoprefixer')({}),
      api.mode === 'production' ?
        require('cssnano')() :
        false
    ]
  };
}
