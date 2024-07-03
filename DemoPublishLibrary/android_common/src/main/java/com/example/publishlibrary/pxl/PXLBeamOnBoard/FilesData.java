package com.example.publishlibrary.pxl.PXLBeamOnBoard;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/**Copyright (c) 2017 PXLVision. All rights reserved.
 *
 * The FilesData is a container for files created during scanning process
 */
public class FilesData implements Parcelable, Serializable
{
    private int fileType;
    private String fileDescription;
    private byte[] fileData;

    public FilesData(int fileType, String fileDescription, byte[] fileData)
    {
        this.fileType = fileType;
        this.fileDescription = fileDescription;
        this.fileData = fileData;
    }

    public byte[] getFileData()
    {
        return fileData;
    }

    public int getFileType()
    {
        return fileType;
    }

    public String getFileDescription()
    {
        return fileDescription;
    }

    public void setFileData(byte[] fileData)
    {
        this.fileData = fileData;
    }

    public void setFileType(int fileType)
    {
        this.fileType = fileType;
    }

    public void setFileDescription(String fileDescription)
    {
        this.fileDescription = fileDescription;
    }

    @Override public int describeContents()
    {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.fileType);
        dest.writeString(this.fileDescription);
        dest.writeInt(this.fileData.length);
        dest.writeByteArray(this.fileData);
    }

    protected FilesData(Parcel in)
    {
        this.fileType = in.readInt();
        this.fileDescription = in.readString();
        this.fileData = new byte[in.readInt()];
        in.readByteArray(this.fileData);
    }

    public static final Creator<FilesData> CREATOR = new Creator<FilesData>() {
        @Override public FilesData createFromParcel(Parcel in)
        {
            return new FilesData(in);
        }

        @Override public FilesData[] newArray(int size)
        {
            return new FilesData[size];
        }
    };
}
