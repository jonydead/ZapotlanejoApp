package com.example.luisangel.zapotlanejoapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private Context mContext;
    private List<Menu> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView thumbnail;
        Context context;

        public MyViewHolder(View view) {
            super(view);
            context= view.getContext();
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }

        public void setOnClickListeners() {

            thumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.thumbnail:
                    Intent intent=new Intent(context,Zapotlanejoweb.class);
                    intent.putExtra("title",title.getText());
                    context.startActivity(intent);
                    break;
            }

        }
    }


    public MenuAdapter(Context mContext, List<Menu> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Menu album = albumList.get(position);
        holder.title.setText(album.getName());


        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        holder.setOnClickListeners();

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_menu, popup.getMenu());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}