package com.example.angkanak.assetcode.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angkanak.assetcode.DatabaseHelper;
import com.example.angkanak.assetcode.R;
import com.example.angkanak.assetcode.ShowDetailAssetActivity;
import com.example.angkanak.assetcode.model.QrDivices;

import java.util.ArrayList;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.AssetViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    private AdapterView.OnItemClickListener mListener;
    ArrayList<QrDivices> divices = new ArrayList<>();

    public AssetAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class AssetViewHolder extends RecyclerView.ViewHolder{
        public TextView numberText;
        public TextView idText;
        public TextView costcenterText;
        public ImageView mDetail;

        public AssetViewHolder(View itemView, final AdapterView.OnItemClickListener listener) {
            super(itemView);

            idText = itemView.findViewById(R.id.txtId_item);
            numberText = itemView.findViewById(R.id.txtTagNumber_item);
            costcenterText = itemView.findViewById(R.id.txtCostCenter_item);
            mDetail = itemView.findViewById(R.id.image_detail);

            mDetail.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int position = getAdapterPosition();
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        Intent intent = new Intent(mContext, ShowDetailAssetActivity.class);
                        mCursor.moveToPosition(position);
                        String tagnumberClick = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_TAG_NUMBER));
                        intent.putExtra("tagClick", tagnumberClick);
                        mContext.startActivity(intent);

//                        Toast.makeText(mContext, "Click here" + position , Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    public AssetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item, viewGroup, false);
        return new AssetViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetViewHolder assetViewHolder, int position) {

        if (!mCursor.moveToPosition(position)){
            return;
        }

        String index = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_index));
        String tagnumber = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_TAG_NUMBER));
        String costcenter = mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.COL_COST_CENTER));

        assetViewHolder.idText.setText(index);
        assetViewHolder.numberText.setText(tagnumber);
        assetViewHolder.costcenterText.setText(costcenter);

    }

    @Override
    public int getItemCount() {
        if(mCursor == null){
            return 0;
        }else {
            return mCursor.getCount();
        }

    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }

}
