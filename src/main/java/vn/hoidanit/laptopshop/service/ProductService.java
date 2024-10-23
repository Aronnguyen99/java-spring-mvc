package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.Products;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    public final ProductRepository productRepository;
    public final UploadFileService uploadFileService;

    public ProductService(ProductRepository productRepository,
            UploadFileService uploadFileService) {
        this.productRepository = productRepository;
        this.uploadFileService = uploadFileService;
    }

    public Products handleSaveProducts(Products products) {
        return this.productRepository.save(products);
    }

    public String handleUploadFile(MultipartFile file, String target) {
        return this.uploadFileService.handleUploadFile(file, target);
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getIDproducts(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

}
