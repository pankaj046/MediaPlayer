package sharma.pankaj.mediaplayer.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.dmoral.toasty.Toasty;
import sharma.pankaj.mediaplayer.R;
import sharma.pankaj.mediaplayer.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public FavoriteFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_favorite, container, false);
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.bottomNav.getMenu().add(Menu.NONE, 1, Menu.NONE, "Video").setIcon(R.drawable.ic_fav_video_24);
        binding.bottomNav.getMenu().add(Menu.NONE, 2, Menu.NONE, "Audio").setIcon(R.drawable.ic_fav_audio_24);
        changeFragment(new FavVideoFragment());
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            if (item.getTitle().equals("Video")){
                changeFragment(new FavVideoFragment());
            }else if (item.getTitle().equals("Audio")){
                changeFragment(new FavAudioFragment());
            }else {
                Toasty.error(requireContext(), "Error", Toasty.LENGTH_SHORT).show();
            }
            return true;
        });

        return view;
    }
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.favContainer, fragment).commit();
    }
}