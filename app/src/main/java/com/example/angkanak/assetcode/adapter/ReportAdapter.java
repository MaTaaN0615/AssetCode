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

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder>{
    private Context mContext;
    private Cursor mCursor;
    private AdapterView.OnItemClickListener mListener;

    public ReportAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder{
        public TextView Tagnumber;
        public TextView Description;
        public TextView Thisasset;
        public TextView Inarea;

        public ReportViewHolder(View itemView, final AdapterView.OnItemClickListener listener) {
            super(itemView);

            Tagnumber = itemView.findViewById(R.id.txtTagnumber_itemReport);
            Description = itemView.findViewById(R.id.txtDescription_itemReport);
            Thisasset = itemView.findViewById(R.id.txtAsset_itemreport);
            Inarea = itemView.findViewById(R.id.txtInarea_itemreport);
        }
    }

    @NonNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.itemforreport, viewGroup, false);
        return new ReportAdapter.ReportViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder reportViewHolder, int i) {
        if (!mCursor.moveToPosition(i)){
            return;
        }
        String tagnumber = mCursor.getString(0);
        String description =  mCursor.getString(1);
        String thisasset =  mCursor.getString(2);
        String inarea =  mCursor.getString(3);

        reportViewHolder.Tagnumber.setText(tagnumber);
        reportViewHolder.Description.setText(description);
        reportViewHolder.Thisasset.setText(thisasset);
        reportViewHolder.Inarea.setText(inarea);
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
