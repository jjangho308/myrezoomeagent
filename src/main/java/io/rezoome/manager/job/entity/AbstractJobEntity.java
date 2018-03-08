package io.rezoome.manager.job.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

/**
 * Abstraction of {@link JobEntity}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public abstract class AbstractJobEntity extends AbstractEntity implements JobEntity{
  @SerializedName("username")
  protected final String username = null;
  
  @SerializedName("birth")
  protected final String birth = null;
  
  @SerializedName("gender")
  protected final String gender = null;
  
  @SerializedName("phone")
  protected final String phone = null;

  @SerializedName("email")
  protected final String email = null;
  
  @SerializedName("ci")
  protected final String ci = null;
  
  @SerializedName("cmd")
  protected final String cmd = null;

  public String toJSON(){
    return "{username:" + username + ",birth:" + birth + ",gender:" + gender + ",phone:" + phone + ",email:" + email + ",ci:" + ci + ",cmd:" + cmd + "}";
  }
}
