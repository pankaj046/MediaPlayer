package sharma.pankaj.mediaplayer.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import sharma.pankaj.mediaplayer.AppController;
import sharma.pankaj.mediaplayer.model.VideoModel;

public class VideoViewModel extends AndroidViewModel {

    private MutableLiveData<List<VideoModel>> videoList;
    private static final String TAG = "VideoViewModel";
    Application application;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<VideoModel>> getVideoList() {
        if (videoList == null) {
            videoList = new MutableLiveData<List<VideoModel>>();
            loadVideos();
        }
        return videoList;
    }

    private void loadVideos() {
        List<VideoModel> list = new ArrayList<>();
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME};
        Cursor cursor =  application.getApplicationContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        String name, path, duration;
        Bitmap bitmap = null;
        try {
            cursor.moveToFirst();
            do{
                path = (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                name = getName(path);
                duration = getDuration(path);
                list.add(new VideoModel(name, duration, path, bitmap));

            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "loadVideos: " +e );
        }
        videoList.postValue(list);
    }
    //Extract name from file path
    private String getName(String path){
        String[] loc = path.split("/");
        String[] nameWithExtension = loc[loc.length-1].split("\\.");
        return nameWithExtension[0];
    }
    //calculate duration of video
    private String getDuration(String path){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(application.getApplicationContext(), Uri.fromFile(new File(path)));
        String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        String ss = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
        Log.e("xcxcx", "getDuration: " + ss);
        long timeInMillisec = Long.parseLong(time);
        retriever.release();
        @SuppressLint("DefaultLocale")
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(timeInMillisec),
                TimeUnit.MILLISECONDS.toMinutes(timeInMillisec) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInMillisec)),
                TimeUnit.MILLISECONDS.toSeconds(timeInMillisec) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMillisec)));
        String[] duration = hms.split(":");
        String result = "";
        if (duration[0].equalsIgnoreCase("00") || duration[0].equalsIgnoreCase("0")){
            result = duration[1] +":"+duration[2];
        }else {
            result = hms;
        }
        return result;
    }
}
