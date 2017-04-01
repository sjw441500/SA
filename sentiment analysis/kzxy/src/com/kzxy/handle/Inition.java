package com.kzxy.handle;
import com.kzxy.data.Article;
import com.kzxy.plugin.PluginExectExcept;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import jxl.read.biff.BiffException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import org.json.simple.parser.ParseException;



public class Inition{
	public List<Article> allArticle = null;
	public GlobalData allData = null;
	public PluginLoader pluginLoader = null;
    public void initsys(XlsReader reader) throws PluginExectExcept, BiffException, IOException, ParseException
    {
    	String names[] = {"title", "source", "content", "url", "time", "author"};
        ReaderOperation operation = new ReaderOperation(false, 0);
        allArticle = reader.readXls(names, operation);
        allData = new GlobalData();
        Map<String, PluginConfig> allPluginConfig = (Map<String, PluginConfig>)allData.getGlobalConfig().get("plugins");
        pluginLoader = new PluginLoader(allPluginConfig);
    }
}