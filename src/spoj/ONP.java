/**
 * @file       	ONP.java
 * @brief      	Solution for ONP - Transform the Expression
 * @author     	Ron
 * @created 	November 1, 2017
 * @modified   	November 1, 2017
 * 
 * @par [explanation]
 *      Transform the input algebraic expression into RPN form (Reverse Polish Notation).
 *		Input format:
 *			Line 1 - [Number of expressions]
 *			Line 2+ - [Expression] (one per line)
 *		Test values:
 *			Input:
 *			3
 *			(a+(b*c))
 *			((a+b)*(z+x))
 *			((a+t)*((b+(a+c))^(c+d)))
 *  
 *			Output:
 *			abc*+
 *			ab+zx+*
 *			at+bac++cd+^*
 */

package spoj;

import java.util.Scanner;
import java.util.Stack;

/**
 * Class for solving SPOJ ONP.
 */
public class ONP
{
    private static final int MAX_EXPRESSIONS = 100;
    private static final int MAX_EXPRESSION_LENGTH = 400;
    
    /**
     * Runs the solution for ONP.
     */
    public void run()
    {
        // Read system input
        Scanner in = new Scanner(System.in);
        // Read number of expressions
        int exprCount = 0;
        if (in.hasNextInt())
        {
            exprCount = in.nextInt();
			// Limit number of expressions
            if (exprCount <= 0 || exprCount > MAX_EXPRESSIONS)
            {
                return;
            }
            in.nextLine();
        }
        // Initialize stringbuilder and stack to be used for the toRPN function
        StringBuilder outputSB = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        // Read the next exprCount lines, or until a line that doesn't follow
        //  the input format is encountered
        while (exprCount > 0)
        {
            String expr = in.nextLine();
			// Limit expression length
            if (expr.length() <= 0 ||
                expr.length() > MAX_EXPRESSION_LENGTH)
            {
                return;
            }
            // Reset stringbuilder and stack for reuse
            outputSB.setLength(0);
            operatorStack.clear();
            // Convert the line from input to RPN form
            String rpn = toRPN(expr, outputSB, operatorStack);
            System.out.println(rpn);
            exprCount--;
        }
    }

    /**
     * Converts the given string to Reverse Polish Notation.
     * @param expr
     * @return RPN form of expr
     */
    public String toRPN(String expr)
    {
        StringBuilder outputSB = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        return toRPN(expr, outputSB, operatorStack);
    }
    
    /**
     * Converts the given string to Reverse Polish Notation.
     * Note: This overload allows reusing StringBuilder and Stack for multiple toRPN calls.
     * @param expr Expression to convert to RPN.
     * @param outputSB
     * @param operatorStack
     * @return RPN form of expr
     */
    public String toRPN(String expr, StringBuilder outputSB, Stack<Character> operatorStack)
    {
        // Shunting Yard algorithm (from Wikipedia)
		// while there are tokens to be read:
        for (int x = 0; x < expr.length(); ++x)
        {
			// read a token.
            char c = expr.charAt(x);
			// if the token is a number, then:
            if (isNumeric(c))
            {
                outputSB.append(c);
            }
			// if the token is an operator, then:
            else if (isOperator(c))
            {
                Operator curOp = getOperatorType(c);
                int curOpPrec = getPrecedence(curOp);
                while (!operatorStack.empty())
                {
					// while there is an operator at the top of the operator stack
                    Character top = operatorStack.peek();
                    Operator topOp = getOperatorType(top);
					// with greater than or equal to precedence and the operator is left associative
                    if (getPrecedence(topOp) >= curOpPrec && isLeftAssociative(topOp))
                    {
						// pop operators from the operator stack, onto the output queue.
                        outputSB.append(operatorStack.pop());
                    }
                    else
                    {
                        break;
                    }
                }
				// push the read operator onto the operator stack.
                operatorStack.add(c);
            }
			// if the token is a left bracket (i.e. "("), then:
            else if (c == '(')
            {
				// push it onto the operator stack.
                operatorStack.add(c);
            }
			// if the token is a right bracket (i.e. ")"), then:
            else if (c == ')')
            {
				// while the operator at the top of the operator stack is not a left bracket:
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(')
                {
					// pop operators from the operator stack onto the output queue.
                    outputSB.append(operatorStack.pop());
                }
				// pop the left bracket from the stack.
                operatorStack.pop();
                /* if the stack runs out without finding a left bracket, then there are
                mismatched parentheses. */
            }
        }
		// if there are no more tokens to read:
		// while there are still operator tokens on the stack:
        while (!operatorStack.isEmpty())
        {
            /* if the operator token on the top of the stack is a bracket, then
            there are mismatched parentheses. */
			// pop the operator onto the output queue.
            outputSB.append(operatorStack.pop());
        }
		// exit
        return outputSB.toString();
    }

    /**
     * Checks if character is a number.
     * @param c
     * @return true if character is a number
     */
    public boolean isNumeric(char c)
    {
        return Character.getNumericValue(c) >= 0;
    }

    /**
     * Checks if character is one of the following operators: +, -, x, /, or ^.
     * @param c
     * @return true if character is +, -, x, /, or ^
	 */
    public boolean isOperator(char c)
    {
        return getOperatorType(c) != Operator.None;
    }
	
    /**
     * Gets the operator type of the given character.
     * @param c
     * @return Operator type if character is an operator, "None" otherwise
     */
    public Operator getOperatorType(char c)
    {
        switch (c)
        {
            case '+':   return Operator.Add;
            case '-':   return Operator.Subtract;
            case '*':   return Operator.Multiply;
            case '/':   return Operator.Divide;
            case '^':   return Operator.Exponent;
        }
        return Operator.None;
    }

    /**
     * Gets the precedence priority of the given operator (PE>MD>AS).
     * @param o
     * @return Precedence priority, with higher value meaning higher priority
     */
    public int getPrecedence(Operator o)
    {
        switch (o)
        {
            case Add:
            case Subtract:
                return 1;
            case Multiply:
            case Divide:
                return 2;
            case Exponent:
                return 3;
        }
        return 0;
    }

    /**
     * Gets whether the given operator is left-associative.
     * @param o
     * @return True if left-associative, false if right-associative
     */
    public boolean isLeftAssociative(Operator o)
    {
        // Only exponent operator is right associative
        return o != Operator.Exponent;
    }

    /**
     * Operator types.
     */
    public enum Operator
    {
        Add,
        Subtract,
        Multiply,
        Divide,
        Exponent,
        None
    }
}