package me.whitebear.jpa.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.whitebear.jpa.jdbc.vo.AccountVO;

public class AccountDAO {

  private Connection conn = null;

  private PreparedStatement stmt  = null;

  private ResultSet rs = null;

  private final static String url = "jdbc:postgresql://localhost:5432/messenger";
  private final static String username = "jipang";
  private final static String password = "pass";

  //SQL 관련 명령어
  private final String ACCOUNT_INSERT = "INSERT INTO account(ID, USERNAME, PASSWORD) "
      + "VALUES((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), ?, ?)";

  private final String ACCOUNT_SELECT = "SELECT * FROM account WHERE ID = ?";


  // CRUD 기능 메소드드
 public Integer insertAccount(AccountVO vo){
    var id = -1;
    try{
      String[] returnId = {"id"};
      conn = DriverManager.getConnection(url, username, password);
      stmt = conn.prepareStatement(ACCOUNT_INSERT,returnId);
      stmt.setString(1, vo.getUsername());
      stmt.setString(2, vo.getPassword());
      stmt.executeUpdate();

      try(ResultSet rs = stmt.getGeneratedKeys()){
        if(rs.next()) {
          id = rs.getInt(1);
        }
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return id;
  }

  public AccountVO selectAccount(Integer id){
   AccountVO vo = null;
    try{
      conn = DriverManager.getConnection(url, username, password);
      stmt = conn.prepareStatement(ACCOUNT_SELECT );
      stmt.setInt(1, id);
      ResultSet resultSet = stmt.executeQuery();
      if(resultSet.next()){
        vo = new AccountVO();
        vo.setId(resultSet.getInt("ID"));
        vo.setUsername(resultSet.getString("USERNAME"));
        vo.setPassword(resultSet.getString("PASSWORD"));
      }
    }catch (SQLException e){
      e.printStackTrace();
    }
    return vo;
  }

}