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
  
  @SerializedName("familyNameEN")
  private final String  familyNameEN  = null;

  @SerializedName("firstNameEN")
  private final String  firstNameEN   = null;

  @SerializedName("fullNameEN")
  private final String  fullNameEN    = null;

  @SerializedName("familyNameKO")
  private final String  familyNameKO   = null;

  @SerializedName("firstNameKO")
  private final String  firstNameKO   = null;

  @SerializedName("fullNameKO")
  private final String  fullNameKO      = null;

  @SerializedName("birth")
	private final String	birth		= null;

	@SerializedName("gender")
	private final String	gender		= null;

	@SerializedName("phone")
	private final String	phone		= null;

	@SerializedName("email")
	private final String	email		= null;

	@SerializedName("ci")
	private final String	ci			= null;

	@SerializedName("pkey")
  private final String  pkey      = null;
	
/*  @Override
  public String toString() {
    return "SearchCommandEntity [mid=" + super.mid + ", sid=" + sid + ", token=" + token + ", cmd=" + cmd + ", username=" + username + ", birth=" + birth + ", gender=" + gender + ", phone=" + phone
        + ", email=" + email + ", ci=" + ci + "]";
  }
	*/
	

}
