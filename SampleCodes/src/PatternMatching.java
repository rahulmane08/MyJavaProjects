import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternMatching {
	static boolean match (final String patternString, final String targetString) {
		Pattern pattern = Pattern.compile(createRegexPattern(patternString));
		Matcher matcher = pattern.matcher(targetString);
		
		if(matcher.find())
		{
			System.out.println("matched = "+matcher.group());
			return true;
		}
		return false;
	}
	
	private static String createRegexPattern(String str)
	{
		System.out.println("string = "+str);
		str = str.replaceAll("\\*", "[\\\\w]+");
		System.out.println("regex string = "+str);
		return str;
	}
	public static void main(String[] args) {
		String str1 = "ABC.*.*.XCV1";
		String str2 = "ABC.EXG.*.XCV1";
		String tstr = "ABC.EXG.RAF.XCV1";
		System.out.println(match(str1, tstr));
		System.out.println(match(str2, tstr));
		System.out.println(match("ABC.EEE.RAF.XCV1", tstr));
	}
}
