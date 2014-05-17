package com.quanix.app.integration.server.shiro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : lihaoquan
 */
@Controller("/test")
public class IndexController {

    @RequestMapping(value = "/index")
    public String goHome() {

        System.out.println("======= go home now ");

        return "index.html";
    }
}
