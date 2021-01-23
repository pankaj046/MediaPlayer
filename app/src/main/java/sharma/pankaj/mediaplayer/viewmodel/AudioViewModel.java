package sharma.pankaj.mediaplayer.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import sharma.pankaj.mediaplayer.model.AudioModel;
import sharma.pankaj.mediaplayer.model.VideoModel;

public class AudioViewModel extends AndroidViewModel {

    private MutableLiveData<List<AudioModel>> AudioList;
    private static final String TAG = "AudioViewModel";
    Application application;

    public AudioViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<AudioModel>> getAudioList() {
        if (AudioList == null) {
            AudioList = new MutableLiveData<>();
            loadAudios();
        }
        return AudioList;
    }

    private void loadAudios() {
        List<AudioModel> list = new ArrayList<>();
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.Media.DISPLAY_NAME};
        Cursor cursor =  application.getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        String name, path, duration;
        try {
            cursor.moveToFirst();
            do{
                path = (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                name = getName(path);
                duration = getDuration(path);
                list.add(new AudioModel(name, duration, path));
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "loadAudios: " +e );
        }
        AudioList.postValue(list);
    }

    //Extract name from file path
    private String getName(String path) {
        String[] loc = path.split("/");
        String[] nameWithExtension = loc[loc.length - 1].split("\\.");
        return nameWithExtension[0];
    }

    //calculate duration of Audio
    private String getDuration(String path){
        String result = "";
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(application.getApplicationContext(), Uri.fromFile(new File(path)));
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            String ss = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
            Log.e("RRRRR", "getDuration: " + ss);
            long timeInMillisec = Long.parseLong(time);
            retriever.release();
            @SuppressLint("DefaultLocale")
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeInMillisec),
                    TimeUnit.MILLISECONDS.toMinutes(timeInMillisec) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInMillisec)),
                    TimeUnit.MILLISECONDS.toSeconds(timeInMillisec) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMillisec)));
            String[] duration = hms.split(":");

            if (duration[0].equalsIgnoreCase("00") || duration[0].equalsIgnoreCase("0")) {
                result = duration[1] + ":" + duration[2];
            } else {
                result = hms;
            }
        }catch (Exception e){
            result = "00:00";
            return result;
        }

        return result;
    }
}
