package boxuegu.example.packagecom.boxuegu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import boxuegu.example.packagecom.boxuegu.R;
import boxuegu.example.packagecom.boxuegu.activity.ExercisesDetailActivity;
import boxuegu.example.packagecom.boxuegu.bean.ExercisesBean;

/**
 * Created by pc on 2018/4/21.
 */

public class ExercisesAdapter extends BaseAdapter {
    private Context mContext;
    private List<ExercisesBean> ebl;
    public ExercisesAdapter( Context context) {
        this.mContext=context;
    }
    public void setData(List<ExercisesBean> ebl){
        this.ebl=ebl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ebl==null ? 0 : ebl.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return ebl==null ? null : ebl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_exercises, null);
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.order = (TextView) convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        final ExercisesBean bean = getItem(position);
        SharedPreferences sp = mContext.getSharedPreferences("Click", Context.MODE_PRIVATE);
        boolean isFinish = sp.getBoolean("isFinish" + position, false);
        if (bean != null) {
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            if (isFinish) {
                vh.content.setText("已经完成");
            } else {
                vh.content.setText(bean.content);
                vh.order.setBackgroundResource(bean.background);
            }
        }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean == null) {
                        return;
                    }
                    Intent intent = new Intent(mContext, ExercisesDetailActivity.class);
                    intent.putExtra("id", bean.id);
                    intent.putExtra("title", bean.title);
                    ((Activity) mContext).startActivityForResult(intent, 000);
                }
            });
            return convertView;
        }

        class ViewHolder {
            public TextView title, content;
            public TextView order;
        }
    }
