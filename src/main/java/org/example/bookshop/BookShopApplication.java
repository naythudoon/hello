package org.example.bookshop;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.RoleDao;
import org.example.bookshop.dao.UserDao;
import org.example.bookshop.security.Role;
import org.example.bookshop.security.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Bean @Transactional @Profile("dev")
    public ApplicationRunner runner(){
        return r->{
            Role userRole = new Role();
            userRole.setRoleName("ROLE_USER");

            Role adminRole = new Role();
            adminRole.setRoleName("ROLE_ADMIN");

            User user1 = new User();
            user1.setUsername("john");
            user1.setEmail("john@gmail.com");
            user1.setPhoneNumber("123456789");
            user1.setPassword(passwordEncoder.encode("12345"));
            user1.addRole(roleDao.save(adminRole));
            userDao.save(user1);

            User user2 = new User();
            user2.setUsername("mary");
            user2.setEmail("mary@example.com");
            user2.setPhoneNumber("123456789");
            user2.setPassword(passwordEncoder.encode("12345"));
            user2.addRole(roleDao.save(userRole));
            userDao.save(user2);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
