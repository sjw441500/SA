package com.kzxy.handle;

import com.kzxy.data.Article;
import com.kzxy.plugin.PluginExectExcept;
import com.kzxy.plugin.PluginInterface;
import jdk.internal.util.xml.impl.Input;
import jdk.nashorn.internal.objects.Global;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.Buffer;
import java.util.*;

/**
 * Created by fuxiuyin on 15-12-28.
 */
public class ConfigReader
{


    static String readAllFileContent(String filePath) throws IOException
    {
        BufferedReader reader = null;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        reader = new BufferedReader(inputStreamReader);
        String tmpString = null;
        String laststr = "";
        while((tmpString = reader.readLine()) != null)
        {
            laststr += tmpString;
        }
        inputStreamReader.close();
        fileInputStream.close();
        reader.close();
        return laststr;
    }


    static Config ReadConfig(String pluginDir) throws IOException, org.json.simple.parser.ParseException
    {
        String rootConfigPath = pluginDir + "/config.json";
        String rootConfigContent = ConfigReader.readAllFileContent(rootConfigPath);
        JSONParser parser = new JSONParser();

        JSONObject jobj = (JSONObject) parser.parse(rootConfigContent);
        JSONArray plugins = (JSONArray)jobj.get("plugin");
        Map<String, Object> configMap = new HashMap<>();
        Map<String, PluginConfig> pluginConfigMap = new HashMap<>();
        for(Object plugin : plugins)
        {
            JSONObject pluginObject = (JSONObject)plugin;
            String jarName;
            String className;
            String pluginName = (String)pluginObject.get("name");
            String pluginFilePath = pluginDir + "/" + pluginObject.get("dir");
            String pluginPath = pluginFilePath + "/" + "config.json";
            try
            {
                String pluginConfigContent = ConfigReader.readAllFileContent(pluginPath);
                JSONObject pluginObj = (JSONObject)parser.parse(pluginConfigContent);
                jarName = (String)pluginObj.get("jar_name");
                className = (String)pluginObj.get("class_name");
                if (jarName == null)
                {
                    throw new ParseException(10);
                }
                if (className == null)
                {
                    throw new ParseException(10);
                }
            }
            catch (org.json.simple.parser.ParseException e)
            {
                System.err.println("加载" + pluginObject.get("name") + "失败");
                System.err.println(pluginPath + "格式错误");
                continue;
            }
            catch (IOException e)
            {
                System.err.println("加载" + pluginObject.get("name") + "失败");
                System.err.println(pluginPath + "不存在");
                continue;
            }
            pluginConfigMap.put(pluginName, new PluginConfig(pluginFilePath, jarName, className));
        }
        configMap.put("plugins", pluginConfigMap);
        return new Config(configMap);
    }
}



class Config
{
    private Map<String, Object> data;


    public Config(Map<String, Object> data)
    {
        this.data = data;
    }


    public Object get(String key)
    {
        return data.get(key);
    }


    public Set<String> getAllKeys()
    {
        return data.keySet();
    }


    public void set(String key, Object value)
    {
        this.data.put(key, value);
    }


    public void fuse(Config config)
    {
        for (String key : config.getAllKeys())
        {
            this.set(key, config.get(key));
        }
    }
}


class PluginConfig
{
    private String pluginPath;
    private String jarName;
    private String className;
    private PluginInterface plugin;

    public PluginConfig(String pluginPath, String jarName, String className)
    {
        this.pluginPath = pluginPath;
        this.jarName = jarName;
        this.className = className;
    }


    public String getJarName()
    {
        return this.jarName;
    }


    public String getClassName()
    {
        return this.className;
    }


    public URL getJarUrl()
    {
        File file = new File(this.pluginPath + "/" + this.jarName);
        try
        {
            return file.toURL();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    private void load() throws PluginExectExcept
    {
        URL jarUrl = this.getJarUrl();
        if (jarUrl == null)
        {
            throw new PluginExectExcept("load fail", 31);
        }
        URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl});
        try
        {
            plugin = (PluginInterface)(loader.loadClass(className).newInstance());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            throw new PluginExectExcept("load fail", 31);
        }
    }


    public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept
    {
        if (plugin == null)
        {
            this.load();
        }
        this.plugin.exect(allArticle, allData);
    }


    public void clean()
    {
        if (this.plugin != null)
        {
            this.plugin.clean();
        }
    }


    public String getPluginPath()
    {
        return pluginPath;
    }




}