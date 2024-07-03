package com.example.publishlibrary.pxl.PXLBeamOnBoard;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

//---------FilesData---------//

/**Copyright (c) 2017 PXLVision. All rights reserved.
 *
 * The FilesDataList type contains all files created during the scanning process
 * and extracted data value.
 */

public class FilesDataList implements Parcelable, Serializable
{
    private ArrayList<FilesData> filesDataArray;

    public FilesDataList()
    {
        filesDataArray = new ArrayList<>();
    }

    @Override public int describeContents()
    {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeList(this.filesDataArray);
    }

    protected FilesDataList(Parcel in)
    {
        this.filesDataArray = in.readArrayList(FilesData.class.getClassLoader());
    }

    public static final Creator<FilesDataList> CREATOR = new Creator<FilesDataList>() {
        @Override public FilesDataList createFromParcel(Parcel in)
        {
            return new FilesDataList(in);
        }

        @Override public FilesDataList[] newArray(int size)
        {
            return new FilesDataList[size];
        }
    };

    /**Adds a FilesData object
     *
     * @param fileType The format of the file: PDF, MP4, JPG....
     * @param fileDescription The File description
     * @param fileData The bytes of the file
     */
    public void addFilesData(int fileType, String fileDescription, byte[] fileData)
    {
        filesDataArray.add(new FilesData(fileType, fileDescription, fileData));
    }

    /**
     *
     * @param index The index of the FilesData object
     * @return The format of the file: PDF, MP4, JPG....
     */
    public int getFileType(int index)
    {
        return filesDataArray.get(index).getFileType();
    }

    public String getFileDescription(int index)
    {
        return filesDataArray.get(index).getFileDescription();
    }

    public void setFileDescription(int index, String description)
    {
        filesDataArray.get(index).setFileDescription(description);
    }

    /**The FilesData contains all files created during the scanning process
     *and extracted data value.
     *
     * @param index The Index of the file to return
     * @return FilesData object with the given index
     */
    public byte[] getFileData(int index)
    {
        return filesDataArray.get(index).getFileData();
    }

    public int getFileDataLength(int index)
    {
        return filesDataArray.get(index).getFileData().length;
    }

    /**
     * @return The number of files contained in the FilesData object
     */
    public int getFilesCount()
    {
        return filesDataArray.size();
    }

    /**Removes a FilesData object from the FilesDataList
     *
     * @param index The index of the FilesData object to be removed
     */
    public void remove(int index)
    {
        filesDataArray.remove(index);
    }
}
