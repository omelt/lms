package com.lc.lms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lms.pojo.Admin;
import com.lc.lms.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
