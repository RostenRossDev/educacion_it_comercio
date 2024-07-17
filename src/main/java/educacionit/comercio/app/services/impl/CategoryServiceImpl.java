package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Category;
import educacionit.comercio.app.repositories.CategoryRepository;
import educacionit.comercio.app.services.CategoryService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAllCategories(){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("El metdo findAllCategories se ejecuto : " + date);
        return repository.findAll();
    }

    @Override
    public Category findById(Long id) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("El metdo findById se ejecuto : " + date);
        return repository.findById(id).orElse(null);
    }
}
