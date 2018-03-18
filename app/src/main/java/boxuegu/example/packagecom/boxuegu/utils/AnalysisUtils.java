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
}
