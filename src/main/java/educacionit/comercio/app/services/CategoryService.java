package educacionit.comercio.app.services;

import educacionit.comercio.app.entities.basedos.Category;

import java.util.List;

public interface CategoryService {
     List<Category> findAllCategories();

     Category findById(Long id);
}
