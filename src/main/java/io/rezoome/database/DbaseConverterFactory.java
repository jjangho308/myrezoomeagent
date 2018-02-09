package io.rezoome.database;

public class DbaseConverterFactory {

  public static DbaseConverterImpl createConverter(String convName, String dbDriverName) throws Exception {
    DbaseConverterImpl dbConv = null;
    if (dbDriverName.equals("com.mysql.jdbc.Driver")) {
      dbConv = new MySQLConverter();
    } else if (dbDriverName.equals("com.oracle.jdbc.Driver")) {
      dbConv = new OracleConverter();
    } else {

    }
    return dbConv;
  }
}
