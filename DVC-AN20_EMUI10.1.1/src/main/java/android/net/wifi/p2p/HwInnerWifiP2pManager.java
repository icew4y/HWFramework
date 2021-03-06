package android.net.wifi.p2p;

import android.net.wifi.WifiConfiguration;

public interface HwInnerWifiP2pManager {
    boolean configIPAddr(String str, String str2, String str3);

    boolean disableP2pGcDhcp(String str, int i);

    String getGroupConfigInfo();

    WifiConfiguration getWifiRepeaterConfiguration();

    boolean releaseIPAddr(String str);

    boolean setWifiRepeaterConfiguration(WifiConfiguration wifiConfiguration);
}
