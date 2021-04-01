module.exports = (api) => {
  return {
    plugins: [
      require('postcss-import')({}),
      api.mode === 'production' ?
        require('@tailwindcss/jit')('./tailwind.prod.config.js') :
        require('@tailwindcss/jit')('./tailwind.dev.config.js'),
      require('autoprefixer'),
      api.mode === 'production' ?
        require('cssnano')() :
        false
    ]
  };
}
