package com.tychicus.WestLakeHotel.Service;

import com.tychicus.WestLakeHotel.Model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();

    Role createRole(Role theRole);

    void deleteRole(Long id);

    Role findByName(String name);


}
