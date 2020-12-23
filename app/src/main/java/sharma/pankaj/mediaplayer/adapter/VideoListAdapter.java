package sharma.pankaj.mediaplayer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.VideoListLayoutBinding;
import sharma.pankaj.mediaplayer.model.VideoModel;
import sharma.pankaj.mediaplayer.viewmodel.VideoViewModel;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<VideoModel> list;
    private final Context context;
    public VideoListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(VideoListLayoutBinding.inflate(inflater, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.ViewHolder holder, int position) {
        Log.e("sdsjd", "ssss: " + list.get(position));
        holder.binding.location.setText(list.get(position).getName());
        holder.binding.duration.setText(list.get(position).getDuration());
        if (list.get(position).getThumbNail()!=null){
            holder.binding.icon.setImageBitmap(list.get(position).getThumbNail());
        }else {
            holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_video_24dp));
        }

        holder.binding.option.setOnClickListener(v -> {
            optionMenu(list.get(position).getPath(), v);
        });

    }

    @Override
    public int getItemCount() {
        if (!list.isEmpty()) {
            return list.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        VideoListLayoutBinding binding;

        public ViewHolder(@NonNull VideoListLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public void setVideoList(List<VideoModel> videoList) {
        list = videoList;
        notifyDataSetChanged();
    }

    private void optionMenu(String path, View view){
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenu().add("Play");
        popup.getMenu().add("Favorite");
        popup.getMenu().add("Delete");
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(
                        context,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
        popup.show();
    }
}
