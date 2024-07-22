package educacionit.comercio.app.controllers;

import educacionit.comercio.app.entities.Invoice;
import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller  //Controler se utiliza para el modelo  MVC y RestController retorna solamente String
@RequestMapping("/invoice")  //indica parte de la ruta
public class InvoiceController {

    @Autowired
    @Qualifier(value = "implementacionMejorada")
    private InvoiceService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all") //indica el verbo http con el que realiza la request y tambien le pasamos aprte de la ruta
    public String getAllInvoice(Model model){ //Se puede usar un Map<String, Object> en lugar de model tambien
        List<Invoice> invoices = service.invoices();
        model.addAttribute("invoices", invoices);
        return "invoiceList"; // el retorno debe ser el nombre de un archivo html que se encuentre dentro de la carpeta resources/templates
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Invoice invoice = service.findById(id);
        if(invoice==null) {
            return "redirect:/invoice/all";
        }
        model.addAttribute("invoice", invoice);
        return "invoice";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable(value="id")Long id, Model model){
        service.deleteById(id);
        List<Invoice> invoices = service.invoices();
        model.addAttribute("invoices", invoices);
        return "redirect:/invoice/all";
    }


}
