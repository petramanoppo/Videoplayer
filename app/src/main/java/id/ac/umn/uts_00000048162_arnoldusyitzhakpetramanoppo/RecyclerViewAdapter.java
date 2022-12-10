package id.ac.umn.uts_00000048162_arnoldusyitzhakpetramanoppo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private LinkedList<ModelVideo> modelVid;
    private final RecyclerInterface recyclerInterface;

    public RecyclerViewAdapter(Context context, LinkedList<ModelVideo> modelVid, RecyclerInterface ri){
        this.context = context;
        this.modelVid = modelVid;
        this.recyclerInterface = ri;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycler_layout, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(v, recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(modelVid.get(position).getVideoName());
        holder.imageView.setImageResource(modelVid.get(position).getLinkPhoto());


    }

    @Override
    public int getItemCount() {
        return modelVid.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvName;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView, RecyclerInterface ri) {
            super(itemView);

            imageView =  itemView.findViewById(R.id.imageLib);
            tvName =  itemView.findViewById(R.id.textName);
            delete = itemView.findViewById(R.id.imageDel);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(ri!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            ri.onItemClick(position);
                        }
                    }

                }
            });

            delete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(ri!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            ri.onDelete(position);
                        }
                    }

                }
            });
        }
        public Context getContext(){
            return itemView.getContext();
        }
    }
}
