import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HelloWorld {
	  public static void main(String[] args) {

		   String pageSrc= "<img src=http://cdn.posh24.se/images/:profile/0fd7108c939ab4926a6d5bf33ee21938f alt=Keira Knightley/>";

		   Pattern urlPattern = Pattern.compile("src=(.*?) alt");
		   Matcher urlMatcher = urlPattern.matcher(pageSrc);

		   Pattern namePattern = Pattern.compile("alt=(.*?)/>");
		   Matcher nameMatcher = namePattern.matcher(pageSrc);
		   
		   while(urlMatcher.find()){
		 	System.out.println(urlMatcher.group(1));
		   }

		   while(nameMatcher.find()){
		 	System.out.println(nameMatcher.group(1));
		   }
	  }
}
