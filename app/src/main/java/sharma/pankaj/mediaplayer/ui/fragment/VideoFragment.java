package sharma.pankaj.mediaplayer.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sharma.pankaj.mediaplayer.adapter.VideoListAdapter;
import sharma.pankaj.mediaplayer.databinding.FragmentVideoBinding;
import sharma.pankaj.mediaplayer.viewmodel.VideoViewModel;

import static sharma.pankaj.mediaplayer.ui.MainActivity.isPermissionAllowed;


public class VideoFragment extends Fragment {

    private FragmentVideoBinding binding;
    private static final String TAG = "VideoFragment";
    private VideoListAdapter adapter;
    public VideoFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_media_list, container, false);
        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        adapter = new VideoListAdapter(requireContext());
        binding.videoRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.videoRecycleView.setAdapter(adapter);
        VideoViewModel model = new ViewModelProvider(requireActivity()).get(VideoViewModel.class);
        model.getVideoList().observe(requireActivity(), videosList -> {
            if (videosList.isEmpty()){
                binding.notFound.setVisibility(View.VISIBLE);
                binding.videoRecycleView.setVisibility(View.GONE);
            }else {
                Log.e(TAG, "onCreateView:  vccvcv " +videosList );
                binding.notFound.setVisibility(View.GONE);
                binding.videoRecycleView.setVisibility(View.VISIBLE);
                adapter.setVideoList(videosList);
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