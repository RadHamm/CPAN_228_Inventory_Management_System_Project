package NorthPoint.Assignment.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import NorthPoint.Assignment.model.User;
import NorthPoint.Assignment.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("Admin user created: username='admin', password='admin123'");

            User staff = new User();
            staff.setUsername("staff");
            staff.setPassword(passwordEncoder.encode("staff123"));
            staff.setRole("STAFF");
            userRepository.save(staff);
            System.out.println("Staff user created: username='staff', password='staff123'");

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");
            userRepository.save(user);
            System.out.println("Regular user created: username='user', password='user123'");
        } else {
            System.out.println("Users already exist in the database. Skipping data loading.");
        }
    }
}
