package com.eiconix.sportchampsmemes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.eiconix.sportchampsmemes.R;
import com.eiconix.sportchampsmemes.model.Meme;

import java.util.List;

public class MemesListAdapter extends BaseAdapter {

    private Context context;
    private List<Meme> arrayList;

    public MemesListAdapter(List<Meme> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView textViewId;
        TextView textViewName;
        CheckBox checkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_meme,null);
            holder.textViewId = (TextView) convertView.findViewById(R.id.tvId);
            holder.textViewName = (TextView) convertView.findViewById(R.id.tvName);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        final Meme meme = (Meme) arrayList.get(position);
        holder.textViewId.setText(meme.getId());
        holder.textViewName.setText(meme.getName());
        holder.checkBox.setChecked(meme.isSelected());

        holder.checkBox.setOnClickListener(view -> {
            final boolean isChecked = holder.checkBox.isChecked();
            meme.setSelected(isChecked);
        });

        return convertView;
    }
}


