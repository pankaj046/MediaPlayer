package sharma.pankaj.mediaplayer.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.AudioListLayoutBinding;
import sharma.pankaj.mediaplayer.databinding.DialogLayoutBinding;
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
        if (list!=null) {
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
        popup.setOnMenuItemClickListener(item -> {
            if (item.getTitle().equals("Play")){

            }else if (item.getTitle().equals("Favorite")){

            }else {
                showDialog(path);
            }
            return true;
        });
        popup.show();
    }

    //delete file
    private void deleteFile(String path){
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                notifyDataSetChanged();
                successToast("File Deleted Successfully");
            } else {
                notifyDataSetChanged();
                errorToast("File not Deleted");
            }
        }
    }

    private void successToast(String msg){
        Toasty.success(context, msg, Toasty.LENGTH_LONG, true).show();
    }
    private void errorToast(String msg){
        Toasty.error(context, msg, Toasty.LENGTH_LONG, true).show();
    }

    private void showDialog(String path){
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView ok = dialog.findViewById(R.id.ok);
        cancel.setOnClickListener(v -> dialog.dismiss());
        ok.setOnClickListener(v -> {deleteFile(path); dialog.dismiss();});
        dialog.show();
    }

}
