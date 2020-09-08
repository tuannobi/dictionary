package com.tuan.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.tuan.dictionary.exception.ServiceException;
import com.tuan.dictionary.user.User;
import com.tuan.dictionary.user.UserService;
import com.tuan.dictionary.util.Utils;


@RequestMapping("/web")
@Controller
public class WebController {
	
	private UserService userService;
	
	@Autowired
	public WebController(UserService userService) {
		this.userService=userService;
	}
	
    @GetMapping("/admin/login")
    public String login(){
        return "admin/login";
    }
    
    @GetMapping("/register")
    public String register(ModelMap modelMap) {
    	modelMap.addAttribute("user", new User());
    	return "register";
    }
    
    @PostMapping("/register")
    public Object processRegister(@ModelAttribute("user") User user,@RequestParam("re_password") String rePassword, ModelMap modelMap) {
    	if(!validateUser(user, modelMap)) {
    		modelMap.addAttribute("user",user);
    		return "register";
    	}
    	
    	if(!checkIfMatchingPassword(user.getPassword(), rePassword,modelMap)) {
    		return "register";
    	}
    	
    	try {
    		userService.addClientUser(user);
		} catch (ServiceException e) {
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("serviceError",e.getMessage());
			return "register";
		} catch(Exception e){
    		modelMap.addAttribute("user",user);
			modelMap.addAttribute("serviceError",e.getMessage());
		}
    	return new RedirectView("/web/register",true);
    }
    
    private boolean validateUser(User user, ModelMap modelMap) {
		boolean valid=true;
		if(user.getEmail()==null || "".equals(user.getEmail())) {
			valid=false;
			modelMap.addAttribute("emailError","Please Input email");
		}else if(!Utils.isValidEmail(user.getEmail())) {
			valid=false;
			modelMap.addAttribute("emailError","Invalid email");
		}
		
		if(user.getFullName()==null || "".equals(user.getFullName())) {
			valid=false;
			modelMap.addAttribute("fullNameError", "Please input your name");
		}
		
		if(user.getPhoneNumber()==null || "".equals(user.getPhoneNumber())) {
			valid=false;
			modelMap.addAttribute("phoneNumberError","Please input phone number");
		}else if(!Utils.isValidPhoneNumber(user.getPhoneNumber())) {
			valid=false;
			modelMap.addAttribute("phoneNumberError", "Invalid phone number");
		}
		
		if(user.getPassword()==null || "".equals(user.getPassword())) {
			valid=false;
			modelMap.addAttribute("passwordError", "Please input your password");
		}else if(user.getPassword().length()<6) {
			valid=false;
			modelMap.addAttribute("passwordError","Password must be at least 6 characters");
		}
		
		return valid;
	}

	private boolean checkIfMatchingPassword(String passWord, String rePassword,ModelMap modelMap) {
    	if(passWord.equals(rePassword)) {
    		return true;
    	}
		modelMap.addAttribute("matchingPasswordError", "Password is not matching");
    	return false;
    }
}
