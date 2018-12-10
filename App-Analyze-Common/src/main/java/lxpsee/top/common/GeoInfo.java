package lxpsee.top.common;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 18:57.
 * <p>
 * 地址信息
 */
public class GeoInfo {
    private String country ;
    private String province ;

    public GeoInfo() {
    }

    public GeoInfo(String country, String province) {
        this.country = country;
        this.province = province;
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
