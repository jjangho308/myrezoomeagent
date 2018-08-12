package io.rezoome.external.cau.entity.cert;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;

public class InfoEntity extends AgencyResultEntity{
  
  // 대학 명
  @SerializedName("univ_name")
  public String univ_name;
  
  // 발급 주체  
  @SerializedName("cert_main_agent")
  public String cert_main_agent;
  
  // 메세지 1
  @SerializedName("msg1")
  public String msg1;

  public String getUniv_name() {
    return univ_name;
  }

  public void setUniv_name(String univ_name) {
    this.univ_name = univ_name;
  }

  public String getCert_main_agent() {
    return cert_main_agent;
  }

  public void setCert_main_agent(String cert_main_agent) {
    this.cert_main_agent = cert_main_agent;
  }

  public String getMsg1() {
    return msg1;
  }

  public void setMsg1(String msg1) {
    this.msg1 = msg1;
  }

  @Override
  public String toString() {
    return "[univ_name=" + univ_name + ", cert_main_agent=" + cert_main_agent + ", msg1=" + msg1 + "]";
  }
  
  
}
