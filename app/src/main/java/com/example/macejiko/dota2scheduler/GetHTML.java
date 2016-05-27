package com.example.macejiko.dota2scheduler;

import android.content.AsyncTaskLoader;
import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
        Elements left, right, timer;
        List<String> s = new ArrayList<>();
        String matchDate, timeLeft, outputDate = "";

        try {
            doc = Jsoup.connect("http://wiki.teamliquid.net/dota2/Liquipedia:Upcoming_and_ongoing_matches").get();
            left = doc.select("td.team-left>*>span.team-template-text>a, td.team-left>abbr ");
            right = doc.select("td.team-right>*>span.team-template-text>a, td.team-right>abbr");
            timer = doc.select("span.datetime");

            DateFormat siteDateFormat = new SimpleDateFormat("MMMM d, yyyy - HH:mm z", Locale.ENGLISH);
            SimpleDateFormat myDateFormat = new SimpleDateFormat("HH:mm - dd MMMM", Locale.ENGLISH);
            TimeZone tz = TimeZone.getDefault();
            myDateFormat.setTimeZone(tz);

            Date currentDate = new Date();
            
            for(int i = 0; i < left.size(); ++i){
                try {
                    Date date = siteDateFormat.parse(timer.get(i).text());
                    if(currentDate.after(date)){
                        outputDate = "LIVE!";
                    }
                    else{
                        long timeDiff = date.getTime() - currentDate.getTime();
                        timeLeft = String.format("%d h, %d min",
                                TimeUnit.MILLISECONDS.toHours(timeDiff),
                                TimeUnit.MILLISECONDS.toMinutes(timeDiff) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff))
                        );
                        matchDate = myDateFormat.format(date);
                        outputDate = timeLeft + " left\n" + matchDate;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                s.add(left.get(i).text() + " vs " + right.get(i).text() + " --- " + outputDate);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
