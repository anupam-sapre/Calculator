package calculator.calc;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class CalcTest{

	@Test
	public void testAdd() {
		assertEquals(Calculator.calculate("add(1,mult(2,3))",new HashMap<String,Integer>()), 7);
		assertEquals(Calculator.calculate("add(1,2)",new HashMap<String,Integer>()), 3);
	}
	
	@Test
	public void testLet() {
		assertEquals(Calculator.calculate("let(a,5,add(a,a))",
				new HashMap<String,Integer>()), 10);
		assertEquals(Calculator.calculate("let(a,5,let(b,mult(a,10),add(b,a)))",
				new HashMap<String,Integer>()), 55);
		assertEquals(Calculator.calculate("let(a,let(b,10,add(b,b)),let(b,20,add(a,b)))",
				new HashMap<String,Integer>()), 40);
		
	}
	
	@Test
	public void testMult() {
		assertEquals(Calculator.calculate("mult(add(2,2),div(9,3))",
				new HashMap<String,Integer>()), 12);
	}
	
	
	@Test
	public void testSub(){
		assertEquals(Calculator.calculate("sub(2,1)",new HashMap<String,Integer>()), 1);
	}
	
	@Test
	public void testDiv(){
		assertEquals(Calculator.calculate("div(2,1)",new HashMap<String,Integer>()), 2);
	}
}
