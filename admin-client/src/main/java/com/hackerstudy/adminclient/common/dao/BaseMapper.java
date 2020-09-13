package com.hackerstudy.adminclient.common.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @class: BaseMapper
 * @description:
 * @author: yangpeng03614
 * @date: 2018-11-20 11:07
 */
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
