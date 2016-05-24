package com.example.macejiko.dota2scheduler;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

public class ListViewLoader extends ListActivity implements LoaderManager.LoaderCallbacks<List<String>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new GetHTML(this);
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> list) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item, list);
        this.setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {
        this.setListAdapter(null);
    }

}
