//        /** 
//         * 执行分析程序 
//         */  
//         String url="https://github.com/STIW3054-A191/Main-Issues/issues/1";
//         String url2 = "https://github.com/STIW3054-A191/Assignments/wiki/List_of_Student";
//         String HtmlContent=getContentByJsoup(url);
//         String HtmlContent2  =getContentByJsoup(url2);
//         String divContent=getDivContentByJsoup(HtmlContent);
//         String divContent2 = getTableByJsoup(HtmlContent2);
//         TableList(divContent2);
//         getLinksByJsoup(divContent);
//         getNamesByJsoup(divContent);
//         getMatricNumberByJsoup(divContent);
//         getDifferent(TableList(divContent2),getMatricNumberByJsoup(divContent));
//        System.out.println(" Students who have submitted the GitHub account.");
//        System.out.println("| No. | Matric | Name                                  | GitHub Link                            |");
//        System.out.println("|-----|--------|---------------------------------------|----------------------------------------|");
//        for(int i = 0;i<getMatricNumberByJsoup(divContent).size();i++){
//            System.out.printf("|%-5s|%-8s|%-39s|%-40s|\n",i+1,getMatricNumberByJsoup(divContent).get(i),getNamesByJsoup(divContent).get(i),getLinksByJsoup(divContent).get(i));
//                    }
//        System.out.println("|-----|--------|---------------------------------------|----------------------------------------|");
//         SaveExcel(divContent);
//         
//    }
