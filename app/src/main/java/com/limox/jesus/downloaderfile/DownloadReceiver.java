package com.limox.jesus.downloaderfile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by usuario on 16/02/17.
 */

public class DownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int text = R.string.download_file;
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
