package educacionit.comercio.app.controllers;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.services.CategoryService;
import educacionit.comercio.app.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/productos")
public class ProductsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProducts(@RequestParam(value = "categoryId", required = false) Long categoryId, Model model) {
        List<Category> categories = categoryService.findAllCategories();
        List<Product> products = productService.findAllProducts();

        if(categoryId != null && categoryId > 0){
            List<Product> productsFiltered = new ArrayList<>();
            products.forEach(item -> {
                if(item.getCategory().getId().equals(categoryId)){
                    productsFiltered.add(item);
                }
            });
            products = productsFiltered;
        }
        products.forEach(item -> log.info(item.toString()));
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryId", categoryId);

        return "productos";
    }
}
