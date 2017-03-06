package net.thecatcher.sourceCounter.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
	
	private static List<String> fileList = new ArrayList<String>();
	
	
	/**
	 * to get all files in a directory and it's child dir
	 * @param root
	 * @return
	 */
	private static void  getAllFiles(String root){
		File[] files = new File(root).listFiles();
		for(File eachFile : files){
			if(eachFile.isDirectory()){
				FileUtils.getAllFiles(eachFile.getAbsolutePath());
	//			fileList.add(eachFile.getAbsolutePath());
			}else {
				fileList.add(eachFile.getAbsolutePath());
			}
			
		}
	}
	
	public static List<String> findFiles(String root,String type){
		getAllFiles(root);
		List<String> sourceFile =new  ArrayList<String>();
		Pattern pattern = Pattern.compile("\\."+type+"$");
		for(String eachFile:fileList){
			Matcher matcher = pattern.matcher(eachFile);
			if(matcher.find()){
				sourceFile.add(eachFile);
			}

		}
		return sourceFile;
	}
}
