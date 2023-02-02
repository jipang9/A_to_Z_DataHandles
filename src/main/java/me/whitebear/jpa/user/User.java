package me.whitebear.jpa.user;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.whitebear.jpa.userChannel.UserChannel;


//Lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//JPA
@Entity
@Table(name = "users")
public class User {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  // Entity를 선언하는 시점으로부터 모든 필드에는 @Column이 붙는다
  // -> 그럼 언제 사용하냐 ? -> 특정 조건을 적용할 때 사용한다
  @Column(length = 25)
  private String username;

  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  Set<UserChannel> userChannels = new LinkedHashSet<>();

  @Builder
  public User (String username, String password){
    this.username = username;
    this.password = password;
  }

}
