package com.tuan.dictionary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.tuan.dictionary.exception.ServiceException;
import com.tuan.dictionary.user.User;
import com.tuan.dictionary.user.UserService;
import com.tuan.dictionary.util.Utils;

import javax.validation.Valid;


@RequestMapping("/web")
@Controller
public class WebController {
	
	private final UserService userService;
	
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

    @GetMapping("/login")
	public String adminLogin(ModelMap modelMap){
		modelMap.addAttribute("user",new User());
		return "admin/login";
	}

    @GetMapping("/error/403")
	@ResponseBody
	public String error403(){
		return "Khong co quyen truy cap";
	}

    @GetMapping("/register/success")
	public String registerSuccess(){
		return "register-sucess";
	}
    
    @PostMapping("/register")
    public Object processRegister(
			@RequestParam("re_password") String rePassword,
			ModelMap modelMap,
			@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			return "register";
		}

		if(!checkIfMatchingPassword(user.getPassword(), rePassword,modelMap)) {
			return "register";
		}
    	
    	try {
    		userService.addClientUser(user);
		} catch (ServiceException e) {
			modelMap.addAttribute("user", user);
			modelMap.addAttribute("serviceError", e.getMessage());
			return "register";
		} catch(Exception e){
    		modelMap.addAttribute("user",user);
			modelMap.addAttribute("serviceError",e.getMessage());
		}
    	return new RedirectView("/web/register/success",true);

    }

	private boolean checkIfMatchingPassword(String passWord, String rePassword,ModelMap modelMap) {
    	if(passWord.equals(rePassword)) {
    		return true;
    	}
		modelMap.addAttribute("matchingPasswordError", "Password is not matching");
    	return false;
    }
}
