package com.example.SistemaTransaccionesBancarias.service;

import com.example.SistemaTransaccionesBancarias.model.User;
import com.example.SistemaTransaccionesBancarias.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public User crearUsuario(User usuario) {
        return UserRepository.save(usuario);
    }

    public List<User> obtenerUsuarios() {
        return UserRepository.findAll();
    }
}