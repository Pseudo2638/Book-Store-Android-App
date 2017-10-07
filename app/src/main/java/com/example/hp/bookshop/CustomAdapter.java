package com.example.hp.bookshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 7/29/2017.
 */

public class CustomAdapter extends ArrayAdapter<Books> {

    public CustomAdapter(Context context, List<Books> books) {
        super(context,0,books);
    }



    public View getView(int position,View convertView, ViewGroup parent)
    {
        View listView = convertView;
        if (listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.custom_list,parent,false);
        }

        Books currentBooks = getItem(position);

        TextView id = (TextView)listView.findViewById(R.id.book_name);
        id.setText(currentBooks.getBookID());

        TextView author = (TextView)listView.findViewById(R.id.book_author);
        author.setText(currentBooks.getBookAuthor());

        TextView rating = (TextView)listView.findViewById(R.id.book_rating);
        rating.setText(currentBooks.getBookRating());


        TextView rate =(TextView)listView.findViewById(R.id.rate);
        String rateSet = formatRate(currentBooks.getRatingTwo());
        rate.setText(rateSet);

        return  listView;
    }
    private String formatRate(double rate)
    {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(rate);
    }

}
