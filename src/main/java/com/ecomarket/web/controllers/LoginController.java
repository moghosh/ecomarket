package com.ecomarket.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by moghosh on 1/9/18.
 */

@Controller
public class LoginController {

    /**
     * The Login view name
     */
    public static final String LOGIN_VIEW_NAME = "user/login";

    @RequestMapping("/login")
    public String login(){
        return LOGIN_VIEW_NAME;
    }
}
