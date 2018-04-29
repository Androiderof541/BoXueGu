package boxuegu.example.packagecom.boxuegu.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import boxuegu.example.packagecom.boxuegu.bean.UserBean;
import boxuegu.example.packagecom.boxuegu.sqlite.SQLiteHelper;

/**
 * Created by pc on 2018/3/29.
 */

public class DBUtils {
    private static DBUtils instance=null;
    private static SQLiteOpenHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context){
        helper=new SQLiteHelper(context);
        db=helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context){
        if (instance==null){
            instance=new DBUtils(context);
        }
        return instance;
    }

    public void saveUserInfo(UserBean bean){
        ContentValues cv=new ContentValues();
        cv.put("userName",bean.userName);
        cv.put("qq",bean.qq);
        cv.put("nickName",bean.nickName);
        cv.put("sex",bean.nickName);
        cv.put("signature",bean.signature);
        db.insert(SQLiteHelper.U_USERINFO,null,cv);
    }

    public UserBean getUserInfo(String userName){
        String sql="SELECT * FROM "+SQLiteHelper.U_USERINFO+" WHERE userName=?";
        Cursor cursor=db.rawQuery(sql,new String[]{userName});
        UserBean bean=null;
        while (cursor.moveToNext()){
            bean=new UserBean();
            bean.userName=cursor.getString(cursor.getColumnIndex("userName"));
            bean.nickName=cursor.getString(cursor.getColumnIndex("nickName"));
            bean.signature=cursor.getString(cursor.getColumnIndex("signature"));
            bean.sex=cursor.getString(cursor.getColumnIndex("sex"));
            bean.qq=cursor.getString(cursor.getColumnIndex("qq"));

        }
        cursor.close();
        return bean;
    }
    public void updataUserInfo(String key,String value,String userName){
        ContentValues cv=new ContentValues();
        cv.put(key,value);
        db.update(SQLiteHelper.U_USERINFO,cv,"userName=?",new String[]{userName});
    }

}
