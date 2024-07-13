package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.repositories.CategoryRepository;
import educacionit.comercio.app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAllCategories(){
        return repository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
