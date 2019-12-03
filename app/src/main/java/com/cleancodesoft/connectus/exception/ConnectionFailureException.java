package com.example.myapplication.exception;

import android.util.Log;

public class ConnectionFailureException extends Exception {
    public ConnectionFailureException() {
        Log.e("EXCEPTION", "ConnectionFailureCF_EXCEPTION");
    }
}
