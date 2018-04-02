package com.buid.game.nodie.hitonhead;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GVMainAdapter extends BaseAdapter {

    private Context context;
    private int[] data;

    public GVMainAdapter(Context context, int[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.gv_main_layout, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        if (data[i] == 1) {
            imageView.setImageResource(R.drawable.ic_bunny);
        } else {
            imageView.setImageResource(R.drawable.ic_grass);
        }
        return view;
    }
}
