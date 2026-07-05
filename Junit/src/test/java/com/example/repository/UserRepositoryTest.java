package com.example.repository;

import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        User user = new User(3L, "Bob");
        userRepository.save(user);
        List<User> result = userRepository.findByName("Bob");
        assertEquals(1, result.size());
        assertEquals("Bob", result.get(0).getName());
    }

}
