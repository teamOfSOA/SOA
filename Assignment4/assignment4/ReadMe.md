## 作业4XSLT部分

>使用XSLT对原StudentList.xml进行排序
>
>ScoreList.xsl为XSLT脚本文件，先通过遍历所有的<学生>标签，分别调用template1；然后template1遍历所有成绩性质为总评成绩的<课程成绩>标签，对其按照<得分>标签排序之后，传递得到的课程编号和学号作为参数，调用template2；最后template2遍历所有的<课程成绩>标签，输出其中课程编号和学号与所传参数相同的节点。
>
>StudentList.xml为上次作业得到的结果，即xml文件2。
>
>ScoreList.xml为本次得到的结果，即xml文件3.
>
>XSLTNewXML.java为执行转换的代码。
