package cn.edu.seu.personinfomodify;

import cn.edu.seu.gesturepassword.SetFirstActivity;
import cn.edu.seu.login.Mapplication;
import cn.edu.seu.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModifyActivity extends Activity{
	private Button btn_s1,btn_s2,btn_s3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
 		Mapplication.getInstance().addActivity(this);
		btn_s1=(Button) findViewById(R.id.btn_s1);	
		btn_s2=(Button) findViewById(R.id.btn_s2);
		btn_s3=(Button) findViewById(R.id.btn_s3);
		btn_s1.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ModifyActivity.this, ModifyPwdActivity.class);
				startActivity(intent);
			}
			
		});
		btn_s2.setOnClickListener(new Button.OnClickListener(){


			public void onClick(View v) {
				Intent intent=new Intent();
				intent.setClass(ModifyActivity.this, SetFirstActivity.class);
				intent.putExtra("flag", 3);
				startActivity(intent);
			}
			
		});
		btn_s3.setOnClickListener(new Button.OnClickListener(){


			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ModifyActivity.this, ModifyPhoActivity.class);
				startActivity(intent);
			}
			
		});
	}
}
