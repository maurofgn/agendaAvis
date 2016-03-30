package it.mesis.tmm.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.mesis.tmm.model.User;
import it.mesis.tmm.model.UserProfile;
import it.mesis.tmm.service.UserProfileService;
import it.mesis.tmm.service.UserService;

@Controller
public class AccountController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	private static final String PREFIX_PATH = "/account/";

	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = {"/userList" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return PREFIX_PATH + "userslist";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return PREFIX_PATH + "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return PREFIX_PATH + "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return PREFIX_PATH + "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return PREFIX_PATH + "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return PREFIX_PATH + "newuser";
	}

	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 */
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String saveRegistration(@Valid User user,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
//			System.out.println("There are errors");
			return PREFIX_PATH + "newuser";
		}
		
		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
		    return PREFIX_PATH + "newuser";
		}
		
		userService.save(user);
		
//		System.out.println("First Name : "+user.getFirstName());
//		System.out.println("Last Name : "+user.getLastName());
//		System.out.println("SSO ID : "+user.getSsoId());
//		System.out.println("Password : "+user.getPassword());
//		System.out.println("Email : "+user.getEmail());
//		System.out.println("Checking UsrProfiles....");
//		if(user.getUserProfiles()!=null){
//			for(UserProfile profile : user.getUserProfiles()){
//				System.out.println("Profile : "+ profile.getType());
//			}
//		}
		
		model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
		return PREFIX_PATH + "registrationsuccess";
	}
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySso(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return  PREFIX_PATH + "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return  PREFIX_PATH + "registration";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/
		
		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		return  PREFIX_PATH + "registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
//	@Secured({"ROLE_DBA","ROLE_ADMIN"})
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/userList";
	} 
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

}