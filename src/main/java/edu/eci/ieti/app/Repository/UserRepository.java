package edu.eci.ieti.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.ieti.app.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
