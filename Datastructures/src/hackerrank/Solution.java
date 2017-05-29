package hackerrank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Solution {
	static public String[] findNonMatching(String s, String t)
	{
		if(t==null || t.trim().length()==0)
			return s.split("\\s+");
		
		List<String> subsequence = new ArrayList<>();
		String[] main = s.split("\\s+");
		String[] sub = t.split("\\s+");
		LinkedHashSet<String> set = new LinkedHashSet<>();
		for(String x:main)
			set.add(x);
		for(String x:sub)
			if(!set.contains(x))
				break;
			else
				set.remove(x);
		for(String x: set)
			subsequence.add(x);
		return subsequence.toArray(new String[subsequence.size()]);
	}
	
	static public String[] findNonMatching1(String s, String t)
	{
		List<String> subsequence = new ArrayList<>();
		String[] main = s.split("\\s+");
		String[] sub = t.split("\\s+");
		
		int i=0,j=0;
		for(;i<main.length && j<sub.length;i++)
		{
			if(!main[i].equals(sub[j]))
				subsequence.add(main[i]);
			else
				j++;
			
		}
		
		for(int k=i;k<main.length;k++)
			subsequence.add(main[k]);
		return subsequence.toArray(new String[subsequence.size()]);
	}
	
	static int maxDifference(int[] a) {
		int temp = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i:a)
		{
			if(temp>=i)
			{
				temp = i;
				continue;
			}
			
			int currMax = i - temp;
			if(currMax>max)
				max = currMax;
		}
		if(max == Integer.MIN_VALUE) 
			return -1;
		return max;

    }
	
	static public String[] formatDates(String[] dates)
	{
		String[] output = new String[dates.length];
		for(int i=0;i<dates.length;++i)
		{
			String s = dates[i];
			String[] parts = s.split(" ");
			if(parts[0].length()==3)
				parts[0] = parts[0].substring(0, 1);
			else
				parts[0] = parts[0].substring(0, 2);
			
			s = String.join(" ", parts);
			
			SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date d = null;
			try {
				d = sdf.parse(s);
				output[i] = sdf1.format(d);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return output;
	}
	
	public static void main(String[] args) {
		/*int [] n = {7,9,5,6,3,2};
		System.out.println(maxDifference(n));
		n = new int[]{2,3,10,2,4,8,1};
		System.out.println(maxDifference(n));
		n = new int[]{6,5,4,3,2,1};
		System.out.println(maxDifference(n));
		n = new int[]{1,1,1,1,1};
		System.out.println(maxDifference(n));
		n = new int[]{4,3,2,1,1};
		System.out.println(maxDifference(n));*/
		
//		System.out.println(Arrays.toString(findNonMatching("I am using Hackerrank to improve programming", "am hackerrank to improve")));
		System.out.println(Arrays.toString(formatDates(new String[]{"1st Mar 1984","1st Mar 1986"})));;
	}
	
}
