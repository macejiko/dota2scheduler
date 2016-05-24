package com.example.macejiko.dota2scheduler;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macejiko on 2/1/16.
 */

public class GetHTML extends AsyncTaskLoader<List<String>> {

    public GetHTML(Context context) {
        super(context);
    }

    @Override
    public List<String> loadInBackground() {
        Document doc = null;
        Elements links;
        List<String> s = new ArrayList<>();

        try {
            doc = Jsoup.connect("http://wiki.teamliquid.net/dota2/Liquipedia:Upcoming_and_ongoing_matches").get();
            links = doc.select("a[href]");
            for(Element el : links){
                s.add(el.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
