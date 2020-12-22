package sharma.pankaj.mediaplayer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sharma.pankaj.mediaplayer.databinding.VideoListLayoutBinding;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<String> list;

    public VideoListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(VideoListLayoutBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.ViewHolder holder, int position) {
        Log.e("sdsjd", "ssss: " + list.get(position));
        holder.binding.location.setText(list.get(position));
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

    public void setVideoList(List<String> videoList) {
        list = videoList;
        Log.e("sdsjd", "onBindViewHolder: " + videoList);
        notifyDataSetChanged();
    }
}
