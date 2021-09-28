package com.ti.sistemareservaturnos.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("password");
        String password2 = passwordEncoder.encode("password2");

        userRepository.save(new AppUser("Admin", "admin", "admin@gmail.com", password,
                AppUserRole.ADMIN));
        userRepository.save(new AppUser("User", "user", "user@gmail.com", password2,
                AppUserRole.USER));
    }
}
