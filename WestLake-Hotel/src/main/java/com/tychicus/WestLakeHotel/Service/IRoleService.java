package com.tychicus.WestLakeHotel.Service;

import com.tychicus.WestLakeHotel.Model.Role;
import com.tychicus.WestLakeHotel.Model.Room;
import com.tychicus.WestLakeHotel.Model.User;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> getRoles();

    Role createRole(Role theRole);

    void deleteRole(Long id);

    Role findByName(String name);

    Optional<Role> getRoleById(Long roleId);

    User removeUserFromRole(Long userId, Long roleId);

    User assignRoleToUser(Long userId, Long roleId);

    Role removeAllUserFromRoleId(Long roleId);
}
