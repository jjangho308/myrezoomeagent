package io.rezoome.manager.database.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.external.entity.AgencyUserEntity;

public class UserEntity extends AbstractEntity implements AgencyUserEntity{

  @SerializedName("ci")
  private final String ci = null;

  @SerializedName("familyNameEN")
  private final String familyNameEN = null;

  @SerializedName("firstNameEN")
  private final String firstNameEN = null;

  @SerializedName("fullNameEN")
  private final String fullNameEN = null;

  @SerializedName("familyNameKO")
  private final String familyNameKO = null;

  @SerializedName("firstNameKO")
  private final String firstNameKO = null;

  @SerializedName("fullNameKO")
  private final String fullNameKO = null;

  @SerializedName("birth")
  private final String birth = null;

  @SerializedName("gender")
  private final String gender = null;

  @SerializedName("phone")
  private final String phone = null;

  @SerializedName("email")
  private final String email = null;

  public String getCi() {
    return ci;
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

  public String getBirth() {
    return birth;
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


}
