package com.example.demopublishlibrary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class NFCResultData implements Serializable
{
    private HashMap<String, String> data;
    private NFCResultDataType type;

    public NFCResultData()
    {
        type = new NFCResultDataType();
    }

    public void setData(HashMap<String, String> data)
    {
        this.data = data;
    }

    public HashMap<String, String> getData()
    {
        return data;
    }

    public static String[] NFC_FILED_NAMES = { "Last name",       "First name",    "Date Of Birth",
                                               "Gender",          "Nationality",   "Expiration Date",
                                               "Document Number", "Document Type", "Issuing Country" };

    public ArrayList<String> getType()
    {
        return type.arrayList;
    }
}
