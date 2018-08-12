package io.rezoome.external.kmu.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class KmuResponseEntity extends ViaResponsePacketEntity{

  @SerializedName("err")
  public KmuResponseErrArgsEntity err;
  
  @SerializedName("result")
  public List<KmuResponseResultArgsEntity> result;

  public KmuResponseErrArgsEntity getErr() {
    return err;
  }

  public void setErr(KmuResponseErrArgsEntity err) {
    this.err = err;
  }

  public List<KmuResponseResultArgsEntity> getResult() {
    return result;
  }

  public void setResult(List<KmuResponseResultArgsEntity> result) {
    this.result = result;
  }
}
