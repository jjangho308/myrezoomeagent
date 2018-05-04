package io.rezoome.manager.vianetwork.entity.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.common.entity.AgencyErrEntity;
import io.rezoome.external.common.entity.AgencyResultEntity;
import io.rezoome.manager.network.entity.NetworkPacket;

public class ViaResponsePacketEntity  extends AbstractEntity implements NetworkPacket{
  
  @SerializedName("err")
  AgencyErrEntity err;
  
  @SerializedName("result")
  List<AgencyResultEntity> result;

  public AgencyErrEntity getErr() {
    return err;
  }

  public void setErr(AgencyErrEntity err) {
    this.err = err;
  }

  public List<AgencyResultEntity> getResult() {
    return result;
  }

  public void setResult(List<AgencyResultEntity> result) {
    this.result = result;
  }
  
  
  
}