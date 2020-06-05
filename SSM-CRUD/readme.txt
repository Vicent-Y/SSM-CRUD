开发环境：
	IDE  版本：2019.2.4
	java 版本：java1.8
	maven版本：3.6.3
	tomcat版本：8.0.50
	打包方式： war



遇到的问题：
数据源：
	视频中的数据源替换成下面的：
	<dependency>
      		<groupId>com.mchange</groupId>
     	 	<artifactId>c3p0</artifactId>
      		<version>0.9.5.2</version>
    	</dependency>

p13空指针异常
	打断点测试一下，我测试到mockMvc为空，所以找到的解决办法（在@Before中的mockMvc不要加类型声明），也有可能是变量大小写问题。

p13 pageContext.setAttribute("APP_PATH", request.getContextPath()) 报红？
	
	加入依赖：
	<dependency>
      		<groupId>javax.servlet</groupId>
      		<artifactId>jsp-api</artifactId>
      		<version>2.0</version>
   	 </dependency>

p14浏览器无法显示样式？
	默认的jsp有可能忽略EL表达式，而是直接作为html的文本进行显示输出，所以要加上<%@ page isELIgnored="false" %>

解决一个bug:若选中全选复选框，则点击下一页的时候，全选复选框仍为选中状态
办法：每进行一次页面渲染（发送查员工列表ajax）时，把复选框取消选中状态
        	$("#check_all").prop("checked",false);

