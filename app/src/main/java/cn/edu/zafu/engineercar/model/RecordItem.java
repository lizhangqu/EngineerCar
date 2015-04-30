package cn.edu.zafu.engineercar.model;

/**
 * Created by lizhangqu on 2015/1/2.
 */
public class RecordItem {
    private String id;
    private String studyTime;
    private String startTime;
    private String endTime;
    private boolean flag;

    public RecordItem(String id, String studyTime, String startTime, String endTime, boolean flag) {
        this.id = id;
        this.studyTime = studyTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
