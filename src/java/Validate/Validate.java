package Validate;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    private static String regexname = "^[\\w\\d\\@\\{3,15}\\.\\p{L}]+$";
    private static String regexInt = "^\\d+$";
    private static String regexDouble = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";
    private static String regexEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private static String REGEXMAIL = "\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
    private static String REGEXADDRESS = "[$&+,:;=?@#|'<>.-^*()%!]";

    public static boolean checkName(String name) {
        return Pattern.matches(regexname, name.replaceAll(" ", ""));
    }
    public static boolean checkMailsend(String email) {
        return Pattern.matches(regexEmail, email.replaceAll(" ", ""));
    }
    public static boolean checkMail(String email) {
        return Pattern.matches(REGEXMAIL, email.replaceAll(" ", ""));
    }

    public static boolean checkAddress(String address) {
        return Pattern.matches(REGEXMAIL, address.replaceAll(" ", ""));
    }

    public static boolean checkInt(String number) {
        number = number.trim();
        // can't null string
        if (number.isEmpty()) {
            return false;
        }
        // number must contain only digit
        if (!number.trim().matches(regexInt)) {
            return false;
        }
        try {
            // try catch parseInt
            int x = Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkDouble(String number) {
        number = number.trim();
        // number must contain - and digit and , 
        if (!number.trim().matches(regexDouble)) {
            return false;
        }
        try {
            // check for parse Double 
            Double x = Double.parseDouble(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(checkMailsend("hoang@hoang.com"));
    }
}
