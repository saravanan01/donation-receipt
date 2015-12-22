package com.sa.app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import org.apache.commons.codec.binary.Base64;

import com.sa.execl.vo.Trust;

public class TrustUtil {
	public static List<Trust> load(){
		List<Trust> data = new ArrayList<>();
		FileReader  fr = null;
		BufferedReader br = null;
		try{
			fr = new FileReader("trust.txt");
			br = new BufferedReader(fr);
			String line;
			while( (line = br.readLine()) != null){
				line = new String(Base64.decodeBase64(line), "UTF-8");
				String[] trust = line.split("\\|");
				Trust newTrust = new Trust();
				newTrust.setName(trust[0]);
				newTrust.setRegnNo(trust[1]);
				newTrust.setPan(trust[2]);
				newTrust.setRegnDate(trust[3]);
				newTrust.setPhone(trust[4]);
				newTrust.setTreasurerName(trust[5]);
				newTrust.setAddress(trust[6]);
				data.add(newTrust);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(br!=null){try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
			
			if(fr != null) {try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		return data;
	}
	
	public static void save(JComboBox<Trust> comboTrust){
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{
			fw = new FileWriter("trust.txt");
			bw = new BufferedWriter(fw);
			int count = comboTrust.getItemCount();
			StringBuilder sb = new StringBuilder(100);
			for (int i =0;i<count;i++) {
				Trust trust = (Trust) comboTrust.getItemAt(i);
				sb.append(trust.getName())
				.append("|")
				.append(trust.getRegnNo())
				.append("|")
				.append(emptyOnNull(trust.getPan()))
				.append("|")
				.append(emptyOnNull(trust.getRegnDate()))
				.append("|")
				.append(emptyOnNull(trust.getPhone()))
				.append("|")
				.append(emptyOnNull(trust.getTreasurerName()))
				.append("|")
				.append(emptyOnNull(trust.getAddress()));
				bw.write(Base64.encodeBase64String(sb.toString().getBytes()));
				bw.newLine();
				sb.delete(0, sb.length());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(bw !=null) {try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
			if(fw != null) {try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
		}
	}

	private static String emptyOnNull(String str) {
		return str == null ? " " : str;
	}
}
