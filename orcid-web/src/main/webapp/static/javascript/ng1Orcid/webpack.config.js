var webpack = require('webpack');
const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
    context: __dirname + "/",
    entry: "./require.js",
    mode: 'production',
    optimization: {
        minimizer: [new TerserPlugin({
            terserOptions: {
                output: {
                    comments: false,
                },
            },
        })],
    },
    module: {
        rules: [{
            test: /\.ts$/, 
            use: [{
                loader: 'ts-loader'
            }]
        }]
    },
    output: {
        path: __dirname + '/',
        filename: "angular_orcid_generated.js"
    },
    plugins: [
    new webpack.DefinePlugin({
        'NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'production'),
        'process.env':{
            'NODE_ENV': JSON.stringify(process.env.NODE_ENV || 'production')
        }
    }),
    ],
    resolve: {
        alias: {
            "@angular/upgrade/static": "@angular/upgrade/bundles/upgrade-static.umd.js"
        },
        extensions: [ ".js", ".ts" ]
    },
    watch: false
}
