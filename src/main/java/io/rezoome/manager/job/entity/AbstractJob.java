package io.rezoome.manager.job.entity;

import io.rezoome.core.entity.ActionResult;
import io.rezoome.manager.job.JobRsltEntity;

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
	public JobRsltEntity call() throws Exception {
		JobRsltEntity result = null;
		this.process(this.jobEntity);
		return result;
	}

	@Override
	public ActionResult process(JOB entity) {
		ActionResult result = null;

		this.processInternal(entity);

		return result;
	}

	protected abstract JobRsltEntity processInternal(JOB entity);
}