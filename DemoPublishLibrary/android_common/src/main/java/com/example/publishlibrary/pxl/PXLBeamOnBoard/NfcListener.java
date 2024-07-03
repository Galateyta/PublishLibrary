package com.example.publishlibrary.pxl.PXLBeamOnBoard;

import org.json.JSONObject;

public interface NfcListener
{
    public void nfcErrorNotification(PackageData data, int error, boolean isInterruptScanning, String processId);
    public void nfcCompleted(PackageData data, JSONObject nfcJson, String processId, boolean updateResult);
}
