package cn.edu.zafu.engineercar.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.model.UpdateFragmentEvent;
import cn.edu.zafu.engineercar.view.BarView;
import de.greenrobot.event.EventBus;


public class RoadFragment extends Fragment {


    private BarView bar=null;
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EventBus.getDefault().post(new UpdateFragmentEvent("历史"));
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.fragment_road, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bar= (BarView) view.findViewById(R.id.road_bar);
        bar.setTitleText("路检");
        bar.setListener(listener);

    }

}
