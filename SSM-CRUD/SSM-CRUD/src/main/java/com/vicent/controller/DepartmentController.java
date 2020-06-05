package com.vicent.controller;


import com.vicent.bean.Department;
import com.vicent.bean.Msg;
import com.vicent.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理和部门有关的请求
 *
 * @author vicent
 * @date 2020/6/4
 */
@Controller
public class DepartmentController {


    @Autowired
    DepartmentService departmentService;


    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){

        List<Department> list=departmentService.getAll();

        return Msg.success().add("depts",list);
    }
}
