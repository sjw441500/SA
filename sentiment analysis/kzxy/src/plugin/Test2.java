package plugin;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;
import com.kzxy.plugin.PluginExectExcept;
import com.kzxy.plugin.PluginInterface;

import java.util.List;

/**
 * Created by fuxiuyin on 15-12-29.
 */
public class Test2 implements PluginInterface
{
    public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept
    {
        System.out.println("hello world");
        allArticle.size();
        Object test = allArticle.get(0).get("title");
        Object test2 = allArticle.get(0).get("content");//内容
        
        
        System.out.println(test);
        
    }


    public void clean()
    {

    }
}
