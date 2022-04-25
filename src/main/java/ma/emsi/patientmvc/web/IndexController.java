package ma.emsi.patientmvc.web;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class IndexController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

}
