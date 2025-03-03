package com.app.service.auth;

import com.app.entity.User;
import com.app.payload.auth.OTPDetails;
import com.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private final Map<String, OTPDetails> otpStorage = new HashMap<>();
    private static final int OTP_EXPIRY_TIME = 5;

    private final UserRepository userRepository;

    @Autowired
    public OTPService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateOtp(String mobileNumber){
        Optional<User> opUser = userRepository.findByMobile(mobileNumber);
        if(opUser.isPresent()){
            String otp = generateRandomOtp();
            OTPDetails otpDetails = new OTPDetails(otp, System.currentTimeMillis());
            otpStorage.put(mobileNumber,otpDetails);
            return otp;
        }else {
            throw new RuntimeException("Mobile number not found");
        }

    }
    //To generate 6 digit otp
    private String generateRandomOtp(){
        return String.format("%06d",new Random().nextInt(999999));
    }

    public boolean validateOtp(String mobile,String otp){
        OTPDetails otpDetails = otpStorage.get(mobile);
        if(otpDetails==null){
            return false;
        }
        long currentTime = System.currentTimeMillis();
        long timeStamp = otpDetails.getTimestamp();
        long timeDifference = TimeUnit.MILLISECONDS.toMinutes(currentTime-timeStamp);// difference between current time and time when otp was generated
        if(timeDifference>OTP_EXPIRY_TIME){
            otpStorage.remove(mobile); //remove expired otp
            return false; //otp expired
        }
        return otpDetails.getOtp().equals(otp);
    }
}
