package com.training.deviceoperation.parser;

import org.testng.annotations.Test;
// la ya3mal :3 :3 
public class mockTesting {

		@Test
		public void method1() {
			System.out.println("This is method 1");
		}

		@Test(dependsOnMethods = { "method1" })
		public void method2() {
			System.out.println("This is method 2");
		}

	}
