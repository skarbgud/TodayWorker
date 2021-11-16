module.exports = {
  devServer: {
    host: 'localhost',  // 로컬 구동
    port: 3333, // 포트번호
    overlay: false,
    contentBase: '.',
    proxy: {
      '^/todayworker/api': {
        target: 'http://localhost:8080/', // 요청할 서버 주소
        pathRewrite: { '^/todayworker/api': '' },
        changeOrigin: true,
        logLevel: 'debug', // 터미널에 proxy 로그 남기기
      },
    },
  },
  chainWebpack: config => {
    config.module
      .rule('vue')
      .use('vue-loader')
      .loader('vue-loader')
      .tap(options => {
        options.transformAssetUrls = {
          img: 'src',
          image: 'xlink:href',
          'b-avatar': 'src',
          'b-img': 'src',
          'b-img-lazy': ['src', 'blank-src'],
          'b-card': 'img-src',
          'b-card-img': 'src',
          'b-card-img-lazy': ['src', 'blank-src'],
          'b-carousel-slide': 'img-src',
          'b-embed': 'src'
        }

        return options
      })
  }
};