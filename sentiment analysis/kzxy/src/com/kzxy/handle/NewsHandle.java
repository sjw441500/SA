package com.kzxy.handle;

import com.kzxy.data.New;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiuyin on 15-12-25.
 */
public class NewsHandle
{
    private List<New> allNews;
    private int newsNum;

    public NewsHandle()
    {
        allNews = new ArrayList<>();
        newsNum = 0;
    }


    public List<New> getAllNews()
    {
        return allNews;
    }


    public void addNews(New news)
    {
        allNews.add(news);
        news.setNewId(newsNum);
        ++newsNum;
    }
}
