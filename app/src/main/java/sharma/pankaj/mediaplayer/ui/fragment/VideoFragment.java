package sharma.pankaj.mediaplayer.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sharma.pankaj.mediaplayer.databinding.FragmentVideoBinding;
import sharma.pankaj.mediaplayer.viewmodel.VideoViewModel;


public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;
    private static final String TAG = "VideoFragment";
    public VideoFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_media_list, container, false);
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        VideoViewModel model = new ViewModelProvider(requireActivity()).get(VideoViewModel.class);
        model.getVideoList().observe(requireActivity(), videosList -> {
            if (videosList.isEmpty()){
                binding.notFound.setVisibility(View.VISIBLE);
                binding.videoRecycleView.setVisibility(View.GONE);
            }else {
                binding.notFound.setVisibility(View.GONE);
                binding.videoRecycleView.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}