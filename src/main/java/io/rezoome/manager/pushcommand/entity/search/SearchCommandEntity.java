package io.rezoome.manager.pushcommand.entity.search;

import java.util.Date;

import io.rezoome.lib.json.Jsonable;
import io.rezoome.manager.pushcommand.entity.AbstractPushCommandEntity;

/**
 * Search command entity. <br />
 * 
 * This data comes from AMQ "search" commnad. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class SearchCommandEntity extends AbstractPushCommandEntity implements Jsonable  {
	private final MemberProfile profile;
	
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
