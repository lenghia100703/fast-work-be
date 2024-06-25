package com.fastwork.controllers.impls;

import com.fastwork.controllers.AuthController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Override
    public String login() {
        return "Login";
    }
}
