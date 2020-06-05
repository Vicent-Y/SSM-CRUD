package com.vicent.test;

import com.vicent.bean.Department;
import com.vicent.bean.Employee;
import com.vicent.dao.DepartmentMapper;
import com.vicent.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author vicent
 * @date 2020/5/28
 * 推荐使用spring的单元测试，可以自动注入我们需要的组件
 *
 *  1.导入springTest模块
 *  2.使用@ContextConfiguration指定spring配置文件的位置
 *  3.直接@Autowired即可
 *
 *  注：要使用junit 4.12以上
 */

@RunWith(SpringJUnit4ClassRunner.class)//指定单元测试模块，使用SpringJUnit4ClassRunner运行测试
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    /**
     * 测试DepartmentMapper
     */
    @Test
    public void testCRUD(){

//        //1.创建IOC容器
//        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中拿mapper
//        DepartmentMapper bean = ac.getBean(DepartmentMapper.class);
//        System.out.println(departmentMapper);

        //1.插入几个部门
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        //2.插入几个员工
//       employeeMapper.insertSelective(new Employee(null,"Jerry", "M","Jerry@guigu.com",1));
//       employeeMapper.insert(new Employee(2,"Tom","M","tom@163.com",2));

        //3.批量插入员工，使用可批量操作的sqlsession
       /* for(){
            employeeMapper.insertSelective(new Employee(null,, "M","Jerry@guigu.com",1));
        }*/

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@guigu.com",1));
            System.out.println();
        }


        System.out.println("批量完成");

    }
}
