var exec = require('cordova/exec');

exports.getWifiInfo = function(success, error) {
    exec(success, error, 'wifiInfo', 'getWifiInfo', []);
};
