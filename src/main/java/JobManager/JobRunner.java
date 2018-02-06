package JobManager;

import MessageManager.JobImpl;
import vo.RzmVOImpl;

public interface JobRunner {

  public RzmVOImpl contertJobToRzmVO(JobImpl job);
}
