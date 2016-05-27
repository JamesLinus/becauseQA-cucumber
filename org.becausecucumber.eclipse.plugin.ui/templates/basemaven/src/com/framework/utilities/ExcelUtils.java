/**
 * Project Name:PAF_HC
 * File Name:ExcelUtils.java
 * Package Name:com.hp.utility
 * Date:Aug 24, 20132:03:52 PM
 * Copyright (c) 2013, alterhu2020@gmail.com All Rights Reserved.
 *
 */

package com.framework.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;

/**
 * ClassName:ExcelUtils
 * 
 * Reason: TODO ADD REASON. Date: Aug 24, 2013 2:03:52 PM
 * 
 * @author huchan
 * 
 * @since JDK 1.6
 * @see
 * @version $Revision: 1.0 $
 */
public class ExcelUtils {

	private static Logger logger = Logger.getLogger(ExcelUtils.class);
	public static Workbook workbook;
	public static ExcelUtils INSTANCE = null;

	public static int rowindex = 0;
	public static boolean iterateAccount = false;
	
	public File excelfile=null;

	private ExcelUtils() {
		if (INSTANCE != null) {
			// SHOUT
			throw new IllegalStateException("Already instantiated");
		}
	}

	/**
	 * @Title: getInstance
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @return
	 * @return ExcelUtils return type
	 * @throws
	 */

	public static ExcelUtils getInstance() {
		// Creating only when required.
		if (INSTANCE == null) {
			INSTANCE = new ExcelUtils();

			// init the excel file
			ClassLoader classLoader = ExcelUtils.class.getClassLoader();
			File excelfile = INSTANCE.getExcelfile();
			if(excelfile==null){
				excelfile = new File(classLoader.getResource(
						GlobalDefinition.GLOBAL_EXCEL_FILE).getFile());
			}

			try {
				workbook = Workbook.getWorkbook(excelfile);
			} catch (BiffException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return INSTANCE;
	}
	
	/**
	 * @Title: getDataTableRowBasedOnTowParameterValues
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param sheetname
	 * @param @param firstparametervalue,secondparametervalue
	 * @param @return
	 * @return Map<String,String> return type
	 * @throws
	 */

	public Map<String, String> getRowdataViaOneColumns(
			String sheetname,
			String firstcolumn) {

		List<String> header = new ArrayList<String>();
		Map<String, String> rowmap = new HashMap<String, String>();

		boolean findrow = false;
		int rownumber = 0;
		Sheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();

		for (int columnindex = 0; columnindex < columns; columnindex++) {
			String headerelement = sheet.getCell(columnindex, 0).getContents()
					.trim();
			header.add(columnindex, headerelement);
		}

		for (int rowindex = getRowindex(); rowindex < rows; rowindex++) {
			// get the column 1 to get the host name,the column index begin with
			// 0
			String firstcontent = sheet.getCell(0, rowindex).getContents()
					.toLowerCase().trim();
			
			if (firstcontent.toLowerCase().contains(firstcolumn.toLowerCase().trim())) {
				logger.debug("Found the correct cell data,the case type we found in excel is:"
						+ firstcontent);
				findrow = true;
				rownumber = rowindex;
				if (isIterateAccount()) {
					setRowindex(rownumber);
				}
				break;
			} else {
				findrow = false;
			}

		}
		// put the map value for the host name row
		if (findrow) {
			for (int columnindex = 0; columnindex < columns; columnindex++) {
				String findcontent = sheet.getCell(columnindex, rownumber)
						.getContents().trim();
				String mapheader = header.get(columnindex);
				rowmap.put(mapheader, findcontent);
			}
		}

		return rowmap;
	}


	/**
	 * @Title: getDataTableRowBasedOnTowParameterValues
	 * @Description: TODO
	 * @author alterhu2020@gmail.com
	 * @param @param sheetname
	 * @param @param firstparametervalue,secondparametervalue
	 * @param @return
	 * @return Map<String,String> return type
	 * @throws
	 */

	public Map<String, String> getRowdataViaTwoColumns(
			String sheetname,
			String firstcolumn, 
			String secondcolumn) {

		List<String> header = new ArrayList<String>();
		Map<String, String> rowmap = new HashMap<String, String>();

		boolean findrow = false;
		int rownumber = 0;
		Sheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getRows();
		int columns = sheet.getColumns();

		for (int columnindex = 0; columnindex < columns; columnindex++) {
			String headerelement = sheet.getCell(columnindex, 0).getContents()
					.trim();
			header.add(columnindex, headerelement);
		}

		for (int rowindex = getRowindex(); rowindex < rows; rowindex++) {
			// get the column 1 to get the host name,the column index begin with
			// 0
			String firstcontent = sheet.getCell(0, rowindex).getContents()
					.toLowerCase().trim();
			String secondcontent = sheet.getCell(1, rowindex).getContents()
					.toLowerCase().trim();

			if (firstcontent.toLowerCase().contains(firstcolumn.toLowerCase().trim())
					&& secondcontent.toLowerCase().contains(secondcolumn.toLowerCase().trim())) {
				logger.debug("Found the correct cell data,the case type we found in excel is:"
						+ firstcontent);
				findrow = true;
				rownumber = rowindex;
				if (isIterateAccount()) {
					setRowindex(rownumber);
				}
				break;
			} else {
				findrow = false;
			}

		}
		// put the map value for the host name row
		if (findrow) {
			for (int columnindex = 0; columnindex < columns; columnindex++) {
				String findcontent = sheet.getCell(columnindex, rownumber)
						.getContents().trim();
				String mapheader = header.get(columnindex);
				rowmap.put(mapheader, findcontent);
			}
		}

		return rowmap;
	}

	/**
	 * @Title: getRowNumbers
	 * @Description: TODO
	 * @author ahu@greendotcorp.com
	 * @param @param sheetname
	 * @param @return
	 * @return int return type
	 * @throws
	 */

	public int getRowNumbers(String sheetname) {
		Sheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getRows() - 1;
		return rows;
	}

	public static void main(String[] args) {
		// Map<String,String>
		// data=getDataTableRowBasedOnFirstParameterValue("Home_Page", "case2");
		// logger.info("data is:"+data);
		// getInstance().setRowindex(0);
	
	}

	public int getRowindex() {
		return rowindex + 1;
	}

	public void setRowindex(int rowindex) {
		ExcelUtils.rowindex = rowindex;
	}

	public boolean isIterateAccount() {
		return iterateAccount;
	}

	public void setIterateAccount(boolean iterateAccount) {
		ExcelUtils.iterateAccount = iterateAccount;
	}

	public File getExcelfile() {
		return excelfile;
	}

	public void setExcelfile(File excelfile) {
		this.excelfile = excelfile;
	}

}
