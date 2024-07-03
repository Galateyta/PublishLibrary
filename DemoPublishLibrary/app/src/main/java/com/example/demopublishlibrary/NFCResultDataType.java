package com.example.demopublishlibrary;

import java.util.ArrayList;

public class NFCResultDataType
{
    public ArrayList<String> arrayList;

    NFCResultDataType()
    {
        arrayList = new ArrayList<String>();
        arrayList.add("Last name");
        arrayList.add("First name");
        arrayList.add("Date Of Birth");
        arrayList.add("Gender");
        arrayList.add("Nationality");
        arrayList.add("Expiration Date");
        arrayList.add("Document Number");
        arrayList.add("Document Type");
        arrayList.add("Issuing Country");
    }

    public ArrayList<String> getDataType()
    {
        return arrayList;
    }
}
