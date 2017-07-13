package io.corbs;

import org.springframework.util.ObjectUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AppUtils {

    public static InetAddress getIp() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("Your current IP address : " + ip);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }

        return ip;
    }

    public static Date parseDate(String input) throws java.text.ParseException {

        // NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        // things a bit. Before we go on we have to repair this.
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

        // this is zero time so we need to add that TZ indicator for
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;
            String s0 = input.substring(0, input.length() - inset);
            String s1 = input.substring(input.length() - inset, input.length());
            input = s0 + "GMT" + s1;
        }

        return df.parse(input);
    }

    public static Date parseDateSilently(String input) {
        try {
            return ObjectUtils.isEmpty(input) ? null : parseDate(input);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateAsString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        df.setTimeZone(tz);
        String output = df.format(date);
        return output.replaceAll("UTC", "+00:00");
    }

    public static String now() {
        return dateAsString(new Date());
    }

}
