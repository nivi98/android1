package com.example.nivi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
        private ArrayList<String> myImageList=new ArrayList<>();
        private ArrayList<String> myText1=new ArrayList<>();
        private ArrayList<String> myText2=new ArrayList<>();
        private Context mContext;

    public RecyclerViewAdapter(Context context,ArrayList<String> myImageList,ArrayList<String> myText1,ArrayList<String> myText2) {
        this.myImageList=myImageList;
        this.myText1 = myText1;
        this.myText2=myText2;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mylayout,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(mContext)
                .asBitmap()
                .load(myImageList.get(i))
                .into(viewHolder.image);
        viewHolder.text1.setText(myText1.get(i));
        viewHolder.text2.setText(myText2.get(i));
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,myText1.get(i).toUpperCase(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView text1,text2;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout=itemView.findViewById(R.id.myviewlayout);
            image=itemView.findViewById(R.id.cimage);
            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
        }
    }
}
