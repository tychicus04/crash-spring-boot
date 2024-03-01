package com.tychicus.WestLakeHotel.Repository;

import com.tychicus.WestLakeHotel.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByEmail(String email);

    void deleteByEmail(String email);

    Optional<User> findByEmail(String email);
}
