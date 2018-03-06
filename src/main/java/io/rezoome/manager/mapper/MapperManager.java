package io.rezoome.manager.mapper;


import io.rezoome.manager.Manager;

public interface MapperManager extends Manager{
  
  public void createMapper() throws InstantiationException,  ClassNotFoundException , IllegalAccessException;
  public Mapper getMapper() ;
}
