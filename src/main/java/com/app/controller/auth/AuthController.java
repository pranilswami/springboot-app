package com.app.controller.auth;

import com.app.entity.User;
import com.app.payload.auth.JWTTokenDto;
import com.app.payload.auth.LoginDto;
import com.app.repository.UserRepository;
import com.app.service.auth.JWTService;
import com.app.service.auth.OTPService;
import com.app.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final OTPService otpService;
    private final JWTService jwtService;

    @Autowired
    public AuthController(UserRepository userRepository, UserService userService, OTPService otpService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user){
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmailId = userRepository.findByEmailId(user.getEmailId());
        if(opEmailId.isPresent()){
            return new ResponseEntity<>("Email Id already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String hashpw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    @PostMapping("/content-manager-signup")
    public ResponseEntity<?> createContentManagerAccount(@RequestBody User user){
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmailId = userRepository.findByEmailId(user.getEmailId());
        if(opEmailId.isPresent()){
            return new ResponseEntity<>("Email Id already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String hashpw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_CONTENT_MANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    @PostMapping("/blog-manager-signup")
    public ResponseEntity<?> createBlogManagerAccount(@RequestBody User user){
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmailId = userRepository.findByEmailId(user.getEmailId());
        if(opEmailId.isPresent()){
            return new ResponseEntity<>("Email Id already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String hashpw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashpw);
        user.setRole("ROLE_BLOG_MANAGER");
        userRepository.save(user);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto dto){
        System.out.println("In the signin method of auth controller");
        String jwtToken = userService.verifyLogin(dto);
        if(jwtToken!=null){
            JWTTokenDto tokenDto = new JWTTokenDto();
            tokenDto.setToken(jwtToken);
            tokenDto.setTokenType("JWT");
            return new ResponseEntity<>(tokenDto,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid token ",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login-otp")
    public String generateOtp(@RequestParam String mobile){
        try {
            return otpService.generateOtp(mobile);
        }
        catch (RuntimeException e){
            return e.getMessage();
        }
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestParam String mobile,@RequestParam String otp){
        boolean status = otpService.validateOtp(mobile,otp);
        if(status){
            System.out.println("Inside Status of /validate-otp");
            Optional<User> opUser = userRepository.findByMobile(mobile);
            if(opUser.isPresent()){
                String jwtToken = jwtService.generateToken(opUser.get().getUsername());
                JWTTokenDto jwtTokenDto = new JWTTokenDto();
                jwtTokenDto.setToken(jwtToken);
                jwtTokenDto.setTokenType("JWT");
                return new ResponseEntity<>(jwtTokenDto,HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("OTP is Invalid",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/hello")
    public String getMessage(){
        return "Hello, World!";
    }
}