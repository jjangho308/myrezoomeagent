package io.rezoome.external.opic.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class OpicResponseEntity extends ViaResponsePacketEntity{

  @SerializedName("err")
  public OpicResponseErrArgsEntity err;
  
  @SerializedName("result")
  public List<OpicResponseResultArgsEntity> result;

  public OpicResponseErrArgsEntity getErr() {
    return err;
  }

  public void setErr(OpicResponseErrArgsEntity err) {
    this.err = err;
  }

  public List<OpicResponseResultArgsEntity> getResult() {
    return result;
  }

  public void setResult(List<OpicResponseResultArgsEntity> result) {
    this.result = result;
  }
}