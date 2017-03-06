package net.thecatcher.sourceCounter.Utils;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CounterUtils {
	
	public static Map<String, Integer> sourceFileCounter(File sourceFile) throws IOException{
		int sourceCount =0;
		int commentCount=0;
		Map<String, Integer> counterMap = new HashMap<String, Integer>();
		
		FileReader fr = new FileReader(sourceFile);
		BufferedReader br = new BufferedReader(fr);
		
		Pattern singleCommentPattern = Pattern.compile("\\/\\/"); 
		Pattern blockCommentPattern1 = Pattern.compile("\\/\\*");
		Pattern blockCommentPattern2 = Pattern.compile("\\*");
		Pattern blankPattern = Pattern.compile("\\s*|\t|\r|\n");
		
		String eachLine = null;
		
		while((eachLine=br.readLine())!=null){
			Matcher singleCommentMatcher = singleCommentPattern.matcher(eachLine);
			Matcher blockComment1Matcher = blockCommentPattern1.matcher(eachLine);
			Matcher blockComment2Matcher = blockCommentPattern2.matcher(eachLine);
			Matcher blankMatcher = blankPattern.matcher(eachLine);
			if(singleCommentMatcher.find()||blockComment1Matcher.find()||blockComment2Matcher.find()){
				commentCount++;
			}else if (blankMatcher.matches()) {
				continue;
			}else {
				sourceCount++;
			}
		}
		
		counterMap.put("sourceCount", sourceCount);
		counterMap.put("commentCount",commentCount);
		
		return counterMap;
	}
	
	
}
