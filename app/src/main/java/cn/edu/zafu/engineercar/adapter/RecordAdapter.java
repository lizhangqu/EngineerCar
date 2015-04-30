package cn.edu.zafu.engineercar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.model.RecordItem;


public class RecordAdapter extends ArrayAdapter<RecordItem> {
    private int resourceId;
    public RecordAdapter(Context context, int resource, List<RecordItem> objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecordItem item = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder=new ViewHolder();
            viewHolder.recordNum= (TextView) view.findViewById(R.id.record_num);
            viewHolder.studyTime= (TextView) view.findViewById(R.id.study_time);
            viewHolder.startTime= (TextView) view.findViewById(R.id.start_time);
            viewHolder.endTime= (TextView) view.findViewById(R.id.end_time);
            viewHolder.flag= (TextView) view.findViewById(R.id.flag);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.recordNum.setText(item.getId());
        viewHolder.studyTime.setText(item.getStudyTime());
        viewHolder.startTime.setText(item.getStartTime());
        viewHolder.endTime.setText(item.getEndTime());
        viewHolder.flag.setText(item.isFlag()?"有效":"无效");
        return view;
    }

    class ViewHolder{
        TextView recordNum;
        TextView studyTime;
        TextView startTime;
        TextView endTime;
        TextView flag;
    }
}
