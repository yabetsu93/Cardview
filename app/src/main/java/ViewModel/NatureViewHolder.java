package ViewModel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jabespauya.cardview.R;

/**
 * Created by jabespauya on 10/11/2017 AD.
 */

public class NatureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title, description;
    public ImageView mImageView;
    public String natureId;

    public ItemClickListener mItemClickListener;

    public NatureViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.mTitle);
        description = itemView.findViewById(R.id.mDescription);
        mImageView = itemView.findViewById(R.id.thumbnail);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        mItemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
