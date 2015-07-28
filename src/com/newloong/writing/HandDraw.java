package com.newloong.writing;

import java.io.File;

import com.easemob.chatuidemo.R;
import com.newloong.writing.paint.BitMapUtils;
import com.newloong.writing.paint.PaintView;
import com.newloong.writing.paint.PaintViewCallBack;
import com.newloong.writing.paint.PaintConstants.PEN_SIZE;
import com.newloong.writing.paint.PaintConstants.PEN_TYPE;

import android.os.Bundle;
import android.os.Environment;
import android.provider.SyncStateContract.Constants;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HandDraw extends Activity implements OnClickListener {
	
	LinearLayout paintViewLayout = null;//画板布局
	PaintView    mPaintView		 = null;//画板
	Button       btnSetting		 = null;//设置
	Button       btnEraser		 = null;//橡皮
	Button       btnUndo		 = null;//撤销
	Button       btnClean		 = null;//清空
	
	RelativeLayout layoutSetting = null;//设置
	LinearLayout   layoutColor   = null;//设置-颜色
	LinearLayout   layoutSize    = null;//设置-大小
	LinearLayout   layoutShape   = null;//设置-形状
	
	Button  btnColor = null;//颜色
	Button  btnSize	 = null;//大小
	Button  btnShape = null;//形状
	Button  btnSave  = null;//保存
	Button  btnSend  = null;//发送
	
	//颜色
	ImageView imgColorBlack  = null;
	ImageView imgColorBlue 	 = null;
	ImageView imgColorGreen  = null;
	ImageView imgColorYellow = null;
	ImageView imgColorRed 	 = null;
	
	//大小
	ImageView imgSize01  = null;
	ImageView imgSize02  = null;
	ImageView imgSize03  = null;
	ImageView imgSize04  = null;
	ImageView imgSize05  = null;
	
	//形状
	ImageView imgShapeBlur  = null;
	ImageView imgShapePen   = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_pad);
        
        initView();
    }

	private void initView() {
		
		paintViewLayout = (LinearLayout) findViewById(R.id.paintViewLayout);
		layoutSetting   = (RelativeLayout) findViewById(R.id.layoutSetting);
		layoutColor     = (LinearLayout) findViewById(R.id.layoutColor);
		layoutSize      = (LinearLayout) findViewById(R.id.layoutSize);
		layoutShape     = (LinearLayout) findViewById(R.id.layoutShape);
		
		btnSetting	= (Button) findViewById(R.id.btnSetting);
		btnEraser	= (Button) findViewById(R.id.btnEraser);
		btnUndo		= (Button) findViewById(R.id.btnUndo);
		btnClean	= (Button) findViewById(R.id.btnClean);
		btnColor	= (Button) findViewById(R.id.btnColor);
		btnSize		= (Button) findViewById(R.id.btnSize);
		btnShape	= (Button) findViewById(R.id.btnShape);
		btnSave     = (Button) findViewById(R.id.btnSave);
		btnSend     = (Button) findViewById(R.id.btnSend);
		btnSetting.setOnClickListener(this);
		btnEraser.setOnClickListener(this);
		btnUndo.setOnClickListener(this);
		btnClean.setOnClickListener(this);
		btnColor.setOnClickListener(this);
		btnSize.setOnClickListener(this);
		btnShape.setOnClickListener(this);
		btnSave.setOnClickListener(this);
		btnSend.setOnClickListener(this);
		
		imgColorBlack  = (ImageView) findViewById(R.id.imgColorBlack);
		imgColorBlue   = (ImageView) findViewById(R.id.imgColorBlue);
		imgColorGreen  = (ImageView) findViewById(R.id.imgColorGreen);
		imgColorYellow = (ImageView) findViewById(R.id.imgColorYellow);
		imgColorRed    = (ImageView) findViewById(R.id.imgColorRed);
		imgColorBlack.setOnClickListener(this);
		imgColorBlue.setOnClickListener(this);
		imgColorGreen.setOnClickListener(this);
		imgColorYellow.setOnClickListener(this);
		imgColorRed.setOnClickListener(this);
		
		imgSize01 = (ImageView) findViewById(R.id.imgSize01);
		imgSize02 = (ImageView) findViewById(R.id.imgSize02);
		imgSize03 = (ImageView) findViewById(R.id.imgSize03);
		imgSize04 = (ImageView) findViewById(R.id.imgSize04);
		imgSize05 = (ImageView) findViewById(R.id.imgSize05);
		imgSize01.setOnClickListener(this);
		imgSize02.setOnClickListener(this);
		imgSize03.setOnClickListener(this);
		imgSize04.setOnClickListener(this);
		imgSize05.setOnClickListener(this);
		
		imgShapePen  = (ImageView) findViewById(R.id.imgShapePen);
		imgShapeBlur = (ImageView) findViewById(R.id.imgShapeBlur);
		imgShapePen.setOnClickListener(this);
		imgShapeBlur.setOnClickListener(this);
		
		initPaintView();
		initPaintViewCallBack();
		
		
	}
	
	private void initPaintView() {
		
		mPaintView = new PaintView(this);
		paintViewLayout.addView(mPaintView);
		
		mPaintView.setPenStyle(Paint.Style.STROKE);			
		mPaintView.setPenSize(5);
		mPaintView.setPenColor(Color.BLACK);
		mPaintView.setBackGroundColor(Color.WHITE);	
	}
	
	
	/**
	 * 初始化paintView的回调函数
	 */
	private void initPaintViewCallBack() {
		mPaintView.setCallBack(new PaintViewCallBack() {
			// 当画了之后对Button进行更新
			public void onHasDraw() {
				/*enableUndoButton();
				disableRedoButton();*/
			}

			public void onTouchDown() {
				
			}
		});
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnSetting://设置
			showSettingView();
			break;
		case R.id.btnColor://设置-颜色
			showSettingColorView();
			break;
		case R.id.btnSize://设置-大小
			showSettingSizeView();
			break;
		case R.id.btnShape://设置-形状
			showSettingShapeView();
			break;
			
		case R.id.btnEraser://橡皮
			mPaintView.setCurrentPainterType(PEN_TYPE.ERASER);
			layoutSetting.setVisibility(View.GONE);
			break;
		case R.id.btnUndo://撤销
			mPaintView.undo();
			layoutSetting.setVisibility(View.GONE);
			break;
		case R.id.btnClean://清空
			mPaintView.clearAll();
			layoutSetting.setVisibility(View.GONE);
			break;
		case R.id.btnSave://保存
			savePic();
		case R.id.btnSend://发送
            sendPic();
		
		//颜色
		case R.id.imgColorBlack:
			mPaintView.setPenColor(Color.BLACK);
			break;	
		case R.id.imgColorBlue:
			mPaintView.setPenColor(Color.BLUE);
			break;	
		case R.id.imgColorGreen:
			mPaintView.setPenColor(Color.GREEN);
			break;	
		case R.id.imgColorYellow:
			mPaintView.setPenColor(Color.YELLOW);
			break;	
		case R.id.imgColorRed:
			mPaintView.setPenColor(Color.RED);
			break;	
			
		//大小
		case R.id.imgSize01:
			mPaintView.setPenSize(PEN_SIZE.SIZE_1);
			break;	
		case R.id.imgSize02:
			mPaintView.setPenSize(PEN_SIZE.SIZE_2);
			break;	
		case R.id.imgSize03:
			mPaintView.setPenSize(PEN_SIZE.SIZE_3);
			break;	
		case R.id.imgSize04:
			mPaintView.setPenSize(PEN_SIZE.SIZE_4);
			break;	
		case R.id.imgSize05:
			mPaintView.setPenSize(PEN_SIZE.SIZE_5);
			break;	
		
		//形状
		case R.id.imgShapePen:
			mPaintView.setCurrentPainterType(PEN_TYPE.PLAIN_PEN);
			break;	
		case R.id.imgShapeBlur:
			mPaintView.setCurrentPainterType(PEN_TYPE.BLUR);
			break;	
		}
	}

	
    //显示 "设置"-形状
	private void showSettingShapeView() {
		layoutShape.setVisibility(View.VISIBLE);
		layoutSize.setVisibility(View.GONE);
		layoutColor.setVisibility(View.GONE);
		btnShape.setBackgroundResource(R.drawable.paint_setting_btn);
		btnSize.setBackgroundResource(R.drawable.paint_btn_noselected);
		btnColor.setBackgroundResource(R.drawable.paint_btn_noselected);
	}
	
	//显示 "设置"-大小
	private void showSettingSizeView() {
		layoutSize.setVisibility(View.VISIBLE);
		layoutColor.setVisibility(View.GONE);
		layoutShape.setVisibility(View.GONE);
		btnSize.setBackgroundResource(R.drawable.paint_setting_btn);
		btnColor.setBackgroundResource(R.drawable.paint_btn_noselected);
		btnShape.setBackgroundResource(R.drawable.paint_btn_noselected);
	}
	
	//显示 "设置"界面
	private void showSettingView() {
		if (layoutSetting.getVisibility() == View.VISIBLE) {
			layoutSetting.setVisibility(View.GONE);
		}else if (layoutSetting.getVisibility() == View.GONE) {
			layoutSetting.setVisibility(View.VISIBLE);
		}
		mPaintView.setCurrentPainterType(PEN_TYPE.PLAIN_PEN);
	}
	
	//显示 "设置"-颜色 界面
	private void showSettingColorView() {
		layoutColor.setVisibility(View.VISIBLE);
		layoutSize.setVisibility(View.GONE);
		layoutShape.setVisibility(View.GONE);
		btnColor.setBackgroundResource(R.drawable.paint_setting_btn);
		btnSize.setBackgroundResource(R.drawable.paint_btn_noselected);
		btnShape.setBackgroundResource(R.drawable.paint_btn_noselected);
	} 
	
	//保存图画到SD卡
	private void savePic() {

		String SDCardPath = Environment.getExternalStorageDirectory().getPath();
		File dirFile = new File( SDCardPath +"/"+"my_pics");
		if (!dirFile.exists()) { 
			dirFile.mkdirs();
		}
			
		String file = dirFile + "/"+"answer.png";
		Bitmap bitmap = mPaintView.getSnapShoot();
		BitMapUtils.saveToSdCard(file, bitmap);			
		
		Toast.makeText(getApplicationContext(), "已保存", 0).show();
	}
	
	//发送保存过的图片
    private void sendPic() {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "请到聊天界面发送保存后的图片", 0).show();
    }

	
	
}
