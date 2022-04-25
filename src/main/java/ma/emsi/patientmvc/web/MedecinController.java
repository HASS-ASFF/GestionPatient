package ma.emsi.patientmvc.web;


import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entities.Medecin;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MedecinController {
    private MedecinRepository medecinRepository;

    @GetMapping(path = "/user/listeM")
    public String Medecin(Model model,
                           @RequestParam(name = "page",defaultValue ="0") int page,
                           @RequestParam(name="size",defaultValue ="5") int size,
                           @RequestParam(name="keyword",defaultValue ="") String keyword) {
        Page<Medecin> PageMedecins = medecinRepository.findByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listMedecins",PageMedecins.getContent());
        model.addAttribute("pages",new int[PageMedecins.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "Medecins/medecins";
    }

    @GetMapping(path = "/admin/deleteMedecin")
    public String delete(Long id,String keyword,int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/listeM?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/admin/formMedecins")
    public String formPatient(Model model){
        model.addAttribute("medecin",new Medecin());
        return "Medecins/formMedecins";
    }

    @PostMapping("/admin/saveMedecin")
    public String save(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formMedecins";
        medecinRepository.save(medecin);
        return "redirect:/user/listeM?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editMedecin")
    public String editPatient(Model model,Long id,String keyword,int page){
        Medecin medecin=medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new RuntimeException("Medecin introuvable");
        model.addAttribute("medecin",medecin);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "Medecins/editMedecin";
    }

}
