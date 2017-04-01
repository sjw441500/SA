package com.kzxy.handle;

import com.kzxy.data.Article;
import com.kzxy.plugin.PluginExectExcept;
import com.kzxy.plugin.PluginInterface;

import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

/**
 * Created by fuxiuyin on 15-12-28.
 */
public class PluginLoader
{
    private String[] allPlugin;
    private Map<String, PluginConfig> allPluginConfig;

    public PluginLoader(Map<String, PluginConfig> allPluginConfig)
    {
        this.allPluginConfig = allPluginConfig;
        allPlugin = new String[allPluginConfig.size()];
        int i = 0;
        for (String pluginName : allPluginConfig.keySet())
        {
            allPlugin[i] = pluginName;
            ++i;
        }
    }


    public void exect(List<Article> allArticle, GlobalData allData, String pluginName) throws PluginExectExcept
    {
        PluginConfig pluginToExect = allPluginConfig.get(pluginName);
        pluginToExect.exect(allArticle, allData);
    }

}