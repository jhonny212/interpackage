package com.interpackage.users.pk;

import com.interpackage.users.model.Permission;
import com.interpackage.users.model.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class PKRolePermission implements Serializable {

    private Permission permission;
    private Role role;
}
