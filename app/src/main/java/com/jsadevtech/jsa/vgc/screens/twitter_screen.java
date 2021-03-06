package com.jsadevtech.jsa.vgc.screens;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.jsadevtech.jsa.vgc.R;
import com.twitter.sdk.android.Twitter;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;


import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.UserTimeline;




/**
 * Created by Ivan on 06/08/2015. screen encargada de controlar la api de twitter
 */
public class twitter_screen extends ListActivity {

    private static final String TWITTER_KEY = "XIvJydx7ZcQovBSAOzMltxxyZ";
    private static final String TWITTER_SECRET = "KuC1QuvOAWOvJYKd2sZIZlbfVAwWWFSZlseBAldbvkeqtyWB67";

   /* List<Long> tweetIds = Arrays.asList(503435417459249153L, 510908133917487104L, 473514864153870337L, 477788140900347904L);
    final  TweetViewFetchAdapter adapter =
            new TweetViewFetchAdapter<CompactTweetView>(twitter_screen.this);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);

        setContentView(R.layout.activity_twitter_screen);
        Button buttonEnviarTweet =(Button) findViewById(R.id.button_new_twit);

        buttonEnviarTweet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               /* startActivity(new Intent(twitter_screen.this, twitter_send_tweet.class));*/
                create_tweet();
            }
        });

        Fabric.with(this, new TwitterCore(authConfig), new TweetUi(), new Twitter(authConfig), new TweetUi());



        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("VGComic")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);



    }

    private void create_tweet(){
        TwitterAuthConfig authConfig =  new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new TweetComposer());
        TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("@VGComic");
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_twitter_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_colaboradores) {
            startActivity(new Intent(twitter_screen.this, informacion.class));
            return true;
        }
        if(id == R.id.action_zonas) {
            startActivity(new Intent(twitter_screen.this, zonas_screen.class));
            return true;
        }
        if(id == R.id.action_about) {
            startActivity(new Intent(twitter_screen.this, about_screen.class));
            return true;
        }
        if(id == R.id.action_principal) {
            startActivity(new Intent(twitter_screen.this, main_screen.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
