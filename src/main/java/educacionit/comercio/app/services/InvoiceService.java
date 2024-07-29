package educacionit.comercio.app.services;

import educacionit.comercio.app.entities.basedos.Invoice;

import java.util.List;

public interface InvoiceService {

    List<Invoice> invoices();

    void deleteById(Long id);

    Invoice findById(Long id);
}
