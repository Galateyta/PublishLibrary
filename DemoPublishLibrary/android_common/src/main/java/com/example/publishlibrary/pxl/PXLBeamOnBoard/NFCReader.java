package com.example.publishlibrary.pxl.PXLBeamOnBoard;

import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.*;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import com.kurzdigital.android.emrtdconnector.ClosedListener;
import com.kurzdigital.android.emrtdconnector.EmrtdConnector;
import com.kurzdigital.android.emrtdconnector.EmrtdPassport;
import com.kurzdigital.android.emrtdconnector.StatusListener;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NFCReader
{
    private static final String TAG = NFCReader.class.getName();
    private PackageData mPackageData;
    private CountDownTimer mTimer;
    private boolean isNfcStarted = false;
    private CallbackListenerPXLBeam mCallbackListenerPXLBeam;
    private String processId;
    private String FSTID;
    private String transactionCode;
    private Tag mTag;

    private NfcAdapter nfcAdapter;
    private PendingIntent nfcPendingIntent;
    private Activity mActivity;
    private EmrtdConnector emrtdConnector;
    private NfcListener mNfcListener;

    private NFCConfig nfcConfig;

    public NFCReader()
    {
    }

    public void initCardReader(Activity activity, String url)
    {
        this.mActivity = activity;
        this.nfcConfig = new NFCConfig();

        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        try
        {
            emrtdConnector = new EmrtdConnector(NFCConstants.CLIENT_ID, url, this::closedListener, this::statusListener,
                                                this::emrtdPassportListener);
        }
        catch (URISyntaxException e)
        {
            String msg = "Failed to create EmrtdConnector";
            Log.e(TAG, msg);
        }

        initNfcAdapter();
    }

    public void setIds(String processId, String transactionCode)
    {
        this.processId = processId;
        this.transactionCode = transactionCode;
    }

    private void initNfcAdapter()
    {
        nfcAdapter = NfcAdapter.getDefaultAdapter(mActivity);
        if (nfcAdapter != null)
        {
            Intent intent = new Intent(mActivity.getApplicationContext(), mActivity.getClass());
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            int flags;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            {
                flags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
            }
            else
            {
                flags = PendingIntent.FLAG_UPDATE_CURRENT;
            }

            nfcPendingIntent = PendingIntent.getActivity(mActivity, 50, intent, flags);
        }
        else
        {
            nfcPendingIntent = null;
        }
    }

    public void enableForegroundDispatch()
    {
        if (nfcAdapter == null || nfcPendingIntent == null)
        {
            return;
        }

        if (!nfcAdapter.isEnabled())
        {
            mActivity.startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        }
        else
        {
            String[][] filter = new String[][] { new String[] { NFCConstants.ISO_DEP } };
            nfcAdapter.enableForegroundDispatch(mActivity, nfcPendingIntent, null, filter);
        }
    }

    public void onNewIntentRun(Intent intent)
    {
        if (!NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())
            || intent.getParcelableExtra(NfcAdapter.EXTRA_TAG) == null
            || (mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)) == null
            || !Arrays.asList(mTag.getTechList()).contains(NFCConstants.ISO_DEP))
        {
            return;
        }

        if (isNfcStarted)
        {
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_DataReadingStart);
            startNFCReading(mTag);
        }
    }

    public static String convertDate(String input, String returnType, String parseType)
    {
        if (input == null)
        {
            return null;
        }
        try
        {
            return new SimpleDateFormat(returnType, Locale.US)
                .format(new SimpleDateFormat(parseType, Locale.US).parse(input));
        }
        catch (ParseException e)
        {
            Log.w(TAG, e.getMessage());
            return null;
        }
    }

    public void stopNFC()
    {
        isNfcStarted = false;
        if (mTimer != null)
        {
            mTimer.cancel();
        }
        mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadingStopped);
    }

    private void startNFCReading(final Tag tag)
    {
        final String passportNumber = mPackageData.getData().getValue(EContentField_MRZ_DocumentNumber);
        final String expirationDate = convertDate(mPackageData.getData().getValue(EContentField_MRZ_ExpiryDate),
                                                  NFCConstants.SIMPLE_DATE_FORMAT, NFCConstants.DATE_FORMAT);
        final String birthDate = convertDate(mPackageData.getData().getValue(EContentField_MRZ_DateOfBirth),
                                             NFCConstants.SIMPLE_DATE_FORMAT, NFCConstants.DATE_FORMAT);

        if ((passportNumber == null || passportNumber.isEmpty()) || (expirationDate == null || expirationDate.isEmpty())
            || (birthDate == null || birthDate.isEmpty()))
        {
            NFCErrorNotification(EError_NFC_MutualAuthenticationFailedNotSatisfied, true);
        }
        else
        {
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ChipClonedDetectionStart);
            emrtdConnector.connect(IsoDep.get(tag), processId, passportNumber, birthDate, expirationDate);
        }
    }

    public void startNfc(final PackageData packageData, final CallbackListenerPXLBeam callbackListenerPXLBeam,
                         NFCConfig config, NfcListener listener)
    {
        if (config == null)
        {
            this.nfcConfig = new NFCConfig();
        }
        else
        {
            this.nfcConfig = config;
        }

        mPackageData = packageData;
        mCallbackListenerPXLBeam = callbackListenerPXLBeam;
        mNfcListener = listener;

        if (nfcAdapter == null)
        {
            mActivity.startActivity(new Intent(Settings.ACTION_SETTINGS));
            return;
        }
        if (!isNfcStarted)
        {
            if (nfcAdapter.isEnabled())
            {
                mTimer = new CountDownTimer(NFCConstants.TIMEOUT, NFCConstants.INTERVAL) {
                    @Override public void onTick(long l)
                    {
                        Log.i(TAG, NFCConstants.ON_TICK + l / NFCConstants.INTERVAL);
                    }

                    @Override public void onFinish()
                    {
                        NFCErrorNotification(EError_NFC_Timeout, true);
                    }
                }.start();
                isNfcStarted = true;
            }
            else
            {
                mActivity.startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
            }
        }
        else
        {
            Log.w(TAG, "NFC reading already launched wait untill it will be completed");
        }
    }

    public void disableForegroundDispatch()
    {
        if (nfcAdapter != null)
        {
            emrtdConnector.cancel();
            nfcAdapter.disableForegroundDispatch(mActivity);
        }
    }

    private void NFCErrorNotification(int error, boolean isInterruptScanning)
    {
        mNfcListener.nfcErrorNotification(mPackageData, error, isInterruptScanning, processId);
        if (isInterruptScanning)
        {
            mTimer.cancel();
            isNfcStarted = false;
        }
    }

    private void closedListener(int code, String reason, boolean remote)
    {
        if (code != 1000)
        {
            Log.e(TAG, reason.replace("_", " "));
            // These are the close reasons to expect,
            // if the Close Code is not 1000
            switch (reason)
            {
            case ClosedListener.TIMEOUT_WHILE_WAITING_FOR_START_MESSAGE:
            case ClosedListener.TIMEOUT_WHILE_WAITING_FOR_RESPONSE:
            case ClosedListener.MAX_SESSION_TIME_EXCEEDED:
                NFCErrorNotification(EError_NFC_Timeout, true);
                break;
            case ClosedListener.UNEXPECTED_MESSAGE:
            case ClosedListener.INVALID_START_MESSAGE:
            case ClosedListener.INVALID_CLIENT_ID:
            case ClosedListener.ACCESS_CONTROL_FAILED:
            case ClosedListener.SERVER_ERROR:
            case ClosedListener.POST_TO_RESULT_SERVER_FAILED:
                NFCErrorNotification(EError_NFC_TechnicalError, true);
                break;
            case ClosedListener.FILE_READ_ERROR:
                NFCErrorNotification(EError_NFC_ReadFailed, true);
                break;
            case ClosedListener.EMRTD_PASSPORT_READER_ERROR:
                NFCErrorNotification(EError_NFC_TagWasLost, true);
                break;
            case ClosedListener.INVALID_ACCESS_KEY_VALUES:
                Log.e(TAG, "Please verify the Access Key (CAN or MRZ Info).");
                NFCErrorNotification(EError_NFC_MutualAuthenticationFailedNotSatisfied, true);
                break;
            case ClosedListener.COMMUNICATION_FAILED:
            case ClosedListener.NFC_CHIP_COMMUNICATION_FAILED:
                Log.e(TAG, "Network communication failed. Please check connection and try again.");
                NFCErrorNotification(EError_NFC_NotConnected, true);
                break;
            case ClosedListener.CANCELLED_BY_USER:
                NFCErrorNotification(EError_NFC_SessionInvalidated, true);
                break;
            default:
                NFCErrorNotification(EError_NFC_UnexpectedException, true);
            }
        }
    }

    private void emrtdPassportListener(EmrtdPassport emrtdPassport, JSONException e)
    {
        if (emrtdPassport != null)
        {
            try
            {
                if (emrtdPassport.activeAuthenticationResult.equals(EmrtdPassport.CheckResult.FAILED)
                    || emrtdPassport.chipAuthenticationResult.equals(EmrtdPassport.CheckResult.FAILED))
                {
                    NFCErrorNotification(EError_NFC_CLONED_CHIP, false);
                }
                FilesDataList filesDataList = mPackageData.getFiles();
                for (int i = 0; i < filesDataList.getFilesCount(); i++)
                {
                    if (filesDataList.getFileDescription(i).contains(NFCConstants.NFC_CERT))
                    {
                        filesDataList.remove(i);
                        i--;
                    }
                    else if (filesDataList.getFileDescription(i).contains(NFCConstants.NFC_AVATAR))
                    {
                        filesDataList.remove(i);
                        i--;
                    }
                }
                mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_DataReadingEndSuccess);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                JSONObject nfcJson = new JSONObject();
                if (nfcConfig.getSaveNFCOnMobile())
                {
                    Log.d(TAG, "Saving NFC document data");
                    if (emrtdPassport.mrzInfo != null)
                    {
                        String secondaryIdentifier = "";
                        for (String str : emrtdPassport.mrzInfo.secondaryIdentifier)
                        {
                            secondaryIdentifier += str;
                        }
                        JSONObject mrzObject = mrzInfoMapping(emrtdPassport);
                        nfcJson.put("mrz_info", mrzObject);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_SecondaryIdentifier,
                            secondaryIdentifier.replaceAll("<", ""));
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_DocumentType,
                                                              emrtdPassport.mrzInfo.documentType);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_DocumentCode,
                                                              emrtdPassport.mrzInfo.documentCode);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_IssuingState,
                                                              emrtdPassport.mrzInfo.issuingState);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_PrimaryIdentifier,
                                                              emrtdPassport.mrzInfo.primaryIdentifier);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Nationality,
                                                              emrtdPassport.mrzInfo.nationality);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_DocumentNumber,
                                                              emrtdPassport.mrzInfo.documentNumber);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_DateOfBirth,
                                                              convertDate(emrtdPassport.mrzInfo.dateOfBirth,
                                                                          NFCConstants.DATE_FORMAT,
                                                                          NFCConstants.SIMPLE_DATE_FORMAT));
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Gender,
                                                              emrtdPassport.mrzInfo.gender);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_DateOfExpiry,
                                                              convertDate(emrtdPassport.mrzInfo.dateOfExpiry,
                                                                          NFCConstants.DATE_FORMAT,
                                                                          NFCConstants.SIMPLE_DATE_FORMAT));
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_OptionalData1,
                                                              emrtdPassport.mrzInfo.optionalData1);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_OptionalData2,
                                                              emrtdPassport.mrzInfo.optionalData2);
                        mPackageData.getData().setExtractData(EContentField_NFC_FSTID,
                                                              Long.toString(timestamp.getTime()));
                        mPackageData.getData().setExtractData(EContentField_NFC_TransactionCode, transactionCode);
                    }

                    if (emrtdPassport.additionalPersonalDetails != null)
                    {
                        JSONObject additionalPersonalDetailsObject = additionalPersonalDetailsMapping(emrtdPassport);
                        nfcJson.put("additional_personal_details", additionalPersonalDetailsObject);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_FullNameOfHolder,
                                                              emrtdPassport.additionalPersonalDetails.fullNameOfHolder);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_OtherNames,
                            String.valueOf(emrtdPassport.additionalPersonalDetails.otherNames));
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_PersonalNumber,
                                                              emrtdPassport.additionalPersonalDetails.personalNumber);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_FullDateOfBirth,
                            convertDate(emrtdPassport.additionalPersonalDetails.fullDateOfBirth,
                                        NFCConstants.DATE_FORMAT, NFCConstants.SIMPLE_DATE_FORMAT));
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_PlaceOfBirth,
                                                              emrtdPassport.additionalPersonalDetails.placeOfBirth);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_PermanentAddress,
                            String.valueOf(emrtdPassport.additionalPersonalDetails.permanentAddress));
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Telephone,
                                                              emrtdPassport.additionalPersonalDetails.telephone);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Profession,
                                                              emrtdPassport.additionalPersonalDetails.profession);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Title,
                                                              emrtdPassport.additionalPersonalDetails.title);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_PersonalSummary,
                                                              emrtdPassport.additionalPersonalDetails.personalSummary);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_ProofOfCitizenshipImage,
                            String.valueOf(emrtdPassport.additionalPersonalDetails.proofOfCitizenshipImage));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_OtherValidTravelDocumentNumbers,
                            String.valueOf(emrtdPassport.additionalPersonalDetails.otherValidTravelDocumentNumbers));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_CustodyInformation,
                            emrtdPassport.additionalPersonalDetails.custodyInformation);
                    }

                    if (emrtdPassport.additionalDocumentDetails != null)
                    {
                        JSONObject additionalDocumentDetailsObject = additionalDocumentDetailsMapping(emrtdPassport);
                        nfcJson.put("additional_document_details", additionalDocumentDetailsObject);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_IssuingAuthority,
                                                              emrtdPassport.additionalDocumentDetails.issuingAuthority);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_DateOfIssue,
                            String.valueOf(emrtdPassport.additionalDocumentDetails.dateOfIssue));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_NamesOfOtherPersons,
                            emrtdPassport.additionalDocumentDetails.namesOfOtherPersons);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_EndorsementsAndObservations,
                            emrtdPassport.additionalDocumentDetails.endorsementsAndObservations);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_TaxOrExitRequirements,
                            emrtdPassport.additionalDocumentDetails.taxOrExitRequirements);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_ImageOfFront,
                            String.valueOf(emrtdPassport.additionalDocumentDetails.imageOfFront));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_ImageOfRear,
                            String.valueOf(emrtdPassport.additionalDocumentDetails.imageOfRear));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_DateAndTimeOfPersonalization,
                            emrtdPassport.additionalDocumentDetails.dateAndTimeOfPersonalization);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_PersonalizationSystemSerialNumber,
                            emrtdPassport.additionalDocumentDetails.personalizationSystemSerialNumber);
                    }

                    if (emrtdPassport.passiveAuthenticationDetails != null)
                    {
                        JSONObject passiveAuthenticationDetailsObject =
                            passiveAuthenticationDetailsMapping(emrtdPassport);
                        nfcJson.put("passive_authentication_details", passiveAuthenticationDetailsObject);
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_PassiveAuthentication,
                            String.valueOf(emrtdPassport.passiveAuthentication));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_SodSignatureValid,
                            String.valueOf(emrtdPassport.passiveAuthenticationDetails.sodSignatureValid));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_DocumentCertificateValid,
                            String.valueOf(emrtdPassport.passiveAuthenticationDetails.documentCertificateValid));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_DataGroupsChecked,
                            Arrays.toString(emrtdPassport.passiveAuthenticationDetails.dataGroupsChecked));
                        if (emrtdPassport.passiveAuthenticationDetails.error != "null")
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Error,
                                                                  emrtdPassport.passiveAuthenticationDetails.error);
                        }

                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_AllHashesValid,
                            String.valueOf(emrtdPassport.passiveAuthenticationDetails.allHashesValid));
                        mPackageData.getData().setExtractData(
                            EContentField_NFC_DocumentVerification_DataGroupsWithValidHash,
                            Arrays.toString(emrtdPassport.passiveAuthenticationDetails.dataGroupsWithValidHash));
                    }
                    if (emrtdPassport.sodInfo != null && emrtdPassport.sodInfo.hashForDataGroup != null)
                    {
                        JSONObject sodInfoObject = sodInfoMapping(emrtdPassport);
                        nfcJson.put("sod_info", sodInfoObject);
                        mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_HashAlgorithm,
                                                              emrtdPassport.sodInfo.hashAlgorithm);
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(1) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg1,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(1));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(2) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg2,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(2));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(3) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg3,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(3));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(4) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg4,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(4));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(5) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg5,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(5));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(6) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg6,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(6));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(7) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg7,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(7));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(8) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg8,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(8));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(9) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg9,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(9));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(10) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg10,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(10));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(11) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg11,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(11));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(12) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg12,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(12));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(14) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg14,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(14));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(15) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg15,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(15));
                        }
                        if (emrtdPassport.sodInfo.hashForDataGroup.get(16) != null)
                        {
                            mPackageData.getData().setExtractData(EContentField_NFC_DocumentVerification_Dg16,
                                                                  emrtdPassport.sodInfo.hashForDataGroup.get(16));
                        }
                    }
                    nfcJson.put("active_authentication_result", emrtdPassport.activeAuthenticationResult);
                    nfcJson.put("chip_authentication_result", emrtdPassport.chipAuthenticationResult);
                }

                if (nfcConfig.getSaveActiveAuth())
                {
                    Log.d(TAG, "Saving NFC authentication data");
                    mPackageData.getData().setExtractData(EContentField_NFC_ActiveAuthenticationResult,
                                                          emrtdPassport.activeAuthenticationResult.toString());
                    mPackageData.getData().setExtractData(EContentField_NFC_ChipAuthenticationResult,
                                                          emrtdPassport.chipAuthenticationResult.toString());
                    mPackageData.getData().setExtractData(EContentField_NFC_ActiveAuthentication_ProcessId, processId);
                    mPackageData.getData().setExtractData(EContentField_NFC_ActiveAuthentication_FSTID,
                                                          Long.toString(timestamp.getTime()));
                    mPackageData.getData().setExtractData(EContentField_NFC_ActiveAuthentication_TransactionCode,
                                                          transactionCode);
                    mPackageData.getData().setExtractData(EContentField_NFC_ChipAuthentication_ProcessId, processId);
                    mPackageData.getData().setExtractData(EContentField_NFC_ChipAuthentication_FSTID,
                                                          Long.toString(timestamp.getTime()));
                    mPackageData.getData().setExtractData(EContentField_NFC_ChipAuthentication_TransactionCode,
                                                          transactionCode);
                    mPackageData.getData().setExtractData(EContentField_NFC_ProcessId, processId);
                    mPackageData.getData().setExtractData(EContentField_NFC_FSTID, Long.toString(timestamp.getTime()));
                    mPackageData.getData().setExtractData(EContentField_NFC_TransactionCode, transactionCode);
                }

                FilesDataList filteredFilesData = mPackageData.getFiles();

                if (nfcConfig.getSaveNFCOnMobile())
                {
                    String imagePath = "images/id_document_nfc/" + NFCConstants.NFC_AVATAR;
                    filteredFilesData.addFilesData(EFileType_JPG, imagePath, emrtdPassport.facePhoto);
                }

                if (emrtdPassport.filesBinary != null && nfcConfig.getSaveAdditionalFiles())
                {
                    Log.d(TAG, "Saving NFC binary files");
                    for (Map.Entry<String, byte[]> entry : emrtdPassport.filesBinary.entrySet())
                    {
                        if (entry.getKey() != null && entry.getValue() != null)
                        {
                            filteredFilesData.addFilesData(EFileType_EF, entry.getKey() + NFCConstants.EF,
                                                           entry.getValue());
                        }
                    }
                }
                mPackageData.setFiles(filteredFilesData);
                boolean updateResult = nfcConfig.getSaveNFCOnMobile() || nfcConfig.getSaveActiveAuth();
                mNfcListener.nfcCompleted(mPackageData, nfcJson, processId, updateResult);

                isNfcStarted = false;
                mTimer.cancel();
            }
            catch (Exception ex)
            {
                Log.e(TAG, ex.getMessage());
                NFCErrorNotification(EError_NFC_UnexpectedException, true);
            }
        }
    }

    private JSONObject sodInfoMapping(EmrtdPassport emrtdPassport) throws JSONException
    {
        HashMap<String, String> hashForDataGroupMap = new HashMap<String, String>();
        for (int dgNumber = 1; dgNumber <= 16; dgNumber++)
        {
            if (emrtdPassport.sodInfo.hashForDataGroup.get(dgNumber) != null)
            {
                hashForDataGroupMap.put(String.valueOf(dgNumber), emrtdPassport.sodInfo.hashForDataGroup.get(dgNumber));
            }
        }
        JSONObject sodInfoObject = new JSONObject();
        sodInfoObject.put("hashAlgorithm", emrtdPassport.sodInfo.hashAlgorithm);
        JSONObject hashForDataGroupObject = new JSONObject(hashForDataGroupMap);
        sodInfoObject.put("hashForDataGroup", hashForDataGroupObject);
        return sodInfoObject;
    }

    private JSONObject passiveAuthenticationDetailsMapping(EmrtdPassport emrtdPassport) throws JSONException
    {
        JSONObject passiveAuthenticationDetailsObject = new JSONObject();
        passiveAuthenticationDetailsObject.put("passiveAuthentication", emrtdPassport.passiveAuthentication);
        passiveAuthenticationDetailsObject.put("sodSignatureValid",
                                               emrtdPassport.passiveAuthenticationDetails.sodSignatureValid);
        passiveAuthenticationDetailsObject.put("documentCertificateValid",
                                               emrtdPassport.passiveAuthenticationDetails.documentCertificateValid);
        passiveAuthenticationDetailsObject.put("allHashesValid",
                                               emrtdPassport.passiveAuthenticationDetails.allHashesValid);
        JSONArray dataGroupsChecked = new JSONArray(emrtdPassport.passiveAuthenticationDetails.dataGroupsChecked);
        JSONArray dataGroupsWithValidHash =
            new JSONArray(emrtdPassport.passiveAuthenticationDetails.dataGroupsWithValidHash);
        passiveAuthenticationDetailsObject.put("dataGroupsChecked", dataGroupsChecked);
        passiveAuthenticationDetailsObject.put("dataGroupsWithValidHash", dataGroupsWithValidHash);
        passiveAuthenticationDetailsObject.put("error", emrtdPassport.passiveAuthenticationDetails.error);
        return passiveAuthenticationDetailsObject;
    }

    private JSONObject additionalDocumentDetailsMapping(EmrtdPassport emrtdPassport)
    {
        JSONObject additionalDocumentDetailsObject;
        HashMap<String, String> additionalDocumentDetailsMap = new HashMap<String, String>();
        additionalDocumentDetailsMap.put("issuingAuthority", emrtdPassport.additionalDocumentDetails.issuingAuthority);
        additionalDocumentDetailsMap.put("dateOfIssue",
                                         convertDate(emrtdPassport.additionalDocumentDetails.dateOfIssue,
                                                     NFCConstants.DATE_FORMAT, NFCConstants.SIMPLE_DATE_FORMAT));
        additionalDocumentDetailsMap.put("namesOfOtherPersons",
                                         emrtdPassport.additionalDocumentDetails.namesOfOtherPersons);
        additionalDocumentDetailsMap.put("endorsementsAndObservations",
                                         emrtdPassport.additionalDocumentDetails.endorsementsAndObservations);
        additionalDocumentDetailsMap.put("taxOrExitRequirements",
                                         emrtdPassport.additionalDocumentDetails.taxOrExitRequirements);
        additionalDocumentDetailsMap.put("dateAndTimeOfPersonalization",
                                         emrtdPassport.additionalDocumentDetails.dateAndTimeOfPersonalization);
        additionalDocumentDetailsMap.put("personalizationSystemSerialNumber",
                                         emrtdPassport.additionalDocumentDetails.personalizationSystemSerialNumber);
        additionalDocumentDetailsObject = new JSONObject(additionalDocumentDetailsMap);
        return additionalDocumentDetailsObject;
    }

    private JSONObject additionalPersonalDetailsMapping(EmrtdPassport emrtdPassport) throws JSONException
    {
        JSONObject additionalPersonalDetailsObject = new JSONObject();
        ;
        additionalPersonalDetailsObject.put("fullNameOfHolder",
                                            emrtdPassport.additionalPersonalDetails.fullNameOfHolder);
        additionalPersonalDetailsObject.put("personalNumber", emrtdPassport.additionalPersonalDetails.personalNumber);
        additionalPersonalDetailsObject.put("otherNames",
                                            new JSONArray(emrtdPassport.additionalPersonalDetails.otherNames));
        additionalPersonalDetailsObject.put("fullDateOfBirth",
                                            convertDate(emrtdPassport.additionalPersonalDetails.fullDateOfBirth,
                                                        NFCConstants.DATE_FORMAT, NFCConstants.SIMPLE_DATE_FORMAT));
        additionalPersonalDetailsObject.put("placeOfBirth", emrtdPassport.additionalPersonalDetails.placeOfBirth);
        additionalPersonalDetailsObject.put("permanentAddress",
                                            emrtdPassport.additionalPersonalDetails.permanentAddress);
        additionalPersonalDetailsObject.put("telephone", emrtdPassport.additionalPersonalDetails.telephone);
        additionalPersonalDetailsObject.put("profession", emrtdPassport.additionalPersonalDetails.profession);
        additionalPersonalDetailsObject.put("title", emrtdPassport.additionalPersonalDetails.title);
        additionalPersonalDetailsObject.put("personalSummary", emrtdPassport.additionalPersonalDetails.personalSummary);
        additionalPersonalDetailsObject.put("proofOfCitizenshipImage",
                                            emrtdPassport.additionalPersonalDetails.proofOfCitizenshipImage);
        additionalPersonalDetailsObject.put("otherValidTravelDocumentNumbers",
                                            emrtdPassport.additionalPersonalDetails.otherValidTravelDocumentNumbers);
        additionalPersonalDetailsObject.put("custodyInformation",
                                            emrtdPassport.additionalPersonalDetails.custodyInformation);
        return additionalPersonalDetailsObject;
    }

    @NonNull private JSONObject mrzInfoMapping(EmrtdPassport emrtdPassport) throws JSONException
    {
        JSONObject mrzObject = new JSONObject();
        mrzObject.put("documentType", emrtdPassport.mrzInfo.documentType);
        mrzObject.put("documentCode", emrtdPassport.mrzInfo.documentCode);
        mrzObject.put("issuingState", emrtdPassport.mrzInfo.issuingState);
        mrzObject.put("primaryIdentifier", emrtdPassport.mrzInfo.primaryIdentifier);
        mrzObject.put("secondaryIdentifier", new JSONArray(emrtdPassport.mrzInfo.secondaryIdentifier));
        mrzObject.put("nationality", emrtdPassport.mrzInfo.nationality);
        mrzObject.put("documentNumber", emrtdPassport.mrzInfo.documentNumber);
        mrzObject.put("dateOfBirth", convertDate(emrtdPassport.mrzInfo.dateOfBirth, NFCConstants.DATE_FORMAT,
                                                 NFCConstants.SIMPLE_DATE_FORMAT));
        mrzObject.put("gender", emrtdPassport.mrzInfo.gender);
        mrzObject.put("expirationDate", convertDate(emrtdPassport.mrzInfo.dateOfExpiry, NFCConstants.DATE_FORMAT,
                                                    NFCConstants.SIMPLE_DATE_FORMAT));
        mrzObject.put("optionalData1", emrtdPassport.mrzInfo.optionalData1);
        mrzObject.put("optionalData2", emrtdPassport.mrzInfo.optionalData2);
        return mrzObject;
    }

    private void statusListener(String status)
    {
        Log.i(TAG, status.replace("_", " "));

        // These are the status values to expect
        switch (status)
        {
        case StatusListener.CONNECTING_TO_SERVER:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ConnectingToServer);
            break;
        case StatusListener.READ_ATR_INFO:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadAtrInfo);
            break;
        case StatusListener.ACCESS_CONTROL:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_AccessControl);
            break;
        case StatusListener.READ_SOD:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadSod);
            break;
        case StatusListener.READ_DG14:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg14);
            break;
        case StatusListener.CHIP_AUTHENTICATION:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ChipAuthentication);
            break;
        case StatusListener.READ_DG15:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg15);
            break;
        case StatusListener.ACTIVE_AUTHENTICATION:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ActiveAuthentication);
            break;
        case StatusListener.READ_DG1:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg1);
            break;
        case StatusListener.READ_DG2:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg2);
            break;
        case StatusListener.READ_DG7:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg7);
            break;
        case StatusListener.READ_DG11:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg11);
            break;
        case StatusListener.READ_DG12:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ReadDg12);
            break;
        case StatusListener.PASSIVE_AUTHENTICATION:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_PassiveAuthentication);
            break;
        case StatusListener.DONE:
            mCallbackListenerPXLBeam.statusNotification(EStatus_NFC_ChipClonedDetectionEndSuccess);
            break;
        }
    }

    public static String getErrorName(int errorCode)
    {
        switch (errorCode)
        {
        case EError_NFC_TagWasLost:
            return "EError_NFC_TagWasLost";
        case EError_NFC_NotConnected:
            return "EError_NFC_NotConnected";
        case EError_NFC_ReadFailed:
            return "EError_NFC_ReadFailed";
        case EError_NFC_MutualAuthenticationFailedNotSatisfied:
            return "EError_NFC_MutualAuthenticationFailedNotSatisfied";
        case EError_NFC_Timeout:
            return "EError_NFC_Timeout";
        case EError_NFC_CLONED_CHIP:
            return "EError_NFC_CLONED_CHIP";
        case EError_NFC_SessionInvalidated:
            return "EError_NFC_SessionInvalidated";
        case EError_NFC_UnexpectedException:
        default:
            return "EError_NFC_UnexpectedException";
        }
    }
}
