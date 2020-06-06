package com.controlpay.helpers;

import org.apache.commons.codec.binary.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    public static String base64Decode(String token){
        Base64 base64 = new Base64();
        return new String(base64.decode(token.getBytes()));
    }

    public static String getPermissionsFromToken(String token){
        Pattern p = Pattern.compile("\\[(.*)]");
        Matcher m = p.matcher(token);
        String s ="";
        while (m.find()) {
            s= token.substring(m.start(), m.end());
        }
        return s.substring(1,s.length()-1);


    }

}
