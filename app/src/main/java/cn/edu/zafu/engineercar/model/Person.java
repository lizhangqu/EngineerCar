package cn.edu.zafu.engineercar.model;

/**
 * Created by lizhangqu on 2015/1/3.
 */
public class Person {
    private String name;
    private String phone;
    private String id;
    private String idAddress;
    private int carAge;
    private String carTime;
    private int studyTime;
    private String carType;
    private String addTime;
    private String updateTime;
    private String ownCompany;
    public Person() {
    }
    public Person(String name, String phone, String id, String idAddress, int carAge, String carTime, int studyTime, String carType, String addTime, String updateTime, String ownCompany) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.idAddress = idAddress;
        this.carAge = carAge;
        this.carTime = carTime;
        this.studyTime = studyTime;
        this.carType = carType;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.ownCompany = ownCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public int getCarAge() {
        return carAge;
    }

    public void setCarAge(int carAge) {
        this.carAge = carAge;
    }

    public String getCarTime() {
        return carTime;
    }

    public void setCarTime(String carTime) {
        this.carTime = carTime;
    }

    public int getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOwnCompany() {
        return ownCompany;
    }

    public void setOwnCompany(String ownCompany) {
        this.ownCompany = ownCompany;
    }
}
