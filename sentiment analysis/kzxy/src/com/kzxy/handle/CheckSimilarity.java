package com.kzxy.handle;

import com.kzxy.data.Article;

import java.util.*;

/**
 * Created by fuxiuyin on 15-12-16.
 */
public class CheckSimilarity
{
    private Map<String, List<Integer>> contentTree;
    private ArticlePretreatment pretreatmenter;
    public CheckSimilarity()
    {
        contentTree = new HashMap<String, List<Integer>>();
        pretreatmenter = new ArticlePretreatment();
    }


    private void pretreatment(Article article)
    {
        pretreatmenter.pretreatment(article);
    }


    public void buildTree(Article[] articles)
    {
        for (Article article : articles)
        {
            addArticle(article);
        }
    }


    public void buildTree(List<Article> articleList)
    {
        articleList.forEach(this::addArticle);
    }


    public void addArticle(Article article)
    {
        Object articleId = article.get("id");
        Object articleSentences = article.get("sentences");
        if (articleId == null || articleSentences == null)
        {
            pretreatment(article);
            articleId = article.get("id");
            articleSentences = article.get("sentences");
        }

        for (String sentence : (String[])articleSentences)
        {
            List<Integer> articleIdList = contentTree.get(sentence);
            if (articleIdList == null)
            {
                List<Integer> integersTmp = new ArrayList<>();
                integersTmp.add((Integer)articleId);
                contentTree.put(sentence, integersTmp);
            }
            else
            {
                articleIdList.add((Integer)articleId);
            }
        }
    }
}



class Similarity
{
    private double similarity;
    private int articleId;
    private int similarity_num;


    public double getSimilarity()
    {
        return similarity;
    }


    public Similarity(int articleId)
    {
        this.similarity = 0.0;
        this.similarity_num = 0;
        this.articleId = articleId;
    }


    public void add()
    {
        ++similarity_num;
    }


    public void calculate(int sentences_num)
    {
        similarity = similarity_num / sentences_num;
    }


    @Override
    public String toString()
    {
        return String.format("%d:%s", articleId, similarity);
    }
}


class ArticleSimilarity
{
    private boolean isCalculate;
    private List<Similarity> allSimilarity;
    private boolean isSorted;
    private Map<Integer, Similarity> index;
    private int sentencesNum;
    private int articleId;

    public ArticleSimilarity(int articleId, int sentencesNum)
    {
        this.articleId = articleId;
        this.sentencesNum = sentencesNum;
        this.isCalculate = false;
        this.isSorted = true;
        index = new HashMap<>();
        allSimilarity = new ArrayList<>();
    }


    public Similarity getByArticleId(int articleId)
    {
        return index.get(articleId);
    }


    public void addSimilarity(int articleId)
    {
        isSorted = false;
        isCalculate = false;
        Similarity tmpSimilarity = new Similarity(articleId);
        tmpSimilarity.add();
        allSimilarity.add(tmpSimilarity);
        index.put(articleId, tmpSimilarity);
    }


    public void sorted()
    {
        allSimilarity.sort(new SimilairtyComparer());
        isSorted = true;
    }


    public void calculate()
    {
        for (Similarity similarity : allSimilarity)
        {
            similarity.calculate(sentencesNum);
        }
        isCalculate = true;
        isSorted = false;
    }


    public void addLikeArticleList(int[] articleIdList)
    {
        isCalculate = true;
        for (int articleId : articleIdList)
        {
            Similarity similarity = null;
            similarity = index.get(articleId);
            if (similarity == null)
            {
                addSimilarity(articleId);
            }
            else
            {
                similarity.add();
            }
        }
    }


    public Similarity[] getTopN(int topNum)
    {
        if (!isCalculate)
        {
            calculate();
        }
        if (!isSorted)
        {
            sorted();
        }

        Similarity[] result = new Similarity[topNum];
        for (int i = 0; i < topNum; ++i)
        {
            result[i] = allSimilarity.get(i);
        }

        return result;
    }
}


class SimilairtyComparer implements Comparator<Similarity>
{
    public int compare(Similarity o1, Similarity o2)
    {
        return o1.getSimilarity() > o2.getSimilarity() ? 1 : 0;
    }
}




class ArticlePretreatment
{
    private int articleCount;
    private String[] separators;


    public ArticlePretreatment()
    {
        articleCount = 0;
        separators = new String[]{",", "，", ".", "。", "?", "!", "？", "！", " ", "\t", ";", "；", "　"};
    }


    public ArticlePretreatment(String[] separators)
    {
        articleCount = 0;
        this.separators = separators;
    }


    private void markArticleNum(Article article)
    {
        if (article.get("id") != null)
        {
            ++articleCount;
            article.set("id", articleCount);
        }
    }


    private void splitArticle(Article  article)
    {
        if (article.get("sentences") != null)
        {
            String articleContent = (String)article.get("content");
            String splitStr = String.format("\\%s", separators[0]);
            for (int i = 1; i < separators.length; ++i)
            {
                splitStr = String.format("%s|\\%s", splitStr, separators[i]);
            }
            article.set("sentences", articleContent.split(splitStr));
        }
    }


    public void pretreatment(Article article)
    {
        splitArticle(article);
        markArticleNum(article);
    }
}
