package sn.seydina.sendemaildemo.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token) {

        return "Hello " + name + ",\n\n"
                + "Welcome to our platform. Please click on the below link to activate your account:\n\n"
                + getVerificationUrl(host, token) + "\n\n"
                + "Thank you,\n"
                + "The Team";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/v1/users/verify/"+ token;
    }

}
