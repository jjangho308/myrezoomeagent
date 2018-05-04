package io.rezoome.manager.mapper;

import io.rezoome.external.common.mapper.DaoMapper;
import io.rezoome.manager.Manager;

public interface MapperManager extends Manager {

	public void createMapper() throws InstantiationException, ClassNotFoundException, IllegalAccessException;
	public void createDaoMapper();
	public Mapper getMapper();
	public DaoMapper getDaoMapper();
}
