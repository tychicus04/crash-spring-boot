package com.tychicus.WestLakeHotel.Controller;

import com.tychicus.WestLakeHotel.Model.BookedRoom;
import com.tychicus.WestLakeHotel.Model.Role;
import com.tychicus.WestLakeHotel.Model.Room;
import com.tychicus.WestLakeHotel.Model.User;
import com.tychicus.WestLakeHotel.Response.BookingResponse;
import com.tychicus.WestLakeHotel.Response.RoleResponse;
import com.tychicus.WestLakeHotel.Response.RoomResponse;
import com.tychicus.WestLakeHotel.Response.UserResponse;
import com.tychicus.WestLakeHotel.Service.IRoleService;
import com.tychicus.WestLakeHotel.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final IRoleService roleService;

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User> users = userService.getUsers();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = getUserResponse(user);
            userResponses.add(userResponse);
        }
        return ResponseEntity.ok(userResponses);
//        return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/{email}")
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable("email") String email) {
        try {
            User theUser = userService.getUser(email);
            return ResponseEntity.ok(theUser);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }

    @DeleteMapping("/delete/{userId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #email == principal.username)")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String email) {
        try {
            userService.deleteUser(email);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }

//    @PutMapping("/update/{userId}")
//    public ResponseEntity<UserResponse> updateRoom(
//            @PathVariable Long userId,
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) RoleResponse roles, ) throws IOException, SQLException {
//
//        User theUser = userService.updateUser(userId);
//        theRoom.setPhoto(photoBlob);
//        RoomResponse roomResponse = getRoomResponse(theRoom);
//        return ResponseEntity.ok(roomResponse);
//    }

    private UserResponse getUserResponse(User user) {
        // Assuming that getRoles() returns a collection of roles
        Collection<Role> roles = user.getRoles();

        // Check if the collection is not empty before accessing the first role
        if (!roles.isEmpty()) {
            Role theRole = roles.iterator().next(); // Retrieve the first role
            RoleResponse role = new RoleResponse(theRole.getId(), theRole.getName());
            return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), role);
        } else {
            // Handle the case when the user has no roles
            // You might want to provide a default role or throw an exception
            // Depending on your application logic
            return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), null);
        }
    }

}

