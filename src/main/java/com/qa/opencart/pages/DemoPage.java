package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class DemoPage {

	By demo = By.id("demo");
	By cart = By.id("cart");

	

	public void getDemo() {
		System.out.println(demo);
		System.out.println("demo details");
	}

	public void addtoCart() {
		System.out.println(cart);
		System.out.println("cart details");
	}
	
}
