package sharma.pankaj.mediaplayer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import sharma.pankaj.mediaplayer.ui.fragment.FavoriteFragment;
import sharma.pankaj.mediaplayer.ui.fragment.VideoFragment;
import sharma.pankaj.mediaplayer.ui.fragment.AudioFragment;

public class ViewStateAdapter extends FragmentStateAdapter {

    int tab;
    public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int tab) {
        super(fragmentManager, lifecycle);
        this.tab = tab;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new VideoFragment();
        }else if (position == 1){
            return new AudioFragment();
        }else {
            return new FavoriteFragment();
        }
    }

    @Override
    public int getItemCount() {
        return tab;
    }
}
