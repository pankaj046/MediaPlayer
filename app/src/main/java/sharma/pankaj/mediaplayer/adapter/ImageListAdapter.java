package sharma.pankaj.mediaplayer.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.ItemListLayoutBinding;
import sharma.pankaj.mediaplayer.model.ImageModel;
import sharma.pankaj.mediaplayer.ui.fragment.VideoPlayerFragment;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<ImageModel> list;
    private final Context context;

    public ImageListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemListLayoutBinding.inflate(inflater, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ImageListAdapter.ViewHolder holder, int position) {
        Log.e("sdsjd", "ssss: " + list.get(position));
        holder.binding.duration.setVisibility(View.GONE);
        holder.binding.location.setText(list.get(position).getName());
        holder.binding.icon.setImageDrawable(context.getDrawable(R.drawable.ic_image));
        holder.binding.option.setOnClickListener(view -> {
            optionMenu(list.get(position).getPath(), view);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemListLayoutBinding binding;

        public ViewHolder(@NonNull ItemListLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public void setImageList(List<ImageModel> videoList) {
        list = videoList;
        notifyDataSetChanged();
    }

    private void optionMenu(String path, View view) {
        PopupMenu popup = new PopupMenu(context, view);
        popup.getMenu().add("Open");
        popup.getMenu().add("Delete");
        popup.setOnMenuItemClickListener(item -> {
            if (item.getTitle().equals("Open")) {
                Toast.makeText(context, "gh", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(path), "image/*");
                context.startActivity(intent);
            } else {
                // showDialog(path);
            }
            return true;
        });
        popup.show();
    }

    //delete file
    private void deleteFile(String path) {
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

    private void successToast(String msg) {
        Toasty.success(context, msg, Toasty.LENGTH_LONG, true).show();
    }

    private void errorToast(String msg) {
        Toasty.error(context, msg, Toasty.LENGTH_LONG, true).show();
    }

    private void showDialog(String path) {
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        TextView cancel = dialog.findViewById(R.id.cancel);
        TextView ok = dialog.findViewById(R.id.ok);
        cancel.setOnClickListener(v -> dialog.dismiss());
        ok.setOnClickListener(v -> {
            deleteFile(path);
            dialog.dismiss();
        });
        dialog.show();
    }
}
