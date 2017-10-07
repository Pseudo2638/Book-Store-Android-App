package com.example.hp.bookshop;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TechFragment extends Fragment {

    private String url = "https://www.googleapis.com/books/v1/volumes?q=technology";
    private CustomAdapter mAdapter;

    ProgressDialog progressDialog;
    private Context context;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_tech, container, false);
        listView = (ListView)rootView.findViewById(R.id.list_view_tech);
        populate();
        return rootView;


    }
    public void populate()
    {

        mAdapter = new CustomAdapter(getContext(),new ArrayList<Books>());
        listView.setAdapter(mAdapter);
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(url);
    }

    private class BackgroundTask extends AsyncTask<String,Void,List<Books>>
    {

        @Override
        protected List<Books> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<Books> result = QueryUtils.fetchFeatures(urls[0]);
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getContext(),"Please Wait","Loading");
        }

        @Override
        protected void onPostExecute(List<Books> bookses)
        {
            mAdapter.clear();
         if (bookses != null && !bookses.isEmpty())
         {
             mAdapter.addAll(bookses);
             progressDialog.dismiss();
         }
        }
    }



    }
