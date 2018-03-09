package io.rezoome.manager.arrange;

import io.rezoome.manager.job.entity.JobEntity;
import io.rezoome.manager.job.iorequest.IORequestJobAction;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
import io.rezoome.manager.pushcommand.entity.PushCommandEntity;
import io.rezoome.manager.pushcommand.entity.search.SearchCommandAction;
import io.rezoome.manager.pushcommand.entity.search.SearchCommandEntity;

/**
 * Class mapper dummy. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
final class ClassMapper {
	static void setEntityKeyMap(ClassArrangeManagerImpl arranger) {
		arranger.addEntityKeyMap(PushCommandEntity.class, "Search",
				SearchCommandEntity.class);
		arranger.addActionMap(PushCommandEntity.class,
				SearchCommandEntity.class, SearchCommandAction.class);

		arranger.addEntityKeyMap(JobEntity.class, "Search",
				IORequestJobEntity.class);
		arranger.addActionMap(JobEntity.class, IORequestJobEntity.class,
				IORequestJobAction.class);
	}
}