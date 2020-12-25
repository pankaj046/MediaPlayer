package sharma.pankaj.mediaplayer.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.FragmentFavAudioBinding;

public class FavAudioFragment extends Fragment {

    FragmentFavAudioBinding binding;

    public FavAudioFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_fav_audio, container, false);
        binding = FragmentFavAudioBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}