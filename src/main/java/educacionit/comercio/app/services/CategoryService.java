package educacionit.comercio.app.services;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.entities.Invoice;

import java.util.List;

public interface CategoryService {
     List<Category> findAllCategories();

     Category findById(Long id);
}
