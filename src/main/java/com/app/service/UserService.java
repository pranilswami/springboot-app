package com.app.service;

import com.app.entity.User;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.app.service.JWTService;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    public UserService(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public String verifyLogin(LoginDto dto){
        Optional<User> opUser = userRepository.findByUsername(dto.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if(BCrypt.checkpw(dto.getPassword(), user.getPassword())){
                return jwtService.generateToken(user.getUsername());
            }
        }
        return null;
    }
}
