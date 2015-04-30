package cn.edu.zafu.engineercar.model;

/**
 * Created by lizhangqu on 2015/1/3.
 */
public class UpdateFragmentEvent {
    private String name;
    public UpdateFragmentEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
