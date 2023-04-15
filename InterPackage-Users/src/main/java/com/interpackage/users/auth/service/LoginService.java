package com.interpackage.users.auth.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.interpackage.users.auth.model.AuthToken;
import com.interpackage.users.auth.util.JwtTokenUtil;
import com.interpackage.users.model.RolePermission;
import com.interpackage.users.service.UserService;

@Service
public class LoginService {

    @Autowired
    JwtTokenUtil utilToken;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private UserService userService;

    public AuthToken doLogin(String user, String password) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var userDB = userService.getUserByName(user);
        if(userDB==null){
            return null;
        }

        Boolean matches = passwordEncoder.matches(password, userDB.getPassword());
        if (matches) {
            Set<String> roles = new HashSet<>();
            roles.add(userDB.getRole().getName());

            Set<String> permissions = new HashSet<>();
            for (RolePermission roleP : userDB.getRole().getRolePermissions()) {
                if (roleP.getEdition()) {
                    permissions.add(roleP.getPermission().getName()+"::edit");
                }
                if (roleP.getElimination()) {
                    permissions.add(roleP.getPermission().getName()+"::delete");
                }
                if (roleP.getExport()) {
                    permissions.add(roleP.getPermission().getName()+"::export");
                }
                if (roleP.getReading()) {
                    permissions.add(roleP.getPermission().getName()+"::read");
                }
                if (roleP.getWriting()) {
                    permissions.add(roleP.getPermission().getName()+"::create");
                }
            }

            Map<String, Object> claims = new HashMap<>();
            claims.put("permissions", permissions);
            claims.put("roles", roles);
            final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(userDB.getName());
            return new AuthToken(utilToken.generateToken(userDetails, claims));
        } else {
            return null;
        }

    }

}
