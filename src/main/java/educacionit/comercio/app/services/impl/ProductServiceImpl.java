package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.repositories.CategoryRepository;
import educacionit.comercio.app.repositories.ProductRepository;
import educacionit.comercio.app.services.CategoryService;
import educacionit.comercio.app.services.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product save(Product product){
        return repository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
      //  throw  new RuntimeException("Error al buscar los productos - Prueba", new Exception());
        return repository.findAll();
    }

    @Override
    public List<Product> productFilterd(Long categoryId ) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("El metdo findById se ejecuto : " + date);
        List<Product> products = new ArrayList<>();
        try{
                products = findAllProducts();
        }catch (RuntimeException e){
            //e.printStackTrace();
        }
        List<Product> productsFiltered = new ArrayList<>();
        if(categoryId != null && categoryId > 0){
            products.forEach(item -> {
                if(item.getCategory().getId().equals(categoryId)){
                    productsFiltered.add(item);
                }
            });
        }else{
            productsFiltered.addAll(products);
        }
        return productsFiltered;
    }

    @Override
    public Product findById(Long id){
        return repository.findById(id).orElse(null);
    }
}
