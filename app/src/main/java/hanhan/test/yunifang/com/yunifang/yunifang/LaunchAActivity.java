package hanhan.test.yunifang.com.yunifang.yunifang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import hanyongguang1124.test.bwie.com.yunifang.R;

public class LaunchAActivity extends AppCompatActivity {
   Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           if(msg.what==1){
               Intent intent=new Intent(LaunchAActivity.this,LaunchBActivity.class);
               startActivity(intent);
               finish();

           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_a);

        handler.sendEmptyMessageDelayed(1,2000);

    }
}
