package string;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringPermutations {
	private Map<Character, Integer> countsByChars = new LinkedHashMap<>();
	
	public void permute(String str)
	{
		if(str==null || str.length()==0) return;
		int n = str.length();
		for(Character c: str.toCharArray())
		{
			countsByChars.computeIfAbsent(c, k-> 0);
			countsByChars.computeIfPresent(c, (k,v)->++v);
		}
		System.out.println(countsByChars);
		this.permuteUtil(str,countsByChars, new char[n], 0);
	}
	
	private void permuteUtil(String str,Map<Character, Integer> countsByChars, char[] permutation, int current)
	{
		if(current==str.length())
		{
			System.out.println("Possible permutation: "+new String(permutation));
			return;
		}
		for(Character c: countsByChars.keySet())
		{
			if(countsByChars.get(c)!=0)
			{
				
				permutation[current]=c;
				countsByChars.computeIfPresent(c, (k,v)->--v);
				permuteUtil(str, countsByChars, permutation, current+1);
				countsByChars.computeIfPresent(c, (k,v)->++v);
				
			}
		}
	}
	
	public static void main(String[] args) {
		new StringPermutations().permute("AABC");
	}
}
