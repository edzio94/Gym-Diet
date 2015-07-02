/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator.diet;

import ExcelParser.ExcelParser;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author lukasz
 */
public class Diet {
   
    public List<Food> food;
    public Diet(){
        food = new ArrayList<>();
        parseData();
    }
    
    private void parseData()
    {
           try
        {
            File file = (new File("/home/lukasz/NetBeansProjects/GymGenerator/src/ExcelParser/BazaBetav1.1.html"));
            String html = file.toString();
            Document doc = Jsoup.parse(file,"UTF-8");
            Element content = doc.getElementById("content");
           // System.out.println("SSS: "+content);
            Elements links = doc.select("tr");
            int counter = 0;
            for(Element link : links)
            {
                Elements linksTD = link.select("td");
                int i = 0;
                List<String> x = new ArrayList<>();
                for (Element td : linksTD)
                {
                    System.out.println(td.select("p").text());
                    x.add(td.select("p").text());
                }

                food.add(new Food(counter++,x.get(0), Float.parseFloat(x.get(1).toString().trim().replaceAll(",", ".")),
                         Float.parseFloat(x.get(2).toString().trim().replaceAll(",", ".")), Float.parseFloat(x.get(3).toString().trim().replaceAll(",", "."))
                                 ,  Float.parseFloat(x.get(4).toString().trim().replaceAll(",", "."))));
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
