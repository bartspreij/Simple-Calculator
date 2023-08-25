/**
 * ShuntingYard - Implementation of the Shunting Yard algorithm for converting infix expressions to Reverse Polish Notation (RPN).
 * Algorithm based on the blog post by Andrei Ciobanu: "Converting Infix to RPN (Shunting Yard Algorithm)"
 * Blog post link: https://www.andreinc.net/2010/10/05/converting-infix-to-rpn-shunting-yard-algorithm
 *
 * This class provides methods to convert infix expressions to RPN and evaluate RPN expressions.
 */
package dev.goochem.model;

import java.util.*;

public class ShuntingYard {

    final static Map<String, Operator> OPS = new HashMap<>();

    static {
        // Fill map with all operators
        for (Operator operator : Operator.values()) {
            OPS.put(String.valueOf(operator.symbol), operator);
        }
    }

    public static List<String> shuntingYard(List<String> tokens) {

        List<String> output = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (OPS.containsKey(token)) { // Token is operator
                while (!stack.isEmpty() && OPS.containsKey(stack.peek())) {
                    Operator currentOperator = OPS.get(token);
                    Operator topOperator = OPS.get(stack.peek());
                    if (currentOperator.comparePrecedence(topOperator) <= 0) {
                        output.add(stack.pop());
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack
                stack.push(token);

            } else { // Else add token to output buffer
                output.add(token);
            }
        }

        // While there are still operator tokens in the stack, pop them to output
        while(!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }
}

