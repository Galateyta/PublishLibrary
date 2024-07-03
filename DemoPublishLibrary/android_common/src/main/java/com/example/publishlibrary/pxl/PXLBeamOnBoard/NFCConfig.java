package com.example.publishlibrary.pxl.PXLBeamOnBoard;

public class NFCConfig
{
    private Boolean saveAdditionalFiles = false;
    private Boolean saveActiveAuth = true;
    private Boolean saveNFCOnMobile = true;

    public Boolean getSaveActiveAuth()
    {
        return saveActiveAuth;
    }

    public void setSaveActiveAuth(Boolean saveActiveAuth)
    {
        this.saveActiveAuth = saveActiveAuth;
    }

    public Boolean getSaveNFCOnMobile()
    {
        return saveNFCOnMobile;
    }

    public void setSaveNFCOnMobile(Boolean saveNFCOnMobile)
    {
        this.saveNFCOnMobile = saveNFCOnMobile;
    }

    public Boolean getSaveAdditionalFiles()
    {
        return saveAdditionalFiles;
    }

    public void setSaveAdditionalFiles(Boolean saveAdditionalFiles)
    {
        this.saveAdditionalFiles = saveAdditionalFiles;
    }
}
