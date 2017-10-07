package com.example.hp.bookshop;

import android.app.ProgressDialog;
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

public class RomedyFragment extends Fragment {

    public CustomAdapter customAdapter;
    public String url = "https://www.googleapis.com/books/v1/volumes?q=romedy";
    ProgressDialog progressDialog;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_biography,container,false);
        listView = (ListView)rootView.findViewById(R.id.list_view_bio);
        populate();
        return rootView;
    }
    public void populate()
    {
        customAdapter = new CustomAdapter(getContext(),new ArrayList<Books>());
        listView.setAdapter(customAdapter);
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(url);
    }
    public class BackgroundTask extends AsyncTask<String,Void,List<Books>>
    {

        @Override
        protected List<Books> doInBackground(String... urls) {
            if (urls.length<1 || urls[0]==null) {
                return null;
            }
            List<Books> booksList = QueryUtils.fetchFeatures(urls[0]);
            return  booksList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(getContext(),"Please Wait","Loading");
        }

        @Override
        protected void onPostExecute(List<Books> bookses) {
            super.onPostExecute(bookses);
            customAdapter.clear();
            if (bookses != null && !bookses.isEmpty())
            {
                customAdapter.addAll(bookses);
                progressDialog.dismiss();
            }
        }
    }
}
