package com.example.isafetybots.Fragments;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isafetybots.BT;
import com.example.isafetybots.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    private TextView dashboardO2, dashboardTemp, dashboardHeartRate, batteryPercentage, bluetoothConnection;
    private BT bt;
    private Button emModebtn, melodybtn, mutebtn;
    //private TextView hrlbl,o2lbl,batlbl,templbl,lastUpdatedlbl,bluetoothstatustv;
    private ImageView muteimg;
    private final String SET_TIME_TAG = "TI", SET_MODE_TAG = "SM", SET_HRTHRESHOLD_TAG = "TH", ALARM_TAG = "SING";
    private String[] items = {"0", "0", "0", "0", "0", "0", "0"};
    private String[] mobileNos = {"0786113200", "0772741400", "0784320006", "0763489016", "0787110008","0767952020"};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        bt = BT.getInstance();

        emModebtn = view.findViewById(R.id.emergency_mode_btn);
        //melodybtn=findViewById(R.id.melody_btn);
        mutebtn = view.findViewById(R.id.mute_btn);
        emModebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBTCommand(SET_MODE_TAG, 3);    ///////////////// send 3 to trigger emergency mode

            }
        });
        //melodybtn.setOnClickListener((View.OnClickListener) this);
        mutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendBTCommand(SET_MODE_TAG, 8);   ///////////////// send 8 to mute device

            }
        });

        dashboardHeartRate = view.findViewById(R.id.dashboard_heat_rate_level);
        dashboardO2 = view.findViewById(R.id.dashboard_o2_level);
        batteryPercentage = view.findViewById(R.id.battery_percentage);
        dashboardTemp = view.findViewById(R.id.dashboard_temp_level);
        //lastUpdatedlbl=findViewById(R.id.last_tv);
        bluetoothConnection = view.findViewById(R.id.dashboard_bluetooth_connection);

        muteimg = view.findViewById(R.id.mute_image);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(bluetoothConnectReceiver, new IntentFilter(BT.BLUETOOTH_CONNECTED));
        //Fired when the connection is lost
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(bluetoothDisconnectReceiver, new IntentFilter(BT.BLUETOOTH_DISCONNECTED));
        //Fired when connection can not be established, after 30 attempts.
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(bluetoothNewMessage, new IntentFilter(BT.BLUETOOTH_NEW_MSG));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(bluetoothConnecting, new IntentFilter(BT.BLUETOOTH_TRYING));
        // Setup listener for SeekBar:

        return view;
    }

    BroadcastReceiver bluetoothConnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            bluetoothConnection.setTextColor(Color.GREEN);
            bluetoothConnection.setText("Connected");
        }
    };

    BroadcastReceiver bluetoothDisconnectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            bluetoothConnection.setTextColor(Color.RED);
            bluetoothConnection.setText("Connection lost");

        }
    };


    BroadcastReceiver bluetoothNewMessage = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //bluetoothConnection.setTextColor(Color.BLUE);
            final String raw = bt.getBtMsg();
            //bluetoothConnection.setText("raw"+raw);

            if (raw != null && raw.contains("[") && raw.contains("]"))
                serialDecode(raw);          /////   serial decode method removes start and end characters


        }
    };

    private void updateUI() {
        {
            batteryPercentage.setText(getBat());
            dashboardTemp.setText(getTemp());
            //lastUpdatedlbl.setText(getLastReadTime());


            if (Float.parseFloat(getTemp()) > 31.5) {
                dashboardO2.setText(getSPO2());
                dashboardHeartRate.setText(getHR());

                if (Integer.parseInt(getCurrentMode())== 3) notifyAttempt();

            } else {
                ///// "Please wear the health monitor properly"
                dashboardO2.setText("--");
                dashboardHeartRate.setText("--");
            }


            if (checkIfMute()) muteimg.setVisibility(View.VISIBLE);
            else muteimg.setVisibility(View.INVISIBLE);

        }
    }

    private String getLastReadTime() {
        final int epox = Integer.parseInt(items[2]);
        return String.valueOf(45);  //////////////!!!!!!!!!!!!!!!!!    calculate time
    }

    private String getTemp() {
        return items[1];
    }

    private String getBat() {
        return items[0];
    }

    private String getSPO2() {

        int spo2 = Integer.parseInt(items[4]);
        if (spo2 > 100) spo2 = 100;
        return String.valueOf(spo2);

    }

    private String getHR() {
        return items[3];
    }

    private String getCurrentMode() {
        return items[5];
    }

    private boolean checkIfMute() {
        return items[6].equals("1");
    }

    private void sendBTCommand(String tag, long value) {
        bt.sendBt(tag + value);

    }

    int msgCount = 0;
    boolean isNotified = false;

    private void notifyAttempt() {
        if (!isNotified) {
            isNotified = true;
            String msg = "Attention! Threshold values reached\n" +
                    "Patient ID:002\n" +
                    "Oxygen Saturation:" + getSPO2() +
                    "%\nHeart Rate:" + getHR() +
                    "bpm\nBody Temp:" + getTemp() +
                    "*C\nThis is an automated message from iSafety";
            try {
                msgQ(msg);/////// method to send multiple messages with 3 second delays
                buildMessage("Attention!", "Device has detected an Emergency Situation." +
                        "Your Doctor and Relatives were Notified. Please Stay Calm !");
            }
                catch(Exception e){
                    buildMessage("Message(s) may not sent!", e.toString());
                }
            }
        }


        BroadcastReceiver bluetoothConnecting = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                bluetoothConnection.setTextColor(Color.YELLOW);
                bluetoothConnection.setText("Connecting...");

            }
        };

