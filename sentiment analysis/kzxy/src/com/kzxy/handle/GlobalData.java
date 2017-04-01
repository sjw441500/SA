package com.kzxy.handle;

import com.kzxy.data.Article;
import jxl.read.biff.BiffException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

/**
 * Created by fuxiuyin on 15-12-28.
 */
public class GlobalData
{
    private List<Article> allArticle;
    private Map<String, Object> otherData;
    private boolean isReaded;
    private Config globalConfig;

    public GlobalData() throws IOException, ParseException
    {
        allArticle = new ArrayList<>();
        otherData = new HashMap<>();
        isReaded = false;
        globalConfig = ConfigReader.ReadConfig("./plugin");
    }

    public Config getGlobalConfig()
    {
        return this.globalConfig;
    }


    public void setSettings(Config config)
    {
        globalConfig.fuse(config);
    }


    public void setSettings(String key, Object value)
    {
        globalConfig.set(key, value);
    }


    public Object getSettings(String key)
    {
        return globalConfig.get(key);
    }


    public void readArticle() throws ReadArticleException
    {
        Object tmp = null;
        if ((tmp = globalConfig.get("article-init-settings")) != null)
        {
            throw new ReadArticleException("缺少设置", 1);
        }
        else
        {
            String names[] = (String[])globalConfig.get("names");
            String articleFilePath = (String)globalConfig.get("article-file-path");
            if (names == null)
            {
                throw new ReadArticleException("缺少names设置", 1);
            }
            if (articleFilePath == null)
            {
                throw new ReadArticleException("缺少文章路径设置", 1);
            }
            try
            {
                XlsReader reader = new XlsReader(articleFilePath);
                allArticle = reader.readXls(names);
            }
            catch (BiffException | IOException e)
            {
                throw new ReadArticleException("文件打开失败", 2);
            }

        }
        isReaded = true;
    }


//    public ResultSet executePlugin(String pluginName) throws PluginExecuteException
//    {
//        if (isReaded)
//        {
//            return pluginLoader.executePlugin(pluginName, allArticle);
//        }
//        else
//        {
//            throw new PluginExecuteException("没有读取文件", 11);
//        }
//    }
}


class BaseException extends Exception
{
    public BaseException(String msg)
    {
        super(msg);
    }
}



class ReadArticleException extends BaseException
{

    public int errorCode;


    public ReadArticleException(String msg, int errorCode)
    {
        super(msg);
        this.errorCode = errorCode;
    }
}