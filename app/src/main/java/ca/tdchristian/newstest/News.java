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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        output = (TextView)findViewById(R.id.textView);

        try {
            Document doc = Jsoup.connect("http://tdchristian.ca/news").get();
            Log.i("Debug", "Connected");
            output.setText(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
