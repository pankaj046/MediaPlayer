package sharma.pankaj.mediaplayer.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public FavoriteFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_favorite, container, false);
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}