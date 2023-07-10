package version1.demo.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/produzione")
public class HomeworkersC {
    
    public ModelAndView home(Model m){
        return new ModelAndView("Homeworkers.html", "null", null);
    }
}
