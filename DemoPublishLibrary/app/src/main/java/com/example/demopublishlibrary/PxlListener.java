package com.example.demopublishlibrary;

import android.app.Activity;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.*;
import com.example.publishlibrary.pxl.nfc.*;
import com.example.demopublishlibrary.NFCActivity;
import java.lang.ref.WeakReference;

public class PxlListener implements CallbackListenerPXLBeam
{
    private static PxlListener pxlListener = new PxlListener();
    private WeakReference<Activity> nfcActivity;
    private Nfc pxlBeamWrapper;

    private PxlListener()
    {
    }

    public void setActivity(Activity activity)
    {
        nfcActivity = new WeakReference<>(activity);
    }

    public void setPxlBeamWrapper(Nfc pxlBeamWrapper)
    {
        this.pxlBeamWrapper = pxlBeamWrapper;
    }

    public static PxlListener getInstance()
    {
        return pxlListener;
    }

    public Nfc getPxlBeamWrapper()
    {
        return pxlBeamWrapper;
    }

    private Activity getActivity()
    {
        return nfcActivity == null || nfcActivity.get() == null ? null : nfcActivity.get();
    }

    @Override public void errorNotification(final int i)
    {
        final Activity mActivity = getActivity();
//        if (mActivity instanceof RootActivity)
//        {
//            ((RootActivity)mActivity).errorNotification(i);
//        }
//        else if (mActivity instanceof NFCActivity)
//        {
//            ((NFCActivity)mActivity).errorNotification(i);
//        }
    }

    @Override public void faceVerificationInitialized(boolean b)
    {
    }

    @Override
    public void documentRecordingCompleted(PackageData packageData) {

    }

    @Override public void statusNotification(final int i)
    {
        final Activity mActivity = getActivity();
//        if (mActivity != null)
//        {
//            if (mActivity instanceof RootActivity)
//            {
//                ((RootActivity)mActivity).statusNotification(i);
//            }
//            else if (mActivity instanceof NFCActivity)
//            {
//                ((NFCActivity)mActivity).statusNotification(i);
//            }
//        }
    }

    @Override public void NFCRecognitionCompleted(PackageData packageData)
    {
        final Activity mActivity = getActivity();
        if (mActivity instanceof NFCActivity)
        {
            ((NFCActivity)mActivity).NFCRecognitionCompleted(packageData);
        }
    }

    @Override public void idPackageReady(final PackageData packageData)
    {
        final Activity mActivity = getActivity();
//        if (mActivity instanceof RootActivity)
//        {
//            ((RootActivity)mActivity).idPackageReady(packageData);
//        }
    }

    @Override
    public void pdfReady(PackageData packageData) {

    }

    @Override
    public void faceCaptureCompleted(PackageData packageData) {

    }


    @Override public void faceVerificationCompleted(final PackageData packageData)
    {
        // Override methods
    }

    @Override public void encryptDataCompleted(final PackageData packageData)
    {
        // Override methods
    }

    @Override public void decryptDataCompleted(final PackageData packageData)
    {
        // Override methods
    }

    @Override
    public void documentCaptured(PackageData packageData) {

    }

    @Override
    public void documentPackageReady(PackageData packageData) {

    }

    @Override public void dataForUploadReady(final PackageData packageData)
    {
        // Override methods
    }

    @Override
    public void manualCaptureCompleted(FilesData filesData) {

    }

    @Override public void statusNotificationSf(float v, boolean b)
    {
    }

    @Override public void documentTypeNotification(int i)
    {
    }

    @Override public void documentSideNotification(int i)
    {
    }

    @Override
    public void barcodeRecognitionCompleted(PackageData packageData) {

    }

    @Override
    public void idRecognitionCompleted(PackageData packageData) {

    }
}
