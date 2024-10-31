package com.jd.cho.rule.engine.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Description: Util Demo
 *
 * @author DongBoot
 */
public class NetUtils {

    /** 
     * IP_PATTERN
     */
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static String ipCache = getLocalIp();

    /**
     * 获取本机有效的外部IP地址，而非内部的回环IP
     * 此处不加锁,多次获取不影响
     */
    public static String getLocalIp() {
        if (ipCache != null) {
            return ipCache;
        }

        InetAddress address = getLocalAddress();
        if (address == null) {
            return null;
        }

        ipCache = address.getHostAddress();
        if (ipCache == null || "".equals(ipCache.trim()) || "0.0.0.0".equals(ipCache) || "127.0.0.1".equals(ipCache)) {
            ipCache = address.getHostName();
        }

        return ipCache;
    }

    /**
     * @return
     */
    private static InetAddress getLocalAddress() {

        try {
            InetAddress localAddress = null;

            //如果能直接取到正确IP就返回，通常windows下可以
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }

            //通过轮询网卡接口来获取IP
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface network = interfaces.nextElement();
                    Enumeration<InetAddress> addresses = network.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (isValidAddress(address)) {
                            return address;
                        }
                    }
                }
            }
            return localAddress;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否为有效合法的外部IP，而非内部回环IP
     *
     * @param address
     */
    private static boolean isValidAddress(InetAddress address) {
        if ((address == null) || (address.isLoopbackAddress())) {
            return false;
        }

        String ip = address.getHostAddress();

        return (ip != null) && (!"0.0.0.0".equals(ip)) && (!"127.0.0.1".equals(ip)) && (IP_PATTERN.matcher(ip).matches());
    }
}

