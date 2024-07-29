package educacionit.comercio.app.repositories.baseuno;

import educacionit.comercio.app.entities.baseuno.RecordInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<RecordInteraction, Long> {

}
