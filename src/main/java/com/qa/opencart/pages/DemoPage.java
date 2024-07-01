package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class DemoPage {

	By demo = By.id("demo");

	public void getDemo() {
		System.out.println(demo);
	}

}
