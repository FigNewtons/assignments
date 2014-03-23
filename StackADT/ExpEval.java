
public class ExpEval 
{
	
	// stack for temporary storage of operators
	static Stack<Character> opStack = new LinkedStack<Character>(); 
	static Stack<Float> valStack = new LinkedStack<Float>();
	
	static float[] value = {5,3,8,20,18,9,12,7,0,0,1};
	
	// Converts infix expressions to postfix expressions
	public static String infixToPostfix(String str)
	{
		char[] infix = str.toCharArray(); 
		StringBuilder postFix = new StringBuilder();
				
		for(char op: infix)
		{
			// Operands append directly to postFix string
			if(isOperand(op))
				postFix.append(op);
			else
			{
				if(opStack.isEmpty())
					opStack.push(op);
				else
				{
					// Make sure current operator isn't pushed on top of 
					// operator of higher precedence 
					while(order(op) <= order(opStack.topValue()))
					{
						postFix.append(opStack.pop());
						if(opStack.isEmpty())
							break;
					}
					opStack.push(op);
				}	
			}
		}
		
		// Append leftover operators in stack to postfix string
		while(!opStack.isEmpty())
			postFix.append(opStack.pop());
		
		return postFix.toString();
	}
	

	// Returns true if character is an operand
	// and false is an operator 
	public static boolean isOperand(char op)
	{
		String operators = "+-*/";
		
		// check if character is an operator
		if(operators.indexOf(op) >= 0)
			return false;
		else
			return true;
	}	
	
	// Returns the precedent value of an operator
	// for comparison  
	// * and / have a precedence of 2 
	// + and - have a precedence of 1
	public static int order(char op)
	{
		if(op == '+' || op == '-')
			return 1;
		else
			return 2;
	}
	
	public static float evaluatePostfix(String str)
	{
		char[] postfix = str.toCharArray();
		for(char op: postfix)
		{	
			if(isOperand(op))
				valStack.push(value[op - 97]); // 97 is the ascii value of 'a'
			else
			{	
				float op2 = valStack.pop();
				float op1 = valStack.pop();
				
				switch(op)
				{
					case '+':			
						valStack.push(op1 + op2);
						break;
					case '-':
						valStack.push(op1 - op2);
						break;
					case '*':	
						valStack.push(op1 * op2);
						break;
					case '/':
						valStack.push(op1 / op2);
				}
			}
		}

		return valStack.pop();
	}
	
	public static void main(String[] args) 
	{
		String[] infix = {"a+b-c", "a-b+c", "e*f*g", "e+f/g*h-k", "a*b+c*d-e/f",
						  "a/b/c/d", "a-b-c/d-e-f*g/h-k", "a+b*c+d/e-f*g+h/k"};
		
		for(String inStr: infix)
		{
			String postfix = infixToPostfix(inStr);
			System.out.println("Infix: " + inStr);
			
			char[] str = postfix.toCharArray();
			System.out.print("Postfix: ");
			for(char ch: str)
				System.out.print(ch);
			System.out.print("\n");
			System.out.println("Value: " + evaluatePostfix(postfix) + "\n");
		}
	}
}
