package com.example.service;

import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserByIdOrThrow() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserByIdOrThrow(1L);
        });
    }

}
