package io.rezoome.external.mk.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class MkResponsePacketEntity extends ViaResponsePacketEntity{


  @SerializedName("err")
  private MkResponseErrArgsEntity err;
  
  @SerializedName("result")
  private List<MkResponseResultArgsEntity> result;

  public MkResponseErrArgsEntity getErr() {
    return err;
  }

  public void setErr(MkResponseErrArgsEntity err) {
    this.err = err;
  }

  public List<MkResponseResultArgsEntity> getResult() {
    return result;
  }

  public void setResult(List<MkResponseResultArgsEntity> result) {
    this.result = result;
  }

  
}
