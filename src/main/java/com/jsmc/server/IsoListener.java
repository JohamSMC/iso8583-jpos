package com.jsmc.server;

import com.jsmc.util.Util;
import org.jpos.iso.*;

import java.io.IOException;

public class IsoListener implements ISORequestListener {

    @Override
    public boolean process(ISOSource isoSrc, ISOMsg isoMsg) {
        try {
            System.out.println(String.format("ISO8583 incoming message on host [%s:%s]",
                    ((BaseChannel) isoSrc).getSocket().getInetAddress().getHostAddress(),
                    ((BaseChannel) isoSrc).getSocket().getLocalPort()));
            receiveMessage(isoSrc, isoMsg);
        } catch (Exception e) {
            System.out.println(String.format("Error reciviendo ISO, Error %s", e));
            System.err.println(String.format("Error reciviendo ISO, Error %s", e));
            e.printStackTrace(System.err);
        }
        return Boolean.TRUE;
    }

    private void receiveMessage(ISOSource isoSrc, ISOMsg isoMsg) throws ISOException, IOException {
        System.out.println("ISO8583 Message received...");
        ISOMsg isoResponse = (ISOMsg) isoMsg.clone();

        System.out.println(Util.isoToString(isoMsg));

        isoResponse.setMTI("0230");
        isoResponse.set(39, "00");

        System.out.println(Util.isoToString(isoResponse));


        isoSrc.send(isoResponse);
    }
}

