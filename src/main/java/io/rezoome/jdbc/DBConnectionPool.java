package io.rezoome.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class DBConnectionPool {
<<<<<<< HEAD
  // ���� ��� ���� Connection ����
=======
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  private int checkedOut;

  // Free Connection List
  private Vector<Connection> freeConnections = new Vector<Connection>();

<<<<<<< HEAD
  // Connection �ִ� ����
=======
  // Connection
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  private int maxConn;

<<<<<<< HEAD
  // Connection �ʱ� ����
=======
  // Connection
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  private int initConn;

<<<<<<< HEAD
  // Waiting time (pool�� connection�� ������ ��ٸ��� �ִ�ð�)
=======
  // Waiting time 
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  private int maxWait;

  // Connection Pool Name
  private String name;

  // DB Password
  private String password;

  // DB URL
  private String URL;

  // DB UserID
  private String user;

  // Constructor
  public DBConnectionPool(String name,
      String URL,
      String user,
      String password,
      int maxConn,
      int initConn,
      int waitTime) {

    this.name = name;
    this.URL = URL;
    this.user = user;
    this.password = password;
    this.maxConn = maxConn;
    this.maxWait = waitTime;

    for (int i = 0; i < initConn; i++) {
      freeConnections.addElement(newConnection());
    }
  }

<<<<<<< HEAD
  // Connection �ݳ�
  // @param con : �ݳ��� Connection
=======
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  public synchronized void freeConnection(Connection con) {
    freeConnections.addElement(con);
    checkedOut--;
<<<<<<< HEAD
    // Connection�� ��� ���� ����ϰ� �ִ� thread�� �˸�
=======
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
    notifyAll();
  }

<<<<<<< HEAD
  // Connection �� ����
=======
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  public synchronized Connection getConnection() {
    Connection con = null;
<<<<<<< HEAD
    // Connection�� Free List�� ������ List�� ù ��°�� ����
=======
    // Connection
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
    if (freeConnections.size() > 0) {
      con = freeConnections.firstElement();
      freeConnections.removeElementAt(0);

      try {
<<<<<<< HEAD
        // DBMS�� ���� Connection�� close �Ǿ����� �ٽ� �䱸
=======
        // DBMS
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
        if (con.isClosed()) {
          System.out.println("Removed bad connection from " + name);
          con = getConnection();
        }
<<<<<<< HEAD
      } // ����� Connection �߻��ϸ� �ٽ� �䱸
=======
      }
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
      catch (SQLException e) {
        e.printStackTrace();
        con = getConnection();
      }
<<<<<<< HEAD
    } // Connection�� Free List�� ������ ���� ����
=======
    } 
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
    else if (maxConn == 0 || checkedOut < maxConn) {
      con = newConnection();
    }

    if (con != null) {
      checkedOut++;
    }

    return con;
  }

<<<<<<< HEAD
  // Connection�� ����
  // @param timeout : Connection�� ��� ���� �ִ� ��ٸ� �ð�
=======
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  public synchronized Connection getConnection(long timeout) {
    long startTime = new Date().getTime();
    Connection con;
    while ((con = getConnection()) == null) {
      try {
        wait(timeout * maxWait);
      } catch (InterruptedException e) {
      }
      if ((new Date().getTime() - startTime) >= timeout) {
        // ��ٸ� �ð� �ʰ�
        return null;
      }
    }

    return con;
  }
<<<<<<< HEAD

  // Connection ����
=======
>>>>>>> branch 'development' of https://github.com/Team-REZOOME/agent.git
  private Connection newConnection() {
    Connection con = null;
    try {
      if (user == null) {
        con = DriverManager.getConnection(URL);
      } else {
        con = DriverManager.getConnection(URL, user, password);
      }
      System.out.println("Created a new connection in pool " + name);
    } catch (SQLException e) {
      StringBuffer sb = new StringBuffer();
      sb.append("Can't create a new connection for ");
      sb.append(URL);
      sb.append(" user: ");
      sb.append(user);
      sb.append(" passwd: ");
      sb.append(password);
      e.printStackTrace();
      return null;
    }

    return con;
  }
}
