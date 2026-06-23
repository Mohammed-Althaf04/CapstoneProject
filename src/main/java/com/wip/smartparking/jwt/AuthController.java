package com.wip.smartparking.jwt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * REST Controller exposing API endpoints for CRUD and business operations on Auth resources.
 *
 * @author althaf
 */

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
