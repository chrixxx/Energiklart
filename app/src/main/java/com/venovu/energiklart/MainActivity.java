package com.venovu.energiklart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;
import menu.BlankFragment;
import menu.BlankFragment2;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new BlankFragment());
        fragmentTransaction.commit();


        //Intent passed from Login_activity to get the user name and
        // and display it in the navigation drawer header
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String headerUser = extras.getString("headerUser");

            NavigationView navigationViewTest = (NavigationView) findViewById(R.id.nav_view);
            View headerViewTest = navigationViewTest.getHeaderView(0);
            TextView userName_header = (TextView)headerViewTest.findViewById(R.id.nav_header_user);
            userName_header.setText(headerUser);
            navigationViewTest.getMenu().findItem(R.id.nav_send).setVisible(true);
        }


        //Floating Action Button to start an email client
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:abc@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Test App");
                intent.putExtra(Intent.EXTRA_TEXT, "Email Body");
                startActivity(intent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Clickable avatar(Circular ImageView) in the NavigationDrawer header
        View headerView = navigationView.getHeaderView(0);
        CircleImageView userImage = (CircleImageView)headerView.findViewById(R.id.userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login_activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                killActivity();

                //Toast.makeText(MainActivity.this, "Test test", Toast.LENGTH_LONG).show();
            }
        });


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

        //appbar login
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, Login_activity.class);
            startActivity(intent);
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
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new BlankFragment());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_gallery) {

            Intent intent = new Intent(MainActivity.this, Listview_activity.class);
            startActivity(intent);

            /**
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new BlankFragment());
            fragmentTransaction.commit();
             */


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        //} else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void killActivity(){
        finish();
    }


}
