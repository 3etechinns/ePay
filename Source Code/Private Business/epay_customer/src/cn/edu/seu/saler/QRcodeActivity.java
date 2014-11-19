package cn.edu.seu.saler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import cn.edu.seu.datadeal.PropertyInfo;
import cn.edu.seu.login.Mapplication;
import cn.edu.seu.main.MainActivity;
import cn.edu.seu.main.R;

public class QRcodeActivity extends Activity {

	private ImageView img;
	private Button download,btn_back;
	private Bitmap bm;
	private static final String TAG="QRcodeActivity";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode);
		Mapplication.getInstance().addActivity(this);
		
		img = (ImageView)findViewById(R.id.imageView1);
		download=(Button)findViewById(R.id.download);
		btn_back=(Button)findViewById(R.id.btn_back_c);
		btn_back.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				QRcodeActivity.this.finish();
			}
			
		});
		String info = MainActivity.person.getUsername() + ";" + MainActivity.person.getBluetoothmac() + ";individual"; 
		Log.i(TAG,info);
		try {
			bm = EncodingHandler.createQRCode(info, 400);
			img.setImageBitmap(bm);
			download.setOnClickListener(new Button.OnClickListener(){

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ByteArrayOutputStream os = new ByteArrayOutputStream();  
					  // 将Bitmap压缩成PNG编码，质量为100%存储  
					bm.compress(Bitmap.CompressFormat.PNG, 100, os);//除了PNG还有很多常见格式，如jpeg等。  
					byte[] image=os.toByteArray();  
					Properties property=PropertyInfo.getProperties();
					String path=property.getProperty("path", "/sdcard/data");
					String fileName=property.getProperty("qrcodefilename", "qrcode.png");
					File file=new File(path,fileName);
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(file);
						fos.write(image);
						Toast.makeText(QRcodeActivity.this, "下载成功,文件保存到"+path+"/"+fileName, 2000).show();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						Log.i(TAG,"文件打开失败");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.i(TAG,"文件读写失败");

					}
					
				}
				
			});
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
