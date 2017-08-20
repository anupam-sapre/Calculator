package calculator.calc;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class CalcTest{

	@Test
	public void testAdd() {
		assertEquals(7,Calculator.calculate("add(1,mult(2,3))",new HashMap<String,Integer>()));
		assertEquals(3,Calculator.calculate("add(1,2)",new HashMap<String,Integer>()));
	}
	
	@Test
	public void testLet() {
		assertEquals(10,Calculator.calculate("let(a,5,add(a,a))",
				new HashMap<String,Integer>()));
		assertEquals(55,Calculator.calculate("let(a,5,let(b,mult(a,10),add(b,a)))",
				new HashMap<String,Integer>()));
		assertEquals(40,Calculator.calculate("let(a,let(b,10,add(b,b)),let(b,20,add(a,b)))",
				new HashMap<String,Integer>()));
		
	}
	
	@Test
	public void testMult() {
		assertEquals(12,Calculator.calculate("mult(add(2,2),div(9,3))",
				new HashMap<String,Integer>()));
	}
	
	
	@Test
	public void testSub(){
		assertEquals(1,Calculator.calculate("sub(2,1)",new HashMap<String,Integer>()));
	}
	
	@Test
	public void testDiv(){
		assertEquals(2,Calculator.calculate("div(2,1)",new HashMap<String,Integer>()));
	}
	
	@Test(expected=java.lang.ArithmeticException.class)
    public void testDivideByZero() {
		Calculator.calculate("div(1,0)",new HashMap<String,Integer>());
    }
}
