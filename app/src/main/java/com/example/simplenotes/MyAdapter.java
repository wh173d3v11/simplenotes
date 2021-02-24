package com.example.simplenotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<String> notes;
    Context context;
    SharedPref sharedPref;
    MainActivity mainActivity;

    public MyAdapter(ArrayList<String> notes,Context mCtx){
        this.context = mCtx;
        this.notes = notes;
        sharedPref = new SharedPref();
        mainActivity =new MainActivity();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_content,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(notes.get(position));
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu menu = new PopupMenu(context,holder.tv);
                menu.inflate(R.menu.notes_options);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deletenotes) {
                            if (notes.size()>0) {
                                notifyDataSetChanged();
                                notes.remove(position);
                                sharedPref.savedata(context, notes);
                                notifyItemRemoved(position);
                            }
                            Toast.makeText(context, "Deleted!!", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                //DISPLAY POP
                menu.show();

            }

        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.contenttext);
        }
    }

}
