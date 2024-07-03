# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/andrej/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontshrink
-dontoptimize
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
-ignorewarnings

-keep interface com.** { *; }

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.PackageData {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.FilesDataList {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.FilesData {
    public <fields>;
    public <methods>;
}
-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.ConfigScanning {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.NFCConfig {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.NFCReader {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.NFCConstants {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.Enums {
    public <fields>;
    public <methods>;
}

-keep class com.example.publishlibrary.pxl.PXLBeamOnBoard.ExtractDataList {
    public <fields>;
    public <methods>;
}

-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
