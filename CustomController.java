package com.luv2code.springdemo.mvc;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomController {

	// add an initBinder ... to convert trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		// We pass here Constructor value true meaning that if it's all whitespace trim
		// it down to null.
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		// registering this customEditor this implies StringTrimmerEditor on this
		// databinder.

		/*
		 * what it will do :- 1. pre-process every String Form data. 2. Remove leading
		 * and trailing whitespace. 3. If String only has whitespace ... trim it to
		 * null.
		 */
	}

	@RequestMapping("/showForm")
	public String showForm(Model theModel) {
		theModel.addAttribute("customer", new Customer());

		return "customer-form";
	}

	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

		System.out.println("Last name: |" + theCustomer.getLastName() + "|");

		System.out.println("Binding result: " + theBindingResult);

		if (theBindingResult.hasErrors()) {
			return "customer-form";
		} 
		else
			return "customer-confirmation";

	}
}
