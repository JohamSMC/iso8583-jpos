package com.jsmc.server;

import org.jpos.iso.ISOServer;
import org.jpos.iso.ServerChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.ISO87BPackager;

public class ServerMockISO8583 {

    public static void main(String[] args) {
        String host = "localhost";
        Integer port = 5_000;
        Integer timeOut = 40_000;

        ServerChannel channel = new ASCIIChannel(host, port, new ISO87APackager());
        ISOServer server = new ISOServer(port, channel, null);
        server.addISORequestListener(new IsoListener());

        System.out.println("ISO8583 server started...");
        new Thread(server).start();


    }
}
