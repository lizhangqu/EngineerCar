package cn.edu.zafu.engineercar.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.activity.LoginActivity;
import cn.edu.zafu.engineercar.adapter.MyBaseExpandableListAdapter;
import cn.edu.zafu.engineercar.model.ChildItem;
import cn.edu.zafu.engineercar.model.GroupItem;
import cn.edu.zafu.engineercar.model.UpdateFragmentEvent;
import cn.edu.zafu.engineercar.util.SharedPreferenceUtil;
import de.greenrobot.event.EventBus;

public class MenuFragment extends Fragment {
    private TextView mAccount = null;
    private Button mExit = null;
    private ExpandableListView mExpandableListView = null;
    private MyBaseExpandableListAdapter mBaseExpandableListAdapter = null;
    private List<GroupItem> mGroup = new ArrayList<GroupItem>();
    private List<List<ChildItem>> mChild = new ArrayList<List<ChildItem>>();
    private SharedPreferenceUtil util;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initViewAndAdapter();
    }

    private void initViewAndAdapter() {
        util = new SharedPreferenceUtil(getActivity().getApplicationContext(), "login");
        mAccount = (TextView) getView().findViewById(R.id.account);
        mAccount.setText(util.getKeyData("username"));
        //退出按钮逻辑
        mExit = (Button) getView().findViewById(R.id.exit);
        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.setKeyData("isLogin", "false");
                util.setKeyData("username", "");
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        mExpandableListView = (ExpandableListView) getView().findViewById(R.id.expandable_listview);
        mBaseExpandableListAdapter = new MyBaseExpandableListAdapter(getActivity().getApplicationContext(), mGroup, mChild);
        mExpandableListView.setGroupIndicator(null);
        mExpandableListView.setAdapter(mBaseExpandableListAdapter);
        //默认展开
        for (int i = 0; i < mGroup.size(); i++) {
            mExpandableListView.expandGroup(i);
        }
        mExpandableListView.setOnGroupClickListener(new MyOnGroupClickListener());
        mExpandableListView.setOnChildClickListener(new MyOnChildClickListener());
    }

    //数据初始化
    private void initData() {
        GroupItem groupItem = null;

        groupItem = new GroupItem("培训");
        mGroup.add(groupItem);
        groupItem = new GroupItem("路检");
        mGroup.add(groupItem);


        List<ChildItem> child = null;
        ChildItem childItem = null;

        child = new ArrayList<ChildItem>();
        mChild.add(child);

        child = new ArrayList<ChildItem>();
        childItem = new ChildItem(R.drawable.img_menu_item, "路检");
        child.add(childItem);
        childItem = new ChildItem(R.drawable.img_menu_item, "历史");
        child.add(childItem);
        mChild.add(child);

    }

    /*
        一级事件监听
        */
    class MyOnGroupClickListener implements ExpandableListView.OnGroupClickListener {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            int count = mBaseExpandableListAdapter.getChildrenCount(groupPosition);
            if (count == 0) {
                EventBus.getDefault().post(new UpdateFragmentEvent(mGroup.get(groupPosition).getName()));
                return true;
            } else {
                return false;
            }
            //返回false能继续处理展开动作，如果返回了true，则二级项不能展开
        }
    }

    /*
     二级事件监听
     */
    class MyOnChildClickListener implements ExpandableListView.OnChildClickListener {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            ChildItem item = mBaseExpandableListAdapter.getChild(groupPosition, childPosition);
            EventBus.getDefault().post(new UpdateFragmentEvent(item.getName()));
            return true;//事件被处理了返回true不能继续处理事件
        }
    }
}
