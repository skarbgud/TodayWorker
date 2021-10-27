// Webpack Dev Server 설정
module.exports = {
  host: '192.168.121.163',
  port: 3334,
  proxy: {
    '/': {
      target: 'http://192.168.121.163:8080',
      changeOrigin: true,
    },
  },
};
