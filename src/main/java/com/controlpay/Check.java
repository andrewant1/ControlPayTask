package com.controlpay;

import com.controlpay.helpers.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    public static  void main(String[] args){
        String s ="";
        String encodedtoken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImouZG9lQHRlc3QuY29tIiwibmFtZSI6IkpvaG4gRG9lIiwicGVybWlzc2lvbiI6WyJSRUFEIiwiQ1JFQVRFIiwiREVMRVRFIl19.bRS_8EBE9Cc6EqZ00CprRvJC3Ik1VbqW63yxdZr50ak";
        String decodedString =  Utils.base64Decode(encodedtoken);
        System.out.println(decodedString);
        Pattern p = Pattern.compile("\\[(.*)]");
        Matcher m = p.matcher(decodedString);
        while (m.find()) {
            s= decodedString.substring(m.start(), m.end());
        }
        System.out.println(s.substring(1,s.length()-1));

    }
}
