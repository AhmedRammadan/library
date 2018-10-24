package library.com;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class Details extends AppCompatActivity {
    TextView title, descrpition,nameWriter,dsize,dpage,dcounter_download,dview;
    ImageView image;
    String link;
    String id;
    DatabaseReference counter_download;
    int iview;
    int icounter_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        ActionBar actionBar = getSupportActionBar();

        title = findViewById(R.id.dtitle);
        descrpition = findViewById(R.id.ddescrpition);
        image = findViewById(R.id.dimageView);
        nameWriter = findViewById(R.id.nameWriter);
        dcounter_download = findViewById(R.id.dcounter_download);
        dpage = findViewById(R.id.dpage);
        dsize = findViewById(R.id.dsize);
        dview = findViewById(R.id.dview);

        Intent intent = getIntent();
        String ititle = intent.getStringExtra("iTitle");
        String iDesc = intent.getStringExtra("iDesc");
        id = intent.getStringExtra("iid");
        icounter_download = intent.getIntExtra("icounter_download",0);
        iview = intent.getIntExtra("iview",0);
        String ipage = intent.getStringExtra("ipage");
        link = intent.getStringExtra("ilink");
        String inameWriter = intent.getStringExtra("inameWriter");
        String idsize = intent.getStringExtra("idsize");

        byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);

        //set title for action bar
        actionBar.setTitle(ititle);
        //set data

        title.setText(ititle);
        descrpition.setText(iDesc);
        dpage.setText(ipage);
        nameWriter.setText(inameWriter);
        dsize.setText(idsize);
        image.setImageBitmap(bitmap);


        counter_download= FirebaseDatabase.getInstance().getReference().child("books").child("kids Stories").child(id);

        dcounter_download.setText(""+icounter_download);

        dview.setText(""+iview);
        iview++;
        counter_download.child("view").setValue(iview);


    }

    public void download(View view) {
        icounter_download++;
       // count++;
        counter_download.child("counter_download").setValue(icounter_download);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }
}
