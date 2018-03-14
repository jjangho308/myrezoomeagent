package io.rezoome.manager.pushcommand.entity.search;

import io.rezoome.core.entity.ActionResult;
import io.rezoome.lib.json.JSON;
import io.rezoome.manager.job.entity.JobEntity;
import io.rezoome.manager.job.iorequest.IORequestJobEntity;
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
public class SearchCommandAction extends AbstractPushCommandAction<SearchCommandEntity> {

	@Override
	protected ActionResult processInternal(SearchCommandEntity entity) {

	  System.out.println("PushCommandAction : " + entity.toString());
	  JobEntity searchJob = JSON.fromJson(entity.toString(), IORequestJobEntity.class);
	  ((IORequestJobEntity)searchJob).setJobMethod("ASYNC");
	  
	  System.out.println(searchJob);
	  ManagerProvider.job().addJob(searchJob);
	  return null;

	}

	@Override
	protected void invokePushCommandAction(SearchCommandEntity pushCommandEntity) {
		// TODO Auto-generated method stub

	}
}