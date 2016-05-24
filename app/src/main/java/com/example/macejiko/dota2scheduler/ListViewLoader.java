package com.example.macejiko.dota2scheduler;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

//    tools:context="com.example.macejiko.dota2scheduler.ListViewLoader">

public class ListViewLoader extends ListActivity implements AsyncResponse {

    GetHTML asyncTask = new GetHTML();
    Elements links;
    String s[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_list_view_loader);

        asyncTask.delegate = this;
        URL url = null;
        asyncTask.execute(url);

        //String[] toppings = {"Cheese", "Pepperoni", "Black Olives", "хуй"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, s);

        setListAdapter(adapter);

    }

    @Override
    public void processFinish(Document output){
        links = output.select("a[href]");
        int i=0;
        s = new String[links.size()];
        for(Element el : links){
            s[i++] = el.toString();
        }
    }

}
