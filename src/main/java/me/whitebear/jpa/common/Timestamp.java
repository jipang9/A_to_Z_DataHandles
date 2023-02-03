package me.whitebear.jpa.common;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamp {

  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public void updateCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  public void updateModifiedAt() {
    this.modifiedAt = LocalDateTime.now();
  }
}