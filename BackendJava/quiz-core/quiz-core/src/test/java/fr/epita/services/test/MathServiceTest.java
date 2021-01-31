package fr.epita.services.test;

import org.junit.Test;
import org.junit.Assert;

import fr.epita.services.MathService;


public class MathServiceTest {
	
	
	@Test
	public void testFactorialWithPositiveInteger() {
		//given an integer i initialized to 5
		int i = 5;
		
		//when we execute factorial on i and store the result in res
		int res = MathService.factorial(i);
		
		//then we should expect 120 as a result
		// First class
//		if (res != 120) {
//			throw new IllegalStateException("the returned result is not matching the requirements"); //Error
//		}else {
//			System.out.println("success : result = " +res );
//		}
		
		Assert.assertEquals(100, res); // Failure
		Assert.assertEquals(120, res); // Success
		
	}

}
