package io.rezoome.database;

public class MySQLConnecter extends DbaseConnecterImpl {

  // use singleton design patern
  private static MySQLConnecter instance;

  public static MySQLConnecter getInstance() {
    if (instance == null) {
      instance = new MySQLConnecter();
    }
    return instance;
  }
}