package com.app.test;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

public class FragmentB extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_b, container, false);
		Button myButton = view.findViewById(R.id.fragmentbButton2);
		
		// Set click listener
		myButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Handle button click here
				Toast.makeText(getActivity(), "Sunapur", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
		
	}
	
}
