package ca.tdchristian.newstest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class News extends AppCompatActivity {

    TextView output;
    Document doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        output = (TextView)findViewById(R.id.textView);
        try {
            doc = new retrieveHTML().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Elements allElements = doc.select("b");
        int size = allElements.size();
        /*for (Element i : allElements) {
            output.setText(i + " " + i.text());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            texts.concat("\n" + i.text());
        }*/
        output.setText(size);
        //output.setText(doc.select("b").first().text());
        /*Element content = doc.getElementById("PAGES_CONTAINERinlineContent");
        Elements paragraphs = doc.getElementsByTag("h1");
        String texts = "";
        for (Element i : paragraphs){
            texts.concat("\n" + i.text());
        }
        output.setText(texts);
        if (texts.equals("")){
            output.setText(doc.toString());
        }*/


    }
}

class retrieveHTML extends AsyncTask<Void, Void, Document> {
    @Override
    protected Document doInBackground(Void... Void){
        try {
            return Jsoup.connect("http://splash.tdchristian.ca/classes/math/Hagen/C&V/").get();
            //return Jsoup.connect("http://tdchristian.ca/news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("ERROR:", "IOException e, couldn't connect");
        return null;
    }
    @Override
    protected void onPostExecute(Document doc) {
        super.onPostExecute(doc);
    }
}
