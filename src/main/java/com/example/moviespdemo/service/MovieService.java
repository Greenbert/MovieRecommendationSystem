package com.example.moviespdemo.service;

import com.example.moviespdemo.bean.Movie;
import com.example.moviespdemo.bean.MovieInfo;
import com.example.moviespdemo.dao.MovieDAO;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Resource
    private MovieDAO dao;

    //根据推荐的movie的ID，获得movie的详细信息
    public ArrayList<MovieInfo> getMovieByMovieId(int id){
        ArrayList<MovieInfo> movieList = new ArrayList<MovieInfo>();
        Movie m =  dao.getMovieByMovieId(id);
        MovieInfo movieInfo = new MovieInfo();
        movieInfo.setId(id);
        movieInfo.setName(m.getName());
        movieInfo.setType(m.getType());
        movieInfo.setPublished_year(m.getPublished_year());
        movieInfo.setPreference(5.0);

        movieList.add(movieInfo);
        return movieList;
    }

    //根据推荐的movie的ID，获得movie的详细信息
    public ArrayList<MovieInfo> getMovieByMovieId(List<RecommendedItem> recommendations){
        ArrayList<MovieInfo> movieList = new ArrayList<MovieInfo>();

        for(int i=0;i<recommendations.size();i++){
            Movie m =  dao.getMovieByMovieId(recommendations.get(i).getItemID());
            MovieInfo movieInfo = new MovieInfo();
            movieInfo.setId(recommendations.get(i).getItemID());
            movieInfo.setName(m.getName());
            movieInfo.setType(m.getType());
            movieInfo.setPublished_year(m.getPublished_year());
            movieInfo.setPreference(recommendations.get(i).getValue());
            movieList.add(movieInfo);
        }

        return movieList;
    }

    public ArrayList<MovieInfo> getMovieByUserId(long id){
        ArrayList<MovieInfo> movieList = new ArrayList<MovieInfo>();
        List<MovieInfo> ml =  dao.getMovieByUserId(id);
        for(int i=0;i<ml.size();i++){
            movieList.add(ml.get(i));
        }
        return movieList;
    }
}
