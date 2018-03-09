package io.rezoome.manager.job.iorequest;

import io.rezoome.manager.job.entity.AbstractJobEntity;

/**
 * Job to process request from I/O module via AMQ. <br/ >
 * 
 * @author TACKSU
 *
 */
public final class IORequestJobEntity extends AbstractJobEntity {

	String jobMethod;

	public String getJobMethod() {
		return jobMethod;
	}

	public void setJobMethod(String jobMethod) {
		this.jobMethod = jobMethod;
	}

	/*
	 * @SerializedName("username") private final String username = null;
	 * 
	 * @SerializedName("birth") private final String birth = null;
	 * 
	 * @SerializedName("gender") private final String gender = null;
	 * 
	 * @SerializedName("phone") private final String phone = null;
	 * 
	 * @SerializedName("email") private final String email = null;
	 * 
	 * @SerializedName("ci") private final String ci = null;
	 * 
	 * @SerializedName("cmd") private final String cmd = null;
	 * 
	 * 
	 * @Override public String toString() { return
	 * "IORequestJobEntity [jobMethod=" + jobMethod + ", username=" + username +
	 * ", birth=" + birth + ", gender=" + gender + ", phone=" + phone +
	 * ", email=" + email + ", ci=" + ci + ", cmd=" + cmd + "]"; }
	 * 
	 */

	@Override
	public String toString() {
		return "IORequestJobEntity [jobMethod=" + jobMethod + ", username=" + username + ", birth=" + birth
				+ ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", ci=" + ci + ", cmd=" + cmd + "]";
	}

}
