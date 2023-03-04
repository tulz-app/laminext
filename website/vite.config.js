import {resolve} from 'path'
import {createHtmlPlugin} from 'vite-plugin-html'
import commonjs from '@rollup/plugin-commonjs';
import viteCompression from 'vite-plugin-compression';
import fs from 'fs'

import scalaVersion from './scala-version'

const frontrouteVersion = fs.readFileSync('.laminext-version')

// https://vitejs.dev/config/
export default ({mode}) => {
  const mainJS = `target/scala-${scalaVersion}/website-${mode === 'production' ? 'opt' : 'fastopt'}/main.js`
  const script = `<script type="module" src="/${mainJS}"></script>`

  /** @type {import('vite').UserConfig} */
  return {
    server: {
      port: 6080,
    },
    base: `/v/${frontrouteVersion}/`,
    publicDir: './src/main/public',
    build: {
      outDir: `dist/v/${frontrouteVersion}`,
    },
    optimizeDeps: {
      disabled: mode === 'production',
    },
    plugins: [
      ...(mode === 'production' ? [
        commonjs(),
        viteCompression({
          filter: /\.(js|css|html)$/i,
          algorithm: 'gzip'
        }),
        viteCompression({
          filter: /\.(js|css|html)$/i,
          algorithm: 'brotliCompress'
        }),
      ] : []),
      createHtmlPlugin({
        minify: mode === 'production',
        inject: {
          data: {
            script,
            pl: mode === 'production' ? '<script async defer data-domain="laminext.dev" src="/js/index.js"></script>' : ''
          },
        },
      }),
    ],
    resolve: {
      alias: {
        'stylesheets': resolve(__dirname, './src/main/static/stylesheets'),
        'svg': resolve(__dirname, './src/main/static/svg'),
      }
    }
  }
}
