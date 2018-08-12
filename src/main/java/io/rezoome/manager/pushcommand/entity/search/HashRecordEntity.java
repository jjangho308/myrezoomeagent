package io.rezoome.manager.pushcommand.entity.search;

import com.google.gson.annotations.SerializedName;

public final class HashRecordEntity {

  @SerializedName("subID")
  private final String subID = null;

  @SerializedName("hashed")
  private final String hashed = null;
  
  @SerializedName("txid")
  private final String txid = null;
  

  public String getSubID() {
    return subID;
  }

  public String getHashed() {
    return hashed;
  }
  
  public String getTxid() {
    return txid;
  }

}
