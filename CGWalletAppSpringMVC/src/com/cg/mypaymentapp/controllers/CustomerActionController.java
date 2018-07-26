package com.cg.mypaymentapp.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;

@Controller
public class CustomerActionController {
	public static String mobileNo;
	@Autowired
	WalletService walletService;
	
	@RequestMapping(value="/registerCustomer")
public ModelAndView registerCustomer(@Valid @ModelAttribute("customer")Customer customer,BindingResult result) {
		if(result.hasErrors())
			return new ModelAndView("registrationPage");
		customer=walletService.createAccount(customer);
	return new ModelAndView("registrationSuccessPage", "customer",customer);
	
}
	@RequestMapping(value="/loginCustomer", method=RequestMethod.POST)
	public ModelAndView loginCustomer(@ModelAttribute("customer")Customer customer) {
		try{
			mobileNo=customer.getMobileNo();
			customer=walletService.showBalance(mobileNo);
		return new ModelAndView("loginSuccessPage", "customer",customer);
		}
		catch(InvalidInputException e) {
			return new ModelAndView("loginPage","errorMessage",e.getMessage());
		}
	}

	@RequestMapping(value="/fundTransferAction")
	public ModelAndView fundTransfer(@ModelAttribute("customer")Customer customer,@RequestParam("mobileNo")String target,@RequestParam("wallet.balance")BigDecimal amount) {
		try {	
		Customer customer1=walletService.fundTransfer(mobileNo, target, customer.getWallet().getBalance());
		return new ModelAndView("currentbalance", "customer",customer1);
		}
		catch(InsufficientBalanceException e) {
			return new ModelAndView("fundTransfer","errorMessage",e.getMessage());
		}
		
	}
	@RequestMapping(value="/depositAmount")
	public ModelAndView depositBalance(@ModelAttribute("customer")Customer customer,@RequestParam("wallet.balance")BigDecimal amount) {
		
		
		
		Customer customer1=walletService.depositAmount(mobileNo,amount);
		
		return new ModelAndView("currentbalance", "customer",customer1);
		
	}
	@RequestMapping(value="/withdrawAmount")
	public ModelAndView withdrawBalance(@ModelAttribute("customer")Customer customer,@RequestParam("wallet.balance")BigDecimal amount) {
		try {	
		Customer customer1=walletService.withdrawAmount(mobileNo,amount);
		return new ModelAndView("currentbalance", "customer",customer1);
		}
		catch(InsufficientBalanceException e) {
			return new ModelAndView("withdraw","errorMessage",e.getMessage());
		}
		
	}
	@RequestMapping(value="/printTransaction")
	public ModelAndView printtrans() {
		List<Transactions> transaction=walletService.recentTransactions(mobileNo);
		return new ModelAndView("printTransaction","transactions1",transaction);
	}
	@RequestMapping(value="/getAll")
	public ModelAndView getCustomers() {
		List<Customer> customer=walletService.getAllCustomer();
		return new ModelAndView("getAll","customer1",customer);
	}
	@RequestMapping(value="/currentbalance")
	public ModelAndView showBalance() {
		Customer customer=walletService.showBalance(mobileNo);
		return new ModelAndView("currentbalance","customer",customer);
	}
	@RequestMapping(value="/getCustomer")
	public ModelAndView getCustDetails(@RequestParam("wallet.balance")BigDecimal amount) {
		List<Customer> customer=walletService.getDetails(amount);
		return new ModelAndView("getdetail","customer1",customer);
	}
	
}
