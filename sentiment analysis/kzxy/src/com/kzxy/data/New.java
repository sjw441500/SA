package com.kzxy.data;

/**
 * Created by fuxiuyin on 15-12-25.
 */
public class New
{
    private String newName;
    private int newId;

    public New(String newName, int newId)
    {
        this.newName = newName;
        this.newId = newId;
    }


    public New(String newName)
    {
        this.newName = newName;
        this.newId = 0;
    }


    public void setNewId(int newId)
    {
        this.newId = newId;
    }


    public String getNewName()
    {
        return newName;
    }


    public int getNewId()
    {
        return newId;
    }
}
