package cn.edu.zafu.engineercar.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/1/1.
 */
public class SharedPreferenceUtil {
    private Context mContext;
    private String mFileName;
    public SharedPreferenceUtil(Context context,String fileName){
        mContext = context;
        this.mFileName=fileName;
    }
    public void setKeyData(String key,String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public String getKeyData(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, "");//第二个参数为默认值
        return value;
    }

}
