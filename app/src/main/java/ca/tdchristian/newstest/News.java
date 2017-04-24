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
        Log.i(" *** DEBUG ***", "1");
        output = (TextView)findViewById(R.id.textView);
        Log.i(" *** DEBUG ***", "2");
        try {
            doc = new retrieveHTML().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i(" *** DEBUG ***", "3");

        String texts = "";
        Elements titles = doc.select("title");
        Log.i(" *** DEBUG ***", "title size = " + titles.size());
        for (int i = 0; i < titles.size(); i++) {
            Log.i(" *** DEBUG ***", "titles[i] = " + titles.get(i).toString());
            texts += "\n" + titles.get(i).text().toString();
            Log.i(" *** DEBUG ***", "texts = " + texts);
        }
        output.setText(texts);
        texts = "";
        Elements descs = doc.select("description");
        Log.i(" *** DEBUG ***", "desc size = " + descs.size());
        for (int i = 0; i < descs.size(); i++) {
            Log.i(" *** DEBUG ***", "descs[i] = " + descs.get(i).toString());
            texts += "\n" + descs.get(i).text().toString();
            Log.i(" *** DEBUG ***", "descs = " + texts);
        }
        //output.setText(doc.select("b").first().text());
        /*Element content = doc.getElementById("PAGES_CONTAINERinlineContent");
        Elements paragraphs = doc.getElementsByTag("h1");
        String texts = "";
        for (Element i : paragraphs){
            texts.concat("\n" + i.text());
        }
        output.setText(texts);
        if (texts.equals("")){*/
            //output.setText(doc.toString());
        //}


    }
}

class retrieveHTML extends AsyncTask<Void, Void, Document> {
    @Override
    protected Document doInBackground(Void... Void){
        try {
            return Jsoup.connect("http://tdnewstest.blogspot.com/feeds/posts/default?alt=rss").get();
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
