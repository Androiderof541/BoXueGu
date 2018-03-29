package boxuegu.example.packagecom.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.utils.AnalysisUtils;
import boxuegu.example.packagecom.boxuegu.utils.MD5utils;

public class ModifyPswActivity extends AppCompatActivity{

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_new_psw_again;
    private Button btn_save;
    private String userName;
    private String newPsw;
    private String psw;
    private String again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        initView();
        userName= AnalysisUtils.readLoginUserName(this);
    }


    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again = (EditText) findViewById(R.id.et_pws_agian);
        btn_save = (Button) findViewById(R.id.btn_save);


        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPswActivity.this.finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(ModifyPswActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!MD5utils.md5(psw).equals(readPsw())) {
                    Toast.makeText(ModifyPswActivity.this, "输入的密码与原始密码不一致", Toast.LENGTH_SHORT).show();

                    return;
                }else if (MD5utils.md5(newPsw).equals(readPsw())) {
                    Toast.makeText(ModifyPswActivity.this, "输入的新密码与原始密码不能一致", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(ModifyPswActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(again)) {
                    Toast.makeText(ModifyPswActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!newPsw.equals(again)) {
                    Toast.makeText(ModifyPswActivity.this, "两次输入的新密码密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(ModifyPswActivity.this, "新密码设置成功", Toast.LENGTH_SHORT).show();
                    modifyPsw(newPsw);
                    Intent intent=new Intent(ModifyPswActivity.this,LoginActivity.class);
                    startActivity(intent);
                    //SettingActivity.instance.finish();
                    ModifyPswActivity.this.finish();
                }
            }
        });

    }

    private void getEditString() {
        psw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        again = et_new_psw_again.getText().toString().trim();
    }

    private void modifyPsw(String newPsw) {
        String md5psw= MD5utils.md5(newPsw);
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(userName,md5psw);
        editor.commit();
    }

    private String readPsw() {
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sharedPreferences.getString(userName,"");
        return spPsw;
    }
}
