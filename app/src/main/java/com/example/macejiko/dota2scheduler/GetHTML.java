package com.example.macejiko.dota2scheduler;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * Created by macejiko on 2/1/16.
 */

public class GetHTML extends AsyncTask<URL, Integer, Document> {

    public AsyncResponse delegate = null;

    protected Document doInBackground(URL... urls) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://wiki.teamliquid.net/dota2/Liquipedia:Upcoming_and_ongoing_matches").get();
            //textView.setText("Response: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    protected void onProgressUpdate(Integer... progress) {
        //do
    }

    @Override
    protected void onPostExecute(Document result) {
        delegate.processFinish(result);
    }

}
