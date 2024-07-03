package com.example.publishlibrary.pxl.PXLBeamOnBoard;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PackageData
 * Copyright (c) 2017 PXLVision. All rights reserved.
 *
 * The PackageData type contains all information required to be sent for each step
 */

public class PackageData implements Parcelable, Serializable
{
    private ExtractDataList data;
    private int errorType;
    private int errorCode;
    private int status;
    private int activeScenario;
    private int dataLength;
    private FilesDataList files;
    private ArrayList<Integer> errorCodeList = new ArrayList<>();
    private int NFCErrorCode;

    public PackageData()
    {
        data = null;
        errorType = 0;
        errorCode = 0;
        status = 0;
        activeScenario = 0;
        dataLength = 0;
        NFCErrorCode = 0;
        files = null;
    }

    public PackageData(ExtractDataList data, int errorType, int errorCode, int status, int activeScenario,
                       int dataLength, FilesDataList files)
    {
        this.data = data;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.status = status;
        this.activeScenario = activeScenario;
        this.dataLength = dataLength;
        this.files = files;
    }

    public PackageData(ExtractDataList data, int errorType, int errorCode, int status, int activeScenario,
                       int dataLength, FilesDataList files, ArrayList<Integer> errorCodeList)
    {
        this.data = data;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.status = status;
        this.activeScenario = activeScenario;
        this.dataLength = dataLength;
        this.files = files;
        this.errorCodeList = errorCodeList;
    }

    @Override public int describeContents()
    {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.errorType);
        dest.writeInt(this.errorCode);
        dest.writeInt(this.status);
        dest.writeInt(this.activeScenario);
        dest.writeInt(this.dataLength);
        dest.writeParcelable(this.files, 0);
        dest.writeSerializable(this.data);
        dest.writeSerializable(this.errorCodeList);
    }

    protected PackageData(Parcel in)
    {
        this.errorType = in.readInt();
        this.errorCode = in.readInt();
        this.status = in.readInt();
        this.activeScenario = in.readInt();
        this.dataLength = in.readInt();
        this.files = in.readParcelable(FilesDataList.class.getClassLoader());
        this.data = (ExtractDataList)in.readSerializable();
        this.errorCodeList = (ArrayList<Integer>)in.readSerializable();
    }

    public static final Creator<PackageData> CREATOR = new Creator<PackageData>() {
        @Override public PackageData createFromParcel(Parcel in)
        {
            return new PackageData(in);
        }

        @Override public PackageData[] newArray(int size)
        {
            return new PackageData[size];
        }
    };

    /**The result of the data extraction process
     *
     * @return ExtractDataList object
     */
    public ExtractDataList getData()
    {
        return data;
    }

    public void setData(ExtractDataList data)
    {
        this.data = data;
    }

    /**
     * @return the status of the current callback
     */
    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    /**
     * @return the current scenario step that is beeing executed
     */
    public int getActiveScenario()
    {
        return activeScenario;
    }

    public void setActiveScenario(int activeScenario)
    {
        this.activeScenario = activeScenario;
    }

    /**
     * @return  the total number of files allocated
     */
    public int getFilesCount()
    {
        if (files == null)
        {
            return 0;
        }
        return files.getFilesCount();
    }

    /**
     * @return files obtained during the scanning process
     */
    public FilesDataList getFiles()
    {
        return files;
    }

    /**
     * @param files The FilesData contains all files created during the scanning process
    and extracted data value.
     */
    public void setFiles(FilesDataList files)
    {
        this.files = files;
    }

    /**ErrorData type contains the error code for the backend and the error type to be used for user interaction
     * @return error group type for user interaction
     */
    public int getErrorType()
    {
        return errorType;
    }

    public void setErrorType(int errorType)
    {
        this.errorType = errorType;
    }

    /**ErrorData type contains the error code for the backend and the error type to be used for user interaction
     *
     * @return error code to be included in the report
     */
    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    public int getNFCErrorCode()
    {
        return NFCErrorCode;
    }

    public void setNFCErrorCode(int errorCode)
    {
        this.NFCErrorCode = errorCode;
    }

    public void deleteAllData()
    {
        data = null;
        files = null;
    }

    /**
     * @return extracted data length
     */
    public int getDataLength()
    {
        return dataLength;
    }

    public void setDataLength(int dataLength)
    {
        this.dataLength = dataLength;
    }

    public ArrayList<Integer> getErrorCodeList()
    {
        return errorCodeList;
    }

    public void setErrorCodeItem(int errorCode)
    {
        errorCodeList.add(errorCode);
    }
}
