package cn.edu.zafu.engineercar.fragment;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.adapter.RecordAdapter;
import cn.edu.zafu.engineercar.model.Person;
import cn.edu.zafu.engineercar.model.RecordItem;
import cn.edu.zafu.engineercar.view.BarView;
import de.greenrobot.event.EventBus;


public class TrainFragment extends Fragment {
    private ListView mRecord=null;
    private BarView bar=null;
    private List<RecordItem> items=new ArrayList<RecordItem>();
    private RecordAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_train, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniData();
        initView();

    }
    public void onEventMainThread(Person person){
        TextView name= (TextView) getView().findViewById(R.id.name);
        TextView phone= (TextView) getView().findViewById(R.id.phone);
        TextView idcard= (TextView) getView().findViewById(R.id.idcard);
        TextView idcardAddress= (TextView) getView().findViewById(R.id.idcard_address);
        TextView carAge= (TextView) getView().findViewById(R.id.car_age);
        TextView carTime= (TextView) getView().findViewById(R.id.car_time);
        TextView studyTime= (TextView) getView().findViewById(R.id.study_time);
        TextView carType= (TextView) getView().findViewById(R.id.car_type);
        TextView addTime= (TextView) getView().findViewById(R.id.add_time);
        TextView updateTime= (TextView) getView().findViewById(R.id.update_time);
        TextView ownCompany= (TextView) getView().findViewById(R.id.own_company);
        name.setText(person.getName());
        phone.setText(person.getPhone());
        idcard.setText(person.getId());
        idcardAddress.setText(person.getIdAddress());
        carAge.setText(person.getCarAge()+"年");
        carTime.setText(person.getCarTime());
        studyTime.setText(person.getStudyTime()+"次");
        carType.setText(person.getCarType());
        addTime.setText(person.getAddTime());
        updateTime.setText(person.getUpdateTime());
        ownCompany.setText(person.getOwnCompany());
    }
    public void onEventMainThread( List<RecordItem> items){
        adapter.clear();
        adapter.addAll(items);
        adapter.notifyDataSetChanged();
    }
    public void onEventMainThread(Bitmap bitmap){
        ImageView avatar= (ImageView) getView().findViewById(R.id.avatar);
        avatar.setImageBitmap(bitmap);
    }
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        bar= (BarView) getView().findViewById(R.id.train_bar);
        bar.setTitleText("培训");
        bar.setBtnVisible();
        mRecord= (ListView) getView().findViewById(R.id.record_listview);
        adapter=new RecordAdapter(getActivity().getApplicationContext(),R.layout.record_item_layout,items);//新建自己的适配器

        mRecord.setAdapter(adapter);
        mRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecordItem item=items.get(position);//获得点击项对应实体类
                Toast.makeText(getActivity().getApplicationContext(), item.getStudyTime(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void iniData() {
        /*
        RecordItem item=new RecordItem("1","2014-1-12","10:05","11:00",true);
        items.add(item);
        item=new RecordItem("2","2014-2-8","9:35","12:00",false);
        items.add(item);
        item=new RecordItem("3","2014-3-5","8:35","11:10",true);
        items.add(item);
        item=new RecordItem("4","2014-4-26","10:00","12:35",true);
        items.add(item);
        item=new RecordItem("5","2014-5-13","9:00","12:35",true);
        items.add(item);
        item=new RecordItem("6","2014-6-22","13:00","14:35",true);
        items.add(item);
        item=new RecordItem("7","2014-7-17","10:00","11:35",true);
        items.add(item);
        item=new RecordItem("8","2014-8-23","8:00","10:35",true);
        items.add(item);
        item=new RecordItem("9","2014-9-24","8:30","10:35",true);
        items.add(item);
        item=new RecordItem("10","2014-10-21","9:00","11:35",true);
        items.add(item);
        item=new RecordItem("11","2014-11-13","8:00","10:35",true);
        items.add(item);
        item=new RecordItem("12","2014-12-15","9:00","11:35",true);
        items.add(item);
        */
    }
}
