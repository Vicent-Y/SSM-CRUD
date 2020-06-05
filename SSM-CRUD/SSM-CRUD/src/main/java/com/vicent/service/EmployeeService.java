package com.vicent.service;

import com.vicent.bean.Employee;
import com.vicent.bean.EmployeeExample;
import com.vicent.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author vicent
 * @date 2020/6/4
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


    /*
    * 查询所有员工
    * */
    public List<Employee> getAll() {

        return employeeMapper.selectByExampleWithDept(null);
    }

    /*
    * 保存员工
    * */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /*
    * 检验用户名是否可用
    *0 == true 代表可用
    *反之 不可用
    * */
    public boolean checkUser(String empName) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count=employeeMapper.countByExample(example);

        return count==0;
    }

    /*
    * 根据id查询员工
    * */
    public Employee getEmp(Integer id) {

        return employeeMapper.selectByPrimaryKey(id);
    }

    /*
     * 更新员工
     * */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /*
     * 根据ID删除员工
     * */
    public void deleteEmpById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {

        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria=example.createCriteria();
        criteria.andEmpIdIn(ids);  //等价于 delete from xxx where emp_id in(1,2,3);
        employeeMapper.deleteByExample(example);
    }
}
