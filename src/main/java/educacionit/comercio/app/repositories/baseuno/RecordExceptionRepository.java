package educacionit.comercio.app.repositories.baseuno;

import educacionit.comercio.app.entities.baseuno.RecordException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordExceptionRepository extends JpaRepository<RecordException, Long> {

}
