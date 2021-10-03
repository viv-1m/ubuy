package auth.Login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/log")
public class LoginController {
	
//	@RequestMapping(value="/login",method = RequestMethod.GET)
//	public String showLogin(){
//		return "login";
//	}
//	
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("errorMsg", "Your username and password are invalid.");
//
//        if (logout != null)
//            model.addAttribute("msg", "You have been logged out successfully.");
//
//        return "login";
//    }


	
	
//	
	  @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public ModelAndView adminPage() {

	        ModelAndView model = new ModelAndView();
	       
	        model.setViewName("login");

	        return model;

	    }

	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String doLogin(){
		return "j_spring_security_check";
	}
//	
}