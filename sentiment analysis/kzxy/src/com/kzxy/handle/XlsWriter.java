package com.kzxy.handle;

import com.kzxy.data.Article;
import com.sun.corba.se.spi.ior.Writeable;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by fuxiuyin on 15-12-15.
 */
public class XlsWriter
{
    private String name;
    private WritableWorkbook wb;
    private boolean isOpen;
    private String[] title;


    public XlsWriter(String name) throws IOException
    {
        this.name = name;
        File file = new File(name);
        wb = Workbook.createWorkbook(file);
        isOpen = true;
    }


    private void open() throws IOException
    {
        if (!isOpen)
        {
            File file = new File(name);
            wb = Workbook.createWorkbook(file);
            isOpen = true;
        }
    }


    private void close() throws IOException, WriteException
    {
        if (isOpen)
        {
            wb.close();
            isOpen = false;
        }
    }


    public void setTitle(String[] titles)
    {
        this.title = titles.clone();
    }


    public String[] getTitle()
    {
        return this.title;
    }


    public void cleanTitle()
    {
        this.title = null;
    }


    private boolean writeArticle(Article articleToWrite, String[] titles, int lineNum, WritableSheet ws)
    {
        for (int i = 0; i < titles.length; ++i)
        {
            Label label = new Label(i, lineNum, articleToWrite.get(titles[i]).toString());
            try
            {
                ws.addCell(label);
            }
            catch (RowsExceededException e)
            {
                e.printStackTrace();
                System.err.println(e.getMessage());
                return false;
            }
            catch (WriteException e)
            {
                e.printStackTrace();
                System.err.println(e.getMessage());
                return false;
            }
        }
        return true;
    }


    public void write(Article[] articles, WriteOperation operation, WRuleList rule, String tableName, int sheetNum) throws IOException, WriteException
    {
        if (!isOpen)
            open();
        WritableSheet ws = wb.createSheet(tableName, sheetNum);
        int successNum = 0;
        int failNum = 0;
        int ignore = 0;
        int i = 0;
        String[] titles = this.title == null ? articles[0].getAllKey() : this.title;
        if (operation.isHasTitle())
        {
            for (int j = 0; j < titles.length; ++j)
            {
                Label label = new Label(j, 0, titles[j]);
                ws.addCell(label);
            }
            ++i;
        }
        int j = 0;
        for (; j < articles.length; ++j, ++i)
        {
            if (!rule.isNeed(articles[i]))
            {
                ++ignore;
                continue;
            }
            if (writeArticle(articles[i], titles, i, ws))
                ++successNum;
            else
                ++failNum;
        }
        wb.write();
        close();
        System.out.println("成功: " + successNum + "行");
        System.out.println("失败: " + failNum + "行");
        System.out.println("跳过: " + ignore + "行");
    }


    public void write(List<Article> articleList, WriteOperation operation, WRuleList rule, String tableName, int sheetNum) throws IOException, WriteException
    {
        if (!isOpen)
            open();
        WritableSheet ws = wb.createSheet(tableName, sheetNum);
        int successNum = 0;
        int failNum = 0;
        int ignore = 0;
        int i = 0;
        String[] titles = this.title == null ? articleList.get(0).getAllKey() : this.title;
        if (operation.isHasTitle())
        {
            for (int j = 0; j < titles.length; ++j)
            {
                Label label = new Label(j, 0, titles[j]);
                ws.addCell(label);
            }
            ++i;
        }
        int j = 0;
        for (; j < articleList.size(); ++j, ++i)
        {
            if (!rule.isNeed(articleList.get(j)))
            {
                ++ignore;
                continue;
            }
            if (writeArticle(articleList.get(j), titles, i, ws))
                ++successNum;
            else
                ++failNum;
        }
        wb.write();
        close();
        System.out.println("成功: " + successNum + "行");
        System.out.println("失败: " + failNum + "行");
        System.out.println("跳过: " + ignore + "行");
    }


    public void write(List<Article> articleList, WriteOperation operation, String tableName) throws IOException, WriteException
    {
        write(articleList, operation, new WRuleList(), tableName, 0);
    }


    public void write(List<Article> articleList, WriteOperation operation, WRuleList rule, String tableName) throws IOException, WriteException
    {
        write(articleList, operation, rule, tableName, 0);
    }


    public void write(List<Article> articleList, String tableName) throws IOException, WriteException
    {
        write(articleList, new WriteOperation(), new WRuleList(), tableName, 0);
    }


    public void write(List<Article> articleList, WRuleList rule, String tableName) throws IOException, WriteException
    {
        write(articleList, new WriteOperation(), rule, tableName, 0);
    }


    public void write(List<Article> articleList, WriteOperation operation, int sheetNum) throws IOException, WriteException
    {
        write(articleList, operation, new WRuleList(), "list" + sheetNum, sheetNum);
    }


