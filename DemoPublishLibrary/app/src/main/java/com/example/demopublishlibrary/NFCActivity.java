package com.example.demopublishlibrary;

import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_MD_NFCErrorID;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_MRZ_DateOfBirth;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_MRZ_DocumentNumber;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_MRZ_ExpiryDate;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_DateOfBirth;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_DateOfExpiry;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_DocumentCode;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_DocumentNumber;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_Gender;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_IssuingState;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_Nationality;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_PrimaryIdentifier;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EContentField_NFC_DocumentVerification_SecondaryIdentifier;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_FunctionNotIncluded;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NFC_MutualAuthenticationFailedNotSatisfied;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NFC_NotConnected;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NFC_ReadFailed;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NFC_TagWasLost;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NFC_Timeout;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EError_NFC_UnexpectedException;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_AccessControl;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ActiveAuthentication;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ChipAuthentication;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ChipClonedDetectionEndSuccess;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ChipClonedDetectionStart;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ConnectingToServer;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_DataReadingEndSuccess;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_DataReadingStart;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_PassiveAuthentication;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadAtrInfo;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg1;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg11;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg12;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg14;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg15;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg2;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadDg7;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadSod;
import static com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums.EStatus_NFC_ReadingStopped;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.publishlibrary.pxl.nfc.Nfc;
import com.google.android.material.textfield.TextInputEditText;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.CallbackListenerPXLBeam;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.ExtractDataList;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.FilesData;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.NFCConfig;
import com.example.publishlibrary.pxl.PXLBeamOnBoard.PackageData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class NFCActivity extends AppCompatActivity implements CallbackListenerPXLBeam
{
    private static final String TAG = NFCActivity.class.getName();
    private static final String KEY_PASSPORT_NUMBER = "passportNumber";
    private static final String KEY_EXPIRATION_DATE = "expirationDate";
    private static final String KEY_BIRTH_DATE = "birthDate";
    private static final String SHARED_PREFIX = "NFCPref";

    public static final String URL = "wss://kinegramdocval.lkis.de/ws1/validate";

    private SharedPreferences preferences;
    private RootModel mRootModel;
    private ListView NFCResultList;
    private ImageView NFCImage;
    private EditText passportNumberView;
    private EditText expirationDateView;
    private EditText birthDateView;
    private TextView infoText;
    private Button startNFCButton;
    private View mainLayout;
    private View loadingLayout;
    private boolean hasData;
    private PxlListener pxlListener;
    private Switch swSaveAdditionalSOD, swSaveActiveAuth, swSaveNFCOnMobile;
    public static PackageData data;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_activity);

        swSaveAdditionalSOD = (Switch)findViewById(R.id.switchSodFiles);
        swSaveActiveAuth = (Switch)findViewById(R.id.switchActiveAuth);
        swSaveNFCOnMobile = (Switch)findViewById(R.id.switchSaveNFCData);

        mRootModel = new RootModel(this, false);
        pxlListener = PxlListener.getInstance();
        pxlListener.setActivity(this);
        Nfc nfcWrapper = new Nfc();
        pxlListener.setPxlBeamWrapper(nfcWrapper);
        //pxlListener.getPxlBeamWrapper().setActivity(this);
        preferences = this.getSharedPreferences(SHARED_PREFIX, Context.MODE_PRIVATE);
        String dateOfBirth = "1980-02-07";
        String dateOfExpiry = "2016-04-06";
        String passportNumber = "SP0001298";
        if (dateOfBirth != null)
        {
            preferences.edit().putString(KEY_BIRTH_DATE, dateOfBirth).apply();
        }
        if (dateOfExpiry != null)
        {
            preferences.edit().putString(KEY_EXPIRATION_DATE, dateOfExpiry).apply();
        }
        if (passportNumber != null)
        {
            preferences.edit().putString(KEY_PASSPORT_NUMBER, passportNumber).apply();
        }

        passportNumberView = (TextInputEditText)findViewById(R.id.input_passport_number);
        expirationDateView = (TextInputEditText)findViewById(R.id.input_expiration_date);
        birthDateView = (TextInputEditText)findViewById(R.id.input_date_of_birth);
        infoText = (TextView)findViewById(R.id.info_text);
        startNFCButton = (Button)findViewById(R.id.start_nfc_button);

        mainLayout = findViewById(R.id.main_layout);
        loadingLayout = findViewById(R.id.loading_layout);
        NFCResultList = (ListView)findViewById(R.id.NFCResultList);
        NFCImage = (ImageView)findViewById(R.id.nfc_image);

        passportNumberView.setText(preferences.getString(KEY_PASSPORT_NUMBER, null));
        expirationDateView.setText(preferences.getString(KEY_EXPIRATION_DATE, null));
        birthDateView.setText(preferences.getString(KEY_BIRTH_DATE, null));

        passportNumberView.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // Generated method
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // Generated method
            }

            @Override public void afterTextChanged(Editable s)
            {
                preferences.edit().putString(KEY_PASSPORT_NUMBER, s.toString()).apply();
                data.getData().setValue(s.toString(), EContentField_MRZ_DocumentNumber);
            }
        });

        expirationDateView.setOnClickListener(view -> {
            Calendar c = loadDate(expirationDateView);
            final DatePickerDialog dialog =
                new DatePickerDialog(NFCActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth)
                    {
                        saveDate(expirationDateView, year, monthOfYear, dayOfMonth, KEY_EXPIRATION_DATE,
                                 EContentField_MRZ_ExpiryDate);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        birthDateView.setOnClickListener(view -> {
            Calendar c = loadDate(birthDateView);
            final DatePickerDialog dialog =
                new DatePickerDialog(NFCActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth)
                    {
                        saveDate(birthDateView, year, monthOfYear, dayOfMonth, KEY_BIRTH_DATE,
                                 EContentField_MRZ_DateOfBirth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        startNFCButton.setOnClickListener(view -> {
            infoText.setText(R.string.info_scan_passport2);
            NFCConfig configScanning = new NFCConfig();
            Log.e("TAG", "swSaveAdditionalSOD ->  " + swSaveAdditionalSOD.isChecked() + " | swSaveActiveAuth -> "
                             + swSaveActiveAuth.isChecked() + " | swSaveNFCOnMobile -> "
                             + swSaveNFCOnMobile.isChecked());
            //      ZKB

            NFCConfig nfcConfig = new NFCConfig();
            data = new PackageData();
            nfcConfig.setSaveAdditionalFiles(swSaveAdditionalSOD.isChecked());
            nfcConfig.setSaveActiveAuth(swSaveActiveAuth.isChecked());
            nfcConfig.setSaveNFCOnMobile(swSaveNFCOnMobile.isChecked());

            pxlListener.getPxlBeamWrapper().startNfc(data, "", "");
            hideKeyboard(NFCActivity.this);
        });

        pxlListener.getPxlBeamWrapper().initNfcCardReader(this, URL);
    }

    @Override protected void onResume()
    {
        super.onResume();
        pxlListener.getPxlBeamWrapper().onResumeNfc();
    }

    @Override protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        pxlListener.getPxlBeamWrapper().onNewIntentNfc(intent);
    }

    @Override protected void onPause()
    {
        super.onPause();
        pxlListener.getPxlBeamWrapper().onPauseNfc();
    }

    @Override public void errorNotification(int status)
    {
        Log.e("STATUSERROR", "Status: " + status);
        switch (status)
        {
        case EError_NFC_TagWasLost:
            toast("Tag was lost");
            break;
        case EError_NFC_NotConnected:
            toast("Not connected");
            break;
        case EError_NFC_MutualAuthenticationFailedNotSatisfied:
            toast("Mutual authentication failed : Conditions not satisfied");
            break;
        case EError_NFC_Timeout:
            toast("NFC reading timeout");
            break;
        case EError_NFC_ReadFailed:
            toast("Read failed");
            break;
        case EError_NFC_UnexpectedException:
            toast("Unexpected exception");
            break;
        case EError_FunctionNotIncluded:
            infoText.setText(R.string.info_scan_passport1);
            toast("FEATURE IS NOT IMPLEMENTED");
            return;
        default:
            toast(".");
        }
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        loadingLayout.setVisibility(View.GONE);
    }

    @Override public void statusNotification(final int status)
    {
        runOnUiThread(() -> {
            switch (status)
            {
            case EStatus_NFC_DataReadingStart:
                toast("Data Reading Start");
                break;
            case EStatus_NFC_DataReadingEndSuccess:
                loadingLayout.setVisibility(View.GONE);
                toast("Data Reading End Success");
                break;
            case EStatus_NFC_ReadingStopped:
                toast("Data Reading Stopped");
                break;
            case EStatus_NFC_ConnectingToServer:
                toast("EStatus_NFC_ConnectingToServer");
                break;
            case EStatus_NFC_ReadAtrInfo:
                toast("EStatus_NFC_ReadAtrInfo");
                break;
            case EStatus_NFC_AccessControl:
                toast("EStatus_NFC_AccessControl");
                break;
            case EStatus_NFC_ReadSod:
                toast("EStatus_NFC_ReadSod");
                break;
            case EStatus_NFC_ReadDg14:
                toast("EStatus_NFC_ReadDg14");
                break;
            case EStatus_NFC_ChipAuthentication:
                toast("EStatus_NFC_ChipAuthentication");
                break;
            case EStatus_NFC_ReadDg15:
                toast("EStatus_NFC_ReadDg15");
                break;
            case EStatus_NFC_ActiveAuthentication:
                toast("EStatus_NFC_ActiveAuthentication");
                break;
            case EStatus_NFC_ReadDg1:
                toast("EStatus_NFC_ReadDg1");
                break;
            case EStatus_NFC_ReadDg2:
                toast("EStatus_NFC_ReadDg2");
                break;
            case EStatus_NFC_ReadDg7:
                toast("EStatus_NFC_ReadDg7");
                break;
            case EStatus_NFC_ReadDg11:
                toast("EStatus_NFC_ReadDg11");
                break;
            case EStatus_NFC_ReadDg12:
                toast("EStatus_NFC_ReadDg12");
                break;
            case EStatus_NFC_PassiveAuthentication:
                toast("EStatus_NFC_PassiveAuthentication");
                break;
            case EStatus_NFC_ChipClonedDetectionStart:
                toast("EStatus_NFC_ChipClonedDetectionStart");
                break;
            case EStatus_NFC_ChipClonedDetectionEndSuccess:
                toast("EStatus_NFC_ChipClonedDetectionEndSuccess");
                break;
            }
        });
    }

    @Override public void barcodeRecognitionCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void idRecognitionCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void NFCRecognitionCompleted(PackageData packageData)
    {
        String nfcError = packageData.getData().getValue(EContentField_MD_NFCErrorID);

        mRootModel.savePackageData(packageData, "NFCRecognitionCompleted_data");
        for (int i = 0; i < packageData.getFiles().getFilesCount(); i++)
        {
            String fileName = packageData.getFiles().getFileDescription(i);
            if (fileName.startsWith("NFCAvatar"))
            {
                byte[] photoImageBytes = packageData.getFiles().getFileData(i);
                final Bitmap nfcPhoto = BitmapFactory.decodeByteArray(photoImageBytes, 0, photoImageBytes.length);
                runOnUiThread(() -> {
                    NFCImage.setVisibility(View.VISIBLE);
                    NFCImage.setImageBitmap(nfcPhoto);
                });
            }
        }
        runOnUiThread(() -> {
            mainLayout.setVisibility(View.GONE);
            loadingLayout.setVisibility(View.GONE);
            NFCResultData nfcResults = new NFCResultData();

            getExtractedData(data, nfcResults);

            if (nfcError != null && !nfcError.isEmpty())
            {
                toast("NFC ERROR: " + nfcError);
            }

            Log.e("PXL_DEBUG Result", String.valueOf(data.getData().getCount()));
            if (hasData)
            {
                NFCResultList.setVisibility(View.VISIBLE);
                ArrayAdapterItem_NFC nfcAdapter =
                    new ArrayAdapterItem_NFC(NFCActivity.this, R.layout.list_item, nfcResults);
                NFCResultList.setAdapter(nfcAdapter);
            }
            else
            {
                NFCResultList.setVisibility(View.GONE);
            }
        });
    }

    public void getExtractedData(PackageData data, NFCResultData nfcResult)
    {
        HashMap<String, String> nfcData = new HashMap<>();
        ExtractDataList extractData = data.getData();

        if (extractData != null)
        {
            if (extractData.getCount() > 0)
            {
                nfcData.put("First name",
                            extractData.getValue(EContentField_NFC_DocumentVerification_SecondaryIdentifier));
                nfcData.put("Last name",
                            extractData.getValue(EContentField_NFC_DocumentVerification_PrimaryIdentifier));
                nfcData.put("Date Of Birth", extractData.getValue(EContentField_NFC_DocumentVerification_DateOfBirth));
                nfcData.put("Gender", extractData.getValue(EContentField_NFC_DocumentVerification_Gender));
                nfcData.put("Nationality", extractData.getValue(EContentField_NFC_DocumentVerification_Nationality));
                nfcData.put("Expiration Date",
                            extractData.getValue(EContentField_NFC_DocumentVerification_DateOfExpiry));
                nfcData.put("Document Number",
                            extractData.getValue(EContentField_NFC_DocumentVerification_DocumentNumber));
                nfcData.put("Document Type", extractData.getValue(EContentField_NFC_DocumentVerification_DocumentCode));
                nfcData.put("Issuing Country",
                            extractData.getValue(EContentField_NFC_DocumentVerification_IssuingState));
                nfcResult.setData(nfcData);
                hasData = true;
            }
            else
            {
                hasData = false;
            }
        }
        else
        {
            hasData = false;
        }
    }

    @Override public void idPackageReady(PackageData packageData)
    {
        // Generated method
    }

    @Override public void pdfReady(PackageData packageData)
    {
        // Generated method
    }

    @Override public void faceCaptureCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void documentRecordingCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void faceVerificationCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void faceVerificationInitialized(boolean b)
    {
        // Generated method
    }

    @Override public void encryptDataCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void decryptDataCompleted(PackageData packageData)
    {
        // Generated method
    }

    @Override public void documentCaptured(PackageData packageData)
    {
        // Generated method
    }

    @Override public void documentPackageReady(PackageData packageData)
    {
        // Generated method
    }

    @Override public void dataForUploadReady(PackageData packageData)
    {
        // Generated method
    }

    @Override public void manualCaptureCompleted(FilesData data)
    {
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

    private void hideKeyboard(Activity activity)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        // Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        // If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null)
        {
            view = new View(activity);
        }
        if (imm != null)
        {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Calendar loadDate(EditText editText)
    {
        Calendar calendar = Calendar.getInstance();
        if (!editText.getText().toString().isEmpty())
        {
            try
            {
                calendar.setTimeInMillis(
                    new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(editText.getText().toString()).getTime());
            }
            catch (ParseException e)
            {
                Log.w(MainActivity.class.getSimpleName(), e);
            }
        }
        return calendar;
    }

    private void saveDate(EditText editText, int year, int monthOfYear, int dayOfMonth, String preferenceKey,
                          int packageDataKey)
    {
        String value = String.format(Locale.US, "%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
        preferences.edit().putString(preferenceKey, value).apply();
        editText.setText(value);
        data.getData().setValue(value, packageDataKey);
    }

    private void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
