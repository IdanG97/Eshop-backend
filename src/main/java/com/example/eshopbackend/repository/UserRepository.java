package com.example.eshopbackend.repository;

import com.example.eshopbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // התחברות
    User findByEmailAndPassword(String email, String password);

    // שליפת משתמש לפי אימייל (לצורך בדיקה בהרשמה)
    User findByEmail(String email);
}
