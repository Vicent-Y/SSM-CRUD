package com.vicent.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vicent.bean.Employee;
import com.vicent.bean.Msg;
import com.vicent.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vicent
 * @date 2020/6/4
 *
 *处理员工的CRUD请求
 *
 *
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    //单个删除和批量删除二合一
    //批量删除：1-2-3
    //单个删除：1
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmpById( @PathVariable("ids")String ids){

        if (ids.contains("-")){
            List<Integer> del_ids=new ArrayList<Integer>();
            String[] str_ids = ids.split("-");
//            for (String id:str_ids){
//                Integer int_id=Integer.parseInt(id);
//                employeeService.deleteEmpById(int_id);
//            }
            //组装id的集合
            for (String s:str_ids){
                del_ids.add(Integer.parseInt(s));


            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmpById(id);
        }

        return Msg.success();
    }


    /*
    如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * Employee
	 * [empId=1014, empName=null, gender=null, email=null, dId=null]
	 *
	 * 问题：
	 * 请求体中有数据；
	 * 但是Employee对象封装不上；
	 * update tbl_emp  where emp_id = 1014;
	 *
	 * 原因：
	 * Tomcat：
	 * 		1、将请求体中的数据，封装一个map。
	 * 		2、request.getParameter("empName")就会从这个map中取值。
	 * 		3、SpringMVC封装POJO对象的时候。
	 * 				会把POJO中每个属性的值，request.getParamter("email");
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 *
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 *
	 *
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 * 员工更新方法
    * */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){

        employeeService.updateEmp(employee);
        return Msg.success();
    }




    //根据id查询员工
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){

       Employee employee= employeeService.getEmp(id);

        return Msg.success().add("emp",employee);
    }



    //检验用户名是否可用
    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(@RequestParam("empName")/*从请求参数中获得值empName*/ String empName){

        //判断用户名是否是合法的表达式
        String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!empName.matches(regx)){
            return Msg.fail().add("va_msg","用户名可以是2-5位中文或者6-16位英文和数字的组合");
        }

        //数据库用户名重复校验
        boolean b=employeeService.checkUser(empName);

        if(b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名不可用");
        }
    }





    //保存用户
    @RequestMapping(value="/emp",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        //@Valid 校验，并且将校验结果封装到BindingResult result中

        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String,Object> map=new HashMap<>();
            List<FieldError> errors=result.getFieldErrors();
            for (FieldError fieldError:errors){
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误的信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }

            return Msg.fail().add("errorFileds",map);

        }else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }


    }


    /*
    * 导入jackson包，让 @ResponseBody能够转换字符串
    * */
    @RequestMapping("/emps")
    @ResponseBody  //将返回值作为json字符串形式返回给客户端
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
//        引入PageHelper 分页查询插件
//        在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
//        startpage之后紧跟的这个查询就是一个分页查询
        List<Employee> emps=employeeService.getAll();
//        使用pageinfo包装查询后的结果，只需要将pageinfo交给页面就行了
//        封装了详细的页面信息，包括我们查询出来的数据,可以传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo",page);

    }


    /*
    * 查询员工列表（分页查询）
    *
    * */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){

//        这不是一个分页查询
//        引入PageHelper 分页查询插件
//        在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
//        startpage之后紧跟的这个查询就是一个分页查询
        List<Employee> emps=employeeService.getAll();
//        使用pageinfo包装查询后的结果，只需要将pageinfo交给页面就行了
//        封装了详细的页面信息，包括我们查询出来的数据,可以传入连续显示的页数
        PageInfo page = new PageInfo(emps,5);
        model.addAttribute("pageInfo",page);

        return "list";
    }

}
