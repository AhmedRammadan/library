package library.com;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AlertDialog.Builder builder;
    RecyclerView reckids,reclearn,recPsychology_and_self_development;
    ArrayList<Items_Book> bookskids,booksLearn,booksPsychology_and_self_development;
    Adapter_rec adapter_kids,adapter_learn,adapter_Psychology_and_self_development;
    DatabaseReference refkids,reflearn,refPsychology_and_self_development;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Recycler_kids();
        Recycler_learn();
        Recycler_Psychology_and_self_development();





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setbook();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setbook() {
        builder= new AlertDialog.Builder(this);
        final View dialogShow= getLayoutInflater().inflate(R.layout.add_note,null);
        builder.setView(dialogShow);
        builder.create();
        builder.show();
        final EditText et_title=dialogShow.findViewById(R.id.title);
        final EditText descbook=dialogShow.findViewById(R.id.descbook);
        final EditText descwriter=dialogShow.findViewById(R.id.descwriter);
        final EditText namewriter=dialogShow.findViewById(R.id.namewriter);
        final EditText image=dialogShow.findViewById(R.id.image);
        final EditText size=dialogShow.findViewById(R.id.size);
        final EditText page=dialogShow.findViewById(R.id.page);
        final EditText link=dialogShow.findViewById(R.id.Link);
        final EditText category=dialogShow.findViewById(R.id.category);
        Button add=dialogShow.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id =refPsychology_and_self_development.push().getKey();
                Items_Book note = new Items_Book(id,et_title.getText().toString(),category.getText().toString(),0,0,
                        size.getText().toString(),page.getText().toString(),image.getText().toString(),
                        descbook.getText().toString(),descwriter.getText().toString(),link.getText().toString(),
                        namewriter.getText().toString());
                refPsychology_and_self_development.child(id).setValue(note);
            }
        });
    }
    void Recycler_kids(){
        reckids =findViewById(R.id.rec_kids);
        reckids.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));

        bookskids = new ArrayList<>();

           refkids = FirebaseDatabase.getInstance().getReference().child("books").child("kids Stories");
           refkids.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   bookskids.clear();
                   for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                       Items_Book book = dataSnapshot1.getValue(Items_Book.class);
                       bookskids.add(book);
                   }

                   adapter_kids = new Adapter_rec(MainActivity.this, bookskids);
                   reckids.setAdapter(adapter_kids);
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

                   Toast.makeText(MainActivity.this, "opsssssss Sorry", Toast.LENGTH_SHORT).show();
               }
           });
    }
    void Recycler_learn(){
        reclearn =findViewById(R.id.rec_learn);
        reclearn.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));

        booksLearn = new ArrayList<>();

        reflearn = FirebaseDatabase.getInstance().getReference().child("books").child("learn");
        reflearn.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                booksLearn.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Items_Book book = dataSnapshot1.getValue(Items_Book.class);
                    booksLearn.add(book);
                }

                adapter_learn = new Adapter_rec(MainActivity.this, booksLearn);
                reclearn.setAdapter(adapter_learn);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "opsssssss Sorry", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void Recycler_Psychology_and_self_development(){
        recPsychology_and_self_development =findViewById(R.id.rec_Psychology_and_self_development);
        recPsychology_and_self_development.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        booksPsychology_and_self_development = new ArrayList<>();

        refPsychology_and_self_development = FirebaseDatabase.getInstance().getReference().child("books").child("Psychology and self development");
        refPsychology_and_self_development.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                booksPsychology_and_self_development.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Items_Book book = dataSnapshot1.getValue(Items_Book.class);
                    booksPsychology_and_self_development.add(book);
                }

                adapter_Psychology_and_self_development = new Adapter_rec(MainActivity.this, booksPsychology_and_self_development);
                recPsychology_and_self_development.setAdapter(adapter_Psychology_and_self_development);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "opsssssss Sorry", Toast.LENGTH_SHORT).show();
            }
        });
    }

}





