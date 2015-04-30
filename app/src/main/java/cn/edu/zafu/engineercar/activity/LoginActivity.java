package cn.edu.zafu.engineercar.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.util.CommonUtil;
import cn.edu.zafu.engineercar.util.HttpUtil;
import cn.edu.zafu.engineercar.util.SharedPreferenceUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class LoginActivity extends Activity {
    private EditText account = null;
    private EditText password = null;
    private Button login = null;
    private String result = null;
    private SharedPreferenceUtil util = new SharedPreferenceUtil(this, "login");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        account.setText(CommonUtil.getIMEI(this));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = account.getText().toString();
                String pass = password.getText().toString();
                validateByPost(user, pass);

            }
        });
    }

    //http请求的账号密码验证
    public void validateByPost(final String account, String password) {
        //final ProgressDialog pDialog = ProgressDialog.show(this, "请稍等", "正在验证中...");
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("正在验证中...");
        pDialog.setCancelable(false);
        pDialog.show();
        final RequestParams params = new RequestParams();

        params.put("account", account);
        params.put("password", password);
        String urlString = "http://121.199.33.93:81/engineercar/login.php"; // 一個獲取菜谱的url地址
        if (CommonUtil.isNetworkConnected(this)) {
            HttpUtil.post(urlString, params, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    pDialog.dismissWithAnimation();
                    result = new String(responseBody);
                    if (result.equals("success")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                        util.setKeyData("isLogin", "true");
                        util.setKeyData("username", account);
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "账号或者密码不正确！",
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    pDialog.dismissWithAnimation();
                    Toast.makeText(getApplicationContext(), "服务器出现故障，暂时无法验证，请稍后再试！",
                            Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "网络连接不可用，请确认网络连接正常后再重试！", Toast.LENGTH_SHORT).show();
        }
    }

}
