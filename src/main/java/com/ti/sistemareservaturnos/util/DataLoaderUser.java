package com.ti.sistemareservaturnos.util;

import com.ti.sistemareservaturnos.model.Rol;
import com.ti.sistemareservaturnos.model.User;
import com.ti.sistemareservaturnos.repository.IRolRepository;
import com.ti.sistemareservaturnos.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoaderUser implements ApplicationRunner {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IRolRepository rolRepository;

    @Override
    public void run(ApplicationArguments args) {

        Rol admin = rolRepository.save( new Rol(null, "ROLE_ADMIN"));
        Rol user = rolRepository.save( new Rol(null,"ROLE_USER"));


        userRepository.save(new User(null, "admin", "123", Set.of(admin)));
        userRepository.save(new User(null, "user", "456", Set.of(user)));


    }
}
