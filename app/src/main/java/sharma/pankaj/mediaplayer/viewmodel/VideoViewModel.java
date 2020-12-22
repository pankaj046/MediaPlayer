package sharma.pankaj.mediaplayer.viewmodel;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import sharma.pankaj.mediaplayer.AppController;

public class VideoViewModel extends AndroidViewModel {

    private MutableLiveData<List<String>> videoList;
    private static final String TAG = "VideoViewModel";
    Application application;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<String>> getVideoList() {
        if (videoList == null) {
            videoList = new MutableLiveData<List<String>>();
            loadVideos();
        }
        return videoList;
    }

    private void loadVideos() {
        HashSet<String> videoItemHashSet = new HashSet<>();
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME};
        Cursor cursor =  application.getApplicationContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        try {
            cursor.moveToFirst();
            do{
                videoItemHashSet.add((cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
                Log.e(TAG, "loadVideos: " + (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "loadVideos: " +e );
        }
        videoList.setValue(new ArrayList<>(videoItemHashSet));
    }
}
