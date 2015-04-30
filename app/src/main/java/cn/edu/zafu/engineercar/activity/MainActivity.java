package cn.edu.zafu.engineercar.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.zafu.engineercar.R;
import cn.edu.zafu.engineercar.fragment.HistoryFragment;
import cn.edu.zafu.engineercar.fragment.MenuFragment;
import cn.edu.zafu.engineercar.fragment.RoadFragment;
import cn.edu.zafu.engineercar.fragment.TrainFragment;
import cn.edu.zafu.engineercar.model.Person;
import cn.edu.zafu.engineercar.model.RecordItem;
import cn.edu.zafu.engineercar.model.UpdateFragmentEvent;
import cn.edu.zafu.engineercar.util.CommonUtil;
import cn.edu.zafu.engineercar.util.HttpUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.greenrobot.event.EventBus;


public class MainActivity extends Activity {

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private Map<String, Fragment> fragments = new HashMap<String, Fragment>();
    private MenuFragment menuFragment = null;
    private FragmentManager fragmentManager = null;
    private SweetAlertDialog pDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();//初始化fragment
        initNFC();//初始化NFC模块

    }

    private void initNFC() {
        // 获取默认的NFC控制器
        mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            Toast.makeText(this, "设备不支持NFC！", Toast.LENGTH_SHORT).show();
        }
        if (!mAdapter.isEnabled()) {
            Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_SHORT).show();
        }
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);//注册EventBus
        if (this.mAdapter == null)
            return;
        if (!this.mAdapter.isEnabled()) {
            Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_SHORT).show();
        }
        this.mAdapter.enableForegroundDispatch(this, this.mPendingIntent, null,
                null);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);//取消注册EventBus
        if (this.mAdapter == null)
            return;
        this.mAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onNewIntent(Intent paramIntent) {
        setIntent(paramIntent);
        resolveIntent(paramIntent);
        EventBus.getDefault().post(new UpdateFragmentEvent("培训"));
    }

    protected void resolveIntent(Intent intent) {
        // 得到是否检测到TAG触发0
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())
                || NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            // 处理该intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            // 获取id数组
            byte[] bytesId = tag.getId();
            if (CommonUtil.isNetworkConnected(this)) {
                pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("正在加载个人信息...");
                pDialog.setCancelable(false);
                pDialog.show();
                getPersonByPost(CommonUtil.bytesToHexString(bytesId));
            } else {
                Toast.makeText(this, "网络连接不可用，请确认网络连接正常后再重试！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getPersonByPost(final String imei) {
        final RequestParams params = new RequestParams();
        params.put("imei", imei);
        String urlString = "http://121.199.33.93/engineercar/person.php";
        HttpUtil.post(urlString, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //pDialog.dismiss();
                String str = new String(responseBody);
                Gson g = new Gson();
                Person p = g.fromJson(str, Person.class);
                EventBus.getDefault().post(p);
                getHistoryByPost(imei);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismissWithAnimation();
                pDialog=null;
                Toast.makeText(getApplicationContext(), "无法加载个人信息！",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getHistoryByPost(final String imei) {
        pDialog.setTitleText("正在加载培训记录...");
        final RequestParams params = new RequestParams();
        params.put("imei", imei);
        String urlString = "http://121.199.33.93/engineercar/history.php"; //
        HttpUtil.post(urlString, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                Gson g = new Gson();
                List<RecordItem> items = g.fromJson(str, new TypeToken<List<RecordItem>>() {
                }.getType());
                EventBus.getDefault().post(items);
                getPhotoByPost(imei);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismissWithAnimation();
                pDialog=null;
                Toast.makeText(getApplicationContext(), "无法加载培训记录",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getPhotoByPost(final String imei) {
        pDialog.setTitleText("正在加载个人照片...");
        final RequestParams params = new RequestParams();
        params.put("imei", imei);
        String urlString = "http://121.199.33.93/engineercar/photo.php";
        HttpUtil.post(urlString, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pDialog.dismissWithAnimation();
                BitmapFactory factory = new BitmapFactory();
                Bitmap bitmap = factory.decodeByteArray(responseBody, 0, responseBody.length);
                EventBus.getDefault().post(bitmap);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismissWithAnimation();
                pDialog=null;
                Toast.makeText(getApplicationContext(), "无法加载个人照片！",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onEventMainThread(UpdateFragmentEvent updateFragment) {
        fragmentManager.beginTransaction().
                hide(fragments.get("培训")).
                hide(fragments.get("路检")).
                hide(fragments.get("历史")).
                show(fragments.get(updateFragment.getName())).
                commit();
    }

    private void initFragments() {
        fragmentManager = getFragmentManager();
        menuFragment = (MenuFragment) getFragmentManager().findFragmentById(R.id.fragment_menu);
        fragments.put("培训", new TrainFragment());
        fragments.put("路检", new RoadFragment());
        fragments.put("历史", new HistoryFragment());
        fragmentManager.beginTransaction().
                add(R.id.fl_content, fragments.get("培训")).
                add(R.id.fl_content, fragments.get("路检")).
                add(R.id.fl_content, fragments.get("历史")).
                hide(fragments.get("培训")).
                hide(fragments.get("路检")).
                hide(fragments.get("历史")).
                show(fragments.get("培训")).
                commit();
    }


}
