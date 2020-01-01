/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiw3054;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WANG LUO
 */
public class Main {
    public static void main(String[] args){
        try {
            /**
             * 执行分析程序
             */
            String url="https://github.com/STIW3054-A191/Main-Issues/issues/1";
            String url2 = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
            Test test = new Test();
            String HtmlContent=test.getContentByJsoup(url);
            String HtmlContent2  =test.getContentByJsoup(url2);
            String divContent=test.getDivContentByJsoup(HtmlContent);
            String divContent2 = test.getTableByJsoup(HtmlContent2);
            test.TableList(divContent2);
            test.getLinksByJsoup(divContent);
            test.getNamesByJsoup(divContent);
            test.getMatricNumberByJsoup(divContent);
            test.getDifferent(test.TableList(divContent2),test.getMatricNumberByJsoup(divContent));
            System.out.println(" Students who have submitted the GitHub account.");
            System.out.println("| No. | Matric | Name                                  | GitHub Link                            |");
            System.out.println("|-----|--------|---------------------------------------|----------------------------------------|");
            for(int i = 0;i<test.getMatricNumberByJsoup(divContent).size();i++){
                System.out.printf("|%-5s|%-8s|%-39s|%-40s|\n",i+1,test.getMatricNumberByJsoup(divContent).get(i),test.getNamesByJsoup(divContent).get(i),test.getLinksByJsoup(divContent).get(i));
            }
            System.out.println("|-----|--------|---------------------------------------|----------------------------------------|");
            test.SaveExcel(divContent);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    }

