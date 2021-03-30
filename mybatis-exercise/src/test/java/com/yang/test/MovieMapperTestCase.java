package com.yang.test;

import com.yang.entity.Movie;
import com.yang.mapper.MovieMapper;
import com.yang.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-22 11:30
 **/
public class MovieMapperTestCase {
    private SqlSession sqlSession;
    private MovieMapper movieMapper;
    @Before
    public void init() {
        sqlSession = MyBatisUtil.getSqlSession();
        movieMapper = sqlSession.getMapper(MovieMapper.class);
    }
    @After
    public void close() {
        sqlSession.close();
    }
    @Test
    public void find() {
        String title = "%西游记2%";
        List<Movie> movieList = movieMapper.find(title);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
        System.out.println(movieList.size());
    }
    @Test
    public void findByParam() {
        Map<String,Object> searchParam = new HashMap<String,Object>();
        //searchParam.put("title","%2%");
        searchParam.put("director","%xiao%");
        List<Movie> movieList = movieMapper.findByParam(searchParam);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }
    }
    @Test
    public void findByIdList() {
        List<Integer> idList = Arrays.asList(5,4,3,2,1);
        List<Movie> movieList = movieMapper.findByIdList(idList);
        for(Movie movie : movieList) {
            System.out.println(movie);
        }

    }
}
