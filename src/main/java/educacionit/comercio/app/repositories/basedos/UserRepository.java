package educacionit.comercio.app.repositories.basedos;

import educacionit.comercio.app.entities.basedos.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);
}
