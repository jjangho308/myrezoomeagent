package agent.rezoome.manager.job.entity;

import agent.rezoome.core.entity.ActionResult;
import agent.rezoome.manager.job.JobResult;

/**
 * Abstract of job action. <br />
 * 
 * @since 1.0.0s
 * @author TACKSU
 *
 * @param <JOB>
 */
public abstract class AbstractJob<JOB extends JobEntity> implements JobAction<JOB> {

	/**
	 * 
	 */
	protected final JOB jobEntity;

	public AbstractJob(JOB entity) {
		this.jobEntity = entity;
	}

	@Override
	public JobResult call() throws Exception {
		JobResult result = null;
		this.process(this.jobEntity);
		return result;
	}

	@Override
	public ActionResult process(JOB entity) {
		ActionResult result = null;

		this.processInternal(entity);

		return result;
	}

	protected abstract JobResult processInternal(JOB entity);
}