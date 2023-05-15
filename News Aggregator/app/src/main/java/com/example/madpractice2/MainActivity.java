package com.example.madpractice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String TAG="GG";
    private Menu menu;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private TextView textView;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] items = {""};
    TextView t;
    TextView menuItem;
    ViewPager2 viewPager2;

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> cat = new ArrayList<>();
    ArrayList<Arcticledata> art = new ArrayList<>();
    ArrayList<String> newid = new ArrayList<>();
    ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewerpage);
        Log.d(TAG, "onCreate: "+art.toString());
        mAdapter= new ArticleAdapter(this, art);
        viewPager2.setAdapter(mAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        //NewsLoaderVolley.getSourceData(this);
        //Toast.makeText(this, name.toString(), Toast.LENGTH_SHORT).show();
        NewsLoaderVolley.getSourceData(this);
        mDrawerLayout = findViewById(R.id.Drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        mDrawerList.setOnItemClickListener(
                (parent, view, position, id) -> selectItem(position)
        );
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.drawer_open,R.string.drawer_close);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        setTitle("News Gateway");
    }


    public void setname(ArrayList<String> name,ArrayList<String> id,ArrayList<String> cat)
    {
        //Log.d(TAG, "setname: "+name.toString());
        //t.setText(id.toString());
        this.name = name;
        this.id = id;
        this.cat = cat;
        makeMenu();
        setTitle("News Gateway"+" "+String.valueOf(id.size()));
       // t.setText(this.id.toString());
        mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                R.layout.drawer_list_menu, name));
        viewPager2.setAdapter(mAdapter);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if(item.getTitle()=="All") {
            newid.clear();
            newid.addAll(id);
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name));
            setTitle("News Gateway"+" "+String.valueOf(name.size()));
        }
        else if(item.getTitle().equals("general"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("general"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
            //Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        else if(item.getTitle().equals("business"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("business"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
            //Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            Log.d(TAG, "onOptionsItemSelected: "+String.valueOf(newid.size()));
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        else if(item.getTitle().equals("technology"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("technology"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
            //Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        else if(item.getTitle().equals("sports"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("sports"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
           // Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        else if(item.getTitle().equals("entertainment"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("entertainment"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
            //Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        else if(item.getTitle().equals("health"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("health"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
            //Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        else if(item.getTitle().equals("science"))
        {
            ArrayList<String> name2 = new ArrayList<>();
            newid.clear();
            for(int i=0;i<cat.size();i++)
            {
                if(cat.get(i).equals("science"))
                {
                    name2.add(name.get(i));
                    newid.add(id.get(i));
                }
            }
            //newid.addAll(name2);
            Log.d(TAG, "onOptionsItemSelected: "+newid.toString());
            //Toast.makeText(this, name2.toString(), Toast.LENGTH_SHORT).show();
            mDrawerList.setAdapter(new ArrayAdapter<>(this,   // <== Important!
                    R.layout.drawer_list_menu, name2));
            setTitle("News Gateway"+" "+String.valueOf(name2.size()));
        }
        return true;
    }
    private void selectItem(int position) {
       // Log.d(TAG, "selectItem: ");
        if(newid.size()==0) {setTitle(name.get(position));}
        if(newid.size()!=0) {setTitle(newid.get(position)); }
        else {}
        if(newid.size()!=0){NewsLoaderVolley.getSourceData2(this,newid.get(position));}
        else NewsLoaderVolley.getSourceData2(this,id.get(position));
    }
    public void settext(String s)
    {
        t.setText(s);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }
    public void makeMenu() {
        menu.clear();
        int subMenuCount = 3;
        int menuItemCount = 3;
        ArrayList<String> cat2 = new ArrayList<>();
        for(int i=0;i<name.size();i++)
        {
            if(cat2.contains(cat.get(i)))
            {
                continue;
            }
            else {
                cat2.add(cat.get(i));
            }
        }
        menu.addSubMenu("All");
        for (int i = 0; i < cat2.size(); i++) {
            menu.addSubMenu(cat2.get(i));
        }
    }
    public void setartdata(ArrayList<Arcticledata> a)
    {
        art.clear();
        art.addAll(a);
        //mAdapter= new ArticleAdapter(this, art);
       // Log.d(TAG, "setartdata: "+art.toString());
        mAdapter.notifyItemRangeChanged(0,art.size());
    }

}