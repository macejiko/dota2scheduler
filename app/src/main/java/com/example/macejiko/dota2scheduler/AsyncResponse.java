package com.example.macejiko.dota2scheduler;

import org.jsoup.nodes.Document;

/**
 * Created by macejiko on 2/1/16.
 */
public interface AsyncResponse {
    void processFinish(Document output);
}
