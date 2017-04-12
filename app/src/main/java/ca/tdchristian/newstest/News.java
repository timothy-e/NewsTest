package ca.tdchristian.newstest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.io.IOException;

public class News extends AppCompatActivity {

    TextView output;

    protected Document getHTML(){
        try {
            return Jsoup.connect("http://tdchristian.ca/news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        output = (TextView)findViewById(R.id.textView);
        Document doc = getHTML();
        output.setText(doc.title());


    }
}
