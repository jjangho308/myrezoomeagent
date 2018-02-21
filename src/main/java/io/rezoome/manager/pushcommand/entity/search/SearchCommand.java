package io.rezoome.manager.pushcommand.entity.search;

import io.rezoome.core.entity.ActionResult;
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
	public ActionResult process(SearchCommandEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ActionResult processInternal(SearchCommandEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
}