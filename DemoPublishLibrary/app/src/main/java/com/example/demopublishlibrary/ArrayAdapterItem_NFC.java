package com.example.demopublishlibrary;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.demopublishlibrary.R;
import java.util.ArrayList;
import java.util.HashMap;

public class ArrayAdapterItem_NFC extends ArrayAdapter<ArrayList<NFCResultData>>
{
    private Context mContext;
    int layoutResourceId;
    private HashMap<String, String> data = null;
    private ArrayList<String> dataType;

    public ArrayAdapterItem_NFC(Context context, int resource, NFCResultData nfcData)
    {
        super(context, resource);
        mContext = context;
        layoutResourceId = resource;
        data = nfcData.getData();

        dataType = nfcData.getType();
    }

    @NonNull @Override public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            // inflate the layout
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        // object item based on the position
        try
        {
            if (position < NFCResultData.NFC_FILED_NAMES.length)
            {
                String mDataType = dataType.get(position);
                String mDataValue = data.get(mDataType);

                TextView TvDataType = (TextView)convertView.findViewById(R.id.DataType);

                TextView TvDataValue = (TextView)convertView.findViewById(R.id.DataValue);
                TvDataType.setText(mDataType);

                TvDataValue.setText(mDataValue);
                TvDataType.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                ViewGroup.LayoutParams params = TvDataType.getLayoutParams();
                params.width = (ViewGroup.LayoutParams.WRAP_CONTENT);
                TvDataType.setLayoutParams(params);
            }
        }
        catch (NullPointerException e)
        {
            TextView TvDataType = (TextView)convertView.findViewById(R.id.DataType);
            TextView TvDataValue = (TextView)convertView.findViewById(R.id.DataValue);
            TvDataType.setText("");
            TvDataValue.setText("");
            Log.e("ArrayAdapterItem ERROR", "HAS NULL");
        }

        return convertView;
    }

    @Override public int getCount()
    {
        return dataType.size();
    }
}
