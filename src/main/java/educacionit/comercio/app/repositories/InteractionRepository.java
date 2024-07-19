package educacionit.comercio.app.repositories;

import educacionit.comercio.app.entities.Invoice;
import educacionit.comercio.app.entities.RecordInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<RecordInteraction, Long> {

}
