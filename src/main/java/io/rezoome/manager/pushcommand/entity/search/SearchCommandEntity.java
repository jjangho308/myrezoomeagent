package io.rezoome.manager.pushcommand.entity.search;

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
	private final MemberProfile profile;

	public SearchCommandEntity(){
		this.profile = null;
	}
	
	public SearchCommandEntity(MemberProfile profile) {
		super();
		this.profile = profile;
	}

	public MemberProfile getProfile() {
		return profile;
	}

	String cmd;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}
