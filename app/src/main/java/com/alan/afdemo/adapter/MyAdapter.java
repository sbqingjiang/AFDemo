package com.alan.afdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alan.afdemo.R;
import com.alan.afdemo.activity.WebActivity;
import com.alan.afdemo.module.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yinqingjiang on 8/10/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ProductItem> list;
    Context mContext;

    public MyAdapter(ArrayList<ProductItem> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductItem productItem = list.get(position);
        holder.title.setText(productItem.getTitle());
        //check each item, if empty invisible
        if(productItem.getTopDescription().equals(""))
        {
            holder.topDescription.setVisibility(View.GONE);
        }
        else
        {
            holder.topDescription.setVisibility(View.VISIBLE);
            holder.topDescription.setText(productItem.getTopDescription());
        }

        if(productItem.getPromoMessage().equals(""))
        {
            holder.promoMessage.setVisibility(View.GONE);
        }
        else
        {
            holder.promoMessage.setVisibility(View.VISIBLE);
            holder.promoMessage.setText(productItem.getPromoMessage());
        }

        if(productItem.getBottomDescription().equals("") )
        {
            holder.bottomDescription.setVisibility(View.GONE);
        }
        else
        {
            holder.bottomDescription.setVisibility(View.VISIBLE);
            holder.bottomDescription.setText(Html.fromHtml(productItem.getBottomDescription()));
        }

        if(productItem.getSelectButton_one().equals(""))
        {
            holder.selectButton1.setVisibility(View.GONE);
        }
        else{
            holder.selectButton1.setVisibility(View.VISIBLE);
            holder.selectButton1.setText(productItem.getSelectButton_one());
            holder.butoonLink1 = productItem.getButton_one_link();
        }
        if(productItem.getGetSelectButton_two().equals(""))
        {
            holder.selectButton2.setVisibility(View.GONE);
        }
        else {
            holder.selectButton2.setVisibility(View.VISIBLE);
            holder.selectButton2.setText(productItem.getGetSelectButton_two());
            holder.buttonLink2 = productItem.getButton_two_link();
        }
        Picasso.with(this.mContext).load(productItem.getBackgroundImage()).into(holder.backgroundImage);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView backgroundImage;
        public TextView topDescription, title, promoMessage, bottomDescription, selectButton1, selectButton2;
        String butoonLink1, buttonLink2;

        public ViewHolder(View itemView) {
            super(itemView);
            backgroundImage = (ImageView) itemView.findViewById(R.id.backgroundImage);
            topDescription = (TextView) itemView.findViewById(R.id.topDescription);
            title = (TextView) itemView.findViewById(R.id.title);
            promoMessage = (TextView) itemView.findViewById(R.id.promoMessage);
            bottomDescription = (TextView) itemView.findViewById(R.id.bottomDescription);
            selectButton1 = (TextView) itemView.findViewById(R.id.select_button_a);
            selectButton2 = (TextView) itemView.findViewById(R.id.select_button_b);

            selectButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("webView", butoonLink1);
                    mContext.startActivity(intent);
                }
            });

            selectButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("webView", buttonLink2);
                    mContext.startActivity(intent);
                }
            });

            bottomDescription.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
