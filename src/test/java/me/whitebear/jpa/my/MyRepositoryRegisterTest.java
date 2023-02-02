package me.whitebear.jpa.my;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(MyRepositoryRegister.class) // 빈 주입
@SpringBootTest
public class MyRepositoryRegisterTest {

  @Autowired
  MyRepository myRepository;

  @Test
  void setMyRepositoryTest(){
    // given
    var newData = "NEW DATA";
    var savedId = myRepository.save(newData);

    // when
    var newDataList = myRepository.find(savedId);


    // then
    System.out.println(newData);
  }
}