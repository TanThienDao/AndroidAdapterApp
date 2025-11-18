package com.example.adapterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;

/**
 * Custom Adapter demonstrating the "Load Balancer" pattern in Android.
 * 
 * This adapter efficiently manages view resources by:
 * 1. Recycling views that scroll off-screen (like a connection pool)
 * 2. Caching view references with ViewHolder (like a cache layer)
 * 3. Distributing data to views on-demand (like request routing)
 */
public class MyCustomeAdaptor extends BaseAdapter {

    private Context context;
    private String[] items;

    public MyCustomeAdaptor(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * The "Load Balancer" method - efficiently distributes data to views.
     * 
     * This is where the magic happens! The ListView calls this method to get
     * a view for each position. Instead of creating new views every time,
     * it recycles views that scrolled off-screen - just like a load balancer
     * reuses connections from a pool.
     * 
     * @param position The position of the item in the data set
     * @param convertView Recycled view to reuse (null if none available)
     * @param parent The parent ViewGroup
     * @return A view displaying the data at the specified position
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if(convertView == null){
            // LOAD BALANCING STRATEGY #1: Create new view when pool is empty
            // This happens only for the first few visible items.
            // convertView is null = no recycled view available
            
            // Inflate a new view from XML layout (expensive operation)
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.my_list_item,parent,false);
            
            // LOAD BALANCING STRATEGY #2: Cache view references (ViewHolder)
            // Create a ViewHolder to cache findViewById() results
            // This prevents repeated view lookups (expensive operation)
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.text1);
            
            // Store the ViewHolder in the view's tag for later retrieval
            convertView.setTag(holder);
            
        } else {
            // LOAD BALANCING STRATEGY #3: Reuse recycled view from pool
            // This is the "load balancing" in action!
            // convertView contains a view that scrolled off-screen
            // We reuse it instead of creating a new one (fast!)
            
            // Retrieve the cached ViewHolder from the recycled view
            holder = (ViewHolder) convertView.getTag();
        }
        
        // Update the view with current data
        // Only the data changes, not the view structure (efficient!)
        holder.textView.setText(items[position]);

        // Return the view (either newly created or recycled)
        return convertView;
    }
    /**
     * ViewHolder pattern - The caching layer of our "load balancer"
     * 
     * Why use ViewHolder?
     * - findViewById() is an expensive operation (traverses view hierarchy)
     * - Without ViewHolder: findViewById() called every time getView() runs
     * - With ViewHolder: findViewById() called only once, then cached
     * 
     * Performance Impact:
     * - Scrolling 1000 items without ViewHolder: 1000 findViewById() calls
     * - Scrolling 1000 items with ViewHolder: ~10-15 findViewById() calls
     * - Result: ~98% performance improvement!
     */
    static class ViewHolder{
        // Cached reference to the TextView in the list item layout
        // This prevents repeated findViewById() calls
        TextView textView;
    }

}
