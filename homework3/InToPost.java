package homework3;

import java.util.*;
/**
 * 
 * @author Ella Rekow
 *
 */
public class InToPost {
	/**
	 *
	 */
	private String[] in; 
	private String post; 
	private ArrayList<String> errors;
	
	/**
	 * 
	 * @param infix expression 
	 */
	public InToPost(String infix) {
		post = "";
		in = infix.split(" ");
		errors = new ArrayList<String>();
		convertToPost();
	}
	
	public String getPostFix() {
		if(errors.isEmpty())
			return post;
		else
			return getErrors();
	}
	
	/**
	 * Converts the infix expression to postfix 
	 */
	public void convertToPost() {
		//stack for conversion to keep track of operators
		Stack<String> ops = new Stack<String>();
		
		//keeps track of number of items to ensure there are not extra 
		int nums = 0;
		int oper = 0;
		int pars = 0;
		
		boolean parents = false;
		
		int j = 0;
		//preforms the converstion
		while(errors.isEmpty() && j < in.length)
			for(int i = 0; i < in.length; i++) {
				
				//check if operator
				if(precedence(in[i]) > 0) {
					while(!ops.isEmpty() && precedence(ops.peek()) >= precedence(in[i])) {
						post += ops.pop();
						post += " ";
						oper++;
						parents = false;
					}
					ops.push(in[i]);
				}
				
				//clears inside parenthesis
				else if(in[i].equals(")")) {
					//checks to make sure there isnt an operand after a )
					if(precedence(in[i - 1]) > 0 && (i - 1 > 0)){
						errors.add("too many operands (" + in[i - 1] + ")");
					}
					
					//checks to make sure is a sub expression 
					if(parents)
						errors.add("no sub expression detected");
					
					//adds the ops 
					String x = ops.pop();
					parents = false;
					while(!x.equals("(")) {
						post += x;
						post += " ";
						x = ops.pop();
					}
					pars--; 
				}
				
				//adds to stack if parenthesis
				else if(in[i].equals("(")) {
					ops.push(in[i]);
					pars++;
					parents = true;
				}
				
				//if char is an operand, add to postfix
				else {
					parents = false;
					post += in[i];
					post += " ";
					nums++;
				}
				j++;
				
				//checks to make sure not too many operands
				if(precedence(in[i]) < 0 && (i + 1 != in.length)) {
					if(precedence(in[i]) == -1 && precedence(in[i + 1]) == -1) 
						errors.add("too many operators (" + in[i + 1] + ")");
				}
				
				
				//checks to make sure not too many operants
				if(precedence(in[i]) > 0 && (i + 1 != in.length)) {
					if(precedence(in[i]) > 0 && precedence(in[i + 1]) > 0)
						errors.add("too many operants (" + in[i + 1] + ")");
				}
		}
		
		//rest of ops
		if(!ops.isEmpty()) {
			for(int i = 0; i <= ops.size(); i++) {
				post += ops.pop();
				post += " ";
			}
		}
			
		//prethesis error
		if(pars != 0) {
			if(pars > 0)
				errors.add("no closing parenthesis detected");
			else 
				errors.add("no opening parenthesis detected");
		}
//		
			
	}
	
	/**
	 * 
	 * @param peek, the PEMDAS operator 
	 * @return the percendence of the operator 
	 */
	private int precedence(String peek) {
		//gets the PEMDAS precedence values of operators
		switch (peek) {
		case "+": 
		case "-":
			return 1;
			
		case "*": 
		case "/":
		case "%":
			return 2;
			
		case "^":
			return 3;
			
		case "(":
		case ")":
		return -2; 
		}
			
		return -1; 
	}
	
	/**
	 * Prints the first error encountered 
	 * @return 
	 */
	private String getErrors() {
		String err = "";
			err += "Error: ";
			err += errors.get(0);
		return err; 
	}
}
