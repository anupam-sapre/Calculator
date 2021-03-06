package calculator.calc;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class Calculator {

	static Logger log = Logger.getLogger(Calculator.class.getName());
	public static void main( String[] args )
	{
		if(args.length>=1){
			String input = args[0];
			log.info("Input received is " + input);
			input.replaceAll(" ", "");
			try{
				int val = calculate(input,new HashMap<String,Integer>());
				log.info("Result is "+val);
				System.out.println(val);
			}catch(NumberFormatException nr){
				System.out.println("Please enter valid input format");
				//nr.printStackTrace();
			}catch(IllegalArgumentException e){
				System.out.println("Please enter valid input format");
				//e.printStackTrace();
			}catch(ArithmeticException ar){
				System.out.println("Divide by zero is not feasible");
				//ar.printStackTrace();		
			}catch(RuntimeException re){
				System.out.println("Please enter input with result in Integer range");
				//re.printStackTrace();
			}
		}else{
			System.out.println("Please enter a input for program.");
		}
	}


	/**
	 * @param input
	 * @param variables
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ArithmeticException
	 * Method to analyze operator in expression and calculate result
	 */
	public static int calculate(String input,HashMap<String,Integer> variables)  
			throws IllegalArgumentException,ArithmeticException,RuntimeException,NumberFormatException{
		int result =0;
		if(input.length() > 5){
			if(input.substring(0,3).equals(Constants.add)){
				result = processAdd(input.substring(4,input.length()-1),variables);
			}else if(input.substring(0,3).equals(Constants.let)){
				result = processLet(input.substring(4,input.length()-1),variables);
			}else if(input.substring(0,3).equals(Constants.sub)){
				result = processSub(input.substring(4,input.length()-1),variables);
			}else if(input.substring(0,3).equals(Constants.div)){
				result = processDiv(input.substring(4,input.length()-1),variables);
			}else if(input.substring(0,4).equals(Constants.mult)){
				result = processMultiply(input.substring(5,input.length()-1),variables);
			}else{
				IllegalArgumentException e = new IllegalArgumentException("Syntax not valid");
				log.error("Invalid operator Calculator.calculate "  + input);
				throw e;
			}
		}else{
			IllegalArgumentException e = new IllegalArgumentException("Syntax not valid");
			log.error("Invalid operator Calculator.calculate "  + input);
			throw e;
		}
		log.debug("calculate result" + result);
		return result;
	}

	/**
	 * @param input
	 * @param variables
	 * @return
	 * @throws IllegalArgumentException
	 * Method to get result of a let operation
	 */
	public static int processLet(String input,HashMap<String,Integer> variables) 
			throws IllegalArgumentException,NumberFormatException{
		log.debug("ProcesLet called");
		if(input.contains(",")){
			int firstPos = getPos(input,0);
			String varOne = input.substring(0, firstPos);

			int secPos = getPos(input,1);
			String varTwo  = input.substring(firstPos+1, secPos);
			if(checkOperators(varTwo)){
				int varTwoVal = calculate(varTwo,variables);
				variables.put(varOne, varTwoVal);
			}else{
				variables.put(varOne, Integer.valueOf(varTwo));
			}
			String varThree  = input.substring(secPos+1,input.length());
			if(checkOperators(varThree)){
				int varThreeVal = calculate(varThree,variables);
				log.debug("ProcesLet completed");
				return varThreeVal;
			}else{
				if(variables.containsKey(varThree)){
					log.debug("ProcesLet completed");
					return variables.get(varThree);
				}
				log.debug("ProcesLet completed");
				return Integer.valueOf(varThree);
			}
		}else{
			IllegalArgumentException e = new IllegalArgumentException("Syntax not valid");
			log.error("Invalid operator Calculator.processLet " + input);
			throw e;
		}
	}


	/**
	 * @param input
	 * @param variables
	 * @return
	 * @throws IllegalArgumentException
	 * Method to get result of a addition operation
	 */
	public static int processAdd(String input,HashMap<String,Integer> variables) 
			throws IllegalArgumentException,RuntimeException,NumberFormatException{
		log.debug("processAdd called");
		long result =0;
		int[] operands = extractOperands(input,variables);
		result = operands[0] + operands[1];
		if (result > Integer.MAX_VALUE) {
			log.error("Integer max value exceeded in processAdd");
			throw new RuntimeException("Overflow occured");
		} else if (result < Integer.MIN_VALUE) {
			log.error("Integer min value exceeded in processAdd");
			throw new RuntimeException("Underflow occured");
		}
		log.debug("processAdd result" +result);
		return (int)result;
	}

	/**
	 * @param input
	 * @param variables
	 * @return
	 * @throws IllegalArgumentException
	 * Method to get result of a subtraction operation
	 */
	public static int processSub(String input,HashMap<String,Integer> variables) 
			throws IllegalArgumentException,RuntimeException,NumberFormatException{
		log.debug("processSub called");
		long result =0;
		int[] operands = extractOperands(input,variables);
		result = operands[0] - operands[1];
		if (result > Integer.MAX_VALUE) {
			log.error("Integer max value exceeded in processSub");
			throw new RuntimeException("Overflow occured");
		} else if (result < Integer.MIN_VALUE) {
			log.error("Integer min value exceeded in processSub");
			throw new RuntimeException("Underflow occured");
		}
		log.debug("processSub result" + result);
		return (int)result;
	}

	/**
	 * @param input
	 * @param variables
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ArithmeticException
	 * Method to get result of a division operation
	 */
	public static int processDiv(String input,HashMap<String,Integer> variables) 
			throws IllegalArgumentException,ArithmeticException,NumberFormatException{	
		log.debug("processDiv called");
		int result =0;
		try{	
			int[] operands = extractOperands(input,variables);
			result = operands[0] / operands[1];
			log.debug("processDiv result" + result);
			return result;

		}catch(ArithmeticException ar){
			log.error("Arithmatic error for division of operands "+  input);
			throw ar;
		}
	}

	/**
	 * @param input
	 * @param variables
	 * @return
	 * @throws IllegalArgumentException
	 * Method to get result of a multiply operation
	 */
	public static int processMultiply(String input,HashMap<String,Integer> variables) 
			throws IllegalArgumentException,NumberFormatException{
		log.debug("processMultiply called");
		int result =0;
		int[] operands = extractOperands(input,variables);
		result = operands[0] * operands[1];
		log.debug("processMultiply result" + result);
		return result;

	}

	/**
	 * @param input
	 * @param variables
	 * @return
	 * Method to calculate operand expression and return operand values.
	 */
	public static int[] extractOperands(String input,HashMap<String,Integer> variables) 
			throws IllegalArgumentException,NumberFormatException{
		log.debug("extractOperands called");
		int[] result = new int[2];
		if(input.contains(",")){
			int seperatorPos = getPos(input,0);
			String varOne = input.substring(0, seperatorPos);
			int varOneVal = 0;
			//check if expression has any operators
			if(checkOperators(varOne)){
				varOneVal = calculate(varOne,variables);
			}else{
				//check if Value is present in variables list
				if(variables.containsKey(varOne)){
					varOneVal= variables.get(varOne);
				}else{
					varOneVal = Integer.valueOf(varOne);
				}
			}
			String varTwo = input.substring(seperatorPos+1,input.length());
			int varTwoVal = 0;
			if(checkOperators(varTwo)){
				varTwoVal = calculate(varTwo,variables);
			}else{
				if(variables.containsKey(varTwo)){
					varTwoVal= variables.get(varTwo);
				}else{
					varTwoVal = Integer.valueOf(varTwo);
				}
			}
			result[0]= varOneVal;
			result[1] = varTwoVal;
		}else{
			IllegalArgumentException e = new IllegalArgumentException("Syntax not valid");
			log.error("Invalid operator Calculator.extractOperands" + input);
			throw e;
		}
		return result;
	}


	/**
	 * @param inputStr
	 * @return
	 * Method to find if any operator is part of the expression
	 */
	public static boolean checkOperators(String inputStr )
	{
		String[] items = {Constants.add,Constants.let,Constants.mult,Constants.div,Constants.sub};
		for(int i =0; i < items.length; i++)
		{
			if(inputStr.contains(items[i]))
			{
				log.debug("checkOperators operator found");
				return true;
			}
		}
		return false;
	}


	/**
	 * @param input
	 * @param num
	 * @return
	 * 
	 * Method to find the position of ',' between two operands.
	 * num is the operand number to be found
	 * 
	 * ex getPos("1,2" , 0) will give result 1 
	 */
	public static int getPos(String input,int num){
		char[] arr = input.toCharArray();
		int brackets =0;
		int j=0;
		int count =0;
		for(int i=0;i<input.length();i++){
			if(arr[i] =='('){
				brackets++;
			}
			if(arr[i] ==')'){
				brackets--;
			}
			if(brackets == 0 && arr[i] ==','){

				if(count==num){
					return i;
				}
				else{
					count++;
				}
			}
		}
		log.debug("getPos completed");
		return j;
	}
}
