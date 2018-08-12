package io.rezoome.external.kyungki.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.external.kmu.entity.KmuResponseErrArgsEntity;
import io.rezoome.external.kmu.entity.KmuResponseResultArgsEntity;
import io.rezoome.manager.vianetwork.entity.response.ViaResponsePacketEntity;

public class KyungkiResponseEntity extends ViaResponsePacketEntity{

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
