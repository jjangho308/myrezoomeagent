package io.rezoome.manager.pushcommand.entity.search;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public final class HashRecordEntity {

  @SerializedName("subID")
  private final String subID = null;

  @SerializedName("hashed")
  private final List<String> hashed = null;

  public String getSubID() {
    return subID;
  }

  public List<String> getHashed() {
    return hashed;
  }

}
