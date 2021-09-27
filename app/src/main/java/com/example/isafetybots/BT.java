package com.example.isafetybots;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
//import android.support.v4.content.LocalBroadcastManager;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import eu.basicairdata.bluetoothhelper.BluetoothHelper;


public class BT {
    private static BT ourInstance = new BT();
    private BluetoothHelper mBluetooth = new BluetoothHelper();
    // private String DEVICE_NAME = "Thilina @ ONLINE";           // The name of the remote device (BlueSMIRF Gold)
    private String DEVICE_NAME = "HC-06";            // The name of the remote device (HC-05)
    public static String BLUETOOTH_CONNECTED = "Bt-started";
    public static String BLUETOOTH_DISCONNECTED = "Bt-lost";
    public static String BLUETOOTH_TRYING = "Bt-trying";
    public static String BLUETOOTH_NEW_MSG = "Bt-new_msg";
    private static String btMsg;
    private Context context;



    public static BT getInstance()
    {
        return ourInstance;
    }

    private BT() {


        BluetoothAdapter mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter== null) {
            // Device does not support Bluetooth
            notifyState(BLUETOOTH_DISCONNECTED);
        }else{

            // Start Bluetooth connection with the paired device (BlueSMIRF Gold)
            notifyState(BLUETOOTH_TRYING);
            mBluetooth.Connect(DEVICE_NAME);

            mBluetooth.setBluetoothHelperListener(new BluetoothHelper.BluetoothHelperListener() {
                @Override
                public void onBluetoothHelperMessageReceived(BluetoothHelper bluetoothhelper, final String message) {
                    btMsg=message;
                    notifyState(BLUETOOTH_NEW_MSG);
                }

                @Override
                public void onBluetoothHelperConnectionStateChanged(BluetoothHelper bluetoothhelper, boolean isConnected) {
                    if (isConnected) {
                        notifyState(BLUETOOTH_CONNECTED);
                    } else {
                        notifyState(BLUETOOTH_DISCONNECTED);
                        // Auto reconnect!
                        mBluetooth.Connect(DEVICE_NAME);
                    }
                }
            });
        }
    }
    public void sendBt(String t){
        if (mBluetooth.isConnected()) {
            // Write the new value to Bluetooth (The String is something like "$PWM,128")
            final String buffer="-["+t+"]-";
            mBluetooth.SendMessage(buffer);
        }
    }

    private void notifyState(String st){
        Intent intent = new Intent(st);
        if(context!=null) LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }



    public void setContext(Context context) {
        this.context = context;
    }


    public String getBtMsg() {
        return btMsg;
    }

    public boolean isConnected() {
        return mBluetooth.isConnected();
    }


}

