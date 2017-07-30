package com.kun.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WhxCsdnCrawler {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		int rowNumber = 0;

		Document doc = null;
		Elements elements = null;
		HSSFRow rows = null;
		File xlsFile = new File("D:/room.xls");
		FileOutputStream xlsStream = new FileOutputStream(xlsFile);

		int page = 1;
		String url = "http://nj.58.com/chuzu/pn";

		while (page <= 70) {

			doc = Jsoup.connect(url + page + "/").get();
			elements = doc.getElementsByAttribute("sortid");

			//System.out.println(elements);
			for (Element element : elements) {
				if (element.getElementsByAttribute("tongji_label").text().trim() != null) {

					rows = sheet.createRow(rowNumber);
					
					String[] loc=element.getElementsByAttribute("tongji_label").text().split(" \\| ");
					rows.createCell(0).setCellValue(loc[0]);
					rows.createCell(1).setCellValue(loc[1]);
					
					String[] room=element.select(".room").text().split("     ");
					rows.createCell(2).setCellValue(room[0].trim());
					rows.createCell(3).setCellValue(room.length==2?Double.valueOf(room[1].trim().split("㎡")[0]):0);
					rows.createCell(4).setCellValue(element.select(".jjrName_span").text());
					String price = element.getElementsByTag("b").text();
					rows.createCell(5).setCellValue("面议".equals(price)?-1:Double.valueOf(price));				
					rows.createCell(6).setCellValue(element.select(".sendTime").text());
					++rowNumber;

				}
			}
			workbook.write(xlsStream);
			++page;
		}

		workbook.close();
		xlsStream.close();

	}

}
