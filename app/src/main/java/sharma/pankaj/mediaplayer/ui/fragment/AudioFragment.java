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

import sharma.pankaj.mediaplayer.adapter.AudioListAdapter;
import sharma.pankaj.mediaplayer.adapter.VideoListAdapter;
import sharma.pankaj.mediaplayer.databinding.FragmentAudioBinding;
import sharma.pankaj.mediaplayer.viewmodel.AudioViewModel;
import sharma.pankaj.mediaplayer.viewmodel.VideoViewModel;


public class AudioFragment extends Fragment {

    private FragmentAudioBinding binding;
    private AudioListAdapter adapter;
    private static final String TAG = "AudioFragment";
    public AudioFragment() { }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_user_selection_media, container, false);
        binding = FragmentAudioBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        adapter = new AudioListAdapter(requireContext());
        binding.audioRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.audioRecycleView.setAdapter(adapter);
        AudioViewModel model = new ViewModelProvider(requireActivity()).get(AudioViewModel.class);
        model.getVideoList().observe(requireActivity(), videosList -> {
            if (videosList.isEmpty()){
                binding.notFound.setVisibility(View.VISIBLE);
                binding.audioRecycleView.setVisibility(View.GONE);
            }else {
                Log.e(TAG, "onCreateView:  vccvcv " +videosList );
                binding.notFound.setVisibility(View.GONE);
                binding.audioRecycleView.setVisibility(View.VISIBLE);
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