package hanhan.test.yunifang.com.yunifang.yunifang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import hanyongguang1124.test.bwie.com.yunifang.R;

public class LaunchBActivity extends AppCompatActivity {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Intent intent=new Intent(LaunchBActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                handler.sendEmptyMessageDelayed(1,3000);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_b);
        handler.sendEmptyMessageDelayed(1,3000);
    }
}
