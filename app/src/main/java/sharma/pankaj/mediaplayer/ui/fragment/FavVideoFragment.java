package sharma.pankaj.mediaplayer.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.FragmentFavVideoBinding;

public class FavVideoFragment extends Fragment {

    FragmentFavVideoBinding binding;

    public FavVideoFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_fav_video, container, false);
        binding = FragmentFavVideoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}