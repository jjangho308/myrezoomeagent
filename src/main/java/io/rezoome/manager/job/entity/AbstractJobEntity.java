package io.rezoome.manager.job.entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.manager.pushcommand.entity.search.HashRecordEntity;

/**
 * Abstraction of {@link JobEntity}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public abstract class AbstractJobEntity extends AbstractEntity implements JobEntity {

  @SerializedName("mid")
  private final String mid = null;

  @SerializedName("sid")
  private final String sid = null;

  @SerializedName("token")
  private final String token = null;

  @SerializedName("cmd")
  protected final String cmd = null;

  // @SerializedName("username")
  // protected final String username = null;
  @SerializedName("familyNameEN")
  protected final String familyNameEN = null;

  @SerializedName("firstNameEN")
  protected final String firstNameEN = null;

  @SerializedName("fullNameEN")
  protected final String fullNameEN = null;

  @SerializedName("familyNameKO")
  protected final String familyNameKO = null;

  @SerializedName("firstNameKO")
  protected final String firstNameKO = null;

  @SerializedName("fullNameKO")
  protected final String fullNameKO = null;

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

  @SerializedName("pkey")
  private final String pkey = null;

  @SerializedName("n")
  private final String n = null;

  @SerializedName("e")
  private final String e = null;

  @SerializedName("subIDs")
  private final List<String> subIds = null;

  @SerializedName("require")
  private final List<String> require = null;

  @SerializedName("records")
  private final List<HashRecordEntity> records = null;

  public String getMid() {
    return mid;
  }

  public String getSid() {
    return sid;
  }

  public String getToken() {
    return token;
  }

  public String getCmd() {
    return cmd;
  }

  // public String getUsername() {
  // return username;
  // }

  public String getBirth() {
    return birth;
  }

  public String getFamilyNameEN() {
    return familyNameEN;
  }

  public String getFirstNameEN() {
    return firstNameEN;
  }

  public String getFullNameEN() {
    return fullNameEN;
  }

  public String getFamilyNameKO() {
    return familyNameKO;
  }

  public String getFirstNameKO() {
    return firstNameKO;
  }

  public String getFullNameKO() {
    return fullNameKO;
  }

  public String getGender() {
    return gender;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getCi() {
    return ci;
  }

  public String getPkey() {
    return pkey;
  }

  public String getN() {
    return n;
  }

  public String getE() {
    return e;
  }

  public List<String> getSubIds() {
    return subIds;
  }

  public List<String> getRequire() {
    return require;
  }

  public List<HashRecordEntity> getRecords() {
    return records;
  }

  public String toJSON() {
    return "{familyNameEN:" + familyNameEN + ", firstNameEN:" + firstNameEN + ", fullNameEN:" + fullNameEN
        + ", familyNameKO:" + familyNameKO + ", firstNameKO:" + firstNameKO + ", fullNameKO:" + fullNameKO
        + ", birth:" + birth + ", gender:" + gender + ", phone:" + phone + ", email:" + email + ", ci:" + ci + "}";
  }

}