    public void write(List<Article> articleList, WriteOperation operation, WRuleList rule, int sheetNum) throws IOException, WriteException
    {
        write(articleList, operation, rule, "list" + sheetNum, sheetNum);
    }


    public void write(List<Article> articleList, int sheetNum) throws IOException, WriteException
    {
        write(articleList, new WriteOperation(), new WRuleList(), "list" + sheetNum, sheetNum);
    }


    public void write(List<Article> articleList, WRuleList rule, int sheetNum) throws IOException, WriteException
    {
        write(articleList, new WriteOperation(), rule, "list" + sheetNum, sheetNum);
    }


    public void write(List<Article> articleList, WriteOperation operation) throws IOException, WriteException
    {
        write(articleList, operation, new WRuleList(), "list0", 0);
    }


    public void write(List<Article> articleList, WriteOperation operation, WRuleList rule) throws IOException, WriteException
    {
        write(articleList, operation, rule, "list0", 0);
    }


    public void write(List<Article> articleList) throws IOException, WriteException
    {
        write(articleList, new WriteOperation(), new WRuleList(), "list0", 0);
    }


    public void write(List<Article> articleList, WRuleList rule) throws IOException, WriteException
    {
        write(articleList, new WriteOperation(), rule, "list0", 0);
    }


    public void write(Article[] articles, WriteOperation operation, String tableName) throws IOException, WriteException
    {
        write(articles, operation, new WRuleList(), tableName, 0);
    }


    public void write(Article[] articles, WriteOperation operation, WRuleList rule, String tableName) throws IOException, WriteException
    {
        write(articles, operation, rule, tableName, 0);
    }


    public void write(Article[] articles, String tableName) throws IOException, WriteException
    {
        write(articles, new WriteOperation(), new WRuleList(), tableName, 0);
    }


    public void write(Article[] articles, WRuleList rule, String tableName) throws IOException, WriteException
    {
        write(articles, new WriteOperation(), rule, tableName, 0);
    }


    public void write(Article[] articles, WriteOperation operation, int sheetNum) throws IOException, WriteException
    {
        write(articles, operation, new WRuleList(), "list" + sheetNum, sheetNum);
    }


    public void write(Article[] articles, WriteOperation operation, WRuleList rule, int sheetNum) throws IOException, WriteException
    {
        write(articles, operation, rule, "list" + sheetNum, sheetNum);
    }


    public void write(Article[] articles, int sheetNum) throws IOException, WriteException
    {
        write(articles, new WriteOperation(), new WRuleList(), "list" + sheetNum, sheetNum);
    }


    public void write(Article[] articles, WRuleList rule, int sheetNum) throws IOException, WriteException
    {
        write(articles, new WriteOperation(), rule, "list" + sheetNum, sheetNum);
    }


    public void write(Article[] articles, WriteOperation operation) throws IOException, WriteException
    {
        write(articles, operation, new WRuleList(), "list0", 0);
    }


    public void write(Article[] articles, WriteOperation operation, WRuleList rule) throws IOException, WriteException
    {
        write(articles, operation, rule, "list0", 0);
    }


    public void write(Article[] articles) throws IOException, WriteException
    {
        write(articles, new WriteOperation(), new WRuleList(), "list0", 0);
    }


    public void write(Article[] articles, WRuleList rule) throws IOException, WriteException
    {
        write(articles, new WriteOperation(), rule, "list0", 0);
    }
}


class WriteOperation
{
    private boolean hasTitle;


    public WriteOperation(boolean hasTitle)
    {
        this.hasTitle = hasTitle;
    }


    public WriteOperation()
    {
        this.hasTitle = true;
    }


    public boolean isHasTitle()
    {
        return this.hasTitle;
    }
}


class WRuleList
{
    private List<WRule> ruleList;

    public WRuleList()
    {
        ruleList = new ArrayList<>();
    }


    public void addRule(WRule rule)
    {
        ruleList.add(rule);
    }


    public boolean isNeed(Article article)
    {
        for (WRule rule : ruleList)
        {
            if (!rule.isNeed(article))
            {
                return false;
            }
        }
        return true;
    }
}


abstract class WRule
{
    protected String argName;
    abstract public boolean isNeed(Article article);
}



class WEqualRule extends WRule
{
    private String needValue;


    public WEqualRule(String argName, String needValue)
    {
        this.argName = argName;
        this.needValue = needValue;
    }


    public boolean isNeed(Article article)
    {
        Object tmp = article.get(argName);
        return tmp != null && needValue.equals(tmp.toString());
    }
}


class WNotEqualRule extends WRule
{
    private String noNeedValue;


    public WNotEqualRule(String argName, String noNeedValue)
    {
        this.argName = argName;
        this.noNeedValue = noNeedValue;
    }


    public boolean isNeed(Article article)
    {
        Object tmp = article.get(argName);
        return tmp == null ||  !noNeedValue.equals(tmp.toString());
    }
}