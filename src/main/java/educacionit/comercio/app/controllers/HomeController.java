package educacionit.comercio.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping({"/inicio"})
public class HomeController {

    @GetMapping("/inicio")
    public String home(Model model){
        return "index";
    }

    @GetMapping({"/", ""})
    public String goHome(){
        return "redirect:/inicio";
    }
}
