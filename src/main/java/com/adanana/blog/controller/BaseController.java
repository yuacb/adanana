package com.adanana.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
//@CrossOrigin(origins = {"http://127.0.0.1:80801", "null"})
@CrossOrigin(value = "http://127.0.0.1:8081", maxAge = 1800, allowedHeaders ="*",allowCredentials="true")
//@CrossOrigin(allowCredentials = "true")
@Controller
@RequestMapping(value ="/app")
public class BaseController {
}
