package com.vicent.service;

import com.sun.org.apache.regexp.internal.RE;
import com.vicent.bean.Department;
import com.vicent.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.log.LogInputStream;

import java.util.List;

/**
 * @author vicent
 * @date 2020/6/4
 */

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /*
    返回所有部门信息
    * */
    public List<Department> getAll(){
        return departmentMapper.selectByExample(null);
    }
}
