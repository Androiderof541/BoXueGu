package boxuegu.example.packagecom.boxuegu.frament;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.adapter.ExercisesAdapter;
import boxuegu.example.packagecom.boxuegu.bean.ExercisesBean;

public class MainViewExerciseFragment extends Fragment  {

    private ListView lvList;
    private List<ExercisesBean> ebl;
    private ExercisesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_view_exercise, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvList = (ListView) view.findViewById(R.id.lv_list);
        adapter=new ExercisesAdapter(getActivity());
        initData();
        adapter.setData(ebl);
        lvList.setAdapter(adapter);
    }
    private void initData() {
        ebl=new ArrayList<ExercisesBean>();
        for (int i=0;i<10;i++){
            ExercisesBean bean=new ExercisesBean();
            bean.id=(i+1);
            switch (i){
                case 0:
                    bean.title="第1章Android基础入门";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 1:
                    bean.title="第2章Android UI开发";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 2:
                    bean.title="第3章activity";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 3:
                    bean.title="第4章数据储存";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 4:
                    bean.title="第5章SQLite数据库";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 5:
                    bean.title="第6章广播接收者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                case 6:
                    bean.title="第7章服务";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_3);
                    break;
                case 7:
                    bean.title="第8章内容提供者";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_4);
                    break;
                case 8:
                    bean.title="第9章网络编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_1);
                    break;
                case 9:
                    bean.title="第10章高级编程";
                    bean.content="共计5题";
                    bean.background=(R.drawable.exercises_bg_2);
                    break;
                default:
                    break;
            }
            ebl.add(bean);
        }
    }

}
