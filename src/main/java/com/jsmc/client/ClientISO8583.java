package com.jsmc.client;

import com.jsmc.util.Util;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.ISO87APackagerBBitmap;

public class ClientISO8583 {

    public static void main(String[] args) {
        String host = "localhost";
        Integer port = 5_000;
        Integer timeOut = 40_000;

        try {

            ISOMsg isoRequest = new ISOMsg();
            isoRequest.setPackager(new ISO87APackagerBBitmap());

            isoRequest.setMTI("0210");
            isoRequest.set(2, "500");

            ASCIIChannel channel = new ASCIIChannel(host, port, new ISO87APackager());
            channel.setTimeout(timeOut);
            channel.connect();

            System.out.println(Util.isoToString(isoRequest));
            channel.send(isoRequest);

            ISOMsg isoResponse = channel.receive();
            System.out.println(Util.isoToString(isoResponse));

            if (!channel.isConnected()) {
                System.out.println("Cerrando Channel...");
                channel.disconnect();
                System.out.println("Channel CERRADO");
            }


        } catch (Exception e) {
            System.out.println(String.format("Error ISO, Error %s", e));
            System.err.println(String.format("Error ISO, Error %s", e));
            e.printStackTrace(System.err);
        }


    }
}
