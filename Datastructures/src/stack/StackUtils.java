package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class StackUtils {
	private static HashMap<String, Integer> priortyMap = new HashMap<String, Integer>();
	private static HashMap<Character, Character> parenthesisPairs = new HashMap<Character, Character>();
	private static List<Character> operators = Arrays.asList(new Character[] { '/', '-', '+', '*' });
	private static List<Character> parenthesis = Arrays.asList(new Character[] { '(', ')', '{', '}', '[', ']' });
	static {
		priortyMap.put("+", 1);
		priortyMap.put("-", 1);
		priortyMap.put("/", 2);
		priortyMap.put("*", 2);
		priortyMap.put("(", 1);
		priortyMap.put(")", 1);

		parenthesisPairs.put(')', '(');
		parenthesisPairs.put('}', '{');
		parenthesisPairs.put(']', '[');
	}

	private static int checkPriority(String operator1, String operator2) {
		return (priortyMap.get(operator1)).compareTo(priortyMap.get(operator2));
	}

	private static int checkPriority(char operator1, char operator2) {
		return checkPriority("" + operator1, "" + operator2);
	}

	private static boolean isOperand(char operand) {
		return !priortyMap.containsKey("" + operand);
	}

	private static boolean isParenthesisPairs(char p1, char p2) {
		return ((parenthesisPairs.get(p1).equals(p2)) || (parenthesisPairs.get(p2).equals(p1)));
	}

	public static void convertToPostfix(String infixExp) {
		if (infixExp == null)
			return;
		char[] characters = infixExp.toCharArray();
		Stack<Character> s = new Stack<Character>();
		for (char t : characters) {

			// if its an operand then print
			if (isOperand(t))
				System.out.print(t);
			// if its a right brace, then pop and print until ( is encountered/
			// pop ( as well but dont print
			else if (t == ')') {
				for (char x = s.pop(); x != '(';) {
					System.out.print(x);
					x = s.pop();
				}
			} else {
				// if its a ( then push, this means ) is coming ahead
				if (t == '(')
					s.push(t);
				else {
					// its a operator
					if (s.isEmpty())
						s.push(t);
					else {
						do {
							char y = s.pop();
							if (y == '(' || checkPriority(y, t) < 0) {
								s.push(y);
								break;
							} else {
								System.out.print(y);

							}
						} while (!s.isEmpty());
						s.push(t);
					}

				}
			}
		}
		while (!s.isEmpty())
			System.out.print(s.pop());
	}

	public static void main(String[] args) {
		String infix = "A*B-(C+D)+E";
		// convertToPostfix(infix);
		evaluatePostfixExpression("123*+5-");
	}

	static public void evaluatePostfixExpression(String exp) {
		if (exp == null) {
			System.out.println(0);
			return;
		}
		char[] characters = exp.toCharArray();
		Stack<Double> s = new Stack<Double>();
		for (char t : characters) {
			if (isOperand(t))
				s.push(Double.parseDouble("" + t));
			else {
				double o1 = s.pop();
				double o2 = s.pop();
				double result = compute(o2, o1, t);
				s.push(result);
			}
		}
		System.out.println(s.pop());
	}

	static public boolean checkIfBalancedExpression(String expression) {
		if (null == expression)
			return false;
		char[] elements = expression.toCharArray();
		Stack<Character> charStack = new Stack<>();
		for (char c : elements) {
			if (parenthesis.contains(c)) {
				if (parenthesisPairs.containsKey(c)) {
					if (charStack.isEmpty() || !isParenthesisPairs(c, charStack.pop()))
						return false;
				} else
					charStack.push(c);
			}
		}

		return true;
	}

	static private double compute(double a, double b, char operator) {
		double result = 0;
		switch (operator) {
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		case '/':
			result = a / b;
			break;
		default:
			break;
		}
		return result;
	}

	public static <T> void reversify(Stack<T> s) {
		if (s == null || s.isEmpty())
			return;
		T elem = s.pop();
		reversify(s);
		insertAtBottom(s, elem);
	}

	private static <T> void insertAtBottom(Stack<T> s, T elem) {
		if (s.isEmpty()) {
			s.push(elem);
			return;
		}
		T curr = s.pop();
		insertAtBottom(s, elem);
		s.push(curr);
	}

	public static int[] findSpan(int[] arr) {
		int[] span = new int[arr.length];
		span[0] = 1;
		stack.Stack<Integer> stack = new stack.Stack<>(arr.length);
		for (int i = 1; i < arr.length; i++) {
			int p = 0;
			while (!stack.isEmpty())
				if (arr[i] > arr[stack.top()])
					stack.pop();
				else
					break;
			if (stack.isEmpty())
				p = -1;
			else
				p = stack.top();
			span[i] = i - p;
			stack.push(i);
		}

		return span;
	}

	static public int findPermutationsGreaterThanOriginalNumber(int n) {
		int result = 0;
		for (int i = 1; i <= 9; i++) {
			Stack<Integer> stack = new Stack<>();
			if (i <= n) {
				System.out.println("found = "+i);
				stack.push(i);
				result++;
			}
			while (!stack.isEmpty()) {
				int tp = stack.pop();
				for (int j = tp % 10; j <= 9; j++) {
					int x = tp * 10 + j;
					if (x <= n) {
						System.out.println("found = "+x);
						stack.push(x);
						result++;
					}
				}
			}
		}
		return result;
	}

	static public int[] findPrevGreaterOrSmallerElement(int[] arr, boolean greater) {
		int[] pgse = new int[arr.length];
		Stack<Integer> s = new Stack<>();
		for(int i=0;i<arr.length;i++)
		{
			while(!s.isEmpty() && (greater ? arr[s.peek()] <= arr[i] : arr[s.peek()] >= arr[i]))
				s.pop();
			if(s.isEmpty())
				pgse[i]=0;
			else
				pgse[i]=s.peek();
			s.push(i);
		}
		return pgse;
	}
	static public int[] findNextGreaterOrSmallerElement(int[] arr, boolean greater) {
		int[] ngse = new int[arr.length];
		Stack<Integer> s = new Stack<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			while (!s.isEmpty() && (greater ? arr[s.peek()] <= arr[i] : arr[s.peek()] >= arr[i]))
				s.pop();
			if (s.isEmpty())
				ngse[i] = -1;
			else
				ngse[i] = s.peek();
			s.push(i);
		}
		return ngse;
	}

	static public int[] findNextSmallerToNextGreater(int[] arr) {
		int[] result = new int[arr.length];
		int[] nge = findNextGreaterOrSmallerElement(arr, true);
		int[] nse = findNextGreaterOrSmallerElement(arr, false);
		for (int i = 0; i < nge.length; i++) {
			result[i] = -1;
			if (nge[i] != -1 && nse[nge[i]] != -1)
				result[i] = arr[nse[nge[i]]];
		}
		return result;
	}

	
	static public void sort(Stack<Integer> stack) {
		int curr = Integer.MIN_VALUE;
		sort(stack, curr);
		stack.pop();
	}

	static private void sort(Stack<Integer> s, int prev) {
		if (s.isEmpty()) {
			s.push(prev);
			return;
		}
		int curr = s.pop();
		System.out.println(curr + "," + prev + "," + s);
		if (prev > curr) {
			sort(s, prev);
		} else {
			sort(s, curr);
			curr = prev;
		}
		s.push(curr);
	}
}
