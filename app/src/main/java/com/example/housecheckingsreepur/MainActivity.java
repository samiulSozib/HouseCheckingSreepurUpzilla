package com.example.housecheckingsreepur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //
        setContentView(R.layout.activity_main);

        /////////////////////
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        drawerLayout=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        /////////////////////
    }

    public void goyespur_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    public void amolsar_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    public void sreekol_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    public void sreepur_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    public void dariapur_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }


    public void kadirpara_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    public void sobdalpur_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    public void nakol_union(View view) {
        startActivity(new Intent(MainActivity.this,Code_Check.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setTitle("সতর্ক !");
        alertDialogBuilder.setMessage("আপনি কি বাহির হতে চান ?");

        alertDialogBuilder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("না", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialogBuilder.setNeutralButton("বাতিল", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
        //super.onBackPressed();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int item=menuItem.getItemId();

        if (item==R.id.nav_privacy){
            startActivity(new Intent(getApplicationContext(),Privacy.class));
            finish();
        }


        else if (item==R.id.nav_about_us){
            startActivity(new Intent(getApplicationContext(),About.class));
            finish();
        }
        else if (item==R.id.nav_develop){
            startActivity(new Intent(getApplicationContext(),Developer.class));
            finish();
        }else if (item==R.id.nav_contact_us){
            startActivity(new Intent(getApplicationContext(),Contact.class));
            finish();
        }



        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
