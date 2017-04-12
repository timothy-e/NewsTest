package ca.tdchristian.newstest;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Tim on 2017-04-11.
 */

public class getHTML extends AsyncTask<void, void, Document> {
    protected Document doInBackground(){
        try {
            return Jsoup.connect("http://tdchristian.ca/news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(){
        showDialog
    }
}
