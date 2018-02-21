package agent.rezoome.manager.pushcommand.entity.search;

import agent.rezoome.core.entity.ActionResult;
import agent.rezoome.manager.provider.ManagerProvider;
import agent.rezoome.manager.pushcommand.annotation.PushCommand;
import agent.rezoome.manager.pushcommand.entity.AbstractPushCommandAction;

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
	public ActionResult process(SearchCommandEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionResult processInternal(SearchCommandEntity entity) {
	  //JobEntity searchJob = new SearchJobEntity(entity.getName(), entity.getBirthday());
	  return null;
	}

	@Override
	protected void invokePushCommandAction(SearchCommandEntity pushCommandEntity) {
		// TODO Auto-generated method stub
		
	}
}