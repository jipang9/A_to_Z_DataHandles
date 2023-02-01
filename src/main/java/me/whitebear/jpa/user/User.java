package me.whitebear.jpa.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  // Entity를 선언하는 시점으로부터 모든 필드에는 @Column이 붙는다
  // -> 그럼 언제 사용하냐 ? -> 특정 조건을 적용할 때 사용한다
  @Column
  private String username;

  private String password;
}
