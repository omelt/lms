package com.lc.lms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.lms.pojo.Book;
import com.lc.lms.pojo.Borrow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {
}
