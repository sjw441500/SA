package com.kzxy.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuxiuyin on 15-12-14.
 */

public class Article
{
    private Map<String, Object> data;

    @Override
    public String toString()
    {
        String result = null;
        for (String key : data.keySet())
        {
            if (result == null)
            {
                result = String.format("\nkey:%s value: %s", key, data.get(key).toString());
            }
            else
            {
                result = String.format("%s\n%s", result, String.format("key:%s value: %s", key, data.get(key).toString()));
            }
        }
        result += "\n\n";
        return result;
    }

    public Article()
    {
        data = new HashMap<String, Object>();
    }


    public Object get(String key)
    {
        return data.get(key);
    }


    public Object set(String key, Object value)
    {
        return data.put(key, value);
    }


    public Object remove(String key)
    {
        return data.remove(key);
    }


    public String[] getAllKey()
    {
        String keys[] = new String[data.size()];
        int i = 0;

        for (String key : data.keySet())
        {
            keys[i] = key;
            ++i;
        }

        return keys;
    }


    public int getDatasNum()
    {
        return data.size();
    }
}
