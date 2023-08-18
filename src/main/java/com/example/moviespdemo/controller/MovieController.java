package com.example.moviespdemo.controller;

import com.example.moviespdemo.bean.MovieInfo;
import com.example.moviespdemo.config.recommender.MySlopeOneRecommender;
import com.example.moviespdemo.service.MovieService;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService service;

    @RequestMapping("/ranking")
    public ArrayList<MovieInfo> getRMovieData(long userID,int size){
        //用户的所有电影
        ArrayList<MovieInfo> ownMovieInfo = service.getMovieByUserId(userID);

        //推荐电影的List
        List<RecommendedItem> recommendation = null;
        MySlopeOneRecommender msor = new MySlopeOneRecommender();
        //拿到推荐的电影
        recommendation = msor.mySlopeOneRecommender(userID,size);

        //拿到推荐的电影的详细信息
        ArrayList<MovieInfo> recommendMovieInfo = service.getMovieByMovieId(recommendation);

        return recommendMovieInfo;
    }

    @RequestMapping("/user")
    public ArrayList<MovieInfo> getUserData(long userID){
        //用户的所有电影
        ArrayList<MovieInfo> ownMovieInfo = service.getMovieByUserId(userID);

        return ownMovieInfo;
    }

}
