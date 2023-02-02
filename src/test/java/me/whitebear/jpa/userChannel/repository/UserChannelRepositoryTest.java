package me.whitebear.jpa.userChannel.repository;

import static org.junit.jupiter.api.Assertions.*;

import me.whitebear.jpa.channel.Channel;
import me.whitebear.jpa.channel.repository.ChannelRepository;
import me.whitebear.jpa.user.User;
import me.whitebear.jpa.user.repository.UserRepository;
import me.whitebear.jpa.userChannel.UserChannel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserChannelRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ChannelRepository channelRepository;


  @Test
  void userJoinChannelTest() {
    // given
    var newChannel = Channel.builder().name("new-group").build();
    var newUser = User.builder().username("new_user").password("new-pass").build();
    var newUserChannel = newChannel.joinUser(newUser);

    // when
    var savedChannel = channelRepository.insertChannel(newChannel);
    var savedUser = userRepository.insertUser(newUser);

    // then
    var foundChannel = channelRepository.selectChannel(savedChannel.getId());
//    assert foundChannel.getId().equals(savedChannel.getId());

    assert foundChannel.getUserChannels().stream()
        .map(UserChannel::getChannel)
        .map(Channel::getName)
        .anyMatch(name->name.equals(newChannel.getName()));
  }

  @Test
  void userJoinChannelWithCascadeTest() {
    // given
    var newChannel = Channel.builder().name("new-group").build();
    var newUser = User.builder().username("new_user").password("new-pass").build();
    newChannel.joinUser(newUser);

    // when
    var savedChannel = channelRepository.insertChannel(newChannel);
    var savedUser = userRepository.insertUser(newUser);


    // then
    var foundChannel = channelRepository.selectChannel(savedChannel.getId());
//    assert foundChannel.getId().equals(savedChannel.getId());
    assert foundChannel.getUserChannels().stream()
        .map(UserChannel::getChannel)
        .map(Channel::getName)
        .anyMatch(name->name.equals(newChannel.getName()));
  }


}