package com.busra.testapp;

public class SensorItem {
    private String name,vendor;
    String version;
    public SensorItem (){

    }
public SensorItem(String name,String vendor,String version){
        this.name=name;
        this.vendor=vendor;
        this.version=version;


}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
