package com.example.moviespdemo.dao;

import com.example.moviespdemo.bean.Movie;
import com.example.moviespdemo.bean.MovieInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieDAO {
    //获得movie的详细信息
    @Select("select m.name,m.published_year,m.type from movies m where m.id=#{id}")
    public Movie getMovieByMovieId(long id);

    //根据用户的id，获得用户的所有电影
    @Select("select m.id, m.name,m.published_year,m.type,mp.preference from movie_preferences mp,movies m where mp.movieID=m.id and mp.userID = #{id} order by preference desc")
    public List<MovieInfo> getMovieByUserId(long id);

}
