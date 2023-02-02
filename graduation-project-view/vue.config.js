const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})
module.exports = { 
    devServer: {
        open: false,
        port: 80, //端口
        proxy: {
            '/api': {
                target: 'http://localhost:10010/', // 服务器server
                ws: false, // proxy websockets 
                changeOrigin: true, // needed for virtual hosted sites
                pathRewrite: {
                    '^/api': '' // rewrite path
                }
            },
        },
    }
}
