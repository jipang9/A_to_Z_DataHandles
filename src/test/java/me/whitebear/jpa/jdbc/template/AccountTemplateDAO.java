package me.whitebear.jpa.jdbc.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import me.whitebear.jpa.jdbc.vo.AccountVO;

public class AccountTemplateDAO {

  private final JdbcTemplate jdbcTemplate;

  private final String ACCOUNT_INSERT = "INSERT INTO account(ID, USERNAME, PASSWORD) "
      + "VALUES((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), ?, ?)";

  private final String ACCOUNT_SELECT = "SELECT * FROM account WHERE ID = ?";

  public AccountTemplateDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  //CRUD 기능의 메소드 구현
  //계정 등록
  public Integer insertAccount(AccountVO vo){

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(con -> {
      var ps = con.prepareStatement(ACCOUNT_INSERT, new String[]{"id"});
      ps.setString(1, vo.getUsername());
      ps.setString(2, vo.getPassword());
      return ps;
    }, keyHolder);

    return (Integer) keyHolder.getKey();
  }

  public AccountVO selectAccount(Integer id){
    return jdbcTemplate.queryForObject(ACCOUNT_SELECT,new AccountRowMapper(), id);
  }

}

