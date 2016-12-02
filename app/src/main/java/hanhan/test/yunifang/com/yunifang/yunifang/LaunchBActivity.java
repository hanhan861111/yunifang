package hanhan.test.yunifang.com.yunifang.yunifang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hanyongguang1124.test.bwie.com.yunifang.R;

public class LaunchBActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (time > 1) {
                    if (boo) {
                        time--;
                        bt_launch_b.setText(time + "秒跳转");
                        handler.sendEmptyMessageDelayed(1, 1000);
                    }
                } else {
                    Intent intent = new Intent(LaunchBActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    private SharedPreferences sharedPreferences;
    private Button bt_launch_b;
    private int time = 4;
    private SharedPreferences.Editor edit;
    private boolean boo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_b);
        initView();
        initData();
    }

    private void initData() {
        sharedPreferences = getSharedPreferences("key", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("tag", false)) {
            Intent intent = new Intent(LaunchBActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            LaunchBActivity.this.edit.putBoolean("tag", true);
            LaunchBActivity.this.edit.commit();
            bt_launch_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boo = false;
                    Intent intent = new Intent(LaunchBActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    }

    private void initView() {
        bt_launch_b = (Button) findViewById(R.id.bt_launch_b);
    }
}
