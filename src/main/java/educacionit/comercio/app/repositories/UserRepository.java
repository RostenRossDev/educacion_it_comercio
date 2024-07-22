package educacionit.comercio.app.repositories;

import educacionit.comercio.app.entities.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);
}
