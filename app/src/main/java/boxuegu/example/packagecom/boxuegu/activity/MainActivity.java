package boxuegu.example.packagecom.boxuegu.activity;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.frament.FragmentCourseFragment;
import boxuegu.example.packagecom.boxuegu.frament.FragmentExercisesFragment;
import boxuegu.example.packagecom.boxuegu.frament.FragmentMyinfoFragment;
import boxuegu.example.packagecom.boxuegu.frament.MainViewExerciseFragment;
import boxuegu.example.packagecom.boxuegu.utils.AnalysisUtils;
import boxuegu.example.packagecom.boxuegu.view.MyInfoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            if (isLogin) {
                clearBottomImageState();
                selectDisplayView(0);
            }
            if (myInfoView!=null){
                myInfoView.setLoginParams(isLogin);
            }

        }
    }*/



    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private LinearLayout mBottomLayout;
    private RelativeLayout mCoursesBtn;
    private RelativeLayout mExercisesBtn;
    private RelativeLayout mMyinfoBtn;
    private TextView tv_courses;
    private TextView tv_exercises;
    private TextView tv_myinfo;
    private FrameLayout mBodyLayout;
    private ImageView iv_courses;
    private ImageView iv_exercises;
    private ImageView iv_myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initBottomBar();
        setListener();
        setInitStatus();
        setMain();
    }
    private void setMain() {
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body, new FragmentCourseFragment()).commit();
        setSelectedStatus(0);
    }

    private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(0);
        createView(0);
    }

    private void setListener() {
        for(int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    private void initBottomBar() {
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mCoursesBtn = (RelativeLayout) findViewById(R.id.bottom_bar_courses_btn);
        mExercisesBtn = (RelativeLayout) findViewById(R.id.bottom_bar_exercises_btn);
        mMyinfoBtn = (RelativeLayout) findViewById(R.id.bottom_bar_myinfo_btn);

        tv_courses = (TextView) findViewById(R.id.bottom_bar_text_courses);
        tv_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        tv_myinfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);

        iv_courses = (ImageView) findViewById(R.id.bottom_courses_icon);
        iv_exercises = (ImageView) findViewById(R.id.bottom_exercises_icon);
        iv_myinfo = (ImageView) findViewById(R.id.bottom_myinfo_icon);

    }

    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        initBodyLayout();

        /*Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity .class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        };
        timer.schedule(task,3000);*/
    }

    private void initBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_bar_courses_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new FragmentCourseFragment()).commit();
                setSelectedStatus(0);
                /*clearBottomImageState();
                selectDisplayView(0);*/
                break;
            case R.id.bottom_bar_exercises_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new FragmentExercisesFragment()).commit();
                setSelectedStatus(1);
                /*clearBottomImageState();
                selectDisplayView(1);*/
                break;
            case R.id.bottom_bar_myinfo_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new FragmentMyinfoFragment()).commit();
                setSelectedStatus(2);
                /*clearBottomImageState();
                selectDisplayView(2);*/
                break;
        }
    }

    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }

    private void setSelectedStatus(int index) {
        switch (index){
            case 0:
                mCoursesBtn.setSelected(true);
                iv_courses.setImageResource(R.drawable.main_course_icon_selected);
                tv_courses.setTextColor(Color.parseColor("#0097F7"));

                iv_myinfo.setImageResource(R.drawable.main_my_icon);
                tv_myinfo.setTextColor(Color.parseColor("#666666"));

                iv_exercises.setImageResource(R.drawable.main_exercises_icon);
                tv_exercises.setTextColor(Color.parseColor("#666666"));

                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));

                iv_courses.setImageResource(R.drawable.main_course_icon);
                tv_courses.setTextColor(Color.parseColor("#666666"));

                iv_myinfo.setImageResource(R.drawable.main_my_icon);
                tv_myinfo.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                mMyinfoBtn.setSelected(true);
                iv_myinfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myinfo.setTextColor(Color.parseColor("#0097F7"));

                iv_courses.setImageResource(R.drawable.main_course_icon);
                tv_courses.setTextColor(Color.parseColor("#666666"));

                iv_exercises.setImageResource(R.drawable.main_exercises_icon);
                tv_exercises.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.GONE);
                break;
        }
    }

    private void removeAllView(){
        for (int i=0;i<mBodyLayout.getChildCount();i++){
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }

    }
    private MyInfoView myInfoView;

    private void createView(int viewindex) {
        switch (viewindex){
            case 0:
                break;
            case 1:
                break;
            case 2:
                if (myInfoView==null){
                    myInfoView=new MyInfoView(this);
                    mBodyLayout.addView(myInfoView.getView());
                }else{
                    myInfoView.getView();
                }
                myInfoView.showView();
                break;
        }
    }

    private void clearBottomImageState() {
        tv_courses.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myinfo.setTextColor(Color.parseColor("#666666"));

        iv_courses.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myinfo.setImageResource(R.drawable.main_my_icon);
        for (int i=0;i<mBottomLayout.getChildCount();i++){
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }
protected long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN)){
            if ((System.currentTimeMillis() - exitTime)>2000){
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                /*MainActivity.this.finish();
                if(readLoginStatus()){
                    clearLoginStatus();

                }*/
                this.finish();
                if (AnalysisUtils.readLoginStatus(this)){
                    AnalysisUtils.clearLoginStatus(this);
                }
                System.exit(0);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void clearLoginStatus() {
        SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("LoginUserName","");
        editor.commit();
    }

    private boolean readLoginStatus(){
        SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin=sp.getBoolean("isLogin",false);
        return isLogin;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            if (isLogin){
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new FragmentMyinfoFragment()).commit();
                setSelectedStatus(2);
            }else{
                setSelectedStatus(2);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new FragmentMyinfoFragment()).commit();
            }
        }
    }

}
