package io.rezoome.manager.database.convert;


import io.rezoome.manager.database.DatabaseManagerImpl;

public abstract class AbstractDBConverter extends DatabaseManagerImpl{
  public abstract DBConverter getConverter();
}
