/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcelParser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author lukasz
 */
public class ExcelParser {
    
    public ExcelParser()
    {
        try
        {
            File file = (new File("/home/lukasz/NetBeansProjects/GymGenerator/src/ExcelParser/BazaBeta.html"));
            String html = file.toString();
            Document doc = Jsoup.parse(file,"UTF-8");
            Element content = doc.getElementById("content");
           // System.out.println("SSS: "+content);
            Elements links = doc.select("tr");
            for(Element link : links)
            {
                Elements linksTD = link.select("td");
                for (Element td : linksTD)
                {
                    System.out.println(td.select("p").text());
                    System.out.println("");
                }
                //System.out.println(link);//.select("td").select("p").text());
            }
            
            //System.out.println(doc);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
}

