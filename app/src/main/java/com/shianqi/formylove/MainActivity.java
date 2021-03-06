package com.shianqi.formylove;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView timeDay;
    private TextView timeHour;
    private TextView timeMinute;
    private TextView timeSecond;
    private TextView timeAll;

    private int intDay;
    private int intHour;
    private int intMinute;
    private int intSecond;
    private int intSecond1;
    private int intTimeAll;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch (msg.what){
                case 1:
                    setTime();
                    handler.sendEmptyMessageDelayed(1,200);
                    break;
                default: break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        timeDay = (TextView)findViewById(R.id.day);
        timeHour = (TextView)findViewById(R.id.hour);
        timeMinute = (TextView)findViewById(R.id.mint);
        timeSecond = (TextView)findViewById(R.id.sec);
        timeAll = (TextView)findViewById(R.id.secs);

        setTime();
        handler.sendEmptyMessage(1);
    }

    private void setTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2016);
        calendar.set(Calendar.MONTH,5);
        calendar.set(Calendar.DAY_OF_MONTH,16);
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        Long nowTime = System.currentTimeMillis();

        intDay = (int)((nowTime-calendar.getTimeInMillis())/(1000 * 60 * 60 * 24));
        intHour = (int)((nowTime-calendar.getTimeInMillis())/(1000 * 60 * 60)-24*intDay);
        intMinute = (int)((nowTime-calendar.getTimeInMillis())/(1000 * 60 )-24*intDay*60-intHour*60);
        intSecond = (int)((nowTime-calendar.getTimeInMillis())/(1000)
                -24*intDay*60*60-intHour*60*60-intMinute*60);

        intTimeAll = (int)((nowTime-calendar.getTimeInMillis())/(1000));

        timeDay.setText(intDay+"");
        timeHour.setText(intHour+"");
        timeMinute.setText(intMinute+"");
        timeSecond.setText(intSecond+"");
        timeAll.setText(intTimeAll+"秒");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this,FaceActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
