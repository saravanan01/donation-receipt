package com.sa.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sa.execl.vo.Receipt;
import com.sa.execl.vo.Trust;

public class ReadExcel {

	public static List<Receipt> readXLSXFile(String filename,Trust trust, int skipRows) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(filename);
		XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;

		Iterator<Row> rows = sheet.rowIterator();
		Receipt receipt = null;
		List<Receipt> donations = new ArrayList<>(100);
		boolean skipFirst = true;
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();
			
			if(skipRows > 0){
				skipRows--;
				skipFirst = false;
				continue;
			}
			if (skipFirst) {
				skipFirst = false;
				continue;
			}
			Iterator<Cell> cells = row.cellIterator();
			int cellNbr = 0;
			receipt = new Receipt();
			boolean skip = false;
			while (cells.hasNext()) {
				if (skip)
					break;
				cell = (XSSFCell) cells.next();
				cellNbr++;
				String cellTxt = "";

				switch (cellNbr) {
				case 1:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
						String year = (String.valueOf(cal.get(Calendar.YEAR)));
						cellTxt = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + year;

					}
					receipt.setDate(cellTxt);
					break;
				case 2:
					if (!cell.getStringCellValue().equalsIgnoreCase("Donation")) {
						skip = true;
						receipt = null;
						break;
					}
					receipt.setDescription(getString(cell));
					break;
				case 3:
					receipt.setReceiptNo(Long.parseLong(getLongStr(cell)));
					break;
				case 4:
					receipt.setName(getString(cell));
					break;
				case 5:
					receipt.setTransferMode(getString(cell));
					break;
				case 6:
					receipt.setChequeDetails(getString(cell)==null?"":getString(cell));
					break;
				case 7:
					try {
						if (getLongStr(cell) != null) {
							receipt.setCredit(new BigDecimal(getLongStr(cell)));
						}
					} catch (Exception e) {
						System.err.println("invalid credit");
					}
					break;
				case 8:
					try {
						if (getLongStr(cell) != null) {
							receipt.setDebit(new BigDecimal(getLongStr(cell)));
						}
					} catch (Exception e) {
						System.err.println("invalid debit");
					}
					break;
				case 9:
					receipt.setRefferedBy(getString(cell));
					break;
				case 10:
					receipt.setEmailId(getString(cell));
					break;
				case 11:
					break;
				case 12:
					receipt.setPhoneNo(getString(cell));
					break;
				case 13:
					receipt.setAddress(getString(cell));
					break;
				}
				
			}
			if (receipt != null) {
				receipt.setTrust(trust);
				donations.add(receipt);
			}

		}

		wb.close();
		return donations;

	}

	private static String getLongStr(XSSFCell cell) {
		String cellTxt = null;
		if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			DecimalFormat df = new DecimalFormat("0000000000000");
			cellTxt = df.format(cell.getNumericCellValue());
		}
		return cellTxt;
	}

	private static String getString(XSSFCell cell) {
		String cellTxt = null;
		if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
			cellTxt = cell.getStringCellValue();
		}
		return cellTxt;
	}

}
