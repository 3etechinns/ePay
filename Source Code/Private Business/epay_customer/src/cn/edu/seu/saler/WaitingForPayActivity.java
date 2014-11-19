package cn.edu.seu.saler;

import java.io.ByteArrayInputStream;

import cn.edu.seu.login.Mapplication;
import cn.edu.seu.main.R;
import cn.edu.seu.xml.XML;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WaitingForPayActivity extends Activity{

	private ImageView  btnreceive;
	private static final String TAG="WaitingForTransferActivity";
	private Thread changeBackgroundThread;
	private Button btn_back;
	private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
				 Toast.makeText(WaitingForPayActivity.this, "转账失败", 2000).show();
				 WaitingForPayActivity.this.finish();
				 try
				 {
					 changeBackgroundThread.interrupt();
				 }
				 catch(Exception e)
				 {
					 Log.i(TAG,"线程中断失败");
				 }
				 break;
            case 1:
				 Intent intent=new Intent(WaitingForPayActivity.this,InputActivity.class);
				 startActivity(intent);
				 WaitingForPayActivity.this.finish();
				 break;
            case 2:
            	 Toast.makeText(WaitingForPayActivity.this, "等待超时", 2000).show();
				 WaitingForPayActivity.this.finish();
				 try
				 {
					 changeBackgroundThread.interrupt();
				 }
				 catch(Exception e)
				 {
					 Log.i(TAG,"线程中断失败");
				 }
				 break;
            case 3:
				btnreceive.setImageDrawable(getResources().getDrawable(R.drawable.receive_latter));
				break;
            case 4:
				btnreceive.setImageDrawable(getResources().getDrawable(R.drawable.receive_former));
				break;
            case 5:
	    		btnreceive.setImageDrawable(getResources().getDrawable(R.drawable.receive));
	    		break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
   		try
   		{
   			changeBackgroundThread.interrupt();
   		}
   		catch(Exception e)
   		{
   			Log.i(TAG,"线程中断失败");
   		}
   	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waitingpay);
		Mapplication.getInstance().addActivity(this);
		
		WaitingPayThread wpt=new WaitingPayThread(handler);
        wpt.start();
	    btn_back = (Button)findViewById(R.id.btn_back_c);
	    btn_back.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				WaitingForPayActivity.this.finish();
			}
	    	
	    });
	    btnreceive=(ImageView)findViewById(R.id.receive);
	    changeBackgroundThread=new Thread(){
	    	public void run()
	    	{
	    		long start=System.currentTimeMillis();
	    		while(true)
    			{
	    			try{
		    			
	    				long end=System.currentTimeMillis();
	    				long last=end-start;
	    				Log.i(TAG,String.valueOf(last));
	    				if(last>60000)
	    					break;
	    				Message msg=handler.obtainMessage();
	    				sleep(500);
	    				msg.what=3;
	    				msg.sendToTarget();
	    				Log.i(TAG,"1");
	    				sleep(500);
	    				msg=handler.obtainMessage();
	    				msg.what=4;
	    				Log.i(TAG,"2");
	    				msg.sendToTarget();
	    				sleep(500);
	    				msg=handler.obtainMessage();
	    				msg.what=5;
	    				Log.i(TAG,"3");
	    				msg.sendToTarget();
		    		}
		    		catch(Exception e)
		    		{
		    			Log.i(TAG,"线程打断");
		    			break;
		    		}
    				
    			}
    			WaitingForPayActivity.this.finish();
	    		
	    	}
	    };
	    changeBackgroundThread.start();
	}

}
