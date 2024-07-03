package com.example.demopublishlibrary;

//import com.pxl.pxlbeamapp.DocumentCapture.DocCaptureImages;

public class LibraryState
{
    public static final int SHUTDOWN = 0;
    public static final int INITIALIZED = 1;
    public static final int INITIALIZING = 2;
    public static final int SHUTTING_DOWN = 3;

    private boolean destroyed = false;
    private int currentState = 0;

    private static final LibraryState ourInstance = new LibraryState();

    public static LibraryState getInstance()
    {
        return ourInstance;
    }

    public int getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(int currentState)
    {
        this.currentState = currentState;
    }

    public boolean isLibState(int state)
    {
        return currentState == state;
    }

    public boolean isDestroyed()
    {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed)
    {
        this.destroyed = destroyed;
    }
}
