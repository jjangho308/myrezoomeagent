package io.rezoome.manager.network.entity;

import com.google.gson.annotations.SerializedName;

import io.rezoome.core.entity.AbstractEntity;

public class RequestSearchResultArgsEntity extends AbstractEntity implements RequestArgsEntity {

<<<<<<< HEAD
  @SerializedName("orgCode")
  private String orgCode;

  @SerializedName("encryptedData")
  private String encryptedData;

  @SerializedName("hashedData")
  private String hashedData;

  @SerializedName("encryptedKey")
  private String encryptedKey;
=======
	private String	orgCode;
	private String	encryptedData;
	private String	hashedData;
	private String	encryptedKey;
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getHashedData() {
		return hashedData;
	}

	public void setHashedData(String hashedData) {
		this.hashedData = hashedData;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}
}
