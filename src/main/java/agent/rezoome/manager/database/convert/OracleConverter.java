package agent.rezoome.manager.database.convert;

import agent.rezoome.manager.job.entity.JobEntity;

public class OracleConverter extends DBConvertManagerImpl {
  private static class Singleton {
    private static final DBConvertManagerImpl instance = new OracleConverter();
  }

  public static OracleConverter getInstance() {
    return (OracleConverter) Singleton.instance;
  }
  
  public OracleConverter() {
  }
  
  @Override
  public void convert(JobEntity job) {
    // TODO Auto-generated method stub
    super.convert(job);
  }
}
