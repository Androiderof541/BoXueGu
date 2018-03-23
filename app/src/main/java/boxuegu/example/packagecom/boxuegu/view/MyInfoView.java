package boxuegu.example.packagecom.boxuegu.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.activity.LoginActivity;
import boxuegu.example.packagecom.boxuegu.activity.SettingActivity;
import boxuegu.example.packagecom.boxuegu.utils.AnalysisUtils;

/**
 * Created by pc on 2018/3/22.
 */

public class MyInfoView {

    private Context mContext;
    private  final LayoutInflater mInflater;
    private View mCurrentView;
    private LinearLayout ll_head;
    private ImageView iv_head_icon;
    private RelativeLayout rl_courses_history;
    private RelativeLayout rl_setting;
    private TextView tv_user_name;

    public MyInfoView(Context context) {
        mContext = context;
        mInflater=LayoutInflater.from(mContext);
    }



    private boolean readLoginStatus(){
        SharedPreferences sp=mContext.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }

    public View getView(){
        if (mCurrentView==null){
            createView();
        }
        return mCurrentView;
    }
    public void createView(){
        initView();
    }
    private void initView(){
        mCurrentView =  mInflater.inflate(R.layout.main_view_myinfo,null);
        ll_head = (LinearLayout) mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon = (ImageView) mCurrentView.findViewById(R.id.iv_head_icon);
        rl_courses_history = (RelativeLayout) mCurrentView.findViewById(R.id.rl_courses_history);
        rl_setting = (RelativeLayout) mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) mCurrentView.findViewById(R.id.tv_user_name);

        ll_head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (readLoginStatus()){

                }else{
                    Intent intent =new Intent(mContext, LoginActivity.class);
                    ((Activity) mContext).startActivityForResult(intent,1);
                }
            }
        });

        rl_courses_history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (readLoginStatus()){

                }else{
                    Toast.makeText(mContext,"你还没登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rl_setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (readLoginStatus()){
                    Intent intent=new Intent(mContext, SettingActivity.class);
                    ((Activity)mContext).startActivityForResult(intent,1);

                }else{
                    Toast.makeText(mContext,"你还没登录，请先登录",Toast.LENGTH_SHORT).show();
                }
            }
        });

        setLoginParams(readLoginStatus());
    }

    public  void setLoginParams(boolean isLogin) {
        if (isLogin){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        }else{
            tv_user_name.setText("点击登录");
        }
    }

    public void showView() {
        if (mCurrentView==null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
