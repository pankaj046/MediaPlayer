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
import java.util.List;
import java.util.concurrent.TimeUnit;

import sharma.pankaj.mediaplayer.model.AudioModel;
import sharma.pankaj.mediaplayer.model.ImageModel;

public class ImageViewModel extends AndroidViewModel {

    private MutableLiveData<List<ImageModel>> imageModel;
    private static final String TAG = "ImageViewModel";
    Application application;

    public ImageViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<ImageModel>> getAudioList() {
        if (imageModel == null) {
            imageModel = new MutableLiveData<>();
            loadImages();
        }
        return imageModel;
    }

    private void loadImages() {
        List<ImageModel> list = new ArrayList<>();
        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor =  application.getApplicationContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        String name, path;
        try {
            cursor.moveToFirst();
            do{
                path = (cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
                name = getName(path);
                list.add(new ImageModel(name, path));
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "loadImages: " + e);
        }
        imageModel.setValue(list);
    }

    //Extract name from file path
    private String getName(String path) {
        String[] loc = path.split("/");
        String[] nameWithExtension = loc[loc.length - 1].split("\\.");
        return nameWithExtension[0];
    }
}
