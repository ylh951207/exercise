package com.yang.mapper;

import com.yang.entity.Movie;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-22 11:21
 **/
public interface MovieMapper {
    /**
     * 使用mybatis传参时，如果仅传入一个类型为String的参数，在xml文件中应该使用_parameter代替参数名
     * 或者使用@Param注解给参数命名 List<Movie> find( @Param("title")String title);
     */
    List<Movie> find(String title);
    List<Movie> findByParam(Map<String,Object> searchParam);
    List<Movie> findByIdList(@Param("idList") List<Integer> idList);
}
