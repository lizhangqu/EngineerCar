package cn.edu.zafu.engineercar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.model.ChildItem;
import cn.edu.zafu.engineercar.model.GroupItem;

/**
 * Created by Administrator on 2015/1/1.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {
    private List<GroupItem> mGroup=null;
    private List<List<ChildItem>> mChild=null;
    private Context mContext=null;
    private LayoutInflater mInflater=null;

    public MyBaseExpandableListAdapter(Context mContext,List<GroupItem> mGroup, List<List<ChildItem>> mChild) {
        this.mGroup = mGroup;
        this.mChild = mChild;
        this.mContext = mContext;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChild.get(groupPosition).size();
    }

    @Override
    public List<ChildItem> getGroup(int groupPosition) {
        return mChild.get(groupPosition);
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return mChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=null;
        GroupViewHolder viewHolder=null;
        if(convertView==null){
            view=mInflater.inflate(R.layout.group_item_layout,null);
            viewHolder=new GroupViewHolder();
            viewHolder.mGroupName= (TextView) view.findViewById(R.id.group_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (GroupViewHolder) view.getTag();
        }
        viewHolder.mGroupName.setText(mGroup.get(groupPosition).getName().toString());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=null;
        ChildViewHolder viewHolder=null;
        if(convertView==null){
            view=mInflater.inflate(R.layout.child_item_layout,null);
            viewHolder=new ChildViewHolder();
            viewHolder.mIcon= (ImageView) view.findViewById(R.id.child_icon);
            viewHolder.mChildName= (TextView) view.findViewById(R.id.child_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ChildViewHolder) view.getTag();
        }
        viewHolder.mIcon.setImageResource(mChild.get(groupPosition).get(childPosition).getResId());
        viewHolder.mChildName.setText(mChild.get(groupPosition).get(childPosition).getName());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    private class GroupViewHolder{
        TextView mGroupName;
    }
    private class ChildViewHolder{
        ImageView mIcon;
        TextView mChildName;
    }
}
