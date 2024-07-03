package com.example.publishlibrary.pxl.PXLBeamOnBoard;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * ExtractDataList
 * Copyright (c) 2017 PXLVision. All rights reserved.
 *
 * The ExtracDataList contains ExtractData objects which are key-value pairs which contain
 * data obtained through the extraction process
 */

public class ExtractDataList implements Serializable
{
    private boolean HAS_DATA = false;
    private int key;
    private String value;

    private ExtractDataList nextElement;

    /**
     * The ExtractData contains extracted data field types
     * and extracted data value.
     */
    public ExtractDataList()
    {
        this.key = 0;
        this.value = "";
        this.nextElement = null;
    }

    public ExtractDataList(int key, String value)
    {
        this.key = key;
        this.value = value;
        this.nextElement = null;
    }

    public boolean getHasData()
    {
        return HAS_DATA;
    }

    public void setHasData()
    {
        HAS_DATA = true;
    }

    /**
     * @return The number of key-value pairs contained in the ExtracDataList object
     */
    public int getCount()
    {
        int rez = 1;
        ExtractDataList thisEl = this;
        while (thisEl.nextElement != null)
        {
            thisEl = thisEl.nextElement;
            rez++;
        }
        if (rez == 1)
        {
            return rez;
        }
        else
        {
            return rez - 1;
        }
    }

    public void addExtractData(int key, String value)
    {
        ExtractDataList thisEl = this;
        while (thisEl.nextElement != null)
        {
            thisEl = thisEl.nextElement;
        }

        thisEl.nextElement = new ExtractDataList(key, value);
    }

    public ExtractDataList getExtractData(int position)
    {
        ExtractDataList thisEl = this;

        for (int i = 0; i < position; i++)
        {
            thisEl = thisEl.nextElement;
        }
        return thisEl;
    }

    /**
     * @param key The key of the value to return
     * @return The value of the ExtractedData
     */
    public String getValue(int key)
    {
        ExtractDataList thisEl = this;
        while ((thisEl.key != key) && (thisEl.nextElement != null))
        {
            thisEl = thisEl.nextElement;
        }

        if (thisEl.key == key)
        {
            return thisEl.value;
        }
        else
        {
            return null;
        }
    }

    public void setValue(String value, int key)
    {
        ExtractDataList thisEl = this;
        while ((thisEl.key != key) && (thisEl.nextElement != null))
        {
            thisEl = thisEl.nextElement;
        }

        if (thisEl.key == key)
        {
            thisEl.value = value;
        }
    }

    public int getKey(int pos)
    {
        ExtractDataList thisEl = this;

        for (int i = 0; i < pos; i++)
        {
            thisEl = thisEl.nextElement;
        }
        return thisEl.key;
    }

    public void cleanDataWithPattern(ArrayList<String> pattern)
    {
        Field[] fields = Enums.class.getDeclaredFields();
        TreeSet<Integer> fieldSet = new TreeSet<Integer>();
        Enums fieldEnum = new Enums();
        for (Field field : fields)
        {
            for (int i = 0; i < pattern.size(); ++i)
            {
                if (field.toString().contains(pattern.get(i)))
                {
                    try
                    {
                        fieldSet.add(field.getInt(fieldEnum));
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        ExtractDataList thisEl = this;
        while (thisEl != null)
        {
            if (fieldSet.contains(thisEl.key))
            {
                thisEl.value = "";
            }
            thisEl = thisEl.nextElement;
        }
    }

    /**
     * @param key The key to update if exists or add
     * @param value The value to set
     */
    public void setExtractData(int key, String value)
    {
        ExtractDataList thisEl = this;
        while (thisEl != null)
        {
            if (thisEl.key == key)
            {
                thisEl.value = value;
                break;
            }
            if (thisEl.nextElement == null)
            {
                thisEl.nextElement = new ExtractDataList(key, value);
                break;
            }
            thisEl = thisEl.nextElement;
        }
    }
}
