package com.example.demopublishlibrary;

import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_CameraAccessForbidden;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_CameraFailure;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_DataDecryptionFaceNoMatch;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_DataDecryptionGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_DataEncryptionGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_DocGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_DocTimeoutExpired;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceCaptureGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceCaptureIncomplete;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceFrameSimilarityFailed;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceLivenessFailed;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceNotRecognised;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceNotVerified;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FaceStatic;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FunctionNotImplemented;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldBirthDay;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldCompositCheckDigit;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldCountry;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldDocumentNumber;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldExpiryDay;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldFirstName;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldGender;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldLastName;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFieldNationality;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdBadMrzFields;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdExpired;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdFinalPackageDataForUpload;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdMatchingFailed;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdNoData;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdNotInList;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdPageMissing;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdTimeoutExpired;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_IdTypeNotValid;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NoInternetConnection;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_None;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_QrCodeBadData;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_QrCodeCaptureFailed;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_QrCodeGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_SignDocGeneric;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_ThirdPartyLibFailure;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_ThirdPartyLibLicenceExpired;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_Unexpected_Stop;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_Unknown;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EUserErrorType_None;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EUserErrorType_Retry;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EUserErrorType_RetrySave;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.ArrayMap;
import android.util.Log;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.FilesData;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.FilesDataList;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.PackageData;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RootModel
{
    private static final String TAG = "ROOT_MODEL";
    public static String DATA_PATH;
    public static String SDK_DATA_PATH;
    public static String INT_STORAGE;
    private Activity mActivity;
    private boolean isFirstInstall;

    public RootModel(Activity activity, boolean isFirstInstall)
    {
        DATA_PATH = activity.getExternalFilesDir(null).getAbsolutePath() + File.separator;
        INT_STORAGE = activity.getFilesDir().getParent() + File.separator;
        SDK_DATA_PATH = INT_STORAGE + "data";
        this.isFirstInstall = isFirstInstall;
        this.mActivity = activity;
    }

    public void deleteTempFolder()
    {
        File tempFolder = new File(DATA_PATH + "temp");
        tempFolder.delete();
        tempFolder.mkdir();
    }

    public void savePackageData(PackageData data, String folderName)
    {
        String pcgDataPath;
        pcgDataPath = RootModel.DATA_PATH + File.separator + folderName;
        if (!new File(pcgDataPath).exists())
        {
            File file = new File(pcgDataPath);
            //FUtils.deleteRecursive(file);
            file.mkdirs();
        }
        else
        {
            File file = new File(pcgDataPath);
            //FUtils.deleteRecursive(file);
            file.mkdirs();
        }
        pcgDataPath = pcgDataPath + File.separator;
        FilesDataList filesDataList = data.getFiles();
        for (int i = 0; i < filesDataList.getFilesCount(); i++)
        {
            Log.d("FILE DATA ARRAY", "ENTERED");
            Log.d("FILE DATA ARRAY", filesDataList.getFileDescription(i));
            byte[] mFileBytes = filesDataList.getFileData(i);
            String mFileDesc = filesDataList.getFileDescription(i);
            if (mFileDesc.contains("faceImage"))
            {
                String faceDataPath = RootModel.DATA_PATH + File.separator + "Images" + File.separator;
                //FUtils.fileWriter(faceDataPath + "ID" + mFileDesc, mFileBytes);
            }

            Path parentPath = Paths.get(mFileDesc).getParent();
            if (parentPath != null)
            {
                File file = new File(pcgDataPath + parentPath);
                file.mkdirs();
            }

            //FUtils.fileWriter(pcgDataPath + mFileDesc, mFileBytes);
        }
    }

    public void saveManualCaptureImage(FilesData data, Context context)
    {
        String imgRootPath = context.getExternalFilesDir("").getAbsolutePath() + File.separator + "manual_capture";
        File file = new File(imgRootPath);
        if (!file.exists())
        {
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String format = simpleDateFormat.format(new Date());
        String imgName = format + ".jpg";
        //FUtils.fileWriter(imgRootPath + File.separator + imgName, data.getFileData());
    }

    public void loadSdkData()
    {
        //FUtils.deleteRecursive(new File(SDK_DATA_PATH));
        //FUtils.copyFileOrDir("data", mActivity, INT_STORAGE);
    }

    public void loadInputXML()
    {
        try
        {
            // find the right context!!!
            AssetManager assetManager = mActivity.getAssets();
            InputStream in = assetManager.open("input.xml");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // Transfer bytes from in to out
            byte[] buff = new byte[1024];
            int i = Integer.MAX_VALUE;
            while ((i = in.read(buff, 0, buff.length)) > 0)
            {
                out.write(buff, 0, i);
            }
            //InputXML.getInstance().setInputXMLBytes(out.toByteArray());
            in.close();
            out.close();
            Log.v(TAG, " Copied inputXML");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadDescriptionXml()
    {
        final String fileName = "description.xml";
        File file = new File(INT_STORAGE + "/" + fileName);
        if (!file.exists())
        {
            //FUtils.copyFileOrDir(fileName, mActivity, INT_STORAGE);
        }
    }

    public void loadLicenses()
    {
        final String licenseSig = "license.sig";
        final String licenseTxt = "license.txt";
        File licenseSigFile = new File(INT_STORAGE + "/" + licenseSig);
        File licenseTxtFile = new File(INT_STORAGE + "/" + licenseTxt);
        if (!licenseSigFile.exists())
        {
            //FUtils.copyFileOrDir(licenseSig, mActivity, INT_STORAGE);
        }
        if (!licenseTxtFile.exists())
        {
            //FUtils.copyFileOrDir(licenseTxt, mActivity, INT_STORAGE);
        }
    }

    ArrayMap<Integer, Integer> getDefaultMap()
    {
        ArrayMap<Integer, Integer> errorList = new ArrayMap<>();
        errorList.put(EError_None, EUserErrorType_None);
        errorList.put(EError_Unknown, EUserErrorType_Retry);
        errorList.put(EError_Unexpected_Stop, EUserErrorType_Retry);
        errorList.put(EError_ThirdPartyLibFailure, EUserErrorType_Retry);
        errorList.put(EError_ThirdPartyLibLicenceExpired, EUserErrorType_Retry);
        errorList.put(EError_CameraFailure, EUserErrorType_Retry);
        errorList.put(EError_CameraAccessForbidden, EUserErrorType_Retry);
        errorList.put(EError_NoInternetConnection, EUserErrorType_Retry);

        errorList.put(EError_QrCodeGeneric, EUserErrorType_Retry);
        errorList.put(EError_QrCodeCaptureFailed, EUserErrorType_Retry);
        errorList.put(EError_QrCodeBadData, EUserErrorType_Retry);

        errorList.put(EError_IdGeneric, EUserErrorType_Retry);
        errorList.put(EError_IdTimeoutExpired, EUserErrorType_Retry);
        errorList.put(EError_IdNoData, EUserErrorType_RetrySave);
        errorList.put(EError_IdNotInList, EUserErrorType_Retry);
        errorList.put(EError_IdExpired, EUserErrorType_Retry);
        errorList.put(EError_IdTypeNotValid, EUserErrorType_RetrySave);
        errorList.put(EError_IdPageMissing, EUserErrorType_Retry);
        errorList.put(EError_IdFinalPackageDataForUpload, EUserErrorType_Retry);
        errorList.put(EError_IdMatchingFailed, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFields, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldBirthDay, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldCompositCheckDigit, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldExpiryDay, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldDocumentNumber, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldGender, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldFirstName, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldLastName, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldCountry, EUserErrorType_RetrySave);
        errorList.put(EError_IdBadMrzFieldNationality, EUserErrorType_RetrySave);

        errorList.put(EError_DocGeneric, EUserErrorType_Retry);
        errorList.put(EError_DocTimeoutExpired, EUserErrorType_Retry);

        errorList.put(EError_SignDocGeneric, EUserErrorType_Retry);

        errorList.put(EError_FaceCaptureGeneric, EUserErrorType_RetrySave);
        errorList.put(EError_FaceCaptureIncomplete, EUserErrorType_RetrySave);
        errorList.put(EError_FaceNotRecognised, EUserErrorType_RetrySave);
        errorList.put(EError_FaceNotVerified, EUserErrorType_RetrySave);
        errorList.put(EError_FaceStatic, EUserErrorType_RetrySave);
        errorList.put(EError_FaceLivenessFailed, EUserErrorType_RetrySave);
        errorList.put(EError_FaceFrameSimilarityFailed, EUserErrorType_RetrySave);

        errorList.put(EError_DataEncryptionGeneric, EUserErrorType_Retry);

        errorList.put(EError_DataDecryptionGeneric, EUserErrorType_Retry);
        errorList.put(EError_DataDecryptionFaceNoMatch, EUserErrorType_Retry);

        errorList.put(EError_FunctionNotImplemented, EUserErrorType_Retry);

        return errorList;
    }
}
