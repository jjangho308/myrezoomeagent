package agent.rezoome.manager.database.convert;


import agent.rezoome.manager.job.entity.JobEntity;

public class MysqlConverter extends DBConvertManager {
  private static class Singleton {
    private static final DBConvertManager instance = new MysqlConverter();
  }

  public static MysqlConverter getInstance() {
    return (MysqlConverter) Singleton.instance;
  }
  
  
  public MysqlConverter() {
  }
  
  @Override
  public void convert(JobEntity job) {
    // TODO Auto-generated method stub
    super.convert(job);
  }
}
