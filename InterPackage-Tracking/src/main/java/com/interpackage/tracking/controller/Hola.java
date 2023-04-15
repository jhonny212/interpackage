package com.interpackage.tracking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping ("/api/tracking/v1")
public class Hola {

    @GetMapping("/hola/{name}")
    public ResponseEntity<Object> getHola(@PathVariable String name) {

        return ResponseEntity.ok(
                new HashMap<String, Object>() {{
                    put("mensaje", "Hola " + name);
                    put("estado", HttpStatus.OK.value());
                }}
        );
    }
    @GetMapping("/adios/{name}")
    public ResponseEntity<String> getAdios(@PathVariable String name) {
        return ResponseEntity.ok("Adios " + name);
    }
}
