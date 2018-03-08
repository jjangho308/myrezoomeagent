package io.rezoome.manager.pushcommand.entity.search;

import com.google.gson.annotations.SerializedName;

import io.rezoome.manager.pushcommand.entity.AbstractPushCommandEntity;

/**
 * Search command entity. <br />
 * 
 * This data comes from AMQ "search" commnad. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class SearchCommandEntity extends AbstractPushCommandEntity {
  @SerializedName("username")
  private final String username = null;
  
  @SerializedName("birth")
  private final String birth = null;
  
  @SerializedName("gender")
  private final String gender = null;
  
  @SerializedName("phone")
  private final String phone = null;
  
  @SerializedName("email")
  private final String email = null;
  
  @SerializedName("ci")
  private final String ci = null;
  

}
