package educacionit.comercio.app.repositories.basedos;

import educacionit.comercio.app.entities.basedos.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
