/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stickssoap;

import javax.xml.ws.Endpoint;

/**
 *
 * @author vovab
 */
public class RunServer {
    public static final int port = 8080;

    public static void main(String[] args) {
        Server service = new Server();
        String url = String.format("http://localhost:%d/Server", port);
        Endpoint.publish(url, service);
    }
}
