package com.kzxy.plugin;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;

import java.util.List;

/**
 * Created by fuxiuyin on 15-12-29.
 */
public interface PluginInterface
{
    public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept;
    public void clean();
}
