package io.rezoome.manager.pushcommand.entity.search;

import io.rezoome.core.entity.AbstractEntity;
import io.rezoome.lib.json.Jsonable;

public class MemberProfile extends AbstractEntity implements Jsonable {
	String	username;
	String	birth;
	String	gender;
	String	phone;
	String	agencyId;
	String	agencyPwd;
	String	socialNumber;

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getAgencyPwd() {
		return agencyPwd;
	}

	public void setAgencyPwd(String agencyPwd) {
		this.agencyPwd = agencyPwd;
	}

	public String getSocialNumber() {
		return socialNumber;
	}

	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}