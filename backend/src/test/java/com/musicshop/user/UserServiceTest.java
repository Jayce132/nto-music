package com.musicshop.user;

import com.musicshop.model.user.User;
import com.musicshop.repository.user.UserRepository;
import com.musicshop.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getUser_whenUserExists() {
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        Optional<User> result = userService.getUser(userId);

        assertTrue(result.isPresent());
        assertEquals(mockUser, result.get());
        verify(userRepository).findById(userId);
    }

    @Test
    void getUser_whenUserDoesNotExist() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUser(userId);

        assertFalse(result.isPresent());
        verify(userRepository).findById(userId);
    }

    @Test
    void updateUser_whenUserExists() {
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setFirstName("Original");

        User updatedDetails = new User();
        updatedDetails.setFirstName("Updated");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updatedUser = userService.updateUser(userId, updatedDetails);

        assertEquals("Updated", updatedUser.getFirstName());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUser_whenUserDoesNotExist() {
        Long userId = 1L;
        User updatedDetails = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUser(userId, updatedDetails));
        verify(userRepository, never()).save(any(User.class));
    }
}
