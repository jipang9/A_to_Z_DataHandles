package me.whitebear.jpa.mention;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.whitebear.jpa.common.Timestamp;
import me.whitebear.jpa.thread.Thread;
import me.whitebear.jpa.user.User;

// lombok
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

// jpa
@Entity
public class Mention extends Timestamp {

  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @EmbeddedId
  private MentionId id = new MentionId();
  /**
   * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
   */
  @ManyToOne
  @MapsId("userId")
  private User user;

  @ManyToOne
  @MapsId("threadId")
  private Thread thread;

  /**
   * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
   */
  @Builder
  public Mention(User user, Thread thread) {
    this.user = user;
    this.thread = thread;
  }

  public Mention(MentionId id, User user, Thread thread) {
    this.id = id;
    this.user = user;
    this.thread = thread;
  }
  /**
   * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
   */

  /**
   * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
   */

  /**
   * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
   */

}