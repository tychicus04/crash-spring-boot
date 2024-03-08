package com.tychicus.WestLakeHotel.Service;

import com.tychicus.WestLakeHotel.Model.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);

    List<User> getUsers();

    void deleteUser(String email);

    User getUser(String email);

//    User updateUser(Long userId);
}
