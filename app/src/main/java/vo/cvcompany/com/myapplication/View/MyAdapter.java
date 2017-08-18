package vo.cvcompany.com.myapplication.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vo.cvcompany.com.myapplication.Module.MyYoutube;
import vo.cvcompany.com.myapplication.Presenter.Communicator;
import vo.cvcompany.com.myapplication.R;

/**
 * Created by kh on 8/17/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {

    private static final String TAG = MyAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<MyYoutube> arrayList;
    Communicator communicator;

    public MyAdapter(Context context, ArrayList<MyYoutube> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        communicator = (Communicator) context;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent, false));
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        MyYoutube myYoutube = arrayList.get(position);
        holder.txtTitle.setText(myYoutube.getTitle());
        Picasso.with(context).load(myYoutube.getUrl()).placeholder(R.mipmap.ic_launcher).into(holder.img);
    }


    @Override
    public int getItemCount() {
        return (arrayList==null)?0:arrayList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.imgPicture)
        ImageView img;
        @BindView(R.id.txtTitle)
        TextView txtTitle;

        public MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            img.setOnClickListener(this);
            txtTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            communicator.startVideo(arrayList.get(getAdapterPosition()).getApi());
            Log.i(TAG, "onClick: "+arrayList.get(getAdapterPosition()).getApi());
        }


    }
}
