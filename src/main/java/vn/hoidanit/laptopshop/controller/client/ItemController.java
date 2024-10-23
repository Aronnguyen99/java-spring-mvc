package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import vn.hoidanit.laptopshop.domain.Products;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class ItemController {
    public final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        List<Products> listProducts = this.productService.getAllProducts();
        Products product = this.productService.getIDproducts(id);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("product", product);
        return "client/product/detail";
    }
}
