package com.springmvc.myutil.jexcelsql;

public class CityEntity {
    private String cityId;
    private String cityNameZh;
    private String cityNameEn;
    private String country;
    private String province;

    public CityEntity() {
    }

    public CityEntity(String cityId, String cityNameZh, String cityNameEn, String country, String province) {
        this.cityId = cityId;
        this.cityNameZh = cityNameZh;
        this.cityNameEn = cityNameEn;
        this.country = country;
        this.province = province;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityNameZh() {
        return cityNameZh;
    }

    public void setCityNameZh(String cityNameZh) {
        this.cityNameZh = cityNameZh;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}