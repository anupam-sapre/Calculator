package calculator.calc;

import java.util.HashMap;

public class Calculator {

	public static void main(String[] args) {
		String input = args[0];
		input.replaceAll(" ", "");
		int val = calculate(input);
		System.out.println(val);
	}

	public static int calculate(String input)throws IllegalArgumentException{
		int result =0;

		if(input.substring(0,3).equals(Constants.add)){
			result = processAdd(input.substring(4,input.length()-1));
		}else if(input.substring(0,3).equals(Constants.let)){
			
		}else if(input.substring(0,3).equals(Constants.sub)){
			
		}else if(input.substring(0,3).equals(Constants.div)){
			
		}else if(input.substring(0,4).equals(Constants.mult)){
			
		}else{
			IllegalArgumentException e = new IllegalArgumentException("Operator not valid");
			throw e;
		}

		return result;
	}



	

	public static int processAdd(String input) throws IllegalArgumentException{
		int result = 0;
		int[] operands = extractOperands(input);
		result = operands[0] + operands[1];
		return result;

	}
	
	
	 public static int[] extractOperands(String input){
	    	int[] result = new int[2];
	    	if(input.contains(",")){
	    		int seperatorPos = getPos(input);
	    		String varOne = input.substring(0, seperatorPos);
	    		int varOneVal = 0;
	    		if(checkOperators(varOne)){
	    			varOneVal = calculate(varOne);
	    		}else{
	    				varOneVal = Integer.valueOf(varOne);
	    		}
	    		String varTwo = input.substring(seperatorPos+1,input.length());
	    		int varTwoVal = 0;
	    		if(checkOperators(varTwo)){
	    			varTwoVal = calculate(varTwo);
	    		}else{
	    				varTwoVal = Integer.valueOf(varTwo);
	    		}
	    		result[0]= varOneVal;
	    		result[1] = varTwoVal;
	    	}else{
	    		IllegalArgumentException e = new IllegalArgumentException("Operator not valid");
	    		throw e;
	    	}
	    	return result;
	    }
	    
	    public static boolean checkOperators(String inputStr )
	    {
	    	String[] items = {Constants.add,Constants.let,Constants.mult,Constants.div,Constants.sub};
	        for(int i =0; i < items.length; i++)
	        {
	            if(inputStr.contains(items[i]))
	            {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public static int getPos(String input){
	    	char[] arr = input.toCharArray();
	    	int brackets =0;
	    	int j=0;
	    	for(int i=0;i<input.length();i++){
	    		if(arr[i] =='('){
	    			brackets++;
	    		}
	    		if(arr[i] ==')'){
	    			brackets--;
	    		}
	    		if(brackets == 0 && arr[i] ==','){
	    				return i;
	    		}
	    	}
	    	return j;
	    }
}
