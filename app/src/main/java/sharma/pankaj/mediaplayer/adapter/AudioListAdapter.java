package sharma.pankaj.mediaplayer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.AudioListLayoutBinding;
import sharma.pankaj.mediaplayer.databinding.VideoListLayoutBinding;
import sharma.pankaj.mediaplayer.model.AudioModel;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final Context context;
    private List<AudioModel> list;

    public AudioListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AudioListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AudioListLayoutBinding.inflate(inflater, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull AudioListAdapter.ViewHolder holder, int position) {
        Log.e("sdsjd", "ssss: " + list.get(position));
        holder.binding.location.setText(list.get(position).getName());
        holder.binding.duration.setText(list.get(position).getDuration());
        holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_audiotrack_24));
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

        AudioListLayoutBinding binding;

        public ViewHolder(@NonNull AudioListLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public void setVideoList(List<AudioModel> videoList) {
        list = videoList;
        Log.e("sdsjd", "onBindViewHolder: " + videoList);
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
