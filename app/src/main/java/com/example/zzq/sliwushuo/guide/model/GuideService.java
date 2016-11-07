package com.example.zzq.sliwushuo.guide.model;

import com.example.zzq.sliwushuo.apublic.MyApi;
import com.example.zzq.sliwushuo.guide.model.bean.AdBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
public interface GuideService {
    @GET(MyApi.Guide.VIEW_PAG)
    Observable<AdBean> getAdData();



}
