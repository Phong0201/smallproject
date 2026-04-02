package com.smallproject.databases;

import com.smallproject.modules.users.entitys.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (istableEmpty()) {
            String passwordEncode = passwordEncoder.encode("password");
            User user = new User();
            user.setAddress("djashfgs");
            user.setName("phong");
            user.setEmail("hfdaf@gmail.com");
            user.setPassword(passwordEncode);
            user.setPhone("12546145127");
            user.setUserCatalogueId(1l);
            entityManager.persist(user);
            System.out.println("password: " + passwordEncode);
        }
    }

    private boolean istableEmpty(){
        Long count = (Long) entityManager.createQuery("SELECT COUNT(id) FROM User ").getSingleResult();
        return count == 0;
    }
}
