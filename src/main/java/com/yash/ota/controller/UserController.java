/*
 * UserController
 *
 * Version 0.1
 *
 * Date: 08/15/2018
 *
 * Author: YASH Trainees
 *
 * Copyright YASH Technologies Inc.
 */
package com.yash.ota.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yash.ota.command.UserResetPasswordCommand;
import com.yash.ota.command.UserUpdateCommand;
import com.yash.ota.command.UserLoginCommand;
import com.yash.ota.command.UserPasswordConfirmCommand;
import com.yash.ota.command.UserPasswordResetCommand;
import com.yash.ota.command.UserRegistrationCommand;
import com.yash.ota.domain.Batch;
import com.yash.ota.domain.User;
import com.yash.ota.domain.UserImage;
import com.yash.ota.exception.DuplicateUserException;
import com.yash.ota.exception.UserAuthenticationException;
import com.yash.ota.service.BatchService;
import com.yash.ota.service.ImageService;
import com.yash.ota.service.TechnologyService;
import com.yash.ota.service.UserService;

/**
 * This class provides a controller for the User data model.
 * 
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private ImageService imageService;


	private Logger log = Logger.getLogger(UserController.class);

	/**
	 * @author BILL KING!!!!!
	 * @param model
	 * @return landing page jsp
	 */
	@RequestMapping(value = "/landing.htm", method = RequestMethod.GET)
	public String showLandingPage(Model model) {
		List<User> creators = userService.getAllCreators();
		model.addAttribute("creators", creators);
		return "landing/landing";
	}


	@RequestMapping(value = "/getProfileImage/{userId}.jpg")
	@ResponseBody
	public byte[] getProfileImge(@PathVariable int userId) {
		UserImage userImage = imageService.getProfileImageByUserId(userId);
		if(userImage != null) {
			return userImage.getByteImage();
		} else {
			return null;
		}
	}
	
	/**
	 * This method will redirect to confirmation page
	 * @return confirmation page
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="confirmation.htm", method=RequestMethod.GET)
	public String showConfirmationPage() {
		return "/util/confirmation";
	}

	/**
	 * 
	 * @param model
	 * @return registration page
	 * @author whitney.fout
	 */
	@RequestMapping(value = "/userRegistration.htm", method = RequestMethod.GET)
	public String showUserRegistrationPage(Model model, HttpSession session) {
		log.info("Returning register.jsp page");

		List<Batch> batchList = batchService.listBatches();

		model.addAttribute("command", new UserRegistrationCommand());
		session.setAttribute("batchList", batchList);
		return "util/register";
	}
	//REGISTRATION FUNCTIONALITY
	/**
	 * @param model
	 * @return registration confirmation page
	 * @author whitney.fout
	 */
	@RequestMapping(value = "/processRegistration.htm", method = RequestMethod.POST)
	public String processUserRegistration(@Valid @ModelAttribute("command") UserRegistrationCommand userRegistrationCommand, BindingResult bindingResult,
			Model model, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "util/register";
		}
		User user = new User();
		user.setBatchId(userRegistrationCommand.getBatchId());
		user.setEmail(userRegistrationCommand.getEmail());
		user.setPassword(userRegistrationCommand.getPassword());
		try {
			userService.register(user);
		} catch (DuplicateUserException e) {
			model.addAttribute("errMsg", e.getMessage());
			return "util/register";
		}
		return "util/registration-confirmation";

	}
	
	/**
	 * This method will redirect to a users profile page
	 * @return profile page
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="/profile.htm", method=RequestMethod.GET)
	public String showProfilePage() {
		return "user/profile";
	}
	
	/**
	 * This method will redirect to a users profile page
	 * @return account page
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="/account.htm", method=RequestMethod.GET)
	public String showAccountPage() {
		return "user/account";
	}
	
	/**
	 * Upload file from form and store in database
	 * @param file from form
	 * @param session used for getting user
	 * @return confirmation page if updated successfully or back to account page if failure 
	 * @author almedin.mulalic
	 */
	@RequestMapping(value = "/processProfilePicture.htm", method = RequestMethod.POST)
	public String processProfilePicture(@RequestParam("file") MultipartFile file, HttpSession session, RedirectAttributes redirectAttributes) {
		if (!file.getContentType().equals("image/jpeg")) {
			redirectAttributes.addFlashAttribute("errorMessage", "File must be of type jpeg");
			return "redirect:../users/account.htm";
		}
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				log.info("Server File Location="+ serverFile.getAbsolutePath());
				
				File readFile = new File(serverFile.getAbsolutePath());
				User user = (User) session.getAttribute("user");
				
				//add or update image
				if (imageService.getProfileImageByUserId(user.getId()) != null) {
					imageService.updateImage(readFile, user.getId());
					session.setAttribute("image",imageService.getProfileImageByUserId(user.getId()));
				} else {
					imageService.saveImage(readFile, user.getId());
					session.setAttribute("image",imageService.getProfileImageByUserId(user.getId()));
				}
				redirectAttributes.addFlashAttribute("confirmationMessage", "Image successfully changed!");
				return "redirect:../users/confirmation.htm";
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("errorMessage", "Could not upload file");
				return "redirect:../users/account.htm";
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Could not upload file");
			return "redirect:../users/account.htm";
		}
	}
	
	
	/**
	 * This method will redirect to reset password page
	 * @param model used for reset password page
	 * @return reset password page
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="/reset-password.htm", method=RequestMethod.GET)
	public String showResetPasswordPage(Model model) {
		model.addAttribute("command", new UserResetPasswordCommand()); 
		return "user/reset-password";
	}
	
	/**
	 * This function will process the users reset password inquiry. It will send the user to a success page or
	 * back to the reset password prompt if an error is given and display that error.
	 * @param userResetPasswordCommand used to take input from form
	 * @param session used to get user information and put confirmation message
	 * @return process user reset password 
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="/processResetPassword.htm", method=RequestMethod.POST)
	public String processResetPasswordPage(@Valid @ModelAttribute("command") UserResetPasswordCommand userResetPasswordCommand, 
			BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()) {
			return "user/reset-password";
		}
		User user = (User) session.getAttribute("user");
		String password = userResetPasswordCommand.getPassword();
		
		if (!password.equals(userResetPasswordCommand.getConfirmPassword())) {
			redirectAttributes.addFlashAttribute("errorMessage", "password and confirm password do not match");
			return "redirect:../users/reset-password.htm";
		}
		
		user.setPassword(password);
		user.setModifiedBy(user.getId());
		user.setModifiedDate(new Date());
		userService.updateUser(user);
		
		log.info("User " + user.getId() + " changed password.");
		redirectAttributes.addFlashAttribute("confirmationMessage", "Password successfully changed!");
		return "redirect:../users/confirmation.htm";
	}
	
	/**
	 * This method will redirect to update account page
	 * @param model used for reset password page
	 * @return reset password page
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="/update-account.htm", method=RequestMethod.GET)
	public String showUpdateUserPage(Model model) {
		model.addAttribute("command", new UserUpdateCommand()); 
		return "user/update-account";
	}
	
	/**
	 * This method will process update form given by update-user page
	 * @param userUpdateCommand stores user information from form
	 * @param session used to store user
	 * @return redirect to confirmation page
	 * @author almedin.mulalic
	 */
	@RequestMapping(value="/processUserUpdate.htm", method=RequestMethod.POST)
	public String processUserUpdate(@Valid @ModelAttribute("command") UserUpdateCommand userUpdateCommand, 
			BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes){
		if (bindingResult.hasErrors()) {
			return "user/update-account";
		}
		User user = (User) session.getAttribute("user");
		user.setFirstName(userUpdateCommand.getFirstName());
		user.setLastName(userUpdateCommand.getLastName());
		user.setEmail(userUpdateCommand.getEmail());
		user.setPhoneNumber(userUpdateCommand.getPhoneNumber());
		userService.updateUser(user);
		
		log.info("User " + user.getId() + " updated account information.");
		redirectAttributes.addFlashAttribute("confirmationMessage", "Account information successfully changed!");
		return "redirect:../users/confirmation.htm";
	}
	
	//LOGIN FUNCTIONALITY

	/**
	 * 
	 *  Forgot Password
	 * @return
	 * @author harish
	 */
	@RequestMapping(value = "/forgotpassword.htm", method = RequestMethod.GET)
	public String forgotpassword() {
		return "util/forgot-password";
	}

	/**
	 * 
	 * Submitting the email to reset the password
	 * @return Reset Password Page
	 * @author harish
	 */
	@RequestMapping(value = "/processForgotPassword.htm", method = RequestMethod.POST)
	public String processForgotPassword(@ModelAttribute("command") UserPasswordResetCommand userPasswordResetCommand,
			HttpSession session) {
		try {
			User u1 = userService.findUserByEmail(userPasswordResetCommand.getEmail());
			if (u1 != null) {
				if (u1.getEmail().equals(userPasswordResetCommand.getEmail())) {
					session.setAttribute("userid", u1.getId());
					return "util/reset-password";
				} else {
					return "redirect:/users/forgotpassword.htm?err=Login Failed";
				}
			}
		} catch (EmptyResultDataAccessException ex) {
			return "redirect:/users/forgotpassword.htm?err=Email Not Found";
		}
		return "redirect:/users/forgotpassword.htm?err=Login Fail";
	}

	/**
	 * 
	 * @param User
	 *            Password Command changing the password
	 * @return Password Changed Page
	 * @author harish
	 */

	@RequestMapping(value = "/processpasswordchanged.htm")
	public String processPasswordChanged(
			@Valid @ModelAttribute("command") UserPasswordConfirmCommand userPasswordConfirmCommand,
			BindingResult result, HttpSession session) {
		try {
			if (result.hasErrors()) {
				return "util/reset-password";
			}
			if (userPasswordConfirmCommand.getPassword().equals(userPasswordConfirmCommand.getConfirmPassword())) {
				userService.resetPassword(userPasswordConfirmCommand.getPassword(),
						(Integer) session.getAttribute("userid"));
				return "util/password-changed";
			} else {
				return "redirect:/users/passwordnotchanged.htm?err=Password Not Match";
			}
		} catch (NullPointerException ex) {
			return "redirect:/users/passwordnotchanged.htm?err=Password Not Match";
		}
	}

	//LOGIN FUNCTIONALITY
	/**
	 * Checks for email and password in database and redirects to dashboard based on role
	 * @param userLoginCommand
	 * @param session
	 * @param model
	 * @return url path 
	 * @author whitney.fout
	 */
	@RequestMapping(value = "/processLogin.htm", method = RequestMethod.POST)
	public String processUserLogin(@ModelAttribute("command") UserLoginCommand userLoginCommand, HttpSession session,
			Model model) {
		String redirect = "util/login";
		User user = new User();
		UserImage image = new UserImage();
		try {
			user = userService.login(userLoginCommand.getEmail(), userLoginCommand.getPassword());
			image = imageService.getProfileImageByUserId(user.getId());
		} catch (UserAuthenticationException e) {
			model.addAttribute("errMsg", e.getMessage());
		}
		if (user.getRoleId() == user.ROLE_TRAINEE) {
			session.setAttribute("user", user);
			session.setAttribute("image", image);
			redirect = "redirect:../trainee/dashboard.htm";
		}
		if (user.getRoleId() == user.ROLE_TRAINER) {
			session.setAttribute("user", user);
			session.setAttribute("image", image);
			redirect = "redirect:../trainer/dashboard.htm";
			
		}
		if (user.getRoleId() == user.ROLE_ADMIN) {
			session.setAttribute("user", user);
			session.setAttribute("image", image);
			redirect =  "redirect:../admin/dashboard.htm";
		} 
		return redirect;
	}

	

	/**
	 * Displays user login page
	 * @param model
	 * @return login page
	 * @author whitney.fout
	 */
	@RequestMapping(value = "/userLogin.htm", method = RequestMethod.GET)
	public String showUserLoginPage(Model model) {
		model.addAttribute("command", new UserLoginCommand());
		return "util/login";
	}
	/**
	 * Logout functionality, invalidates the session
	 * @param session
	 * @return redirect to login page
	 * @author whitney.fout
	 */
	@RequestMapping(value = "/logout.htm")
	public String processLogout(HttpSession session){
		session.invalidate();
		return "redirect:/users/userLogin.htm";
	}
}
