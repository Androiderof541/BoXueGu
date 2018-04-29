package boxuegu.example.packagecom.boxuegu.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.bean.UserBean;
import boxuegu.example.packagecom.boxuegu.utils.AnalysisUtils;
import boxuegu.example.packagecom.boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlHead;
    private ImageView ivHeadIcon;
    private RelativeLayout rlAccount;
    private TextView tvUserName;
    private RelativeLayout rlNickName;
    private TextView tvNickname;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlSignature;
    private TextView tvSignature;
    private String spUserName;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private static final int CHANG_NICKNAME=1;
    private static final int CHANG_SIGNATURE=2;
    private static final int CHANG_QQ=3;
    private TextView tv_qq;
    private RelativeLayout rl_qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        spUserName = AnalysisUtils.readLoginUserName(this);

        rlHead = (RelativeLayout) findViewById(R.id.rl_head);
        ivHeadIcon = (ImageView) findViewById(R.id.iv_head_icon);
        rlAccount = (RelativeLayout) findViewById(R.id.rl_account);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        rlNickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        tvNickname = (TextView) findViewById(R.id.tv_nickname);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        rlSignature = (RelativeLayout) findViewById(R.id.rl_signature);
        tvSignature = (TextView) findViewById(R.id.tv_signature);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);

        rl_qq = (RelativeLayout) findViewById(R.id.rl_qq);
        tv_qq = (TextView) findViewById(R.id.tv_qq);

        tv_main_title.setText("个人资料 ");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));



        initData();
        setListener();
    }

    private void setListener() {
        tv_back.setOnClickListener(this);
        rlNickName.setOnClickListener(this);
        rlSex.setOnClickListener(this);
        rlSignature.setOnClickListener(this);
        rl_qq.setOnClickListener(this);
    }

    private void initData() {
        UserBean bean=null;
        bean= DBUtils.getInstance(this).getUserInfo(spUserName);
        if (bean==null){
            bean=new UserBean();
            bean.userName=spUserName;
            bean.nickName="问答精灵";
            bean.sex="男";
            bean.signature="问答精灵";
            bean.qq="未添加";
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }

    private void setValue(UserBean bean) {
        tvNickname.setText(bean.nickName);
        tvUserName.setText(bean.userName);
        tvSex.setText(bean.sex);
        tvSignature.setText(bean.signature);
        tv_qq.setText(bean.qq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                String name=tvNickname.getText().toString();
                Bundle bdName=new Bundle();
                bdName.putString("content",name);
                bdName.putString("title","昵称");
                bdName.putInt("flag",1);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANG_NICKNAME,bdName);
                break;
            case R.id.rl_sex:
                String sex=tvSex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                String signature=tvSignature.getText().toString();
                Bundle bsignature=new Bundle();
                bsignature.putString("content",signature);
                bsignature.putString("title","签名");
                bsignature.putInt("flag",2);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANG_SIGNATURE,bsignature);
                break;
            case R.id.rl_qq:
                String qq=tv_qq.getText().toString();
                Bundle bdqq=new Bundle();
                bdqq.putString("content",qq);
                bdqq.putString("title","QQ");
                bdqq.putInt("flag",3);
                enterActivityForResult(ChangeUserInfoActivity.class,CHANG_QQ,bdqq);
                break;
            default:
                break;
        }
    }
    private void sexDialog(String sex){
        int sexFlag=0;
        if ("男".equals(sex)){
            sexFlag=0;
        }else if ("女".equals(sex)){
            sexFlag=1;
        }
        final String items[]={"男","女"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create().show();
    }

    private void setSex(String sex) {
        tvSex.setText(sex);
        DBUtils.getInstance(UserInfoActivity.this).updataUserInfo("sex",sex,spUserName);
    }
    public void enterActivityForResult(Class<?> to,int requestCode,Bundle b){
        Intent i=new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANG_NICKNAME:
            if(data!=null){
                String new_info=data.getStringExtra("nickName");
                if (TextUtils.isEmpty(new_info)){
                    return;
                }
                tvNickname.setText(new_info);
                DBUtils.getInstance(UserInfoActivity.this).updataUserInfo("nickName",new_info,spUserName);
            }
            break;
            case CHANG_SIGNATURE:
                if (data!=null){
                    String new_info=data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tvSignature.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updataUserInfo("signature",new_info,spUserName);
                }
                break;
            case CHANG_QQ:
                if (data!=null){
                    String new_info=data.getStringExtra("qq");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tv_qq.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updataUserInfo("qq",new_info,spUserName);
                }
                break;
        }
    }
}
