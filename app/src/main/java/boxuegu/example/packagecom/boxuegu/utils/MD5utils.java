package boxuegu.example.packagecom.boxuegu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pc on 2018/3/14.
 */

public class MD5utils {
    public static String md5(String text){
        try {
            MessageDigest digest= MessageDigest.getInstance("md5");
            byte[] result=digest.digest(text.getBytes());
            StringBuilder builder=new StringBuilder();
            for (byte b: result){
                int number=b & 0xff;
                String hex=Integer.toHexString(number);
                if (hex.length()==1){
                    builder.append("0"+hex);
                }else{
                    builder.append(hex);
                }
            }
            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
