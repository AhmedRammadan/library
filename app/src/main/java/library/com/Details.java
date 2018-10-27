package library.com;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
    TextView title, descrpition, nameWriter, dsize, dpage, dcounter_download, dview,dcategory;
    ImageView image;
    String link;
    String id;
    DatabaseReference counter_download;
    int iview;
    int icounter_download;
    String ititle;
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
    dcategory = findViewById(R.id.category);

    Intent intent = getIntent();
    ititle = intent.getStringExtra("iTitle");
    String iDesc = intent.getStringExtra("iDesc");
    id = intent.getStringExtra("iid");
    icounter_download = intent.getIntExtra("icounter_download", 0);
    iview = intent.getIntExtra("iview", 0);
    String ipage = intent.getStringExtra("ipage");
    String icategory = intent.getStringExtra("icategory");
    link = intent.getStringExtra("ilink");
    String inameWriter = intent.getStringExtra("inameWriter");
    String idsize = intent.getStringExtra("idsize");
try {
    byte[] mBytes = getIntent().getByteArrayExtra("iImage");
    Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);
    image.setImageBitmap(bitmap);
}catch (Exception e){

}
    //set title for action bar
    actionBar.setTitle(ititle);
    //set data

    title.setText(ititle);
    descrpition.setText(iDesc);
    dpage.setText(ipage);
    nameWriter.setText(inameWriter);
    dsize.setText(idsize);
    dcategory.setText(icategory);

    Toast.makeText(this, ""+icategory, Toast.LENGTH_SHORT).show();
    counter_download = FirebaseDatabase.getInstance().getReference().child("books").child(icategory).child(id);

    dcounter_download.setText("" + icounter_download);

    dview.setText("" + iview);
    iview++;
    counter_download.child("view").setValue(iview);

}

    public void download(View view) {
       AlertDialog();
    }

    void AlertDialog(){
        String yes =getResources().getString(R.string.yes);
        String no =getResources().getString(R.string.no);
        String message =getResources().getString(R.string.youNeedDownload);
        new AlertDialog.Builder(this)
                .setTitle(ititle)
                .setIcon(R.drawable.download)
                .setMessage(message+" "+ititle)
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        icounter_download++;
                        counter_download.child("counter_download").setValue(icounter_download);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(link));
                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        request.setTitle(ititle);
                        request.allowScanningByMediaScanner();
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        downloadManager.enqueue(request);
                    }
                })
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
}

