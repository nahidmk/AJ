package bd.edu.seu.coronavirus.Controller;

import bd.edu.seu.coronavirus.Service.AllStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @Autowired
    private AllStateService allStateService;

    @GetMapping("/")
    public String home(Model model)
    {
        int TotalCases = allStateService.getAllStateList().stream().mapToInt(state->state.getTotalCurrentEffected()).sum();
        model.addAttribute("AllStates",allStateService.getAllStateList());
        model.addAttribute("TotalCases",TotalCases);
        return "home";
    }
}
