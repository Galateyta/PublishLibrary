package com.example.publishlibrary.pxl.nfc;

import android.app.Activity;
import android.content.Intent;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.*;
import org.json.JSONObject;

public class Nfc implements NfcListener
{
    private static final String TAG = "Nfc Wrapper";
    public CallbackListenerPXLBeam mCallbackListenerPXLBeam;
    private NFCReader mNFCReader = null;

    public int initialize(CallbackListenerPXLBeam listener)
    {
        mCallbackListenerPXLBeam = listener;
        return 0;
    }

    public void initNfcCardReader(final Activity activity, String nfcUrl)
    {
        if (mNFCReader == null)
        {
            mNFCReader = new NFCReader();
        }

        mNFCReader.initCardReader(activity, nfcUrl);
    }

    public void startNfc(final PackageData packageData, String processId, String transactionCode)
    {
        startNfc(packageData, null, processId, transactionCode);
    }

    public void startNfc(final PackageData packageData, NFCConfig nfcConfig, String processId, String transactionCode)
    {
        mNFCReader.setIds(processId, transactionCode);
        packageData.setNFCErrorCode(Enums.EError_None);
        mNFCReader.startNfc(packageData, mCallbackListenerPXLBeam, nfcConfig, this);
    }

    public void onNewIntentNfc(final Intent intent)
    {
        mNFCReader.onNewIntentRun(intent);
    }

    public void onPauseNfc()
    {
        mNFCReader.disableForegroundDispatch();
    }

    public void onResumeNfc()
    {
        mNFCReader.enableForegroundDispatch();
    }

    public void stopNfcReading()
    {
        mNFCReader.stopNFC();
    }

    public void nfcErrorNotification(PackageData data, int error, boolean isInterruptScanning, String processId)
    {
        data.setNFCErrorCode(error);
        String errorName = NFCReader.getErrorName(error);

        if (isInterruptScanning)
        {
            mCallbackListenerPXLBeam.NFCRecognitionCompleted(data);
        }
    }

    public void nfcCompleted(PackageData data, JSONObject nfcJson, String processId, boolean updateResult)
    {
        mCallbackListenerPXLBeam.NFCRecognitionCompleted(data);
    }
}
