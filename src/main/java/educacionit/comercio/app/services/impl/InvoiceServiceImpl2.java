package educacionit.comercio.app.services.impl;

import educacionit.comercio.app.entities.Invoice;
import educacionit.comercio.app.repositories.InvoiceRepository;
import educacionit.comercio.app.services.InvoiceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
@Service("implementacionMejorada")
public class InvoiceServiceImpl2 implements InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Override
    public List<Invoice> invoices() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("El metdo invoices se ejecuto : " + date);
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("El metdo deleteById se ejecuto : " + date);
        repository.deleteById(id);
    }

    @Override
    public Invoice findById(Long id) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("El metdo findById se ejecuto : " + date);
        return repository.findById(id).orElse(null);
    }
}
