package calculator.calc;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalcTest{

	@Test
	public void testAdd() {
//		/assertEquals(Calculator.calculate("add(1,mult(2,3))"), 7);
		assertEquals(Calculator.calculate("add(1,2)"), 3);
	}
	
	/*@Test
	public void testLet() {
		assertEquals(Calculator.calculate("let(a,5,add(a,a))"), 10);
		assertEquals(Calculator.calculate("let(a,5,let(b,mult(a,10),add(b,a)))"), 55);
		assertEquals(Calculator.calculate("let(a,let(b,10,add(b,b)),let(b,20,add(a,b)))"), 40);
		
	}
	
	@Test
	public void testMult() {
		assertEquals(Calculator.calculate("mult(add(2,2),div(9,3))"), 12);
	}
	
	
	@Test
	public void testSub(){
		assertEquals(Calculator.calculate("sub(2,1)"), 1);
	}
	
	@Test
	public void testDiv(){
		assertEquals(Calculator.calculate("div(2,1)"), 2);
	}*/
}
