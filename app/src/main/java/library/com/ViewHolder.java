package library.com;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
    TextView title,size,nameWriter,dsize,dcounter_download,dview;
    ImageView mgbook;
    ItemClickListener itemClickListener;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title =itemView.findViewById(R.id.title);
        size =itemView.findViewById(R.id.size);
        mgbook=itemView.findViewById(R.id.mgBook);
        nameWriter=itemView.findViewById(R.id.nameWriter);
        dsize=itemView.findViewById(R.id.dsize);
        dcounter_download=itemView.findViewById(R.id.dcounter_download);
        dview=itemView.findViewById(R.id.dview);
        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
}
