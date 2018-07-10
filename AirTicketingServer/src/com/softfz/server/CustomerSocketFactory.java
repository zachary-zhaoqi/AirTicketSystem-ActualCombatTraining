package com.softfz.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

import com.softfz.config.ServerConfig;

/**
 * 指定服务通讯端口，防止被防火墙拦截
 */
public class CustomerSocketFactory extends RMISocketFactory {
    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if (port == 0) {
            port = Integer.parseInt(ServerConfig.SERVER_CLIENT_PORT); //不指定就随机分配了    
        }
        System.out.println("rmi notify port:" + port);
        return new ServerSocket(port);
    }
}