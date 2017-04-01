package com.kzxy.handle;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.kzxy.data.Article;

import java.util.List;

/**
 * Created by fuxiuyin on 15-12-14.
 */

public class ContenHandle
{
    public static void getToken(List<Article> allArticle)
    {
        for (Article article : allArticle)
        {
            getToken(article);
        }
    }


    public static void getToken(Article article)
    {
        article.set("title-token", IndexTokenizer.segment(article.get("title").toString()));
        article.set("content-token", IndexTokenizer.segment(article.get("content").toString()));
    }


    public static void getSummary(List<Article> allArticle, int summaryNum)
    {
        for (Article article : allArticle)
        {
            getSummary(article, summaryNum);
        }
    }


    public static void getSummary(Article article, int summaryNum)
    {
        article.set("content-summary", HanLP.extractSummary(article.get("content").toString(), summaryNum));
    }
}
