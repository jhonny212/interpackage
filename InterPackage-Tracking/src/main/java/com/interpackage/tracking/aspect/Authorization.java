package com.interpackage.tracking.aspect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Authorization {

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(requiredPermission)")
    public Object checkPermissions(ProceedingJoinPoint joinPoint, RequiredPer requiredPermission) throws Throwable {
        String permissionsHeader = request.getHeader("permissions");
        List<String> permissions = Arrays.asList(permissionsHeader.split(","));
        List<String> requiredPermissions = Arrays.asList(requiredPermission.value());
        if (permissions.containsAll(requiredPermissions)) {
            return joinPoint.proceed();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new HashMap<String, Object>() {{
                        put("mensaje", "No tienes permisos suficientes para realizar esta acción.");
                        put("estado", HttpStatus.FORBIDDEN.value());
                    }}
            );
        }
    }
}
