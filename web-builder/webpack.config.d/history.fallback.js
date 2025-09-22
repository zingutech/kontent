const history = require('connect-history-api-fallback');

if (config.devServer) {
  config.devServer.historyApiFallback = {
    rewrites: [
      { from: /./, to: '/index.html' }
    ]
  };
}