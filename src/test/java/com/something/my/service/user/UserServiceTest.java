package com.something.my.service.user;

import com.something.my.user.domain.User;
import com.something.my.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    private static final Long ID = 1L;
    private static final String USER_NAME = "ndgndg91";
    private static final String EMAIL = "ndgndg91@gmail.com";
    private static final String NAME = "남동길";

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("USER_생성_테스트")
    void saveTest() {
        //given
        User newUser = User.builder()
                .id(ID)
                .userName(USER_NAME)
                .email(EMAIL)
                .name(NAME)
                .build();

        //when
        User savedUser = userRepository.save(newUser);

        //then
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals(newUser.getUserName(), savedUser.getUserName());
        Assertions.assertEquals(newUser.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(newUser.getName(), savedUser.getName());
     }

     @Test
     @Order(2)
     @DisplayName("USER_조회_테스트")
     void findByIdTest() {
         //given
         User given = User.builder()
                 .id(ID)
                 .userName(USER_NAME)
                 .email(EMAIL)
                 .name(NAME)
                 .build();

         //when
         User user = userRepository.findById(ID).orElseThrow();

         //then
         Assertions.assertEquals(given, user);
      }
}
