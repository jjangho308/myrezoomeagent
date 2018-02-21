package agent.rezoome.manager.pushcommand.entity.search;

import java.util.Date;

import agent.rezoome.manager.pushcommand.entity.AbstractPushCommandEntity;

/**
 * Search command entity. <br />
 * 
 * This data comes from AMQ "search" commnad. <br />
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class SearchCommandEntity extends AbstractPushCommandEntity {
	private final MemberProfile profile;
	
	private final Date from;
	
	private final Date to;

	public SearchCommandEntity(MemberProfile profile, Date from, Date to) {
		super();
		this.profile = profile;
		this.from = from;
		this.to = to;
	}

	public MemberProfile getProfile() {
		return profile;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}
}
