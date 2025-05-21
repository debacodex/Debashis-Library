package com.app.test;


// MyAdapter.java
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {
	
	private List<String> imageUrls;
	
	public ImageViewAdapter(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
		.inflate(R.layout.item_image, parent, false); // Create your item layout
		return new ViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		String imageUrl = imageUrls.get(position);
		Glide.with(holder.imageView.getContext())
        .load(imageUrl)
		
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_background)
       
		.listener(new RequestListener<Drawable>() {
			@Override
			public boolean onLoadFailed( GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
				// log exception
				Log.e("TAG", "Error loading image", e);
				return false; // important to return false so the error placeholder can be placed
			} 
			@Override
			public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
				return false;
			}
		})
	.into(holder.imageView);
	
	/*	Glide.with(holder.imageView.getContext())
		.load(imageUrl)
		.into(holder.imageView);
		*/
	} 
	
	@Override
	public int getItemCount() {
		return imageUrls.size();
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView imageView;
		
		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.imageViewItem); 
			// Reference your ImageView in the item layout
			
            // Set click listener for the item
            imageView.setOnClickListener(view -> {
    // Handle the click here
	// Or, start a new activity: 
      //  view.getContext().startActivity(new Intent(view.getContext(), ImageActivity.class));
    Toast.makeText(view.getContext(), "Image clicked!", Toast.LENGTH_SHORT).show();
//	Toast.makeText(view.getContext(), "Image clicked!", 3).show();
}); 

		}
	}
}
