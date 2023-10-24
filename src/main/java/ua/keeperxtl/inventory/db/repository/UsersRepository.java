package ua.keeperxtl.inventory.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.keeperxtl.inventory.db.models.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
