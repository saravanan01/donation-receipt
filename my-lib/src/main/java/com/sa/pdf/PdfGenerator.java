package com.sa.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sa.execl.vo.Receipt;
import com.sa.number.NumberToWords;

public class PdfGenerator {
	
	private static String TAX_LINE1 = "Income Tax Exemption Under 80G";
	private static String TAX_LINE2 = "DIT [E] No:2(239)/13-14; Dt:07/08/2014";
	
	public static void generate(Receipt receipt,String outPath, Boolean original) 
			throws IOException, FileNotFoundException, DocumentException {
		File file = new File(outPath);
		if(!file.exists()){
			file.mkdirs();
		}
		PdfReader pdfTemplate = new PdfReader("com/sa/pdf/receipt_templete.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream(outPath +
				"Receipt-"+receipt.getReceiptNo() +".pdf");
		PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
		stamper.setFormFlattening(true);
		if(original){
			stamper.getAcroFields().setField("original", "Original");
		}else{
			stamper.getAcroFields().setField("original", "Duplicate");
		}
		stamper.getAcroFields().setField("trust_name", receipt.getTrust().getName());
		stamper.getAcroFields().setField("trust_addr", receipt.getTrust().getAddress());
		stamper.getAcroFields().setField("pan", receipt.getTrust().getPan());
		stamper.getAcroFields().setField("regnno", receipt.getTrust().getRegnNo());
		stamper.getAcroFields().setField("regndt", receipt.getTrust().getRegnDate());
		stamper.getAcroFields().setField("regnno", receipt.getTrust().getRegnNo());
		stamper.getAcroFields().setField("trust_phone", receipt.getTrust().getPhone());
		stamper.getAcroFields().setField("receipt_no", String.valueOf( receipt.getReceiptNo()) );
		stamper.getAcroFields().setField("date", receipt.getDate());
		stamper.getAcroFields().setField("name", receipt.getName());
		stamper.getAcroFields().setField("phone", receipt.getPhoneNo());
		stamper.getAcroFields().setField("addr", receipt.getAddress());
		stamper.getAcroFields().setField("amt", receipt.getCredit().toPlainString());
		String amtTxt = NumberToWords.getWords(receipt.getCredit().doubleValue());
		stamper.getAcroFields().setField("amt_txt", amtTxt);
		stamper.getAcroFields().setField("mode", receipt.getTransferMode());
		stamper.getAcroFields().setField("cheque", receipt.getChequeDetails());
		stamper.getAcroFields().setField("trust_name_2", receipt.getTrust().getName());
		stamper.getAcroFields().setField("treasurer", receipt.getTrust().getTreasurerName());
		if(receipt.getTrust().getTax().equalsIgnoreCase("true")){
			stamper.getAcroFields().setField("tax_line1", TAX_LINE1);
			stamper.getAcroFields().setField("tax_line2", TAX_LINE2);
		}
		
		stamper.close();
		pdfTemplate.close();

	}
}
