package com.example.mobdev_nhom7.utils;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class AutoIpConfig {

    private TextView deviceListTextView;
    Context context;
    public AutoIpConfig(Context context) {
        this.context = context;
    }

    public class NetworkScannerTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            return getDevicesOnNetwork();
        }

        @Override
        protected void onPostExecute(ArrayList<String> deviceList) {
            updateDeviceList(deviceList);
        }
    }

    private ArrayList<String> getDevicesOnNetwork() {
        ArrayList<String> deviceList = new ArrayList<>();

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();

            int ipAddress = wifiInfo.getIpAddress();
            int netmask = dhcpInfo.netmask;

            int networkAddress = ipAddress & netmask;
            Log.d("networkAddress", String.valueOf(networkAddress));
            for (int i = 190; i <= 200; i++) {
                int potentialDeviceIP = networkAddress + i;
                Log.d("potentialDeviceIP", String.valueOf(potentialDeviceIP));

                try {
                    InetAddress address = InetAddress.getByName(Formatter.formatIpAddress(potentialDeviceIP));
                    Log.d("address", String.valueOf(address.getHostAddress()));

//                    if (address.isReachable(1000)) {
//                        String deviceName = getHostName(address.getHostAddress());
//                        Log.d("deviceName", String.valueOf(deviceName));
//
//                        deviceList.add("IP: " + address.getHostAddress() + ", Name: " + deviceName);
//                    }
                } catch (Exception e) {
                    Log.e("NetworkActivity", "Error: " + e.getMessage());
                }
            }
        }

        return deviceList;
    }

    private String getHostName(String ipAddress) {
        String hostName = ipAddress;

        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            hostName = address.getCanonicalHostName();
        } catch (UnknownHostException e) {
            Log.e("NetworkActivity", "Error getting host name: " + e.getMessage());
        }

        return hostName;
    }

    private void updateDeviceList(ArrayList<String> deviceList) {
//        StringBuilder builder = new StringBuilder();
//        for (String device : deviceList) {
//            builder.append(device).append("\n");
//        }
//        deviceListTextView.setText(builder.toString());
    }
}
