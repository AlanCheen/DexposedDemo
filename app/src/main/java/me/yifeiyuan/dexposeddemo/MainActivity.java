package me.yifeiyuan.dexposeddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodReplacement;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    static {
        // load xposed lib for hook.
        try {
            if (android.os.Build.VERSION.SDK_INT == 22){
                System.loadLibrary("dexposed_l51");
            } else if (android.os.Build.VERSION.SDK_INT > 19 && android.os.Build.VERSION.SDK_INT <= 21){
                System.loadLibrary("dexposed_l");
            } else if (android.os.Build.VERSION.SDK_INT > 14){
                System.loadLibrary("dexposed");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    TextView tvEnable;
    TextView tvBefore;
    TextView tvAfter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvEnable = (TextView) findViewById(R.id.enable);
        tvBefore = (TextView) findViewById(R.id.before);
        tvAfter = (TextView) findViewById(R.id.after);

        boolean enable = DexposedBridge.canDexposed(this);

        tvEnable.setText("Dexposed 是否能开启：" + enable);


        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        String deviceId = tm.getDeviceId();

        tvBefore.setText("Dexposed之前 IMEI："+deviceId);


        if (enable) {
            DexposedBridge.findAndHookMethod(TelephonyManager.class, "getDeviceId", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d(TAG, "replaceHookedMethod() called with: param = [" + param + "]");
                    return "233232424";
                }
            });

            String hooked = tm.getDeviceId();

            tvAfter.setText("Dexposed之后 IMEI："+hooked);

        }
    }
}
