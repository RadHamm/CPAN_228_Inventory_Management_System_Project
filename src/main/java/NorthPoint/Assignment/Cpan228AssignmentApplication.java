package NorthPoint.Assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = { 
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class, 
    org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
public class Cpan228AssignmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(Cpan228AssignmentApplication.class, args);
    }
}