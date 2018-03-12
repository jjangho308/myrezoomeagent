package io.rezoome.manager.database.dao.xml.agency;

import java.io.IOException;

import io.rezoome.manager.database.entity.DBEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Dao {
	// public List<DBRsltEntity> getRecord(DBEntity entity) throws IOException;
	public DBRsltEntity getRecord(DBEntity entity) throws IOException;
}
