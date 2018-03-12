package io.rezoome.manager.database.convert;

import io.rezoome.manager.database.DatabaseManagerImpl;

public class DBConvertManagerImpl extends DatabaseManagerImpl implements DBConverterManager {

	private static class Singleton {
		private static final DatabaseManagerImpl instance = new DBConvertManagerImpl();
	}

	public static DatabaseManagerImpl getInstance() {
		return Singleton.instance;
	}

	public DBConvertManagerImpl() {

<<<<<<< HEAD
  @Override
  public DBConverter getConverter() {
    // TODO Auto-generated method stub
    return (DBConverter) converter;
  }
=======
	}

	@SuppressWarnings("static-access")
	@Override
	public void createConverter() {
		if ("ORACLE".equals(super.dbType.toUpperCase())) {
			super.converter = new OracleConverter();
		} else if ("MYSQL".equals(super.dbType.toUpperCase())) {
			super.converter = new MysqlConverter();
		}
		System.out.println("Create Converter - " + super.dbType.toUpperCase());
	}
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git

	@Override
	public boolean isPrepared() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DBConverter getConverter() {
		// TODO Auto-generated method stub

		System.out.println("getConverter()");
		return (DBConverter) converter;
	}

}
