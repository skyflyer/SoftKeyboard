package demo.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "KeyboardDemo";
    Button _btnShow;
    Button _btnHide;
    TextView _txtStatus;
    Handler _handler;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        _handler = new Handler();
        _txtStatus = (TextView) findViewById(R.id.txtStatus);
        _btnShow = (Button) findViewById(R.id.btnShowKeyboard);
        _btnHide = (Button) findViewById(R.id.btnHideKeyboard);
        
        _btnShow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "Showing keyboard...");
		        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
		        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			}
		});
        _btnHide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Hiding keyboard...");
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		});
        
        // update the screen periodically
        
        
        _handler.postDelayed(_task, 2000);
    }
    
    Runnable _task = new Runnable() {
		@Override
		public void run() {
			updateDisplaySize();
			_handler.postDelayed(_task, 2000);
		}
    };
    
    
    @Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		startTask();
	}
    
	@Override
	protected void onPause() {
		super.onPause();
		stopTask();
	}


	void startTask()
	{
		Log.d(TAG, "Starting task");
		_task.run();
	}

	void stopTask()
	{
		Log.d(TAG, "Stopping task");
		_handler.removeCallbacks(_task);
	}
	
	void updateDisplaySize()
    {
		Log.d(TAG, "Updating display size");
    	Display display = getWindowManager().getDefaultDisplay();
    	Point size = new Point();
    	display.getSize(size);
    	Rect rectangle = new Rect();
    	getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
    	_txtStatus.setText(String.format("Window: width=%d, height=%d\nVisible: width=%d, height=%d", size.x, size.y, rectangle.width(), rectangle.height()));
    }
}