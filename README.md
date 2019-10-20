# 251230-STIW3054-A191-A1
Name:Wang Luo  
Matric Number:251230  

I develop a small system using MAVEN and Java programming language. The system can read data from a Github Issue page from the link https://github.com/STIW3054-A191/Main-Issues/issues/1 and also can read data from a Github Wiki page from link https://github.com/STIW3054 A191/Assignments/wiki/List_of_Student.Specifically,at first i add the org.jsoup and org.apache dependency in maven file.And Secondly,i create 9 method in my system class to finish the process of read data.These nine methods separtely called       getContentByJsoup,getDivContentByJsoup,getLinkByJsoup,getMatricNumberByJsoup,getNameByJsoup,getTableByJsoup,TableList,getDifferent and saveExcel.  

Becasue of this system have 10 methods in the whole class,so i create 10 classes,and 9 classes all association to main method.  

UML Diagram:![Image Class Diagram](https://github.com/WwLuo-1024/251230-STIW3054-A191-A1/blob/master/Images/003.png)  

We collect the comment matric number in one list,and table matric number into anther list,after that,i create a new method to mainly compare them if contain in another list or not.If there is,so it means the people have submitted their account,but if there is not,it means this people have not submitted their account.  

Output:![Image output](https://github.com/WwLuo-1024/251230-STIW3054-A191-A1/blob/master/Images/output.png)  

  
i read data from github comment page,and collect name,matric number and github link of those people who have submmited their account into the excel file.  

Result:![Image result](https://github.com/WwLuo-1024/251230-STIW3054-A191-A1/blob/master/Images/result.png) 

Youtube Presentation:https://youtu.be/j4zr7MZFp_o

Reference:
(zygzzp, 2013, https://blog.csdn.net/nyistzp/article/details/17261433)  
(csdnfeiguo,2018,https://blog.csdn.net/csdnfeiguo/article/details/80748122)
