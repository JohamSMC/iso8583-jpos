package com.jsmc.util;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import java.util.List;
import java.util.Objects;

public class Util {

    public static String isoToString(ISOMsg isoMsg) {
        String response = "";
        String tag = "ISO INPUT";
        try {
            if (!Objects.isNull(isoMsg)) {
                if (isoMsg.getMTI().equals("0230")) {
                    tag = "ISO OUTPUT";
                }
                StringBuilder responseData = new StringBuilder("->\n[ISO\n");
                for (int i = 0; i <= isoMsg.getMaxField(); i++) {
                    if (isoMsg.hasField(i)) {
                        responseData.append("\t");
                        responseData.append(tag);
                        responseData.append(" <field id='");
                        responseData.append(i);
                        responseData.append("' value='");
                        responseData.append(isoMsg.getString(i).trim());
                        responseData.append("'/>\n");
                    }
                }
                responseData.append("]");
                response = responseData.toString();

            } else {
                System.err.println("ISO es null");
            }

        } catch (ISOException e) {
            System.err.println("Error en ISOToString: " + e);
        }
        return response;
    }

    public static String isoToStringObfuscate(ISOMsg isoMsg, String obfuscate) {
        String response = null;
        try {
            response = isoToString(isoMsg);
            if (response.contains(obfuscate)) {
                response = response.replaceFirst(obfuscate, "******");
            }

        } catch (Exception e) {
            System.err.println("Error en ISOToStringObfuscate: " + e);
        }
        return response;
    }

    public static String isoToStringObfuscateList(ISOMsg iSOMsg, List<String> obfuscates) {
        String response = null;
        try {
            response = isoToString(iSOMsg);
            for (String Obfuscate : obfuscates) {
                response = response.replaceFirst(Obfuscate, "******");
            }
        } catch (Exception e) {
            System.err.println("Error en ISOToStringObfuscateList: " + e);
        }
        return response;
    }
}
