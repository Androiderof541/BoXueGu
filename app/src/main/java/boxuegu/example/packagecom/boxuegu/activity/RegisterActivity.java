package boxuegu.example.packagecom.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.utils.MD5utils;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_back;
    private TextView tv_mian_title;
    private RelativeLayout rl_title_bar;
    private Button btn_register;
    private EditText et_user_name;
    private EditText et_psw;
    private TextView et_psw_agian;
    private String userName;
    private String psw;
    private String psw_agian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {

        tv_mian_title = (TextView)findViewById(R.id.tv_main_title);
        tv_back = ((TextView)findViewById(R.id.tv_back));
        tv_mian_title.setText("注册 ");
        rl_title_bar = (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);

        btn_register = (Button) findViewById(R.id.btn_register);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_pws);
        et_psw_agian = (EditText) findViewById(R.id.et_pws_agian);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;

                }else if(TextUtils.isEmpty(psw_agian)){
                    Toast.makeText(RegisterActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(psw_agian)){
                    Toast.makeText(RegisterActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    return;
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this,"该用户已存在",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    saveRegisterInfo(userName,psw);
                    Intent data=new Intent();
                    data.putExtra("userName",userName);
                    setResult(RESULT_OK,data);
                    RegisterActivity.this.finish();

                }
            }
        });

    }

    private void saveRegisterInfo(String userName, String psw) {
        String md5Psw= MD5utils.md5(psw);
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();






    }

    private boolean isExistUserName(String userName) {
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            has_userName=true;
        }
        return has_userName;
    }

    private void getEditString() {
        userName =et_user_name.getText().toString().trim();
        psw =et_psw.getText().toString().trim();
        psw_agian = et_psw_agian.getText().toString().trim();

    }
}
