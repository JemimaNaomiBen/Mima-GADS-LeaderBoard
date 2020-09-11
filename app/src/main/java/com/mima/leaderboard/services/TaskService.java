package com.mima.leaderboard.services;
import com.mima.leaderboard.HoursLeader;
import com.mima.leaderboard.SkillIQLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface TaskService {

    String FIRST_NAME ="entry.1877115667";
    String LAST_NAME = "entry.2006916086";
   static String EMAIL= "entry.1824927963";
   static String GITHUB = "entry.284483984";



    @GET("hours")
    Call<List<HoursLeader>> getHoursLeaders();

    @GET("skilliq")
    Call<List<SkillIQLeader>> getSkillIQLeaders();


    @FormUrlEncoded
    @POST
    Call<Void> submit(@Url String formURL,
                      @Field(FIRST_NAME) String name,
                      @Field(LAST_NAME) String lastName,
                      @Field(EMAIL) String email,
                      @Field(GITHUB) String githubLink);

}
