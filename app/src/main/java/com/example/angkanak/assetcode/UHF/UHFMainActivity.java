package com.example.angkanak.assetcode.UHF;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.angkanak.assetcode.R;
import com.example.angkanak.assetcode.fragment.UHFKillFragment;
import com.example.angkanak.assetcode.fragment.UHFLockFragment;
import com.example.angkanak.assetcode.fragment.UHFReadFragment;
import com.example.angkanak.assetcode.fragment.UHFReadTagFragment;
import com.example.angkanak.assetcode.fragment.UHFSetFragment;
import com.example.angkanak.assetcode.fragment.UHFWriteFragment;
import com.rscja.utility.StringUtility;

import java.util.HashMap;

public class UHFMainActivity extends BaseTabFragmentActivity {
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uhfmain);

        initSound();
        initUHF();
        initViewPageData();
        initViewPager();
        initTabs();
    }
    @Override
    protected void initViewPageData() {
        lstFrg.add(new UHFReadTagFragment());
//        lstFrg.add(new UHFReadFragment());
//        lstFrg.add(new UHFWriteFragment());
//        lstFrg.add(new UHFKillFragment());
//        lstFrg.add(new UHFLockFragment());
//        lstFrg.add(new UHFSetFragment());

        lstTitles.add(getString(R.string.uhf_msg_tab_scan));
        lstTitles.add(getString(R.string.uhf_msg_tab_read));
        lstTitles.add(getString(R.string.uhf_msg_tab_write));
        lstTitles.add(getString(R.string.uhf_msg_tab_kill));
        lstTitles.add(getString(R.string.uhf_msg_tab_lock));
        lstTitles.add(getString(R.string.uhf_msg_tab_set));
    }


    @Override
    protected void onDestroy() {
        if (mReader != null) {
            mReader.free();
        }
        super.onDestroy();
    }

    public class InitTask extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog mypDialog;

        @Override
        protected Boolean doInBackground(String... params) {
//             TODO Auto-generated method stub
            return mReader.init();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            mypDialog.cancel();

            if (!result) {
                Toast.makeText(UHFMainActivity.this, "init fail",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
//             TODO Auto-generated method stub
            super.onPreExecute();

            mypDialog = new ProgressDialog(UHFMainActivity.this);
            mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mypDialog.setMessage("init...");
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }

    }


    public boolean vailHexInput(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        if (str.length() % 2 == 0) {
            return StringUtility.isHexNumberRex(str);
        }

        return false;
    }

    HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();

    private SoundPool soundPool;
    private float volumnRatio;
    private AudioManager am;
    private void initSound(){
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        soundMap.put(1, soundPool.load(this, R.raw.barcodebeep, 1));
        soundMap.put(2, soundPool.load(this, R.raw.serror, 1));
        am = (AudioManager) this.getSystemService(AUDIO_SERVICE);// Instantiate an AudioManager object
    }

    public void playSound(int id) {

        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // Returns the maximum volume value of the current AudioManager object
        float audioCurrentVolumn = am.getStreamVolume(AudioManager.STREAM_MUSIC);// Returns the volume value of the current AudioManager object
        volumnRatio = audioCurrentVolumn / audioMaxVolumn;
        try {
            soundPool.play(soundMap.get(id), volumnRatio, // Left channel volume
                    volumnRatio, // Right channel volume
                    1, // priority，0 is the lowest
                    0, // Cycles，0 no loop，-1 no loop forever
                    1 // Playback speed ，The value is Between 0.5 and 2.0，1 is normal speed
            );
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
