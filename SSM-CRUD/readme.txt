����������
	IDE  �汾��2019.2.4
	java �汾��java1.8
	maven�汾��3.6.3
	tomcat�汾��8.0.50
	�����ʽ�� war



���������⣺
����Դ��
	��Ƶ�е�����Դ�滻������ģ�
	<dependency>
      		<groupId>com.mchange</groupId>
     	 	<artifactId>c3p0</artifactId>
      		<version>0.9.5.2</version>
    	</dependency>

p13��ָ���쳣
	��ϵ����һ�£��Ҳ��Ե�mockMvcΪ�գ������ҵ��Ľ���취����@Before�е�mockMvc��Ҫ��������������Ҳ�п����Ǳ�����Сд���⡣

p13 pageContext.setAttribute("APP_PATH", request.getContextPath()) ���죿
	
	����������
	<dependency>
      		<groupId>javax.servlet</groupId>
      		<artifactId>jsp-api</artifactId>
      		<version>2.0</version>
   	 </dependency>

p14������޷���ʾ��ʽ��
	Ĭ�ϵ�jsp�п��ܺ���EL���ʽ������ֱ����Ϊhtml���ı�������ʾ���������Ҫ����<%@ page isELIgnored="false" %>

���һ��bug:��ѡ��ȫѡ��ѡ��������һҳ��ʱ��ȫѡ��ѡ����Ϊѡ��״̬
�취��ÿ����һ��ҳ����Ⱦ�����Ͳ�Ա���б�ajax��ʱ���Ѹ�ѡ��ȡ��ѡ��״̬
        	$("#check_all").prop("checked",false);

