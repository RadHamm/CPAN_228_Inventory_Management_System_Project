package NorthPoint.Assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import NorthPoint.Assignment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}