package com.newloong.writing;

import com.easemob.chatuidemo.R;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HandDraw extends Activity
{
	EmbossMaskFilter emboss;
	BlurMaskFilter blur;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_writing_pad);
		emboss = new EmbossMaskFilter(new float[]
		{ 1.5f , 1.5f , 1.5f }, 0.6f , 6, 4.2f);
		blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
	}
	@Override
	//璐熻矗鍒涘缓閫夐」鑿滃崟
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflator = new MenuInflater(this);
		//瑁呰浇R.menu.my_menu瀵瑰簲鐨勮彍鍗曪紝骞舵坊鍔犲埌menu涓�
		inflator.inflate(R.menu.my_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	// 鑿滃崟椤硅鍗曞嚮鍚庣殑鍥炶皟鏂规硶
	public boolean onOptionsItemSelected(MenuItem mi)
	{
		DrawView dv = (DrawView)findViewById(R.id.draw);
		//鍒ゆ柇鍗曞嚮鐨勬槸鍝釜鑿滃崟椤癸紝骞堕拡瀵规�х殑浣滃嚭鍝嶅簲銆�
		switch (mi.getItemId())
		{
			case R.id.red:
				dv.paint.setColor(Color.RED);
				mi.setChecked(true);				
				break;
			case R.id.black:
				dv.paint.setColor(Color.BLACK);
				mi.setChecked(true);	
				break;
			case R.id.blue:
				dv.paint.setColor(Color.BLUE);
				mi.setChecked(true);	
				break;
			case R.id.width_1:
				dv.paint.setStrokeWidth(1);
				break;
			case R.id.width_3:
				dv.paint.setStrokeWidth(3);
				break;
			case R.id.width_5:
				dv.paint.setStrokeWidth(5);
				break;
			case R.id.blur:
				dv.paint.setMaskFilter(blur);
				break;
			case R.id.emboss:
				dv.paint.setMaskFilter(emboss);
				break;				
		}
		return true;
	}	
}