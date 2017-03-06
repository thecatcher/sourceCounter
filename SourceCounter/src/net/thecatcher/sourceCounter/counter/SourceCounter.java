package net.thecatcher.sourceCounter.counter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.thecatcher.sourceCounter.Utils.CounterUtils;
import net.thecatcher.sourceCounter.Utils.ExcelUtils;
import net.thecatcher.sourceCounter.Utils.FileUtils;

public class SourceCounter {
	private static String path;
	private static String fileType;
	private static String resultFile;
	private static Map<String, Integer> sourceCounterMap = new HashMap<String, Integer>();

	public static void main(String[] args) throws FileNotFoundException,
			IOException, RowsExceededException, BiffException, WriteException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(
				new File(
						"C:\\Users\\thecatcher\\Documents\\workbench\\SourceCounter\\src\\config.properties")));

		path = prop.getProperty("path");
		fileType = prop.getProperty("fileType");
		resultFile = prop.getProperty("resultPath");

		List<String> sourceFileList = FileUtils.findFiles(path, fileType);

		sourceCounterMap.put("sourceCount", 0);
		sourceCounterMap.put("commentCount", 0);
		for (String eachFile : sourceFileList) {
			Map<String, Integer> eachFileMap = CounterUtils
					.sourceFileCounter(new File(eachFile));
			sumCounter(eachFileMap, sourceCounterMap, "sourceCount");
			sumCounter(eachFileMap, sourceCounterMap, "commentCount");
		}
		// System.out.println(sourceCounterMap.get("sourceCount"));
		// System.out.println(sourceCounterMap.get("commentCount"));
		// System.out.println(sourceFileList.size());
		ExcelUtils.writeToResultFile(sourceCounterMap.get("sourceCount"),
				sourceCounterMap.get("commentCount"), resultFile);
		
		System.out.println("---------------------");
		System.out.println("done!");
		System.out.println("---------------------");
		System.out.println("source Code: "+sourceCounterMap.get("sourceCount"));
		System.out.println("commentCount: "+sourceCounterMap.get("commentCount"));
	}

	private static void sumCounter(Map<String, Integer> srcMap,
			Map<String, Integer> destMap, String key) {
		int srcCounter = srcMap.get(key);
		int destCounter = destMap.get(key);
		int sum = srcCounter + destCounter;

		destMap.put(key, sum);
	}

}
