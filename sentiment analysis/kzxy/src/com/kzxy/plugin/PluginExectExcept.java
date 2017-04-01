package com.kzxy.plugin;

/**
 * Created by fuxiuyin on 15-12-29.
 */
public class PluginExectExcept extends Exception
{
    public int errorCode;

    public PluginExectExcept(String msg, int errorCode)
    {
        super(msg);
        this.errorCode = errorCode;
    }
}
