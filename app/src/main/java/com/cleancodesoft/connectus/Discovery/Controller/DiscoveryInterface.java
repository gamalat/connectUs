package com.cleancodesoft.connectus.Discovery.Controller;

import java.net.InetAddress;
import java.util.List;

public interface DiscoveryInterface {
    void onDiscover(List<InetAddress> users_IPs);
}
