package library.com;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Adapter_rec extends RecyclerView.Adapter<ViewHolder> {
    Context mcontext;
    ArrayList<Items_Book> books;

    Adapter_rec(Context mcontext, ArrayList<Items_Book> books) {
        this.mcontext = mcontext;
        this.books = books;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.book_item,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.title.setText(books.get(i).getTitle());
        viewHolder.size.setText(books.get(i).getSize());
        Picasso.get().load(books.get(i).getIamge()).into(viewHolder.mgbook);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String title= books.get(position).getTitle();
                String desc = books.get(position).getDesc_book();
                String link = books.get(position).getLink();
                String nameWriter = books.get(position).getnamewriter();
                String dsize = books.get(position).getSize();
                String dpage = books.get(position).getPage();
                String id = books.get(position).getId();
                int dcounter_download = books.get(position).getCounter_download();
                int dview = books.get(position).getView();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) viewHolder.mgbook.getDrawable();
                Bitmap bitmap =bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
                byte[] bytes =stream.toByteArray();

                Intent intent =new Intent(mcontext,Details.class);
                intent.putExtra("iTitle",title);
                intent.putExtra("iDesc",desc);
                intent.putExtra("ilink",link);
                intent.putExtra("iid",id);
                intent.putExtra("icounter_download",dcounter_download);
                intent.putExtra("iview",dview);
                intent.putExtra("idsize",dsize);
                intent.putExtra("ipage",dpage);
                intent.putExtra("iImage",bytes);
                intent.putExtra("inameWriter",nameWriter);
                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
