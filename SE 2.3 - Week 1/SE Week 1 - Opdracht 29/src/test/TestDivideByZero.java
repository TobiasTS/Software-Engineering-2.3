package test;
import junit.framework.TestCase;
import multiformat.*;

/**
 * Class to test dividing by zero.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class TestDivideByZero extends TestCase {

	/**
	 * Create new TestDivideByZero object.
	 * @param arg0 Generated for TestCase.
	 */
	public TestDivideByZero(String arg0) {
		super(arg0);
	}
	
	/**
	 * Method with test operations that will run during the test.
	 */
	public void testOperations(){
		Calculator calc = new Calculator();
		
		try{
		calc.addOperand("3");
		calc.addOperand("0");
		calc.divide();
		
		}catch(FormatException e){
			fail("Unexpected format exception");
		}
	}
	
	
}
