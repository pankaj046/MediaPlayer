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
import sharma.pankaj.mediaplayer.adapter.ImageListAdapter;
import sharma.pankaj.mediaplayer.databinding.FragmentImageBinding;
import sharma.pankaj.mediaplayer.viewmodel.AudioViewModel;
import sharma.pankaj.mediaplayer.viewmodel.ImageViewModel;

public class ImageFragment extends Fragment {

    private FragmentImageBinding binding;
    private ImageListAdapter adapter;
    private static final String TAG = "ImageFragment";
    public ImageFragment() { }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_image, container, false);
        binding = FragmentImageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        adapter = new ImageListAdapter(requireContext());
        binding.imageRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.imageRecycleView.setAdapter(adapter);
        ImageViewModel model = new ViewModelProvider(requireActivity()).get(ImageViewModel.class);
        model.getImageList().observe(requireActivity(), imageList -> {
            if (imageList.isEmpty()){
                binding.notFound.setVisibility(View.VISIBLE);
                binding.imageRecycleView.setVisibility(View.GONE);
            }else {
                Log.e(TAG, "onCreateView:  sssss " +imageList );
                binding.notFound.setVisibility(View.GONE);
                binding.imageRecycleView.setVisibility(View.VISIBLE);
                adapter.setImageList(imageList);
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