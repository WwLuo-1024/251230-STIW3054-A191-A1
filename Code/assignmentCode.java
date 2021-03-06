
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 *
 * @author WANG LUO
 */
public class Test {
    /** 
 * 根据jsoup方法获取htmlContent 
        * 加入简单的时间记录 
 * @throws IOException  
 */  
public static String getContentByJsoup(String url){  
    String content="";  
    try {  
        Document doc=Jsoup.connect(url)  
        .data("jquery", "java")  
        .userAgent("Mozilla")  
        .cookie("auth", "token")  
        .timeout(50000)  
        .get();  
        content=doc.toString();//获取iteye网站的源码html内容   require html content from github
//        System.out.println(doc.title());//获取iteye网站的标题  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
    return content;  
}
      /** 
 * 使用jsoup来对文档分析 
        * 获取目标内容所在的目标层 
        * 这个目标层可以是div，table，tr等等 
     * @param content
 */  
public static String getDivContentByJsoup(String content){  
    String divContent="";  
    Document doc=Jsoup.parse(content);  
    Elements divs=doc.getElementsByClass("js-timeline-item js-timeline-progressive-focus-container");  
    divContent=divs.toString();  
    return divContent;  
}
/** 
     * 使用jsoup分析divContent 
     * 1.获取链接 2.获取url地址（绝对路径） 
     * @param divContent
     */  
    public static List<String> getLinksByJsoup(String divContent){  
        String abs = "https://github.com/STIW3054-A191/Main-Issues/issues/1";  
        Document doc = Jsoup.parse(divContent,abs);  
        List<String> linkList=new ArrayList<String>();
        Elements linkStrs = doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");  
//        System.out.println("链接==="+linkStrs.size());  
        for(Element linkStr:linkStrs){  
            String url=linkStr.getElementsByTag("a").attr("abs:href");  
//            System.out.println("url:"+url);
            linkList.add(url);
        }
        return linkList;
    }

    public static List<String> getNamesByJsoup(String divContent){
        String abs = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
        String name = "";
        List<String> nameList=new ArrayList<String>();
        Document doc = Jsoup.parse(divContent,abs);
        Elements matricNums = doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");
//        System.out.println("name==="+matricNums.size());
        
        for(Element matricNum:matricNums){ 
              Elements url=matricNum.getElementsByTag("p");            
              String transfer = url.select("p").toString();           
              Pattern pattern = Pattern.compile("Name: (.*?)<br>|Name :(.*?)<br>|:\\s(U.*)<br>|Name:(.*?)<br>|name :( .*?)<br> |Name (.*?)<br>");// 匹配的模式 //"<p>(.*?)<br>" //:(.*)<br>
              Matcher m = pattern.matcher(transfer);
              while(m.find()){
                  name = m.group(0);
              }           
//            System.out.println("标题:"+name);
            nameList.add(name);
        }
        return nameList;
    }

    
    public static List<String> getMatricNumberByJsoup(String divContent) throws FileNotFoundException, IOException{
        String abs = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
        String matric = "";
        List<String> matricList=new ArrayList<String>();
        Document doc = Jsoup.parse(divContent,abs);
        Elements matricNums = doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");
//        System.out.println("name==="+matricNums.size());
        
        for(Element matricNum:matricNums){ 
              Elements url=matricNum.getElementsByTag("p");            
              String transfer = url.select("p").toString();
              Pattern pattern = Pattern.compile("(\\b2.*?)<br>");// 匹配的模式 //"<p>(.*?)<br>"
              Matcher m = pattern.matcher(transfer);
              while(m.find()){
                  matric = m.group(1);
              }
            matricList.add(matric);
        }
        return matricList;
}

    public static String getTableByJsoup(String content){  
    String divContent="";  
    Document doc=Jsoup.parse(content);  
    Elements divs=doc.getElementsByClass("markdown-body");  
    divContent=divs.toString();  
    return divContent;  
    }
    public static List<String> TableList(String divContent) throws IOException{
    String matric = "";
    List<String>matricList = new ArrayList<String>();
    //网页地址
    String url = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
    Document doc = Jsoup.parse(divContent,url);
    //获取第一个表格
    Element element = doc.select("table").first();
    Elements els = element.select("tr");
    for(Element el:els){
        Elements ele = el.select("td");
        String context = ele.select("td").toString();
        Pattern pattern = Pattern.compile("(\\b2.*?)</td>");
        Matcher m = pattern.matcher(context);
        while(m.find()){
            matric = m.group(1);
        }
        matricList.add(matric);
    //    System.out.println(matric);
    }
        return matricList;
    }

 public static void getDifferent(List<String> list1, List<String> list2){
	        for(String str1 : list1){
	            if(!list2.contains(str1)){
	                // 打印出list2没有b,d
	                System.out.println(str1+" have not submitted the GitHub account.");
	            }
	        }
	        for(String str2 : list2){
	            if(list1.contains(str2)){
	                // 打印出list1共同
	                System.out.println(str2+" have submitted the GitHub account.");
	            }
	        }
	    }


public static void SaveExcel(String divContent) throws IOException{
                List<String> matricList=new ArrayList<String>();
                List<String> nameList=new ArrayList<String>();
                List<String> linkList=new ArrayList<String>();
                String abs = "https://github.com/STIW3054-A191/Main-Issues/issues/1";
                Document doc = Jsoup.parse(divContent,abs);
                Elements matricNums = doc.getElementsByClass("d-block comment-body markdown-body  js-comment-body");
//                System.out.println("name==="+matricNums.size());
                HSSFWorkbook wb = new HSSFWorkbook();
                // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
                HSSFSheet sheet = wb.createSheet("行业统计");
                // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
                HSSFRow row = sheet.createRow((int) 0);
                // 第四步，创建单元格，并设置值表头 设置表头居中
                HSSFCellStyle style = wb.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
                HSSFCell cell = row.createCell((short) 0);
                cell.setCellValue("ID");
                cell.setCellStyle(style);
                cell = row.createCell((short) 1);
                cell.setCellValue("Matric");
                cell.setCellStyle(style);
                cell = row.createCell((short) 2);
                cell.setCellValue("Name");
                cell.setCellStyle(style);
                cell = row.createCell((short) 3);
                cell.setCellValue("Link");
                cell.setCellStyle(style);
                matricList.addAll(getMatricNumberByJsoup(divContent));
                nameList.addAll(getNamesByJsoup(divContent));
                linkList.addAll(getLinksByJsoup(divContent));
                for (int i = 0; i < matricNums.size(); i++)
              {
                  row = sheet.createRow((int) i + 1);
                  // 第四步，创建单元格，并设置值
                  String id = Integer.toString(i+1);
                  row.createCell((short) 0).setCellValue(new HSSFRichTextString(id));
                  row.createCell((short) 1).setCellValue(new HSSFRichTextString(matricList.get(i)));
                  row.createCell((short) 2).setCellValue(new HSSFRichTextString(nameList.get(i)));
                  row.createCell((short) 3).setCellValue(new HSSFRichTextString(linkList.get(i)));
            }
            FileOutputStream fout = new FileOutputStream("D:/info.xls");
            wb.write(fout);
            fout.close();
            System.out.println("The MS Excel file have succesfully created in D:/info.xls");
}
/**
     * @param args
     * @method 测试获取内容程序 
     */  
    public static void main(String[] args) throws IOException {  
          
        /** 
         * 执行分析程序 
         */  
         String url="https://github.com/STIW3054-A191/Main-Issues/issues/1";
         String url2 = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
         String HtmlContent=getContentByJsoup(url);
         String HtmlContent2  =getContentByJsoup(url2);
         String divContent=getDivContentByJsoup(HtmlContent);
         String divContent2 = getTableByJsoup(HtmlContent2);
         TableList(divContent2);
         getLinksByJsoup(divContent);
         getNamesByJsoup(divContent);
         getMatricNumberByJsoup(divContent);
         getDifferent(TableList(divContent2),getMatricNumberByJsoup(divContent));
         SaveExcel(divContent);
    }
}

