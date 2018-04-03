package boxuegu.example.packagecom.boxuegu.frament;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.activity.LoginActivity;
import boxuegu.example.packagecom.boxuegu.activity.SettingActivity;
import boxuegu.example.packagecom.boxuegu.utils.AnalysisUtils;


public class FragmentMyinfoFragment extends Fragment implements View.OnClickListener{


    private LinearLayout ll_head;
    private ImageView iv_head_icon;
    private TextView tv_user_name;
    private RelativeLayout rl_courses_history;
    private ImageView bottom_courses_icon;
    private RelativeLayout rl_setting;
    private ImageView iv_userinfo_icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myinfo, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_head = (LinearLayout) view.findViewById(R.id.ll_head);
        iv_head_icon = (ImageView) view.findViewById(R.id.iv_head_icon);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        rl_courses_history = (RelativeLayout) view.findViewById(R.id.rl_courses_history);
        bottom_courses_icon = (ImageView) view.findViewById(R.id.bottom_courses_icon);
        rl_setting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        iv_userinfo_icon = (ImageView) view.findViewById(R.id.iv_userinfo_icon);


        ll_head.setOnClickListener(this);
        rl_courses_history.setOnClickListener(this);
        rl_setting.setOnClickListener(this);

        if (AnalysisUtils.readLoginStatus(getActivity())){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(getActivity()));
        }else{
            tv_user_name.setText("点击登录");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_head:
                if (AnalysisUtils.readLoginStatus(getActivity())){

                }else{
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }
                break;
            case R.id.rl_courses_history:
                if (AnalysisUtils.readLoginStatus(getActivity())){

            }else{
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.rl_setting:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    Intent intent=new Intent(getActivity(), SettingActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }else{
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
