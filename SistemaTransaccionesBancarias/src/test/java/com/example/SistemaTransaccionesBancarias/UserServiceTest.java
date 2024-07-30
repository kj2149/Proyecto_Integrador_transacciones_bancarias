package com.example.SistemaTransaccionesBancarias;

import com.example.SistemaTransaccionesBancarias.model.User;
import com.example.SistemaTransaccionesBancarias.repository.UserRepository;
import com.example.SistemaTransaccionesBancarias.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {

        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void createUser_whenUserIsValid() {

        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        verify(userRepository).save(user);
    }

    @Test
    public void getUsers_whenCalled() {

        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(expectedUsers);

        List<User> users = userService.getUsers();

        assertEquals(expectedUsers, users);
        verify(userRepository).findAll();
    }
}
