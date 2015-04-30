package cn.edu.zafu.engineercar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.zafu.engineercar.R;


public class BarView extends RelativeLayout {
    private TextView title;
    private Button btn;
    public BarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bar,this);
        title= (TextView) findViewById(R.id.bar_title);
        btn= (Button) findViewById(R.id.bar_btn);
    }
    public void setTitleText(String text){
        title.setText(text);
    }
    public void setBtnVisible(){
        btn.setVisibility(GONE);
    }
    public void setListener(OnClickListener listener){
        btn.setOnClickListener(listener);
    }

}
