package cn.edu.seu.main;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import java.util.Properties;
import java.util.UUID;

import cn.edu.seu.datadeal.PropertyInfo;
import cn.edu.seu.datatransportation.BluetoothDataTransportation;
import cn.edu.seu.datatransportation.LocalInfo;
import cn.edu.seu.datatransportation.LocalInfoIO;
import cn.edu.seu.gesturepassword.SetFirstActivity;
import cn.edu.seu.login.Mapplication;
import cn.edu.seu.main.R;
import cn.edu.seu.personinfomodify.ModifyActivity;
import cn.edu.seu.saler.InputActivity;
import cn.edu.seu.saler.QRcodeActivity;
import cn.edu.seu.saler.WaitingForPayActivity;
import cn.edu.seu.saler.WaitingPayThread;
import cn.edu.seu.xml.PersonInfo;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	Button btnReceive, btnModify, btnExit,btnView; 
	ToggleButton tbtnSwitch; 
	ListView lvBTDevices; 
	ArrayAdapter<String> adtDevices; 
	List<String> lstDevices = new ArrayList<String>(); 
	BluetoothAdapter btAdapt; 
	public static BluetoothSocket btSocket;
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	public static  BluetoothDataTransportation bdt=new BluetoothDataTransportation();
	private TextView receive;
	private static final String TAG="MainActivity";
	public static boolean s=true;
	public static PersonInfo person=new PersonInfo();
	private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
                break;
            case 1:
            	Intent intent=new Intent(MainActivity.this,InputActivity.class);
            	startActivity(intent);
                break;
       
            }
            super.handleMessage(msg);
        }
    };
     @Override 
     public void onCreate(Bundle savedInstanceState) { 
         super.onCreate(savedInstanceState); 
         setContentView(R.layout.main); 
         //初始化person信息
 		Mapplication.getInstance().addActivity(this);
         btAdapt = BluetoothAdapter.getDefaultAdapter();// 初始化本机蓝牙功能 
 		Properties property =PropertyInfo.getProperties();
 		LocalInfoIO lio = new LocalInfoIO(property.getProperty("path") , property.getProperty("filename"));
         LocalInfo x = lio.readfile();
         {
        	try
        	{
        		if(!btAdapt.isEnabled())
            	 {
            		 btAdapt.enable();
            		 Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
               	 intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
               	 startActivity(intent);
            	 }
        		person.setBluetoothmac(BluetoothDataTransportation.getLocalMac().replace(":", ""));
        	}
        	catch(Exception e)
        	{
        		Log.i(TAG,"未打开蓝牙");
        	}
        	person.setPrivatekey(x.getPrivateKey());
        	person.setPublickeyn(x.getPublicKeyn());
     		person.setUsername(x.getUserName());
     		person.setCustomername(x.getCustomerName());
     		person.setCardnum(x.getCardnum());
     		person.setImei(((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
 					.getDeviceId());
     		person.setBalance(x.getBalance());
     	}//载入person
         Log.i(TAG,person.getUsername());
         ConnectivityManager cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
         NetworkInfo info = cwjManager.getActiveNetworkInfo(); 
         if (info != null && info.isAvailable()){ 
         //do nothing 
         } 
         else
         {
        	 Toast.makeText(MainActivity.this,"无互联网连接",Toast.LENGTH_SHORT).show();
         }
         btnExit = (Button) this.findViewById(R.id.exit); 
         btnReceive=(Button) this.findViewById(R.id.shoukuan);
         btnModify=(Button) this.findViewById(R.id.modify);
         btnView=(Button) this.findViewById(R.id.view);
         btnExit.setOnClickListener(new ClickEvent()); 
         btnReceive.setOnClickListener(new ClickEvent()); 
         btnModify.setOnClickListener(new ClickEvent()); 
         btnView.setOnClickListener(new ClickEvent()); 
         
  
     } 
	class ClickEvent implements View.OnClickListener { 
         public void onClick(View v) { 
            if (v == btnExit) { 
        		Intent intent = new Intent();
            	intent.setClass(MainActivity.this,ExitFromSettings.class);
            	startActivityForResult(intent,100);   
             } 
             else if(v==btnReceive)
             {
            	
            	 Intent intent=new Intent();
            	 intent.setClass(MainActivity.this,WaitingForPayActivity.class);
            	 startActivity(intent);
            	 
             }
             else if(v==btnModify)
             {
            	 Intent intent=new Intent();
            	 intent.setClass(MainActivity.this,ModifyActivity.class);
            	 startActivity(intent);
             }
             else if(v==btnView)
             {
            	 Intent intent=new Intent();
            	 intent.setClass(MainActivity.this,QRcodeActivity.class);
            	 startActivity(intent);
             }
         } 
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		Intent intent = new Intent();
        intent.setClass(MainActivity.this,ExitActivity.class);
        startActivityForResult(intent,100);    
		return false;
	}
 	
}