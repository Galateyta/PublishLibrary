package com.example.publishlibrary.pxl.PXLBeamOnBoard;

public final class NFCConstants
{
    private NFCConstants()
    {
    }

    public static final String ISO_DEP = "android.nfc.tech.IsoDep";
    public static final String SIMPLE_DATE_FORMAT = "yyMMdd";
    public static final String NFC_AVATAR = "NFCAvatar.jpg";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String RESULT_XML = "result.xml";
    public static final String RESULT_JSON = "result.json";
    public static final String RESULT = "result.xml";

    // EmrtdConnector
    public static final String CLIENT_ID = "FwpSx7LDS_Vd8YCT";

    public static final String NFC_URL = "wss://kinegramdocval.lkis.de/ws1/validate";
    public static final String CHIP_AUTH_RESULT = "chipAuthenticationResult";

    public static final String ACTIVE_AUTH_RESULT = "activeAuthenticationResult";

    // Reading exception message
    public static final String MUTUAL_AUTHENTICATION_FAILD =
        "Mutual authentication failed: expected length: 40 + 2, actual length: 2 ";
    public static final String MAF_SECURITY_STATUS_NOT_SATISFIED = "(SW = 0x6982: SECURITY STATUS NOT SATISFIED)";
    public static final String MAF_CONDITIONS_NOT_SATISFIED = "(SW = 0x6985: CONDITIONS NOT SATISFIED)";
    public static final String READ_FAILED_102 = "Read binary failed on file 102";
    public static final String UNEXPECTED_EXCEPTION = "Unexpected exception";
    public static final String MAF_UNKNOWN = "(SW = 0x6F02: Unknown)";
    public static final String NOT_CONNECTED = "Not connected";
    public static final String TAG_WAS_LOST = "Tag was lost.";

    // Log message
    public static final String DATA_READING_END_SUCCESS = "* Data Reading End : Success *";
    public static final String DATA_READING_END_FAIL = "* Data Reading End :  Fail *";
    public static final String DATA_READING_START = "* Data Reading Start *";
    public static final String READING_EXCEPTION = "EXCEPTION : ";
    public static final String ON_TICK = "Sec : ";

    // CountDown timer
    public static final int TIMEOUT = 60000;
    public static final int INTERVAL = 1000;

    // for ImageUtil class
    public static final String IMAGE_JPEG_2000 = "image/jpeg2000";
    public static final String IMAGE_X_WSQ = "image/x-wsq";
    public static final String IMAGE_JP2 = "image/jp2";
    public static final String TEMP_PPM = "/temp.ppm";
    public static final String TEMP_JP2 = "temp.jp2";
    public static final String DEBUG = "debug";
    public static final String RATE = "rate";

    // OVD
    public static final String NFC_CERT = "certificate.der";
    public static final String EF = ".ef";
    public static final String SOD = "sod.ef";
    public static final String DG1 = "dg1.ef";
    public static final String DG2 = "dg2.ef";
    public static final String DG3 = "dg3.ef";
    public static final String DG4 = "dg4.ef";
    public static final String DG5 = "dg5.ef";
    public static final String DG6 = "dg6.ef";
    public static final String DG7 = "dg7.ef";
    public static final String DG8 = "dg8.ef";
    public static final String DG9 = "dg9.ef";
    public static final String DG10 = "dg10.ef";
    public static final String DG11 = "dg11.ef";
    public static final String DG12 = "dg12.ef";
    public static final String DG13 = "dg13.ef";
    public static final String DG14 = "dg14.ef";
    public static final String DG15 = "dg15.ef";
    public static final String DG16 = "dg16.ef";
}
