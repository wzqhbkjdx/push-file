package com.example.uploadfile;

import java.io.File;
import java.io.FileNotFoundException;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity 
{
	private EditText etPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etPath = (EditText)findViewById(R.id.etPath); 
	}
	
	public void click(View view)
	{
		String path = etPath.getText().toString().trim();
		String url = "http://192.168.228.1:8080/web/UploadFileServlet";
		if(TextUtils.isEmpty(path))
		{
			Toast.makeText(this, "路径不能为空!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			File file = new File(path);
			if(file.exists() && file.length() > 0)
			{
				AsyncHttpClient client = new AsyncHttpClient();
				RequestParams params = new RequestParams();
				
				try 
				{
					params.put("profile_picture", file);
				} catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				client.post(url, params, new AsyncHttpResponseHandler()
				{
					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
							Throwable error) {
						Toast.makeText(MainActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
					}
				});
			}else
			{
				Toast.makeText(this, "文件不存在!", Toast.LENGTH_SHORT).show();
			}
			
		}
		
		
	}
	
	
}
