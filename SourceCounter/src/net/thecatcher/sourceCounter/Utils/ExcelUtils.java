package net.thecatcher.sourceCounter.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {
	public static void writeToResultFile(int sourcCount,int commentsCount,String path) 
			throws IOException, BiffException, RowsExceededException, WriteException{
		String sourceCount = Integer.toString(sourcCount);
		String commentCount = Integer.toString(commentsCount);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		
		
		File resultFile = new File(path);
		Workbook book = Workbook.getWorkbook(resultFile);
		WritableWorkbook wb = Workbook.createWorkbook(resultFile, book);
		WritableSheet sheet = wb.getSheet(0);
		int rows = sheet.getRows();
		
		if(rows==0){
			Label dateLabel = new Label(0,0,"date");
			Label srcLabel = new Label(1,0,"srcCount");
			Label cmtLabel = new Label(2,0,"cmtCount");
			
			sheet.addCell(cmtLabel);
			sheet.addCell(srcLabel);
			sheet.addCell(dateLabel);
			rows++;
		}
		Label dateLabel = new Label(0,rows,date);
		Label srcCount = new Label(1,rows,sourceCount);
		Label cmtCount = new Label(2,rows,commentCount);
		
		sheet.addCell(dateLabel);
		sheet.addCell(srcCount);
		sheet.addCell(cmtCount);
		
		
		wb.write();
		wb.close();
		
	}
}
