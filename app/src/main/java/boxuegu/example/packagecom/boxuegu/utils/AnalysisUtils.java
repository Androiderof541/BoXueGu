package boxuegu.example.packagecom.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pc on 2018/3/18.
 */

public class AnalysisUtils {
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName","");
        return userName;
    }
    public static boolean readLoginStatus(Context context){
        SharedPreferences sp= context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean islogin=sp.getBoolean("isLogin",false);
        return islogin;
    }
    public static void clearLoginStatus(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }
}
