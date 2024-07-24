package educacionit.comercio.app.controllers;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.services.CategoryService;
import educacionit.comercio.app.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/productos")
@SessionAttributes("productos")
public class ProductsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProducts(@RequestParam(value = "categoryId", required = false) Long categoryId, Model model) {
        List<Category> categories = categoryService.findAllCategories();
        List<Product> products = productService.productFilterd(categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryId", categoryId);
        return "productos";
    }

    @GetMapping("/form/{productoId}")//ruta para mostrar el formulario
    public String editar(@PathVariable(value="productoId") Long productoId, Model model, RedirectAttributes flash) {
        Product producto=productService.findById(productoId);
        if(producto==null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        model.addAttribute("product",producto);//pasamos la factura al modelo
        model.addAttribute("titulo", "Editar Producto");//pasamos un titulo al modelo
        return "form";
    }

    @PostMapping("/form")
    public String guardar(Product product,
                          BindingResult result,
                          Model model,
                          RedirectAttributes flash) {
        log.info("producto en form: " + product.toString());
        if(result.hasErrors()) {
            model.addAttribute("titulo", "Editar producto");
            return"form";
        }
        if(product.getName().isBlank() || product.getName().isEmpty()) {
            model.addAttribute("titulo", "Editar Producto");
            model.addAttribute("error", "El producto NO puede tener un nombre vacio!");
            return"form";
        }
        if(product.getPrice() == null || product.getPrice() < 0) {
            model.addAttribute("titulo", "Editar Producto");
            model.addAttribute("error", "El producto NO puede tener un precio menor a 1!");
            return"form";
        }
        Product updProduct = productService.findById(product.getId());
        updProduct.setName(product.getName());
        updProduct.setPrice(product.getPrice());
        productService.save(updProduct);
        flash.addFlashAttribute("success", "Product editado con exito!");
        return "redirect:/productos";
    }
}
