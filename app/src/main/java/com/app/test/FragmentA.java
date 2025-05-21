package com.app.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment {
	
    private RecyclerView recyclerView;
    private ImageViewAdapter adapter;
    private List<String> imageUrls;
	 // Replace with your actual image URLs
	 public FragmentA() {
        // Required empty public constructor
    }
	
	@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize data here that should persist across view re-creation
        imageUrls = new ArrayList<>();
        imageUrls.add("https://raw.githubusercontent.com/debacodex/Photos/refs/heads/main/IMG-20201216-WA0001-01.jpeg");
        imageUrls.add("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgZrpgWZHOnpZHxEwxngNufikg4HGNeikW56rPwKdXecwtyfb2c8IOjKkIBe8HnCV3UIQW4XJcnLgimQaKh2_DLgymXvlI3bSc4JrwiyfSC9SWDokMvyjjlBtnbCaFbC-8fm0hIuysOZ0CrRfmMFlUeysRsLUSzO-AS4SvY7KPb2YA5S0WVN6fzaIVqqkz7/s1280/2023-ul-banner-cards.png");
        imageUrls.add("https://photos.app.goo.gl/kMA2JuGFDp3hy7gV7");
        // Add more URLs
		
		
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_a, container, false);

		
		recyclerView = view.findViewById(R.id.recyclerView);
	//	recyclerView.setLayoutManager(new GridLayoutManager(getContext(),(1)));
		recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
		adapter = new ImageViewAdapter(imageUrls);
		recyclerView.setAdapter(adapter);
		
		return view;
	}
	
	// You might also need to override onResume(), onPause(), etc., depending on your app's needs
	// For Glide specifically, its lifecycle integration usually handles pausing/resuming requests automatically.

}
