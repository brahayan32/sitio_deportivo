package com.proyecto.citiodeportivo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PasswordEncoder encoder;

    @GetMapping("/encode")
    public String encode(@RequestParam String raw) {
        return encoder.encode(raw);
    }
}
