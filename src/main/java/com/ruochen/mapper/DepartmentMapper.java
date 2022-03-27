package com.ruochen.mapper;

import com.ruochen.domain.Department;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    /**
     * 查询所有院系信息，并通过id进行排序
     *
     * @return
     */
    @Select("select * from department order by id;")
    @Results({
            @Result(column = "dept_name", property = "deptName")
    })
    List<Department> selectDeptAll();
}
