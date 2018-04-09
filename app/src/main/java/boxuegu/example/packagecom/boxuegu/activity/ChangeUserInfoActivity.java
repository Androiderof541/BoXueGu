package boxuegu.example.packagecom.boxuegu.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.example.packagecom.boxuegu.R;

public class ChangeUserInfoActivity extends AppCompatActivity {

    private String title;
    private String content;
    private int flag;
    private ImageView ivDelete;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private TextView tv_back;
    private TextView tv_save;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
    }

    private void init() {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        flag = getIntent().getIntExtra("flag",0);
        ivDelete = ivDelete;
        ivDelete = (ImageView) findViewById(R.id.iv_delete);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText(title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_save.setVisibility(View.VISIBLE);
        et_content = (EditText) findViewById(R.id.et_content);
        if (!TextUtils.isEmpty(content)){
            et_content.setText(content);
            et_content.setSelection(content.length());
        }
        contentListener();
        tv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ChangeUserInfoActivity.this.finish();
            }
        });
        ivDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_content.setText("");
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent data=new Intent();
                String etContent=et_content.getText().toString().trim();
                switch (flag){
                    case 1:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("nickName",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else{
                            Toast.makeText(ChangeUserInfoActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(etContent)){
                            data.putExtra("signature",etContent);
                            setResult(RESULT_OK,data);
                            Toast.makeText(ChangeUserInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            ChangeUserInfoActivity.this.finish();
                        }else{
                            Toast.makeText(ChangeUserInfoActivity.this, "签名不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        });
    }

    private void contentListener() {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable=et_content.getText();
                int len =editable.length();
                if (len>0){
                    ivDelete.setVisibility(View.VISIBLE);
                }else{
                    ivDelete.setVisibility(View.GONE);
                }
                switch (flag){
                    case 1:
                        if (len>8){
                            int selEndIndex= Selection.getSelectionEnd(editable);
                            String str=editable.toString();
                            String newStr=str.substring(0,8);
                            et_content.setText(newStr);
                            editable=et_content.getText();
                            int newLen=editable.length();
                            if (selEndIndex>newLen){
                                selEndIndex=editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                    case 2:
                        if (len>16){
                            int selEndIndex=Selection.getSelectionEnd(editable);
                            String str=editable.toString();
                            String newStr=str.substring(0,16);
                            et_content.setText(newStr);
                            editable=et_content.getText();
                            int newLen=editable.length();
                            if (selEndIndex>newLen){
                                selEndIndex=editable.length();
                            }
                            Selection.setSelection(editable,selEndIndex);
                        }
                        break;
                        default:
                            break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private EditText getEtContent(){
        return (EditText) findViewById(R.id.et_content);
    }
}