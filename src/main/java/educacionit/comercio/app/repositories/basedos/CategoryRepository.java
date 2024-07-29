package educacionit.comercio.app.repositories.basedos;

import educacionit.comercio.app.entities.basedos.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
