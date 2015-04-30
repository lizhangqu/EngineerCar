package cn.edu.zafu.engineercar.model;

/**
 * Created by lizhangqu on 2015/1/2.
 */
public class Item {
    private int resId;
    private String title;
    private String description;
    private String time;

    public Item(int resId, String title, String description, String time) {
        this.resId = resId;
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
