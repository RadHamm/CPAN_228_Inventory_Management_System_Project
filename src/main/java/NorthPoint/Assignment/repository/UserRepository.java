package NorthPoint.Assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import NorthPoint.Assignment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
}
