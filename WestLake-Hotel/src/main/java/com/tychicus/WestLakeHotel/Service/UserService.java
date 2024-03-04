package com.tychicus.WestLakeHotel.Service;

import com.tychicus.WestLakeHotel.Exception.RoleAlreadyExistException;
import com.tychicus.WestLakeHotel.Exception.UserAlreadyExistsException;
import com.tychicus.WestLakeHotel.Model.Role;
import com.tychicus.WestLakeHotel.Model.User;
import com.tychicus.WestLakeHotel.Repository.RoleRepository;
import com.tychicus.WestLakeHotel.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");

        if (roleOptional.isPresent()) {
            Role userRole = roleOptional.get();
            user.setRoles(Collections.singletonList(userRole));
        } else {
            throw new RoleAlreadyExistException("no role");
            // Handle the case where "ROLE_USER" is not found
            // Maybe throw an exception, provide a default role, or log a message
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if(theUser != null) {
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
