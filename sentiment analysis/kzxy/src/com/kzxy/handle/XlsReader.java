package com.kzxy.handle;

import com.kzxy.data.Article;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiuyin on 15-12-14.
 */

public class XlsReader
{
    private String fileName;
    private Workbook wb;
    private boolean isOpen;

    public XlsReader(String fileName) throws IOException, BiffException
    {
        this.fileName = fileName;
        File file = new File(fileName);
        System.out.println(System.getProperty("user.dir"));
        wb = Workbook.getWorkbook(file);
        isOpen = true;
    }


    private void open() throws IOException, BiffException {
        if (!isOpen)
        {
            File file = new File(fileName);
            wb = Workbook.getWorkbook(file);
            isOpen = true;
        }

    }


    public List<Article> readXls(String[] dataName, RRuleList ruleList, ReaderOperation operation) throws IOException, BiffException {
        if (!isOpen)
            open();
        Sheet sheet = wb.getSheet(operation.sheetNum());
        int resultNum = sheet.getRows();
        int rsColumns = sheet.getColumns();
        int resultColumnNum = rsColumns > dataName.length ? dataName.length : rsColumns;
        int beginX = 0;
        int beginY = 0;
        if (operation.ignoreFileLine())
        {
            --resultNum;
            ++beginY;
        }

        List<Article> result = new ArrayList<>();

        for (int y = beginY; y < resultNum; ++y)
        {
            Article article = new Article();
            boolean need = true;
            for (int x = beginX; x < resultColumnNum; ++x)
            {
                Cell cell = sheet.getCell(x, y);
                if (!ruleList.isNeed(cell))
                {
                    need = false;
                    break;
                }
                article.set(dataName[x], cell.getContents());
            }
            if (need)
            {
                result.add(article);
            }

        }
        close();
        return result;
    }


    public List<Article> readXls(String[] dataName, ReaderOperation operation) throws IOException, BiffException
    {
        return readXls(dataName, new RRuleList(), operation);
    }


    public List<Article> readXls(String[] dataName, RRuleList ruleList) throws IOException, BiffException
    {
        return readXls(dataName, ruleList, new ReaderOperation());
    }


    public List<Article> readXls(String[] dataName) throws IOException, BiffException
    {
        return readXls(dataName, new RRuleList(), new ReaderOperation());
    }


    private void close()
    {
        if (isOpen)
        {
            wb.close();
            isOpen = false;
        }
    }
}


class ReaderOperation
{
    private boolean ignoreFirstLine;
    private int sheetNum;


    public ReaderOperation()
    {
        this.ignoreFirstLine = true;
        sheetNum = 0;
    }


    public ReaderOperation(boolean ignoreFirstLine, int sheetNum)
    {
        this.ignoreFirstLine = ignoreFirstLine;
        this.sheetNum = sheetNum;
    }


    public boolean ignoreFileLine()
    {
        return this.ignoreFirstLine;
    }


    public int sheetNum()
    {
        return this.sheetNum;
    }
}


class RRuleList
{
    private List<RRule> ruleList;

    public RRuleList()
    {
        ruleList = new ArrayList<>();
    }


    public void addRule(RRule rule)
    {
        ruleList.add(rule);
    }


    public boolean isNeed(Cell cell)
    {
        for (RRule rule : ruleList)
        {
            if (!rule.isNeed(cell.getColumn(), cell.getContents()))
            {
                return false;
            }
        }
        return true;
    }
}


abstract class RRule
{
    protected int columnNum;
    abstract public boolean isNeed(int columnNum, String value);
}


class REqualRule extends RRule
{
    private String needValue;

    public REqualRule(int columnNum, String needValue)
    {
        this.columnNum = columnNum;
        this.needValue = needValue;
    }


    public boolean isNeed(int columnNum, String value)
    {
        return columnNum != this.columnNum || this.needValue.equals(value);
    }
}


class RNotEqualRule extends RRule
{
    private String noNeedValue;

    public RNotEqualRule(int columnNum, String noNeedValue)
    {
        this.columnNum = columnNum;
        this.noNeedValue = noNeedValue;
    }

    public boolean isNeed(int columnNum, String value)
    {
        return columnNum != this.columnNum || !this.noNeedValue.equals(value);
    }
}