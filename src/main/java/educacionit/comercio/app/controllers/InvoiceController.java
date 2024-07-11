package educacionit.comercio.app.controllers;

import educacionit.comercio.app.entities.Invoice;
import educacionit.comercio.app.entities.Product;
import educacionit.comercio.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller  //Controler se utiliza para el modelo  MVC y RestController retorna solamente String
@RequestMapping("/invoice")  //indica parte de la ruta
public class InvoiceController {

    @Autowired
    @Qualifier(value = "implementacionMejorada")
    private InvoiceService service;

    @GetMapping("/getAll") //indica el verbo http con el que realiza la request y tambien le pasamos aprte de la ruta
    public String getAllInvoice(Model model){ //Se puede usar un Map<String, Object> en lugar de model tambien
        model.addAttribute("message", "Bienvenido a la página de inicio");
        return "index"; // el retorno debe ser el nombre de un archivo html que se encuentre dentro de la carpeta resources/templates
    }

    @GetMapping("/getOne")
    public String findById(Model model, Long id){
        service.findById(id);
        return "index";
    }

    @DeleteMapping("/deleteOne")
    public String deleteById(Model model, Long id){
        service.deleteById(id);
        List<Invoice> invoices = service.invoices();
        model.addAttribute("invoices", invoices);
        return "index";
    }


}
