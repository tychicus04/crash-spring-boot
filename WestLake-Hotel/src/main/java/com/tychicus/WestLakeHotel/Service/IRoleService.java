package com.tychicus.WestLakeHotel.Service;

import com.tychicus.WestLakeHotel.Model.Role;
import com.tychicus.WestLakeHotel.Model.User;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();

    Role createRole(Role theRole);

    void deleteRole(Long id);

    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId);

    User assignRoleToUser(Long userId, Long roleId);

    Role removeAllUserFromRoleId(Long roleId);
}
