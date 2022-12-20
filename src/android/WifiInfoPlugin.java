package com.soyalu.cordova.wifiInfo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * This class echoes a string called from JavaScript.
 */
public class WifiInfoPlugin extends CordovaPlugin {
    private static final String GET_WIFI_INFO = "getWifiInfo";
    private WifiManager wifiManager;
    private CallbackContext callback;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.wifiManager = (WifiManager) cordova.getActivity().getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callback = callbackContext;
        if (action.equals(GET_WIFI_INFO)) {
            this.getWifiInfo();
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getWifiInfo() {
        WifiInfo wifiInfo = this.wifiManager.getConnectionInfo();
        JSONObject obj = new JSONObject();
        try {
            JSONObject activity = new JSONObject();
            activity.put("BSSID", wifiInfo.getBSSID());
            activity.put("HiddenSSID", wifiInfo.getHiddenSSID());
            activity.put("SSID", wifiInfo.getSSID());
            activity.put("Mac", getMacAddr());
            activity.put("Ip", wifiInfo.getIpAddress());
            activity.put("NetworkId", wifiInfo.getNetworkId());
            activity.put("RSSI", wifiInfo.getRssi());
            activity.put("Level", WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5));
            activity.put("LinkSpeed", wifiInfo.getLinkSpeed());
            activity.put("LinkSpeedUnits", WifiInfo.LINK_SPEED_UNITS);
            activity.put("channel", getChannelByFrequency(wifiInfo.getFrequency()));
            obj.put("activity", activity);

            JSONArray available = new JSONArray();
            for (ScanResult scanResult : this.wifiManager.getScanResults()) {
                JSONObject ap = new JSONObject();
                ap.put("BSSID", scanResult.BSSID);
                ap.put("SSID", scanResult.SSID);
                ap.put("frequency", scanResult.frequency);
                ap.put("level", scanResult.level);
                ap.put("timestamp", String.valueOf(scanResult.timestamp));
                ap.put("capabilities", scanResult.capabilities);
                ap.put("channelWidth", scanResult.channelWidth);
                available.put(ap);
            }
            obj.put("available", available);

        } catch (JSONException e) {
            e.printStackTrace();
            callback.error("JSON Exception");
        }
        callback.success(obj);
    }

    /**
     * 根据频率获得信道
     * 
     * @param frequency
     * @return
     */
    private int getChannelByFrequency(int frequency) {
        int channel = -1;
        switch (frequency) {
        case 2412:
            channel = 1;
            break;
        case 2417:
            channel = 2;
            break;
        case 2422:
            channel = 3;
            break;
        case 2427:
            channel = 4;
            break;
        case 2432:
            channel = 5;
            break;
        case 2437:
            channel = 6;
            break;
        case 2442:
            channel = 7;
            break;
        case 2447:
            channel = 8;
            break;
        case 2452:
            channel = 9;
            break;
        case 2457:
            channel = 10;
            break;
        case 2462:
            channel = 11;
            break;
        case 2467:
            channel = 12;
            break;
        case 2472:
            channel = 13;
            break;
        case 2484:
            channel = 14;
            break;
            case 5035:
                channel = 7;
                break;
            case 5040:
                channel = 8;
                break;
            case 5045:
                channel = 9;
                break;
            case 5055:
                channel = 11;
                break;
            case 5060:
                channel = 12;
                break;
            case 5080:
                channel = 16;
                break;
            case 5160:
                channel = 32;
                break;
            case 5170:
                channel = 34;
                break;
            case 5180:
                channel = 36;
                break;
            case 5190:
                channel = 38;
                break;
            case 5200:
                channel = 40;
                break;
            case 5210:
                channel = 42;
                break;
            case 5220:
                channel = 44;
                break;
            case 5230:
                channel = 46;
                break;
            case 5240:
                channel = 48;
                break;
            case 5250:
                channel = 50;
                break;
            case 5260:
                channel = 52;
                break;
            case 5270:
                channel = 54;
                break;
            case 5280:
                channel = 56;
                break;
            case 5290:
                channel = 58;
                break;
            case 5300:
                channel = 60;
                break;
            case 5310:
                channel = 62;
                break;
            case 5320:
                channel = 64;
                break;
            case 5340:
                channel = 68;
                break;
            case 5480:
                channel = 96;
                break;
            case 5500:
                channel = 100;
                break;
            case 5510:
                channel = 102;
                break;
            case 5520:
                channel = 104;
                break;
            case 5530:
                channel = 106;
                break;
            case 5540:
                channel = 118;
                break;
            case 5550:
                channel = 110;
                break;
            case 5560:
                channel = 112;
                break;
            case 5570:
                channel = 114;
                break;
            case 5580:
                channel = 116;
                break;
            case 5590:
                channel = 118;
                break;
            case 5600:
                channel = 120;
                break;
            case 5610:
                channel = 122;
                break;
            case 5620:
                channel = 124;
                break;
            case 5630:
                channel = 126;
                break;
            case 5640:
                channel = 128;
                break;
            case 5660:
                channel = 132;
                break;
            case 5670:
                channel = 134;
                break;
            case 5680:
                channel = 136;
                break;
            case 5690:
                channel = 138;
                break;
            case 5700:
                channel = 140;
                break;
            case 5710:
                channel = 142;
                break;
            case 5720:
                channel = 144;
                break;
        case 5745:
            channel = 149;
            break;
            case 5755:
                channel = 151;
                break;
        case 5765:
            channel = 153;
            break;
            case 5775:
                channel = 155;
                break;
        case 5785:
            channel = 157;
            break;
            case 5795:
                channel = 159;
                break;
        case 5805:
            channel = 161;
            break;
            case 5815:
                channel = 163;
                break;
        case 5825:
            channel = 165;
            break;
            case 5835:
                channel = 167;
                break;
            case 5845:
                channel = 169;
                break;
            case 5855:
                channel = 171;
                break;
            case 5865:
                channel = 173;
                break;
            case 5875:
                channel = 175;
                break;
            case 5885:
                channel = 177;
                break;
            case 5910:
                channel = 182;
                break;
            case 5915:
                channel = 183;
                break;
            case 5920:
                channel = 184;
                break;
            case 5935:
                channel = 187;
                break;
            case 5940:
                channel = 188;
                break;
            case 5945:
                channel = 189;
                break;
            case 5960:
                channel = 192;
                break;
            case 5980:
                channel = 196;
                break;
        }
        return channel;
    }

    private String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }
}