//    public void onClick(View v) {
//        if(v==emModebtn)sendBTCommand(SET_MODE_TAG,3);    ///////////////// send 3 to trigger emergency mode
//        else if(v==melodybtn)bt.sendBt(ALARM_TAG);   /////////// use bt.sendBt method to send custom data
//        else if(v==mutebtn)sendBTCommand(SET_MODE_TAG,8);   ///////////////// send 8 to mute device
//    }

        @Override
        public void onResume () {
            bt.setContext(getActivity());
            super.onResume();
        }

        @Override
        public void onPause () {
            bt.setContext(null);
            super.onPause();
        }

        private void serialDecode (String string){

            try {
                int begin = string.indexOf("[") + 1;
                int end = string.indexOf("]");
                final String data = string.substring(begin, end);
                // return string.replaceAll("-","").replaceAll("<","").replaceAll(">","");
                items = data.split(",");
                if (items.length >= 7)
                    updateUI();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void msgQ (String msg){
            final Handler hander = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);                  //// delay in ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hander.post(new Runnable() {
                        @Override
                        public void run() {

                            if (msgCount < mobileNos.length) {
                                proceedMessege(msg, mobileNos[msgCount]);
                                msgQ(msg);
                                msgCount++;
                            }
                        }
                    });
                }
            }).start();
        }


        private void proceedMessege ( String msg, String mobileNo){

            try {

                //TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);
                // boolean isDualSIM = telephonyInfo.isDualSIM();
                //if(isDualSIM){

                SmsManager smsManager = SmsManager.getDefault();
                PendingIntent pi;
                String SENT = "SMS_SENT";
                pi = PendingIntent.getBroadcast(getActivity(), 0, new Intent(SENT), 0);
                smsManager.sendTextMessage(mobileNo, null, msg, pi, null);
                toastMsg("sending...");

            } catch (Exception e) {
                buildMessage("Error sending message", e.toString());
            }

        }

        private void toastMsg (String s){
            Toast toast = Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT);
            toast.show();
        }

        private void buildMessage(String title, String m){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(m);
            builder.show();
        }



}

