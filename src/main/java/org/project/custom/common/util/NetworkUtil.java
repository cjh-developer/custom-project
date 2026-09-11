package org.project.custom.common.util;

import org.project.custom.common.exception.CustomException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkUtil {

    private NetworkUtil() {}

    public static String getLocalIp() {
        InetAddress inetAddress = getInetAddress();

        return inetAddress.getHostAddress();
    }

    public static InetAddress getInetAddress(){
        try{
            List<InetAddress> iaList = getInetAddressList(false);
            if( iaList != null && !iaList.isEmpty() ){
                return iaList.get(0);
            }
            //window
            return InetAddress.getLocalHost();

        }catch (UnknownHostException e){
            throw new CustomException(e);
        }
    }

    /**
     * ip 목록을 가져온다.
     * @return
     */
    public static List<String> getLocalIpList() {
        return getLocalIpList(false);
    }

    /**
     * 모든 ip 목록을 가져온다.
     * @param isAll
     * @return
     */
    public static List<String> getLocalIpList(boolean isAll) {


        List<String> ipList = new ArrayList<String>();

        List<InetAddress> iaList = getInetAddressList(isAll);

        if( iaList == null || iaList.isEmpty()) {
            return ipList;
        }

        for(InetAddress inetAddress : iaList) {
            ipList.add(inetAddress.getHostAddress());
        }

        return ipList;

    }

    /**
     * 모든 InetAddress 목록을 가져온다.
     * @param isAll
     * @return
     */
    public static List<InetAddress> getInetAddressList(boolean isAll) {

        try {
            List<InetAddress> iaList = new ArrayList<InetAddress>();

            Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();
            while (nienum.hasMoreElements()) {
                NetworkInterface ni = nienum.nextElement();
                Enumeration<InetAddress> enumAddress= ni.getInetAddresses();

                while (enumAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumAddress.nextElement();
                    if( !isAll) {
                        if (inetAddress.isLoopbackAddress() ||
                                inetAddress.isLinkLocalAddress() ) {
                            continue;
                        }
                    }

                    iaList.add(inetAddress);
                }
            }
            return iaList;

        } catch (SocketException e) {
            throw new CustomException(e);
        }
    }

    /**
     * mac address
     * @return
     * @throws SocketException
     */
    public static String getMacAddress(){
        return getMacAddress(getInetAddress());
    }

    /**
     * ip와 연결된 mac address
     * @param ip
     * @return
     */
    public static String getMacAddress(String ip) {
        try {
            return getMacAddress(InetAddress.getByName(ip));
        } catch (UnknownHostException e) {
            throw new CustomException(e);
        }
    }

    /**
     * mac address
     * @param intAddress
     * @return
     */
    public static String getMacAddress(InetAddress intAddress){

        if( intAddress == null) {
            return null;
        }

        try {
            NetworkInterface mac = NetworkInterface.getByInetAddress(intAddress);

            byte[] mc = mac.getHardwareAddress();
            if( mc == null) {
                return null;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mc.length; i++) {
                sb.append(String.format("%02x", mc[i]));
            }

            return sb.toString().toUpperCase();

        } catch (SocketException e) {
            throw new CustomException(e);
        }
    }
}
