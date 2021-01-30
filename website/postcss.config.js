module.exports = (api) => {
  return {
    plugins: [
      require('postcss-import')({}),
      api.mode === 'production' ?
        require('tailwindcss')('./tailwind.prod.config.js') :
        require('tailwindcss')('./tailwind.dev.config.js'),
      require('postcss-preset-env')({
        /* use stage 3 features + css nesting rules */
        stage: 3,
        features: {
          'nesting-rules': true
        }
      }),
      api.mode === 'production' ?
        require('cssnano')() :
        false
    ]
  };
}
