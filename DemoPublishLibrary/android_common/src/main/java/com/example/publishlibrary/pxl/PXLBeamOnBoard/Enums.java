package com.example.publishlibrary.pxl.PXLBeamOnBoard;

public class Enums
{
    /*! \brief  Error codes.
     * There is documentation of these error codes. At the time of writing, they are found at
     * root_folder/docs/error_codes.md
     */
    public static final int EError_NoImages = 999;
    public static final int EError_None = 0;
    public static final int EError_Unknown = 1;         // NOT USED
    public static final int EError_Unexpected_Stop = 2; // NOT USED
    public static final int EError_ThirdPartyLibFailure = 3;
    public static final int EError_ThirdPartyLibLicenceExpired = 4;
    public static final int EError_CameraFailure = 5;
    public static final int EError_CameraAccessForbidden = 6; // NOT USED
    public static final int EError_NoInternetConnection = 7;  // NOT USED
    public static final int EError_FunctionNotIncluded = 8;
    public static final int EError_UnsupportedCameraResolution = 9;    // NOT USED
    public static final int EError_UnsupportedVideoConfiguration = 10; // NOT USED
    public static final int EError_IncorrectInput = 11;                // WEB_SDK
    public static final int EError_ModuleNotInitialised = 12;          // WEB_SDK
    public static final int EError_QrCodeGeneric = 100;
    public static final int EError_QrCodeTimeoutExpired = 101;
    public static final int EError_QrCodeCaptureFailed = 102; // NOT USED
    public static final int EError_QrCodeBadData = 103;
    public static final int EError_IdGeneric = 200;
    public static final int EError_IdTimeoutExpired = 201;
    public static final int EError_IdNoData = 203;
    public static final int EError_IdNotInList = 204;
    public static final int EError_IdExpired = 205;
    public static final int EError_IdFaceImageCaptureFailed = 207;
    public static final int EError_IdTypeNotValid = 208; // type not yet supported
    public static final int EError_IdSecondPageMissing_DEPRECATED = 209;
    public static final int EError_IdPageMissing = 226;
    public static final int EError_IdNeedSecondPageForMrz = 227;      // WEB_SDK
    public static final int EError_IdNeedSecondPageForMatching = 228; // WEB_SDK
    public static final int EError_OptionalPageMissing = 231;
    public static final int EError_OptionalPageNotDetected = 232;
    public static final int EError_IdFinalPackageDataForUpload = 210; // NOT USED
    public static final int EError_IdMatchingFailed = 211;
    public static final int EError_IdBadMrzFields = 212; // unsupported MRZ or MRZ could not be extracted or fake MRZ
    public static final int EError_IdBadMrzFieldBirthDay = 213;           // MRZ DOB not valid
    public static final int EError_IdBadMrzFieldCompositCheckDigit = 214; // MRZ could not be verified
    public static final int EError_IdBadMrzFieldExpiryDay = 215;          // MRZ Expiry Date not valid
    public static final int EError_IdBadMrzFieldDocumentNumber = 216;     // MRZ Document number not valid
    public static final int EError_IdBadMrzFieldGender = 217;             // MRZ Gender not valid
    public static final int EError_IdBadMrzFieldFirstName = 218;          // MRZ First Name not valid
    public static final int EError_IdBadMrzFieldLastName = 219;           // MRZ Last Name not valid
    public static final int EError_IdBadMrzFieldCountry = 220;            // MRZ ID Issuing Country not valid
    public static final int EError_IdBadMrzFieldNationality = 221;        // MRZ Nationality not valid
    public static final int EError_IdIncompleteData = 222;                // MRZ/VIZ extracted data is not complete
    public static final int EError_Document_Upsidedown = 223;             // NOT USED
    public static final int EError_IdMatchingFailedMrz =
        224; // MRZ data doesn't match the detected document template field
    public static final int EError_SignatureCaptureFailed = 225;
    // TODO align codes
    public static final int EError_IdMismatch = 229;
    public static final int EError_IdNotDetected = 230;
    public static final int EError_MRZ_NotDetected = 240;
    public static final int EError_ImageDocumentTooRotated = 301; // NOT USED
    public static final int EError_ImageDocumentTooFar = 302;
    public static final int EError_ImageDocumentTooClose = 303; // TODO add this error once doc classifier is included
    public static final int EError_ImageUnsupportedType = 305;
    public static final int EError_ImageUnsupportedResolution = 306;
    public static final int EError_ImageFromScreen = 350;
    public static final int EError_ImageIsGreyscale = 351;
    public static final int EError_ImageInjection = 352;
    public static final int EError_MrzLayoutValidation = 353;
    public static final int EError_SpecimenDetection = 354;
    public static final int EError_FaceLayoutValidation = 355;
    public static final int EError_ImageAnomaly = 356;
    /* ---- */
    // image quality errors
    public static final int EError_ImageBlured = 304; // Out of order; this is stuck at 304 due to legacy reasons
    public static final int EError_Reflection = 308;
    public static final int EError_SignDocGeneric = 400;
    public static final int EError_PDFDocNotValid = 401;
    public static final int EError_PDFDocNeedInputPassword = 402;
    public static final int EError_PDFDocUnknownEncryption = 403;
    public static final int EError_PDFDocDamagedOrInvalidFormat = 404;
    public static final int EError_PDFDocAccessDeniedOrInvalidFilePath = 405;
    public static final int EError_PDFDocNotFound = 406;
    public static final int EError_PDFDocUnknown = 407;
    public static final int EError_DocGeneric = 450;
    public static final int EError_DocTimeoutExpired = 451;
    public static final int EError_FaceCaptureGeneric = 500;
    public static final int EError_FaceCaptureIncomplete = 502; // User didn't perform all the movements
    public static final int EError_FaceNotRecognised = 503;
    public static final int EError_FaceNotVerified = 504;
    public static final int EError_FaceStatic = 505; // User didn't perform any movements
    public static final int EError_FaceLivenessFailed = 506;
    public static final int EError_FaceCaptureIntegrityCheckFailed = 507;
    public static final int EError_FaceFrameSimilarityFailed = 508;
    public static final int EError_DataEncryptionGeneric = 600; // NOT USED
    public static final int EError_FunctionNotImplemented = 666;
    public static final int EError_DataDecryptionGeneric = 700;     // NOT USED
    public static final int EError_DataDecryptionFaceNoMatch = 701; // NOT USED
    public static final int EError_SecurityFeaturesGeneric = 800;
    public static final int EError_HologramDetectionFailed = 801;
    public static final int EError_LenticularDetectionFailed = 802;
    public static final int EError_ManipulatedDocument = 803;
    public static final int EError_TrackingTimeout = 804;
    public static final int EError_NFC_TagWasLost = 900;
    public static final int EError_NFC_NotConnected = 901;
    public static final int EError_NFC_MutualAuthenticationFailedUnknown_DEPRECATED = 902;
    public static final int EError_NFC_MutualAuthenticationFailedNotSatisfied = 903;
    public static final int EError_NFC_ReadFailed = 904;
    public static final int EError_NFC_UnexpectedException = 905;
    public static final int EError_NFC_Timeout = 906;
    public static final int EError_NFC_NotSupported = 907;
    public static final int EError_NFC_CertificateValidationFailed = 908;
    public static final int EError_NFC_TechnicalError = 909;
    public static final int EError_NFC_CLONED_CHIP = 910;
    public static final int EError_NFC_TEMPERED_DATA_DEPRECATED = 911;
    public static final int EError_NFC_SOD_VS_COM_INSONSITENCY_DEPRECATED = 912;
    public static final int EError_NFC_INVALID_HASHES_DEPRECATED = 913;
    public static final int EError_NFC_SessionInvalidated = 1000;
    public static final int EError_NFC_UserInvalidatedSession_DEPRECATED = 1001;
    public static final int EError_NFC_MoreThanOneTagFound = 1002;
    public static final int EError_NFC_WrongTag = 1003;
    public static final int EError_NFC_PaceOrBacFailed_DEPRECATED = 1004;
    public static final int EError_NFC_LIB_ConnectionLost_DEPRECATED = 1005;
    public static final int EError_NFC_LIB_ConnectingFailed_DEPRECATED = 1006;
    // NFCBackend errors. Possible values for "Error" while performing Passive Authentication
    public static final int EError_NFCBackend_FailedToParseSod = 1007; // The error can be exist in "Errors"
    public static final int EError_NFCBackend_DataGroupsHashesWithUnsupportedAlgorithm = 1008;
    public static final int EError_NFCBackend_SignatureVerifyException = 1009;
    // Possible values for "Errors".
    public static final int EError_NFCBackend_FailedToParseDg1 = 1010;
    public static final int EError_NFCBackend_FailedToParseDg2 = 1011;
    public static final int EError_NFCBackend_FailedToParseDg7 = 1012;
    public static final int EError_NFCBackend_FailedToParseDg11 = 1013;
    public static final int EError_NFCBackend_FailedToParseDg12 = 1014;
    public static final int EError_NFCBackend_FailedToEncodeFacePhotoAsJpeg = 1015;
    public static final int EError_NFCBackend_FailedToEncodeSignaturePhotoAsJpeg = 1016;
    public static final int EError_DocRecordingGeneric = 1100;
    /*  \brief User error types.
     */
    public static final int EUserErrorType_None = 0; /*!< Success. */
    public static final int EUserErrorType_RetrySave =
        1; /*!< User will be displayed with the options to Retry / Save. */
    public static final int EUserErrorType_Retry =
        2; /*!< User will be displayed with the options to Retry (procedure has to be restarted). */
    /*! \brief  Status (notification) codes.
     */
    public static final int EStatus_Init = 0;
    public static final int EStatus_MessageClear = 1;
    public static final int EStatus_QRCodeRecognitionStarted = 100;
    public static final int EStatus_QRCodeExtractionDone = 101;
    public static final int EStatus_QRCodeMoveFurther = 102;
    public static final int EStatus_QRCodeMoveCloser = 103;
    public static final int EStatus_QRCodeRecognitionStopped = 104;
    public static final int EStatus_QRCodeCaptured = 105;
    public static final int EStatus_IdRecognitionStarted = 200;
    public static final int EStatus_IdRecognitionStopped = 201;
    public static final int EStatus_IdFinalDataSet = 204;
    public static final int EStatus_IdRotateLess = 205;
    public static final int EStatus_IdMoveCloser = 206;
    public static final int EStatus_IdMoveFurther = 207;
    public static final int EStatus_IdDetectionDone = 208;
    public static final int EStatus_IdMrzFound = 209;
    public static final int EStatus_IdMrzNotFound = 210;
    public static final int EStatus_IdDocMatched = 211;
    public static final int EStatus_IdDocNotMatched = 212;
    public static final int EStatus_IdNeedSecondPageForMrz = 213;
    public static final int EStatus_IdNeedSecondPageForMatching = 214;
    public static final int EStatus_IdNeedSamePageForMatching = 215;
    public static final int EStatus_WaitingForViz = 216;
    public static final int EStatus_IdDataForUploadSet = 217;
    public static final int EStatus_IdNeedSamePageForMrz = 218;
    public static final int EStatus_IdBadPosition = 219;
    public static final int EStatus_IdHoldStill = 220;
    public static final int EStatus_IdRotate90 = 221;
    public static final int EStatus_IdRotateScreen = 222;
    public static final int EStatus_IdRotateScreenAndDoc = 223;
    public static final int EStatus_IdReflectionDetected = 224;
    public static final int EStatus_IdNotDetected = 225;
    public static final int EStatus_DocCapturingStarted = 300;
    public static final int EStatus_DocCapturingStopped = 301;
    public static final int EStatus_DocCaptured = 302;
    public static final int EStatus_SignatureStarted = 400;
    public static final int EStatus_SignatureAccepted = 401;
    public static final int EStatus_SignatureCleared = 402;
    public static final int EStatus_SignatureStopped = 403;
    public static final int EStatus_SignatureDrawingStarted = 404;
    public static final int EStatus_FaceCapturingStarted = 500;
    public static final int EStatus_FaceCapturingStopped = 501;
    public static final int EStatus_FaceCaptured = 502;
    public static final int EStatus_FaceCaptureMoveRight =
        503; // status used during user movement for liveness detection
    public static final int EStatus_FaceCaptureMoveLeft = 504;
    public static final int EStatus_FaceCaptureTiltRight = 505;
    public static final int EStatus_FaceCaptureTiltLeft = 506;
    public static final int EStatus_FaceCaptureBlink = 507;
    public static final int EStatus_FaceCaptureWait = 508;
    public static final int EStatus_FaceCaptureSmile = 509;
    public static final int EStatus_FaceCaptureLivenessFailed = 510;
    public static final int EStatus_FaceVerificationStarted = 600;
    public static final int EStatus_FaceVerificationStopped = 601;
    public static final int EStatus_FaceVerified = 602;
    public static final int EStatus_FaceVerificationFailed = 603;
    public static final int EStatus_DataEncryptionStarted = 700;
    public static final int EStatus_DataEncryptionStopped = 701;
    public static final int EStatus_DataEncrypted = 702;
    public static final int EStatus_DataDecryptionStarted = 800;
    public static final int EStatus_DataDecryptionStopped = 801;
    public static final int EStatus_DataDecrypted = 802;
    public static final int EStatus_IdNeedSecurityFeatures = 900;
    public static final int EStatus_SecurityFeaturesStarted = 901;
    public static final int EStatus_SecurityFeaturesStopped = 902;
    public static final int EStatus_SecurityFeaturesReady = 903;
    public static final int EStatus_SecurityFeaturesTracking = 904;
    public static final int EStatus_SecurityFeaturesTrackingLost = 905;
    public static final int EStatus_StreamingStarted = 1000;
    public static final int EStatus_NFC_AuthenticationPass_DEPRECATED = 1100;
    public static final int EStatus_NFC_DataReadingStart = 1101;
    public static final int EStatus_NFC_DataReadingEndSuccess = 1102;
    public static final int EStatus_NFC_DataReadingEndFail = 1103;
    public static final int EStatus_NFC_PhotoReadingStart_DEPRECATED = 1104;
    public static final int EStatus_NFC_PhotoReadingFinish_DEPRECATED = 1105;
    public static final int EStatus_NFC_ReadingStopped = 1106;
    public static final int EStatus_NFC_Unavailable = 1107;
    public static final int EStatus_NFC_ReadAtrInfo = 1108;
    public static final int EStatus_NFC_AccessControl = 1109;
    public static final int EStatus_NFC_ReadSod = 1110;
    public static final int EStatus_NFC_ReadDg14 = 1111;
    public static final int EStatus_NFC_ChipAuthentication = 1112;
    public static final int EStatus_NFC_ReadDg15 = 1113;
    public static final int EStatus_NFC_ActiveAuthentication = 1114;
    public static final int EStatus_NFC_ReadDg1 = 1115;
    public static final int EStatus_NFC_ReadDg2 = 1116;
    public static final int EStatus_NFC_ReadDg7 = 1117;
    public static final int EStatus_NFC_ReadDg11 = 1118;
    public static final int EStatus_NFC_ReadDg12 = 1119;
    public static final int EStatus_NFC_PassiveAuthentication = 1120;
    public static final int EStatus_NFC_ChipClonedDetectionStart = 1121;
    public static final int EStatus_NFC_ChipClonedDetectionEndSuccess = 1122;
    public static final int EStatus_NFC_ConnectingToServer = 1123;
    public static final int EStatus_DocRecordingStarted = 1200;
    public static final int EStatus_DocRecordingStopped = 1201;
    public static final int EStatus_DocRecorded = 1202;
    /*! \brief  The EContentField enum contains key values for different data fields extracted from the input.
     *           DO NOT CHANGE THE NUMBERS IN THIS ENUM.
     */
    public static final int EContentField_MRZ_Type = 40;
    public static final int EContentField_MRZ_Subtype = 41;
    public static final int EContentField_MRZ_IssuingState = 42;
    public static final int EContentField_MRZ_Lastname = 43;
    public static final int EContentField_MRZ_Firstnames = 44;
    public static final int EContentField_MRZ_NameTruncationIndicator = 45;
    public static final int EContentField_MRZ_DocumentNumber = 46;
    public static final int EContentField_MRZ_DocumentNumberCheckDigit = 47;
    public static final int EContentField_MRZ_Nationality = 48;
    public static final int EContentField_MRZ_DateOfBirth = 49;
    public static final int EContentField_MRZ_DateOfBirthCheckDigit = 50;
    public static final int EContentField_MRZ_Gender = 51;
    public static final int EContentField_MRZ_ExpiryDate = 52;
    public static final int EContentField_MRZ_ExpiryDateCheckDigit = 53;
    public static final int EContentField_MRZ_OptionalData = 54;
    public static final int EContentField_MRZ_OptionalData2 = 55;
    public static final int EContentField_MRZ_OverallCheckDigit = 56;
    public static final int EContentField_MRZ_Td2CheckDigit = 57;
    public static final int EContentField_MRZ_Td1CheckDigit = 58;
    public static final int EContentField_MRZ_DATA = 59;
    public static final int EContentField_MRZ_Zemis = 60; // ZEMIS
    public static final int EContentField_MRZ_Ocr = 61;
    public static final int EContentField_MRZ_CardIssued = 62;
    public static final int EContentField_MRZ_Fuar = 63;
    public static final int EContentField_MRZ_Line1 = 64;
    public static final int EContentField_MRZ_Line2 = 65;
    public static final int EContentField_MRZ_Line3 = 66;
    public static final int EContentField_MRZ_IssuerName = 67;
    public static final int EContentField_MRZ_FathersName = 68;
    public static final int EContentField_OptionalLastNames = 80;
    public static final int EContentField_OptionalFirstNames = 81;
    public static final int EContentField_LivenessCheck_TransactionCode = 89;
    public static final int EContentField_LivenessCheck_Result = 90;
    public static final int EContentField_LivenessCheck_ErrorID = 91;
    public static final int EContentField_LivenessCheck_Threshold = 92;
    public static final int EContentField_LivenessCheck_ConfidenceLevel = 93;
    public static final int EContentField_LivenessCheck_ProcessId = 94;
    public static final int EContentField_LivenessCheck_FSTID = 95;
    public static final int EContentField_LivenessCheck_BlurThreshold = 96;
    public static final int EContentField_LivenessCheck_BlurCheckImage1 = 97;
    public static final int EContentField_FaceVerification_Result = 98;
    public static final int EContentField_FaceVerification_ErrorID = 99;
    public static final int EContentField_FaceVerification_Threshold = 100;
    public static final int EContentField_FaceVerification_ConfidenceLevel = 101;
    public static final int EContentField_FaceVerification_ProcessId = 102;
    public static final int EContentField_FaceVerification_FSTID = 103;
    public static final int EContentField_FaceVerification_BlurThreshold = 104;
    public static final int EContentField_FaceVerification_BlurCheckImage1 = 105;
    public static final int EContentField_FaceVerification_BlurCheckImage2 = 106;
    public static final int EContentField_FaceVerification_TransactionCode = 107;
    public static final int EContentField_VIZ_MarriedName = 108;
    public static final int EContentField_VIZ_Maidenname = 109;
    public static final int EContentField_VIZ_Type = 110;
    public static final int EContentField_VIZ_Subtype = 111;
    public static final int EContentField_VIZ_IssuingState = 112;
    public static final int EContentField_VIZ_Lastname = 113;
    public static final int EContentField_VIZ_Firstnames = 114;
    public static final int EContentField_VIZ_DocumentNumber = 115;
    public static final int EContentField_VIZ_Nationality = 116;
    public static final int EContentField_VIZ_DateOfBirth = 117;
    public static final int EContentField_VIZ_Gender = 118;
    public static final int EContentField_VIZ_ExpiryDate = 119;
    public static final int EContentField_VIZ_OptionalData = 120;
    public static final int EContentField_VIZ_OptionalData2 = 121;
    public static final int EContentField_VIZ_EmploymentStatus = 122;
    public static final int EContentField_MD_EditedFirstName = 123;
    public static final int EContentField_MD_EditedLastName = 124;
    public static final int EContentField_MD_GeneratedMrz = 125;
    public static final int EContentField_MD_ConfidenceLevel = 126;
    public static final int EContentField_MD_ScanDate = 127;
    public static final int EContentField_MD_ScanMouseId = 128;
    public static final int EContentField_MD_VersionId = 129;
    public static final int EContentField_MD_Checksum = 130;
    public static final int EContentField_MD_CustomerConfirmWrongData = 131;
    public static final int EContentField_MD_PhoneId = 132;
    public static final int EContentField_MD_DeviceModelType = 133;
    public static final int EContentField_MD_OsVersion = 134;
    public static final int EContentField_MD_StatusType = 135; // DEPRECATED
    public static final int EContentField_MD_ErrorID = 136;
    public static final int EContentField_MD_State = 137; // DEPRECATED
    public static final int EContentField_MD_RequestId = 138;
    public static final int EContentField_MD_ReportVersion = 139;
    public static final int EContentField_MD_ScanEngineVersion = 140; // DEPRECATED
    public static final int EContentField_MD_VendorName = 141;
    public static final int EContentField_MD_AppName = 142;
    public static final int EContentField_VIZ_Address = 143;
    public static final int EContentField_VIZ_ElectionCode = 144;
    public static final int EContentField_VIZ_Curp = 145;
    public static final int EContentField_VIZ_DateOfIssue = 146;
    public static final int EContentField_MD_SelfieStatus = 147;
    public static final int EContentField_MD_OrderId = 148;
    public static final int EContentField_VIZ_Zemis = 149; // ZEMIS
    public static final int EContentField_VIZ_PlaceOfBirth = 150;
    public static final int EContentField_VIZ_Authority = 151;
    public static final int EContentField_VIZ_DrivingCategory = 152;
    public static final int EContentField_VIZ_Signature = 153;
    public static final int EContentField_VIZ_DrivingCategoryValue = 154;
    public static final int EContentField_VIZ_DrivingCategoryIssueDate = 155;
    public static final int EContentField_VIZ_DrivingCategoryExpiryDate = 156;
    public static final int EContentField_VIZ_DrivingCategoryRestriction = 157;
    public static final int EContentField_VIZ_OverallDrivingRestriction = 158;
    public static final int EContentField_VIZ_FacePosition = 159;
    public static final int EContentField_MD_CheckDOB = 160;       // NOT USED
    public static final int EContentField_MD_CheckDocNumber = 161; // NOT USED
    public static final int EContentField_MD_CheckFirstNameMRZvsVIZ = 162;
    public static final int EContentField_MD_CheckLastNameMRZvsVIZ = 163;
    public static final int EContentField_MD_CheckFirstNameMRZvsNFC = 164;
    public static final int EContentField_MD_CheckLastNameMRZvsNFC = 165;
    public static final int EContentField_MD_CheckDOBMRZvsVIZ = 166;
    public static final int EContentField_MD_CheckDocNumberMRZvsVIZ = 167;
    public static final int EContentField_MD_CheckDOBMRZvsNFC = 168;
    public static final int EContentField_MD_CheckDocNumberMRZvsNFC = 169;
    public static final int EContentField_MD_PermitFormat = 170;
    public static final int EContentField_MD_NFCErrorID = 171;
    public static final int EContentField_VIZ_AdditionalAddressInformation = 172;
    public static final int EContentField_VIZ_AdditionalNameInformation = 173;
    public static final int EContentField_VIZ_AdditionalPersonalIdNumber = 174;
    public static final int EContentField_VIZ_DocumentAdditionalNumber = 175;
    public static final int EContentField_VIZ_DocumentAdditionalNumber2 = 176;
    public static final int EContentField_VIZ_Employer = 177;
    public static final int EContentField_VIZ_Fullname = 178;
    public static final int EContentField_VIZ_IssuingAuthority = 179;
    public static final int EContentField_VIZ_MaritalStatus = 180;
    public static final int EContentField_VIZ_PersonalIdNumber = 181;
    public static final int EContentField_VIZ_Profession = 182;
    public static final int EContentField_VIZ_Race = 183;
    public static final int EContentField_VIZ_Religion = 184;
    public static final int EContentField_VIZ_ResidentialStatus = 185;
    public static final int EContentField_VIZ_Restrictions = 186;
    public static final int EContentField_VIZ_VehicleClass = 187;
    public static final int EContentField_MD_ExtractedExpiryDate = 188;
    public static final int EContentField_MD_AllowUseDataForML = 189;
    public static final int EContentField_VIZ_FullName = 190;
    public static final int EContentField_VIZ_FathersName = 191;
    public static final int EContentField_SF_Hologram_Result = 200;            // "pass" or ""fail"
    public static final int EContentField_SF_Hologram_ConfidenceLevel = 201;   // validator confidence between 0 and 100
    public static final int EContentField_SF_Hologram_Threshold = 202;         // validator threshold between 0 and 100
    public static final int EContentField_SF_Lenticular_Result = 203;          // "pass" or ""fail"
    public static final int EContentField_SF_Lenticular_ConfidenceLevel = 204; // validator confidence between 0 and 100
    public static final int EContentField_SF_Lenticular_Threshold = 205;       // validator threshold between 0 and 100
    public static final int EContentField_SF_Lenticular_DocumentNumber =
        206; // extracted document number from the lenticular area
    public static final int EContentField_SF_Lenticular_ExpiryYear =
        207; // extracted expiration year from the lenticular area
    public static final int EContentField_SF_DocumentManipulation_Result = 208; // "pass" or ""fail"
    public static final int EContentField_SF_DocumentManipulation_ConfidenceLevel =
        209; // validator confidence between 0 and 100
    public static final int EContentField_SF_DocumentManipulation_Threshold =
        210; // validator threshold between 0 and 100
    public static final int EContentField_MD_ErrorCodeList = 300;
    public static final int EContentField_MD_ApplicationId = 301;
    public static final int EContentField_MD_BrandId = 302;
    public static final int EContentField_MD_FSTID = 303;
    public static final int EContentField_MD_TransactionCode = 304;
    public static final int EContentField_DC_ClassifiedDocumentFirstScan = 400;
    public static final int EContentField_DC_ClassifiedDocumentSecondScan = 401;
    public static final int EContentField_PROCESS_ID = 500;
    public static final int EContentField_AUX_IDLanguage = 600;
    public static final int EContentField_AUX_PermitType = 601;
    public static final int EContentField_NFC_SecondaryIdentifier = 700;
    public static final int EContentField_NFC_PrimaryIdentifier = 701;
    public static final int EContentField_NFC_DateOfBirth = 702;
    public static final int EContentField_NFC_DocumentCode = 703;
    public static final int EContentField_NFC_DocumentNumber = 704;
    public static final int EContentField_NFC_IssuingState = 705;
    public static final int EContentField_NFC_Nationality = 706;
    public static final int EContentField_NFC_Gender = 707;
    public static final int EContentField_NFC_DateOfExpiry = 708;
    public static final int EContentField_NFC_DocumentNumberCheckDigit = 709;
    public static final int EContentField_NFC_DateOfBirthCheckDigit = 710;
    public static final int EContentField_NFC_DateOfExpiryCheckDigit = 711;
    public static final int EContentField_NFC_CompositeCheckDigit = 712;
    public static final int EContentField_NFC_OptionalData1 = 713;
    public static final int EContentField_NFC_OptionalData2 = 714;
    public static final int EContentField_NFC_CertificateValidation_Result = 715;
    public static final int EContentField_NFC_CertificateValidation_ErrorID = 716;
    public static final int EContentField_NFC_CertificateValidation_ProcessId = 717;
    public static final int EContentField_NFC_CertificateValidation_FSTID = 718;
    public static final int EContentField_NFC_CertificateValidation_TransactionCode = 719;
    public static final int EContentField_NFC_ProcessId = 720;
    public static final int EContentField_NFC_FSTID = 721;
    public static final int EContentField_NFC_TransactionCode = 722;
    public static final int EContentField_NFC_DocumentVerification_HashAlgorithm = 749;
    public static final int EContentField_NFC_DocumentVerification_Dg1 = 750;
    public static final int EContentField_NFC_DocumentVerification_Dg2 = 751;
    public static final int EContentField_NFC_DocumentVerification_Dg3 = 752;
    public static final int EContentField_NFC_DocumentVerification_Dg4 = 753;
    public static final int EContentField_NFC_DocumentVerification_Dg5 = 754;
    public static final int EContentField_NFC_DocumentVerification_Dg6 = 755;
    public static final int EContentField_NFC_DocumentVerification_Dg7 = 756;
    public static final int EContentField_NFC_DocumentVerification_Dg8 = 757;
    public static final int EContentField_NFC_DocumentVerification_Dg9 = 758;
    public static final int EContentField_NFC_DocumentVerification_Dg10 = 759;
    public static final int EContentField_NFC_DocumentVerification_Dg11 = 760;
    public static final int EContentField_NFC_DocumentVerification_Dg12 = 761;
    public static final int EContentField_NFC_DocumentVerification_Dg13 = 762;
    public static final int EContentField_NFC_DocumentVerification_Dg14 = 763;
    public static final int EContentField_NFC_DocumentVerification_Dg15 = 764;
    public static final int EContentField_NFC_DocumentVerification_Dg16 = 765;
    public static final int EContentField_NFC_DocumentVerification_DocumentType = 766;
    public static final int EContentField_NFC_DocumentVerification_DocumentCode = 767;
    public static final int EContentField_NFC_DocumentVerification_IssuingState = 768;
    public static final int EContentField_NFC_DocumentVerification_PrimaryIdentifier = 769;
    public static final int EContentField_NFC_DocumentVerification_SecondaryIdentifier = 770;
    public static final int EContentField_NFC_DocumentVerification_Nationality = 771;
    public static final int EContentField_NFC_DocumentVerification_DocumentNumber = 772;
    public static final int EContentField_NFC_DocumentVerification_DateOfBirth = 773;
    public static final int EContentField_NFC_DocumentVerification_Gender = 774;
    public static final int EContentField_NFC_DocumentVerification_DateOfExpiry = 775;
    public static final int EContentField_NFC_DocumentVerification_OptionalData1 = 776;
    public static final int EContentField_NFC_DocumentVerification_OptionalData2 = 777;
    public static final int EContentField_NFC_DocumentVerification_FullNameOfHolder = 778;
    public static final int EContentField_NFC_DocumentVerification_OtherNames = 779;
    public static final int EContentField_NFC_DocumentVerification_PersonalNumber = 780;
    public static final int EContentField_NFC_DocumentVerification_FullDateOfBirth = 781;
    public static final int EContentField_NFC_DocumentVerification_PlaceOfBirth = 782;
    public static final int EContentField_NFC_DocumentVerification_PermanentAddress = 783;
    public static final int EContentField_NFC_DocumentVerification_Telephone = 784;
    public static final int EContentField_NFC_DocumentVerification_Profession = 785;
    public static final int EContentField_NFC_DocumentVerification_PersonalSummary = 786;
    public static final int EContentField_NFC_DocumentVerification_ProofOfCitizenshipImage = 787;
    public static final int EContentField_NFC_DocumentVerification_OtherValidTravelDocumentNumbers = 788;
    public static final int EContentField_NFC_DocumentVerification_CustodyInformation = 789;
    public static final int EContentField_NFC_DocumentVerification_IssuingAuthority = 790;
    public static final int EContentField_NFC_DocumentVerification_DateOfIssue = 791;
    public static final int EContentField_NFC_DocumentVerification_NamesOfOtherPersons = 792;
    public static final int EContentField_NFC_DocumentVerification_EndorsementsAndObservations = 793;
    public static final int EContentField_NFC_DocumentVerification_TaxOrExitRequirements = 794;
    public static final int EContentField_NFC_DocumentVerification_ImageOfFront = 795;
    public static final int EContentField_NFC_DocumentVerification_ImageOfRear = 796;
    public static final int EContentField_NFC_DocumentVerification_DateAndTimeOfPersonalization = 797;
    public static final int EContentField_NFC_DocumentVerification_PersonalizationSystemSerialNumber = 798;
    public static final int EContentField_NFC_DocumentVerification_PassiveAuthentication = 799;
    public static final int EContentField_NFC_DocumentVerification_SodSignatureValid = 800;
    public static final int EContentField_NFC_DocumentVerification_DocumentCertificateValid = 801;
    public static final int EContentField_NFC_DocumentVerification_DataGroupsChecked = 802;
    public static final int EContentField_NFC_DocumentVerification_DataGroupsWithValidHash = 803;
    public static final int EContentField_NFC_DocumentVerification_Error = 804;
    public static final int EContentField_NFC_DocumentVerification_AllHashesValid = 805;
    public static final int EContentField_NFC_DocumentVerification_Errors = 806;
    public static final int EContentField_NFC_CertificateValidation_Idb = 807;
    public static final int EContentField_NFC_DocumentVerification_Title = 808;
    public static final int EContentField_NFC_ActiveAuthenticationResult = 810;
    public static final int EContentField_NFC_ChipAuthenticationResult = 811;
    public static final int EContentField_NFC_ActiveAuthentication_ProcessId = 812;
    public static final int EContentField_NFC_ActiveAuthentication_FSTID = 813;
    public static final int EContentField_NFC_ActiveAuthentication_TransactionCode = 814;
    public static final int EContentField_NFC_ChipAuthentication_ProcessId = 815;
    public static final int EContentField_NFC_ChipAuthentication_FSTID = 816;
    public static final int EContentField_NFC_ChipAuthentication_TransactionCode = 817;
    public static final int EContentField_BARCODE_DATA = 870;
    public static final int EContentField_BARCODE_Firstname = 871;
    public static final int EContentField_BARCODE_Lastname = 872;
    public static final int EContentField_BARCODE_DateOfBirth = 873;
    public static final int EContentField_BARCODE_DateOfIssue = 874;
    public static final int EContentField_BARCODE_DocumentType = 875;
    public static final int EContentField_BARCODE_DocumentNumber = 876;
    public static final int EContentField_BARCODE_Nationality = 877;
    public static final int EContentField_BARCODE_Gender = 878;
    public static final int EContentField_BARCODE_ExpiryDate = 879;
    public static final int EContentField_BARCODE_IssuingState = 880;
    public static final int EContentField_BARCODE_DriverLicenseDetailedInfo = 881;
    public static final int EContentField_BARCODE_AdditionalAddressInformation = 882;
    public static final int EContentField_BARCODE_AdditionalNameInformation = 883;
    public static final int EContentField_BARCODE_AdditionalPersonalIdNumber = 884;
    public static final int EContentField_BARCODE_Address = 885;
    public static final int EContentField_BARCODE_DocumentAdditionalNumber = 886;
    public static final int EContentField_BARCODE_Employer = 887;
    public static final int EContentField_BARCODE_Fullname = 888;
    public static final int EContentField_BARCODE_MaritalStatus = 889;
    public static final int EContentField_BARCODE_PlaceOfBirth = 890;
    public static final int EContentField_BARCODE_Profession = 891;
    public static final int EContentField_BARCODE_Race = 892;
    public static final int EContentField_BARCODE_Religion = 893;
    public static final int EContentField_BARCODE_ResidentialStatus = 894;
    public static final int EContentField_BARCODE_VehicleClass = 895;
    public static final int EContentField_BARCODE_CheckFirstNameBARCODEvsVIZ = 896;
    public static final int EContentField_BARCODE_CheckLastNameBARCODEvsVIZ = 897;
    public static final int EContentField_BARCODE_CheckDOBBARCODEvsVIZ = 989;
    public static final int EContentField_BARCODE_CheckDocNumberBARCODEvsVIZ = 899;
    public static final int EContentField_BARCODE_Restrictions = 914;
    public static final int EContentField_BARCODE_Endorsements = 915;
    public static final int EContentField_VIZ_Endorsements = 916;
    public static final int EContentField_Unknown = 1000;
    /*! \brief  Possible file types in library.
     */
    public static final int EFileType_PDF = 10;
    public static final int EFileType_PDF_contract = 11;
    public static final int EFileType_XML = 20;
    public static final int EFileType_JSON = 21;
    public static final int EFileType_BMP = 30;
    public static final int EFileType_JPG = 31;
    public static final int EFileType_PNG = 32;
    public static final int EFileType_MP4 = 40;
    public static final int EFileType_GIF = 41;
    public static final int EFileType_M4V = 42;
    public static final int EFileType_MOV = 43;
    public static final int EFileType_WEBM = 44;
    public static final int EFileType_DER = 50;
    public static final int EFileType_EF = 51;
    public static final int EFileType_BIN = 60;
    /*! \brief  Group types of errors displayed to the user.
     */
    public static final int EScenarioStep_None = 0;
    public static final int EScenarioStep_QRCode = 1;
    public static final int EScenarioStep_IdRecognition = 2;
    public static final int EScenarioStep_ScanDocument = 3;
    public static final int EScenarioStep_SignDocument = 4;
    public static final int EScenarioStep_FaceCapture = 5;
    public static final int EScenarioStep_FaceVerification = 6;
    public static final int EScenarioStep_DataEncryption = 7;
    public static final int EScenarioStep_DataDecryption = 8;
    public static final int EScenarioStep_RecordDocument = 9;
    /*! \brief  Enumeration related to logging. Will be forwarded on intialize through ConfigEngine struct.
     */
    public static final int ELogLevel_None = 0;
    public static final int ELogLevel_Errors = 1;
    public static final int ELogLevel_Warnings = 1 << 1;
    public static final int ELogLevel_Information = 1 << 2;
    public static final int ELogLevel_Debug = 1 << 3;
    public static final int ELogLevel_Verbose = 1 << 4;
    /*! \brief  Feature status states.
     */
    public static final int IN_PROGRESS = 0;
    public static final int COMPLETED = 1;
    public static final int STARTED = 2;
}