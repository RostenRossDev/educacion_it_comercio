package educacionit.comercio.app.repositories;

import educacionit.comercio.app.entities.RecordException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordExceptionRepository extends JpaRepository<RecordException, Long> {

}
