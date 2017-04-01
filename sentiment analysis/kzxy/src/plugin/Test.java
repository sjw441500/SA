package plugin;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;
import com.kzxy.handle.XlsWriter;
import com.kzxy.plugin.PluginExectExcept;
import com.kzxy.plugin.PluginInterface;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by fuxiuyin on 15-12-29.
 */
public class Test implements PluginInterface
{

    public void test()
    {
        System.out.println("hello world");
    }


    public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept
    {
        XlsWriter write = null;
        try
        {
            Process p = Runtime.getRuntime().exec("cd ./plugin/test/\npython ./plugin/test/test.py");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }
            if (!sb.toString().equals(""))
            {
                System.err.println(sb.toString());
                throw new PluginExectExcept("", 22);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new PluginExectExcept("", 22);
        }
        allData.setSettings("plugin1-result", "hello world");

    }


    public void clean()
    {
        System.out.println("clean success");
    }
}
