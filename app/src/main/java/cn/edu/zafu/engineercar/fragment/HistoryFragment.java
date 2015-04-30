package cn.edu.zafu.engineercar.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.view.BarView;


public class HistoryFragment extends Fragment {
    private BarView bar=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bar= (BarView) view.findViewById(R.id.history_bar);
        bar.setTitleText("历史");
        bar.setBtnVisible();
    }
}
