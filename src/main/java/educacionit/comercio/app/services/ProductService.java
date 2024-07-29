package educacionit.comercio.app.services;

import educacionit.comercio.app.entities.basedos.Product;

import java.util.List;

public interface ProductService {
     List<Product> findAllProducts();

     List<Product> productFilterd(Long categoryId);
     Product findById(Long id);
     Product save(Product product);
}
