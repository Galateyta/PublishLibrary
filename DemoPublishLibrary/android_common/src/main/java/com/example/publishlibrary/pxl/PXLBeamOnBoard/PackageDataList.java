package com.example.publishlibrary.pxl.PXLBeamOnBoard;

/**
 * Created by andrej on 17.7.17..
 */

public class PackageDataList
{
    private PackageData[] packageDataArray;

    public PackageDataList()
    {
        packageDataArray = new PackageData[2];
    }

    public PackageData getPackageData(int index)
    {
        return packageDataArray[index];
    }

    public void setPackageData(int index, PackageData data)
    {
        packageDataArray[index] = data;
    }
}
