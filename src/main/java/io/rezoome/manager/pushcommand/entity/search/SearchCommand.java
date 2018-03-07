package io.rezoome.manager.pushcommand.entity.search;

import io.rezoome.core.entity.ActionResult;
import io.rezoome.manager.job.entity.JobEntity;
import io.rezoome.manager.job.entity.SearchJobEntity;
import io.rezoome.manager.provider.ManagerProvider;
import io.rezoome.manager.pushcommand.annotation.PushCommand;
import io.rezoome.manager.pushcommand.entity.AbstractPushCommandAction;

/**
 * Invoker of {@link SearchCommandEntity}. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
@PushCommand(SearchCommandEntity.class)
public class SearchCommand extends AbstractPushCommandAction<SearchCommandEntity> {

	@Override
	protected ActionResult processInternal(SearchCommandEntity entity) {
	  JobEntity searchJob = new SearchJobEntity(entity.getProfile());	  
	  ManagerProvider.job().addJob(searchJob);
	  System.out.println("pushcommand searchCommand");
	  return null;
	}

	@Override
	protected void invokePushCommandAction(SearchCommandEntity pushCommandEntity) {
		// TODO Auto-generated method stub
		
	}
}