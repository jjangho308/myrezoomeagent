package io.rezoome.manager.mapper;

import java.util.List;

import io.rezoome.entity.RzmRsltEntity;
import io.rezoome.manager.database.entity.DBRsltEntity;

public interface Mapper {
	public MapperEntity convert(List<DBRsltEntity> entity);
}
