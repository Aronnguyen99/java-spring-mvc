package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello Dang Huy. Welcome to SpringBoot !";
    }

    @GetMapping("/user")
    public String userPage() {
        return "This is User's page";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "This is Admin Page";
    }
}
