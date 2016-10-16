package com.java.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.bean.Account;
import com.java.bean.Event;
import com.java.util.FileHelper;
import com.java.util.SessionHelper;
import com.java.util.StringHelper;

@Controller
public class Celebrate {

	@Autowired
	private FileHelper fileHelper;
	
	@RequestMapping("/welcome")
	public ModelAndView Celebrate(HttpServletRequest request) {
		String eventId = request.getParameter("event");
		if(eventId == null || eventId.isEmpty())
		{
			eventId = "0";
		}
 		Event event = fetchEvent(eventId);
 		ModelAndView model = new ModelAndView("welcome");
		model.addObject("title", event.getTitle());
		model.addObject("titleMessage", event.getTitleMessage());
		model.addObject("bgImage", event.getBackground());
		
		return model;
	}
	private Event fetchEvent(String eventId) {
		Map<String, Event> events = new HashMap();
		String tmpEventId = "0";
		Event event = new Event();
		tmpEventId = "0";
		event.setEventId(eventId);
		event.setTitle("KC BATUKAMMA 2016 - from DB");
		event.setTitleMessage("Kansas City Batukamma Team proudly presents Dasara and Batukamma event celebrations! - from DB");
		event.setBackground("header.jpg");
		events.put(tmpEventId, event);
		
		event = new Event();
		tmpEventId = "1";
		event.setEventId(eventId);
		event.setTitle("Sahana Birthday");
		event.setTitleMessage("Sudha & Surya welcome you to our princess 3rd birthday!");
		event.setBackground("birthday.jpg");
		events.put(tmpEventId, event);
		return events.get(eventId);
	}
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
 		Account account = buildAccount(request);
		return new ModelAndView("index", "message", buildMessage(account.getUserName()));
	}

	private Account buildAccount(HttpServletRequest request) {
		SessionHelper helper = new SessionHelper(request);
		Account account = new Account();
		account.setAccountBalance(fetchAccountBalance());
		account.setAccountBalance(depositMoney(123.00));
		account.setHits(buildPageHits(helper));
		account.setUserName(buildUserName(helper));
		System.out.println(account);
		return account;
	}

	private String depositMoney(double depositAmount) {
		String newAccountBalace = getFileHelper().updateFile(depositAmount);
		return newAccountBalace;
	}

	private String buildUserName(SessionHelper helper) {
		String userName = helper.getRequest().getParameter("userName");
		if(userName == null)
		{
			userName =helper.getString(("userName"));
			
		}
		helper.setAttribute("userName", userName);
		return userName;
	}

	private String buildMessage(String userName) {
		String accountBalance = fetchAccountBalance();
		String message = ""
				+ "Hello "+userName+"!Your account balance is $"+accountBalance+"";
		return message;
	}

	private String fetchAccountBalance() {
		String accountBalanceOnFile = getFileHelper().readFile();
		double value = Math.random()*1000000;
		if(StringHelper.isNotEmpty(accountBalanceOnFile))
		{
			value = Double.parseDouble(accountBalanceOnFile);
		}
		
		DecimalFormat myFormatter = new DecimalFormat("###,###.##");
	    String accountBalance = myFormatter.format(value);
	    return accountBalance;
	}

	private int buildPageHits(SessionHelper helper) {
		int pageHit =helper.getInt("pageHit");
		helper.setAttribute("pageHit", ++pageHit);
		return pageHit;
	}

	public FileHelper getFileHelper() {
		return fileHelper;
	}

	public void setFileHelper(FileHelper fileHelper) {
		this.fileHelper = fileHelper;
	}
	
	
}