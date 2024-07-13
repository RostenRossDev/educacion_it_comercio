package educacionit.comercio.app.services;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.entities.Product;

import java.util.List;

public interface ProductService {
     List<Product> findAllProducts();

}
