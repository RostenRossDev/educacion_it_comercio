package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Invoice;
import educacionit.comercio.app.repositories.InvoiceRepository;
import educacionit.comercio.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("implementacionMejorada")
public class InvoiceServiceImpl2 implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Override
    public List<Invoice> invoices() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Override
    public Invoice findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
