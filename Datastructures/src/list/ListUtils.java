package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils {
	private ListUtils() {
	}

	static public int add(int a, int b) {
		Integer[] c1 = toIntArray(a);
		Integer[] c2 = toIntArray(b);
		return addLists(toList(c1), toList(c2));
	}

	static public int subtract(int a, int b) {
		Integer[] c1 = toIntArray(a);
		Integer[] c2 = toIntArray(b);
		return subtractLists(toList(c1), toList(c2));
	}

	public static int addLists(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		int size1 = l1.size();
		int size2 = l2.size();
		LinkedList<Integer> largerList = l1, smallerList = l2;
		int resultSize = size1;
		if (size2 > size1) {
			largerList = l2;
			smallerList = l1;
			resultSize = size2;
		}
		int carry = sum(largerList.start, smallerList.start, 0);
		int result = 0;
		for (int i = 0; i < largerList.size(); i++)
			result = (int) (result + largerList.get(i) * Math.pow(10, i));
		result = carry * (10 ^ resultSize) + result;
		return result;
	}

	public static int subtractLists(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		int size1 = l1.size();
		int size2 = l2.size();
		int signage = 1;
		LinkedList<Integer> largerList = l1, smallerList = l2;
		if (size2 > size1) {
			largerList = l2;
			smallerList = l1;
			signage = -1;
		}
		if (size1 == size2) {
			Node<Integer> curr1 = l1.start, curr2 = l2.start;
			int diff = 0;
			while (curr1.next != null) {
				diff += (curr1.data - curr2.data);
				curr1 = curr1.next;
				curr2 = curr2.next;
			}
			if (diff < 0) {
				largerList = l2;
				smallerList = l1;
				signage = -1;
			}
		}
		diff(largerList.start, smallerList.start, 0);
		int result = 0;
		for (int i = 0; i < largerList.size(); i++)
			result = (int) (result + largerList.get(i) * Math.pow(10, i));
		return signage * result;
	}

	public static int diff(Node<Integer> start1, Node<Integer> start2, int prevCarry) {
		if (start1 == null)
			return prevCarry;
		int result = 0;
		if (start2 == null)
			result = start1.data - prevCarry;
		else {
			result = start1.data - start2.data - prevCarry;
		}
		if (result < 0) {
			result = 10 + result;
			prevCarry = 1;
		} else
			prevCarry = 0;
		start1.data = result;
		if (start2 == null)
			return diff(start1.next, null, prevCarry);
		return diff(start1.next, start2.next, prevCarry);
	}

	public static int sum(Node<Integer> start1, Node<Integer> start2, int prevCarry) {
		if (start1 == null)
			return prevCarry;
		int result = 0;
		if (start2 == null)
			result = start1.data + prevCarry;
		else
			result = start1.data + start2.data + prevCarry;
		prevCarry = result / 10;
		if (result >= 10)
			result = result % 10;
		start1.data = result;
		if (start2 == null)
			return sum(start1.next, null, prevCarry);
		return sum(start1.next, start2.next, prevCarry);
	}

	public static LinkedList<Integer> toList(Integer[] arr) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i : arr)
			list.insert(i);
		return list;
	}

	private static Integer[] toIntArray(int number) {
		List<Integer> i = new ArrayList<>();
		for (int temp = number % 10; number > 0;) {
			i.add(temp);
			number /= 10;
			temp = number % 10;
		}
		return i.toArray(new Integer[i.size()]);
	}

	public static <T> void reverse(LinkedList<T> list) {
		reversify(null, list.start, list);
	}

	private static <T> void reversify(Node<T> prev, Node<T> curr, LinkedList<T> list) {
		if (curr == null) {
			list.start = prev;
			return;
		}
		reversify(curr, curr.next, list);
		curr.next = prev;
	}

	public static <T> void reverseIterative(LinkedList<T> list) {
		if (list == null || list.start.next == null)
			return;
		Node<T> prev = null, curr = list.start;
		while (curr != null) {
			Node<T> next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		list.start = prev;
	}

	/**
	 * 
	 * Input : List = 2->3->7->3->2->12->24
	 * 
	 * Output : 5
	 * 
	 * The longest palindrome list is 2->3->7->3->2
	 * 
	 * Input : List = 12->4->4->3->14
	 * 
	 * Output : 2
	 * 
	 * The longest palindrome list is 4->4
	 * 
	 * @param list
	 * 
	 * @return
	 * 
	 */

	private static <T> int countSame(Node<T> n1, Node<T> n2) {
		int count = 0;
		while (n1 != null && n2 != null && n1.data == n2.data) {
			++count;
			n1 = n1.next;
			n2 = n2.next;
		}
		return count;
	}

	public static <T> int lengthOfLargestPalindrome(LinkedList<T> list) {
		int result = 0;
		Node<T> prev = null, curr = list.start;
		while (curr != null) {
			Node<T> next = curr.next;
			result = Math.max(result, countSame(curr, next) + 1);
			result = Math.max(result, 2 * countSame(prev, next) + 1);
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		list.start = curr;
		return result;
	}

	/**
	 * 
	 * Input : 1->2->3->4->5
	 * 
	 * Output: 2->1->4->3->5
	 * 
	 * @param list
	 * 
	 */

	static public <T> void reversePairs(LinkedList<T> list) {
		Node<T> curr = list.start;
		while (curr != null) {
			Node<T> nextNode = curr.next;
			if (nextNode == null)
				break;
			T temp = nextNode.data;
			nextNode.data = curr.data;
			curr.data = temp;
			nextNode = nextNode.next;
			curr = nextNode;
		}
	}

	/**
	 * 
	 * Input : 1->4->3->2->5->2->3,
	 * 
	 * x = 3
	 * 
	 * Output: 1->2->2->3->3->4->5
	 * 
	 * Input : 1->4->2->10
	 * 
	 * x = 3
	 * 
	 * Output: 1->2->4->10
	 * 
	 * Input : 10->4->20->10->3
	 * 
	 * x = 3
	 * 
	 * Output: 3->10->4->20->10
	 * 
	 * @param list
	 * 
	 * @param x
	 * 
	 */

	static public void partitionlist(LinkedList<Integer> list, int x) {
		if (list == null || list.start == null)
			return;
		Node<Integer> smallerStart = null, equalStart = null, greaterStart = null;
		Node<Integer> smallerEnd = null, equalEnd = null, greaterEnd = null;
		Node<Integer> curr = list.start;
		Node<Integer> newStart = null;
		while (curr != null) {
			Node<Integer> temp = null;
			if (curr.data < x) {
				if (smallerStart == null)
					smallerStart = smallerEnd = curr;
				if (smallerEnd != curr) {
					smallerEnd.next = curr;
					smallerEnd = curr;
				}
			} else if (curr.data > x) {
				if (greaterStart == null)
					greaterStart = greaterEnd = curr;
				if (greaterEnd != curr) {
					greaterEnd.next = curr;
					greaterEnd = curr;
				}
			} else {
				if (equalStart == null)
					equalStart = equalEnd = curr;
				if (equalEnd != curr) {
					equalEnd.next = curr;
					equalEnd = curr;
				}
			}
			curr = curr.next;
		}
		if (smallerStart != null) {
			newStart = smallerStart;
			smallerEnd.next = (equalStart != null ? equalStart : greaterStart);
		} else if (equalStart != null) {
			newStart = equalStart;
			equalEnd.next = greaterStart;
		} else
			newStart = greaterStart;
		list.start = newStart;
	}

	/**
	 * 
	 * Input : list1 = 10->20
	 * 
	 * list2 = 5->10->20
	 * 
	 * Output : LIST FOUND
	 * 
	 * Input : list1 = 1->2->3->4
	 * 
	 * list2 = 1->2->1->2->3->4
	 * 
	 * Output : LIST FOUND
	 * 
	 * Input : list1 = 1->2->3->4
	 * 
	 * list2 = 1->2->2->1->2->3
	 * 
	 * Output : LIST NOT FOUND
	 * 
	 */

	static public boolean containsSublist(LinkedList<Integer> mainList, LinkedList<Integer> subList) {
		if (mainList == null || subList == null || mainList.start == null || subList.start == null)
			return false;
		boolean flag = false;
		Node<Integer> subStart = subList.start;
		Node<Integer> curr = mainList.start;
		while (curr != null) {
			if (flag && subStart == null)
				return true;
			/**
			 * * sublist ended and all of it is present in mail list, break the
			 * loop and return true *
			 */
			if (curr.data == subStart.data) {
				flag = true;
				subStart = subStart.next;
			} else {
				flag = false;
				subStart = subList.start;
				/** * mismatch, hence reset it to start */
			}
			curr = curr.next;
		}
		if (flag && subStart != null)
			/**
			 * * sublist was matching with main list, but main list ended and
			 * some part of sublist is still yet to be checked, hence false
			 */
			flag = false;
		return flag;
	}

	static public void printPairsMatchingSum(DoublyLinkedList<Integer> sortedDLL, int sum) {
		if (sortedDLL == null || sortedDLL.start == null)
			return;
		DLLNode<Integer> start, end;
		start = end = sortedDLL.start;
		while (end.next != null)
			end = end.next;
		while (start != end) {
			int currSum = start.data + end.data;
			if (currSum == sum) {
				System.out.println("Found a pair(" + start.data + "," + end.data + ")");
				start = start.next;
				if (start == end)
					break;
				end = end.prev;
				if (start == end)
					break;
			} else if (currSum > sum)
				end = end.prev;
			else
				start = start.next;
		}
	}

	/**
	 * 
	 * Input: [1 3 8 2 7 5 6 4]
	 * 
	 * Output: [1 8 2 7 3 6 4 5]
	 * 
	 * Input: [1 2 3 4 5 6 7]
	 * 
	 * Output: [1 7 2 6 3 5 4]
	 * 
	 * Input: [1 6 2 5 3 4]
	 * 
	 * Output: [1 6 2 5 3 4]
	 * 
	 * @param arr
	 * 
	 * @return
	 * 
	 */

	static public <T extends Comparable<T>> LinkedList<T> alternateMinMaxList(T[] arr) {
		Arrays.sort(arr);
		int n = arr.length;
		int start = 0;
		int end = n - 1;
		LinkedList<T> list = new LinkedList<>();
		while (start < end) {
			list.insert(arr[start++]);
			list.insert(arr[end--]);
		}
		if (n % 2 != 0)
			list.insert(arr[start]);
		return list;
	}

	/**
	 * 
	 * Input : 0->0->0->1->1->0->0->1->0
	 * 
	 * Output : 50
	 * 
	 * Input : 1->0->0
	 * 
	 * Output : 4
	 * 
	 * @param list
	 * 
	 * @return
	 * 
	 */

	static public double binaryToDecimal(LinkedList<Integer> list) {
		if (list == null || list.start == null)
			return 0;
		int n = list.size();
		return computeDecimal(list.start, n);
	}

	static private double computeDecimal(Node<Integer> start, int n) {
		if (start == null)
			return 0;
		return (start.data * Math.pow(2, n - 1)) + computeDecimal(start.next, --n);
	}

	static public LinkedList<Integer> decimalToBinary(int decimal) {
		LinkedList<Integer> binary = new LinkedList<Integer>();
		int res = 0;
		do {
			res = decimal % 2;
			binary.insert(res);
			decimal = decimal / 2;
		} while (decimal != 0);
		reverse(binary);
		return binary;
	}

	// ############## MERGING ALGOS
	// ############################################################################################################################################

	/**
	 *
	 * 
	 * 
	 * @param list
	 * 
	 * @return
	 * 
	 */

	static public <T> LinkedList<T> partition(LinkedList<T> mainList) {
		if (mainList == null || mainList.start == null)
			return null;
		Node<T> slow, fast;
		slow = mainList.start;
		fast = mainList.start.next;
		while (fast != null) {
			fast = fast.next;
			if (fast == null)
				break;
			fast = fast.next;
			if (fast != null)
				slow = slow.next;
		}
		Node<T> temp = slow.next;
		slow.next = null;
		if (temp != null)
			return new LinkedList<>(temp);
		return null;
	}

	static public LinkedList<Integer> mergeSort(LinkedList<Integer> list) {
		if (list == null || list.start == null)
			return null;
		LinkedList<Integer> second = partition(list);
		if (list.size() > 1)
			list = mergeSort(list);
		if (second.size() > 1)
			second = mergeSort(second);
		return merge(list, second);
	}

	static public LinkedList<Integer> merge(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
			return null;
		if (list2 == null)
			return list1;
		if (list1 == null)
			return list2;
		Node<Integer> resultNode = null;
		resultNode = merge(list1.start, list2.start);
		return new LinkedList<>(resultNode);
	}

	private static Node<Integer> merge(Node<Integer> start1, Node<Integer> start2) {
		Node<Integer> curr = null;
		if (start1 == null)
			return start2;
		if (start2 == null)
			return start1;
		if (start1.data < start2.data) {
			curr = start1;
			curr.next = merge(start1.next, start2);
		} else {
			curr = start2;
			curr.next = merge(start1, start2.next);
		}
		return curr;
	}

	/**
	 * 
	 * For example, if first list is 5->7->17->13->11 and second is
	 * 12->10->2->4->6,
	 * 
	 * the first list should become 5->12->7->10->17->2->13->4->11->6 and second
	 * list should become empty.
	 * 
	 * The nodes of second list should only be inserted when there are positions
	 * available.
	 * 
	 * For example, if the first list is 1->2->3 and second list is
	 * 4->5->6->7->8, then first list should become 1->4->2->5->3->6 and second
	 * list to 7->8.
	 * 
	 * @param list1
	 * 
	 * @param list2
	 * 
	 * @return
	 * 
	 */

	static public LinkedList<Integer> mergeAlternatively(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		if ((list1 == null || list1.start == null) && (list2 == null || list2.start == null))
			return null;
		if (list2 == null)
			return list1;
		if (list1 == null)
			return list2;
		Node<Integer> resultNode = null;
		resultNode = mergeAlternatively(list1.start, list2.start, 0);
		return new LinkedList<>(resultNode);
	}

	private static Node<Integer> mergeAlternatively(Node<Integer> start1, Node<Integer> start2, int round) {
		Node<Integer> curr = null;
		if (start1 == null)
			return start2;
		if (start2 == null)
			return start1;
		if (round % 2 == 0) {
			curr = start1;
			curr.next = mergeAlternatively(start1.next, start2, ++round);
		} else {
			curr = start2;
			curr.next = mergeAlternatively(start1, start2.next, ++round);
		}
		return curr;
	}

	/**
	 * 
	 * Mergesort a dll
	 * 
	 */
	static public <T> DoublyLinkedList<T> divideMiddle(DoublyLinkedList<T> mainList) {
		if (mainList == null || mainList.start == null)
			return null;
		DLLNode<T> slow, fast;
		slow = mainList.start;
		fast = mainList.start.next;
		while (fast != null) {
			fast = fast.next;
			if (fast == null)
				break;
			fast = fast.next;
			if (fast != null)
				slow = slow.next;
		}
		DLLNode<T> temp = slow.next;
		slow.next = null;
		temp.prev = null;
		return new DoublyLinkedList<>(temp);
	}

	static public DoublyLinkedList<Integer> mergeSort(DoublyLinkedList<Integer> dll) {
		if (dll == null || dll.start == null)
			return null;
		DoublyLinkedList<Integer> second = divideMiddle(dll);
		mergeSort(dll);
		mergeSort(second);
		return merge(dll, second);
	}

	static public DoublyLinkedList<Integer> merge(DoublyLinkedList<Integer> dll1, DoublyLinkedList<Integer> dll2) {
		if (dll1 == null && dll2 == null)
			return null;
		if (dll2 == null)
			return dll1;
		if (dll1 == null)
			return dll2;
		DLLNode<Integer> mergedNode = merge(dll1.start, dll2.start);
		if (mergedNode != null)
			return new DoublyLinkedList<>(mergedNode);
		return null;
	}

	static public DLLNode<Integer> merge(DLLNode<Integer> first, DLLNode<Integer> second) {
		if (first == null && second == null)
			return null;
		if (second == null)
			return first;
		if (first == null)
			return second;
		DLLNode<Integer> curr;
		if (first.data < second.data) {
			curr = first;
			curr.next = merge(first.next, second);
			curr.next.prev = curr;
		} else {
			curr = second;
			curr.next = merge(first, second.next);
			curr.next.prev = curr;
		}
		return curr;
	}

	/**
	 * 
	 * Input:
	 * 
	 * First List: 2->4->7->8->10
	 * 
	 * Second List: 1->3->12
	 * 
	 * Output:
	 * 
	 * First List: 1->2->3->4->7
	 * 
	 * Second List: 8->10->12
	 * 
	 */

	static public void mergeInPlace(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		int size = list1.size();
		list1.start = merge(list1.start, list2.start);
		Node<Integer> curr = list1.start;
		for (int i = 1; i <= size - 1; i++)
			curr = curr.next;
		list2.start = curr.next;
		curr.next = null;
	}

	// ############## MERGING ALGOS END

	static public <T> void deleteMiddle(LinkedList<T> list) {
		if (list == null || list.start == null)
			return;
		Node<T> slow, fast;
		slow = list.start;
		fast = list.start.next;
		if (fast == null) {
			list.start = null;
			return;
		}
		while (fast != null) {
			fast = fast.next;
			if (fast == null)
				break;
			fast = fast.next;
			if (fast != null)
				slow = slow.next;
		}
		slow.next = slow.next.next;
	}

	/**
	 * Input : 1 -> -10 output: -10 -> 1
	 * 
	 * Input : 1 -> -2 -> -3 -> 4 -> -5 output: -5 -> -3 -> -2 -> 1 -> 4
	 * 
	 * Input : -5 -> -10 Output: -10 -> -5
	 * 
	 * Input : 5 -> 10 output: 5 -> 10
	 * 
	 * @param list
	 */
	static public void sortAbsolutelySortedList(LinkedList<Integer> list) {
		if (list == null || list.start == null)
			return;
		Node<Integer> prev = list.start;
		Node<Integer> curr = prev.next;
		while (curr != null) {
			if (curr.data < list.start.data) {
				prev.next = curr.next;
				curr.next = list.start;
				list.start = curr;
				curr = prev.next;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}

	/**
	 * 
	 * Input: 1->2->3->4
	 * 
	 * Output: 1->3->2->4
	 * 
	 * Input: 10->22->30->43->56->70
	 * 
	 * Output: 10->30->56->22->43->70
	 * 
	 */

	static public void arrangeOddEvenIndexesTogether(LinkedList<Integer> list) {
		if (list == null || list.start == null)
			return;
		Node<Integer> odd, even, evenStart;
		odd = list.start;
		even = odd.next;
		evenStart = even;
		if (even == null)
			return;
		int i = 1;
		while (even != null && even.next != null) {
			if (i % 2 == 1) {
				odd.next = even.next;
				odd = odd.next;
			} else {
				even.next = odd.next;
				even = even.next;
			}
			++i;
		}
		odd.next = evenStart;
		if (even != null)
			even.next = null;
	}

	static public int lexicalCompare(LinkedList<Character> list1, LinkedList<Character> list2)

	{

		int flag = 0;

		return flag;

	}

	static private int lexicalDiff(Node<Character> start1, Node<Character> start2)

	{

		if (start1 == null && start2 == null)

			return 0;

		if (start2 == null)

			return 1;

		if (start1 == null)

			return -1;

		int diff = start1.data - start2.data;

		if (diff != 0)

			return diff;

		return lexicalDiff(start1.next, start2.next);

	}

	static public <T> boolean swap(T data1, T data2, LinkedList<T> list) {
		if (list == null || list.start == null)
			return false;
		if (data1 == list.start.data && data2 == list.start.data)
			return true;
		Node<T> prevFirst, prevSecond;
		prevFirst = prevSecond = null;
		boolean swapStart = false;
		if (list.start.data == data1) {
			swapStart = true;
			prevSecond = list.start;
			while (prevSecond != null && prevSecond.next.data != data2)
				prevSecond = prevSecond.next;
		}
		if (list.start.data == data2) {
			swapStart = true;
			prevFirst = list.start;
			while (prevFirst != null && prevFirst.next.data != data1)
				prevFirst = prevFirst.next;
		} else {
			prevFirst = prevSecond = list.start;
			while (prevFirst != null && prevFirst.next.data != data1)
				prevFirst = prevFirst.next;
			while (prevSecond != null && prevSecond.next.data != data2)
				prevSecond = prevSecond.next;
		}
		if (prevFirst.next == null || prevSecond.next == null)
			return false;
		Node<T> first, second;
		if (swapStart) {
			first = list.start;
			second = (prevFirst != null ? prevFirst : prevSecond);
			list.start = second;
		} else {
			first = prevFirst.next;
			second = prevSecond.next;
		}
		if (first.next == second) {
			first.next = second.next;
			second.next = first;
		} else {
			Node<T> temp = second.next;
			second.next = first.next;
			first.next = temp;
		}
		if (prevFirst != null)
			prevFirst.next = second;
		if (prevSecond != null)
			prevSecond.next = first;
		return true;
	}

	/**
	 * if the given linked list is 10->20->30->40->50->60 and k is 4, the list
	 * should be modified to 50->60->10->20->30->40
	 * 
	 * @param list
	 * @param n
	 */
	static public <T> void rotateBy(LinkedList<T> list, int n) {
		if (list == null || n < 2 || n > list.size())
			return;

		Node<T> curr = list.start;
		for (int i = 1; i < n; i++)
			curr = curr.next;
		Node<T> newStart = curr.next;
		curr.next = null;
		curr = newStart;
		while (curr.next != null)
			curr = curr.next;
		curr.next = list.start;
		list.start = newStart;
	}

	/**
	 * Input: List1: 10->15->4->20 lsit2: 8->4->2->10 Output: Intersection List:
	 * 4->10 Union List: 2->8->20->4->15->10
	 */

	static public void unionIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		if ((list1 == null || list1.start == null) && (list2 == null && list2.start == null))
			return;
		LinkedList<Integer> union, intersection;
		if (list1 == null || list1.start == null)
			union = intersection = list2;
		else if (list2 == null && list2.start == null)
			union = intersection = list1;
		else {
			union = new LinkedList<>();
			intersection = new LinkedList<>();
			list1 = mergeSort(list1);
			list2 = mergeSort(list2);
			LinkedList<Integer> mergedList = merge(list1, list2);
			Node<Integer> curr = mergedList.start, prev = null;
			int repeat = 0;
			while (curr != null && curr.next != null) {
				if (curr.data == curr.next.data) {
					++repeat;
					curr = curr.next;
				} else {
					if (repeat > 0) {
						Node<Integer> temp = curr.next;
						curr.next = null;
						intersection.insert(curr.data);
						curr = temp;
						if (prev == null)
							mergedList.start = curr;
						else
							prev.next = curr;
						repeat = 0;
					} else {
						prev = curr;
						curr = curr.next;
					}
				}
			}

		}
		System.out.println("Union = " + union);
		System.out.println("Intersection = " + intersection);
	}

	static public <T> void swapKthFromStartAndEnd(LinkedList<T> list, int k) {
		if (list == null || list.start == null)
			return;
		int n = list.size();
		if (k > n)
			return;
		int m = n - k + 1;
		if (m == k)
			return;
		Node<T> prevFirst = null, prevSecond = null, first = null, second = null;
		Node<T> curr = list.start;
		
		//the prevFirst and prevSecond are the prev nodes of the actual nodes to be swapped
		//first and second are the actual nodes to swap
		for (int i = 1; i <= n; i++) {
			if (i < k)
				prevFirst = curr;
			if (i < m)
				prevSecond = curr;
			curr = curr.next;
		}
		second = prevSecond.next;
		
		//it may happen that we need to swap first and last in which case prevFirst remains null and first becomes list.start and list.start=second
		if (prevFirst != null) {
			first = prevFirst.next;
		} else {
			first = list.start;
			list.start = second;
		}
		
		//adjacent nodes
		if (first.next == second) {
			first.next = second.next;
			second.next = first;
			prevFirst.next = second;
		} else {
			Node<T> temp = second.next;
			second.next = first.next;
			if (prevFirst != null)
				prevFirst.next = second;
			prevSecond.next = first;
			first.next = temp;
		}
	}

	static public boolean identical(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		if (list1 == null && list2 == null)
			return true;
		return checkIfEqual(list1.start, list2.start);
	}

	static private boolean checkIfEqual(Node<Integer> start1, Node<Integer> start2) {
		if (start1 == null && start2 == null)
			return true;
		if (start1 == null || start2 == null)
			return false;
		return (start1.data == start2.data) && checkIfEqual(start1.next, start2.next);
	}

	static public <T> void deleteAlternate(LinkedList<T> list, boolean even) {
		if (list == null || list.start == null)
			return;
		
		if (!even) {
			list.start = list.start.next;			
		}
		Node<T> curr = list.start;
		while (curr != null && curr.next != null) {
			Node<T> next = curr.next;
			curr.next = next.next;
			curr = curr.next;
			next.next = null;
		}
	}

	/**
	 * 
	 * 1. Reverse the list.
	 * 
	 * 2. Traverse the reversed list. Keep max till now. If next node < max,
	 * then delete the next node, otherwise max = next node.
	 * 
	 * 3. Reverse the list again to retain the original order. Time Complexity:
	 * O(n) Thanks to R.Srinivasan for providing below code.
	 * 
	 * @param list
	 * 
	 */
	static public void deleteIfRightIsHigher(LinkedList<Integer> list) {
		if (list == null || list.start == null)
			return;
		reverse(list);
		Node<Integer> current, max, temp;
		current = max = list.start;
		while (current != null && current.next != null) {
			if (current.next.data < max.data) {
				temp = current.next;
				current.next = temp.next;
				temp.next = null;
			} else {
				max = current;
				current = current.next;
			}
		}
		reverse(list);
	}
	
	/*
	 *  Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
		Output:   3->2->1->4->5->6->9->8->7->NULL. 
	 */
	static public <T> void reverseAlternativelyAfterK(LinkedList<T> list, int k) {
		int size = list.size();
		boolean toReverse = true;
		for (int i = 1; i <= size; i++) {
			if (i % k == 1) {

				if (toReverse) {
					reverse(list, i, k);
				}
				toReverse = !toReverse;
			}

		}
	}

	static public <T> void reverse(LinkedList<T> list, int start, int offset) {
		if (list == null || list.start == null)
			return;
		
		/**
		 *  calculate the real offset, it may happen than the remaining length of list is less than offset
		 *  in which case offset = remaining length;
		 */
		int size = list.size();
		if ((offset + start - 1) > size)
			offset = size - start;
		
		//init prev curr first and last pointers
		// first = node before the first node of swapped sublist
		// last = 
		Node<T> prev = null, curr = list.start, first, last;
		for (int i = 1; i < start; i++) {
			prev = curr;
			curr = curr.next;
		}
		first = prev;
		last = curr;
		Node<T> next = curr.next;
		for (int i = 1; i <= offset; i++) {
			curr.next = prev;
			prev = curr;
			curr = next;
			if (next != null)
				next = next.next;
		}
		last.next = curr;
		if (first != null)
			first.next = prev;
		else
			list.start = prev;
	}
}