package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.repositories.CategoryRepository;
import educacionit.comercio.app.repositories.ProductRepository;
import educacionit.comercio.app.services.CategoryService;
import educacionit.comercio.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;


    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }
}
