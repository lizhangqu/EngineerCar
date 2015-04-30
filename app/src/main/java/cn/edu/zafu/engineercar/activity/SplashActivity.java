package cn.edu.zafu.engineercar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.util.SharedPreferenceUtil;


public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LIGHT=3000;
    private SharedPreferenceUtil util=new SharedPreferenceUtil(this,"login");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=null;
                if(util.getKeyData("isLogin").equals("true")){
                    intent=new Intent(SplashActivity.this,MainActivity.class);
                }else{
                    intent=new Intent(SplashActivity.this,LoginActivity.class);
                }
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY_LIGHT);
    }
}
