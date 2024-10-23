package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Products;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class ProductController {
    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create Products
    @GetMapping("/admin/product/create")
    public String createItemsPage(Model model) {
        model.addAttribute("newProduct", new Products());
        return "/admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String creatingItems(
            Model model, @ModelAttribute("newProduct") @Valid Products products,
            BindingResult newProductBindingResult,
            @RequestParam("imgProduct") MultipartFile file

    ) {
        // validate
        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        // Save Image
        String linkProductImage = this.productService.handleUploadFile(file, "imgProduct");
        products.setImage(linkProductImage);
        // Save database
        this.productService.handleSaveProducts(products);
        return "redirect:/admin/product";
    }

    // Read ==> show Products
    @GetMapping("/admin/product")
    public String Tableproduct(Model model) {
        List<Products> newItem = this.productService.getAllProducts();
        model.addAttribute("newItem", newItem);
        return "/admin/product/show";
    }

    // View Product
    @GetMapping("/admin/product/{id}")
    public String viewPage(Model model, @PathVariable long id) {
        Products viewProduct = this.productService.productRepository.findById(id);
        model.addAttribute("productInfo", viewProduct);
        return "/admin/product/view";

    }

    // Update Products
    @GetMapping("/admin/product/update/{id}")
    public String updateProductsPage(Model model, @PathVariable long id) {
        Products productUpdate = this.productService.getIDproducts(id);
        model.addAttribute("productUpdate", productUpdate);
        return "/admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(Model model,
            @ModelAttribute("productUpdate") @Valid Products productUpdate,
            BindingResult productUpdateBindingresult,
            @RequestParam("imgProduct") MultipartFile file) {
        if (productUpdateBindingresult.hasErrors()) {
            return "/admin/product/update";
        }
        Products currentProduct = this.productService.getIDproducts(productUpdate.getId());
        if (currentProduct != null) {
            if (!file.isEmpty()) {
                String img = this.productService.handleUploadFile(file, "imgProduct");
                currentProduct.setImage(img);
            }

            currentProduct.setName(productUpdate.getName());
            currentProduct.setPrice(productUpdate.getPrice());
            currentProduct.setDetailDesc(productUpdate.getDetailDesc());
            currentProduct.setShortDesc(productUpdate.getShortDesc());
            currentProduct.setQuantity(productUpdate.getQuantity());
            currentProduct.setFactory(productUpdate.getFactory());
            currentProduct.setTarget(productUpdate.getTarget());
            // this.productService.handleSaveProducts(currentProduct);
        }
        return "redirect:/admin/product";
    }

    // Delete Products
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProductsPage(Model model, @PathVariable long id) {
        Products productDeleted = this.productService.getIDproducts(id);
        model.addAttribute("productDeleted", productDeleted);
        return "/admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String deleteProducts(Model model,
            @ModelAttribute("productDeleted") Products productDeleted) {
        this.productService.deleteProduct(productDeleted.getId());
        return "redirect:/admin/product";
    }
}
