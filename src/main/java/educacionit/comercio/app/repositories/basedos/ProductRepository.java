package educacionit.comercio.app.repositories.basedos;

import educacionit.comercio.app.entities.basedos.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
