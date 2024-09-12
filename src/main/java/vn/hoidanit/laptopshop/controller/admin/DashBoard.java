package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoard {
    @GetMapping("/admin")
    public String getDashBoard() {
        return "/admin/dashboard/show";
    }
}
