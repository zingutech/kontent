// must be in the jsMain/resource folder
const mainCssFile = 'styles.css';

// tailwind config (https://tailwindcss.com/docs/configuration)
const tailwind = {
  darkMode: 'media',
  plugins: [
    require('@tailwindcss/forms')
  ],
  variants: {},
  theme: {
    extend: {
//      colors: {
//        transparent: 'transparent',
//        current: 'currentColor',
//        'white': '#ffffff',
//        'purple': '#3f3cbb',
//        'midnight': '#121063',
//        'metal': '#565584',
//        'tahiti': '#3ab7bf',
//        'silver': '#ecebff',
//        'bubble-gum': '#ff77e9',
//        'bermuda': '#78dcca',
//      },
    },
  },
  content: {
    files: [
      '*.{js,html,css}',
      './kotlin/**/*.{js,html,css}'
    ],
    transform: {
      js: (content) => {
        return content.replaceAll(/(\\r)|(\\n)|(\\r\\n)/g,' ')
      }
    }
  },
};

// webpack tailwind css settings
((config) => {
  let entry = '/kotlin/' + mainCssFile;
  if (config.entry && config.entry.main) {
    config.entry.main.push(entry);
  }
  config.module.rules.push({
    test: /\.css$/,
    use: [
      {loader: 'style-loader'},
      {loader: 'css-loader'},
      {
        loader: 'postcss-loader',
        options: {
          postcssOptions: {
            plugins: [
              require("tailwindcss")({config: tailwind}),
              require("autoprefixer"),
              require("cssnano")
            ]
          }
        }
      }
    ]
  });
})(config);