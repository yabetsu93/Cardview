package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jabespauya.cardview.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ViewModel.Nature;

/**
 * Created by jabespauya on 10/11/2017 AD.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NatureViewHolder> {

    ArrayList<Nature> mNatureArrayList;
    Context mContext;

    public RecyclerViewAdapter(ArrayList<Nature> natureArrayList, Context context){
        this.mNatureArrayList = natureArrayList;
        this.mContext = context;
    }

    @Override
    public NatureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflate the card view
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item,parent, false);
        //bind the view holder
        NatureViewHolder natureViewHolder = new NatureViewHolder(view);

        //return the view holder
        return natureViewHolder;
    }

    @Override
    public void onBindViewHolder(NatureViewHolder holder, int position) {
        Nature natureList = mNatureArrayList.get(position);
        holder.title.setText(natureList.get_mTitle());
        holder.description.setText(natureList.get_mDescription());

    }


    @Override
    public int getItemCount() {

        int array = 0;

        try{

            if(mNatureArrayList.size() == 0){
                array = 0;
            }else{
               array = mNatureArrayList.size();
            }

        }catch(Exception e){

        }
        return array;
    }

    class NatureViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView mImageView;

        public NatureViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.mTitle);
            description = (TextView) itemView.findViewById(R.id.mDescription);
            mImageView = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }

}

