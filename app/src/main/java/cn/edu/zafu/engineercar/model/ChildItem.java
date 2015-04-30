package cn.edu.zafu.engineercar.model;

/**
 * Created by Administrator on 2015/1/1.
 */
public class ChildItem {
    private int resId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {

        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public ChildItem(int resId, String name) {
        this.resId = resId;
        this.name = name;
    }
}
