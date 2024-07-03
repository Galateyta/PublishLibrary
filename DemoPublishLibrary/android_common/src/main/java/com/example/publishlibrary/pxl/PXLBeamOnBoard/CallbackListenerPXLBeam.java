package com.example.publishlibrary.pxl.PXLBeamOnBoard;

/**
 * CallbackListenerPXLBeam.java
 * Copyright (c) 2016 PXLVision. All rights reserved.
 *<ul>
 * <li>ESupportedDocument:
 * Definition of the messages
 *
 *  <li>EUserErrorType:
 * Group types of errors displayed to the user
 *
 * <li> EError:
 *  Error codes
 *  <li> EScenarioStep:
 * Group types of errors displayed to the User
 *
 * <li> EStatus:
 * Status (notification) codes
 *
 * <li> EContentField:
 * Contains key values for different data fields extracted from the input
 *
 *  <li>EFileType:
 * File format (MP4, JPG, BMP...)
 *</ul>
 *
 *
 */
public interface CallbackListenerPXLBeam
{
    /**
     * The receiver is notified that an error occured in the engine
     * @param errorNum Defines the error type
     */
    public void errorNotification(int errorNum);

    /**
     *The receiver is notified that an event happened
     * @param status Defines the status type
     **/
    public void statusNotification(int status);

    /**
     *The receiver is notified that an event happened
     * @param status Defines the status type
     **/
    public void statusNotificationSf(float angle, boolean status);

    /**
     *The receiver is notified about detected document type.
     * @param documentType Defines the document type
     **/
    public void documentTypeNotification(int documentType);

    /**
     *The receiver is notified about detected document side.
     * @param documentSide Defines the document side
     **/
    public void documentSideNotification(int documentSide);

    /**
     *The receiver is notified that the barcode recognition is successful
     * @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     **/
    public void barcodeRecognitionCompleted(PackageData data);

    /**
     *The receiver is notified that the IdRecognition recognition is successful
     * @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     **/
    public void idRecognitionCompleted(PackageData data);

    /**
     *The receiver is notified that the IdRecognition recognition is successful
     * @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     **/
    public void NFCRecognitionCompleted(PackageData data);

    /**
     * The receiver is notified that id package (archive with xml and image files) is ready
     *
     * @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     **
     */
    public void idPackageReady(PackageData data);

    /**
     *@brief This function is called by the engine when output pdf is signed
     ** @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     */
    public void pdfReady(PackageData data);

    /**
     *@brief This function is called by the engine when video is ready
     ** @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     */
    public void faceCaptureCompleted(PackageData data);

    /**
     *@brief This function is called by the engine when the face has been verified against the ID picture
     ** @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     */
    public void faceVerificationCompleted(PackageData data);

    public void faceVerificationInitialized(boolean init);

    /**
     *@brief This function is called by the engine when video is ready
     ** @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     */
    public void documentRecordingCompleted(PackageData data);

    /**
     * @brief This function is called by the engine when the encryption of the data is done
     * @param data Contains all the data, encrypted using the face scan of the user
     */
    public void encryptDataCompleted(PackageData data);

    /**
     * @brief This function is called by the engine when the decryption of the data is done
     * @param data Contains all the data, decrypted using the face scan of the user
     */

    public void decryptDataCompleted(PackageData data);

    /**
     *@brief This function is called by the engine when document is captured
     ** @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     */
    public void documentCaptured(PackageData data);

    /**
     *The receiver is notified that document package (archive with xml and image files) is ready
     ** @param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     */
    public void documentPackageReady(PackageData data);

    /**
     *The receiver is notified that id package (archive with xml and image files of primary or primary and secondary
     *documents) is ready
     **@param data Contains all the data (extracted images and/or codes) and metadata (statistics)
     * */
    public void dataForUploadReady(PackageData data);

    /**
     *The receiver is notified when manualCapture is completed running
     **@param data Contains data regarding the image captured by manualCapture
     * */
    public void manualCaptureCompleted(FilesData data);
}
