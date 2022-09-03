package Actor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_method.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import URL_Requests.CRUD;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.MyViewHolder> {
    private Context mContext;
    private List<ActorModel_Index> list_data;

    public ActorAdapter(Context mContext, List<ActorModel_Index> list_data) {
        this.list_data = list_data;
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView actormain_rl_iv;
        private TextView actormain_rl_tv_name, actormain_rl_tv_note;
        private ImageButton actormain_rl_ib, actormain_rl_ib_delete;

        public MyViewHolder (final View view){
            super(view);

            actormain_rl_iv = view.findViewById(R.id.actormain_rl_iv);
            actormain_rl_tv_name = view.findViewById(R.id.actormain_rl_tv_name);
            actormain_rl_tv_note = view.findViewById(R.id.actormain_rl_tv_note);
            actormain_rl_ib = view.findViewById(R.id.actormain_rl_ib);
            actormain_rl_ib_delete = view.findViewById(R.id.actormain_rl_ib_delete);

        }
    }

    @NonNull
    @Override
    public ActorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.activity_actor_main_rv_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.MyViewHolder holder, int position) {
        final ActorModel_Index listData = list_data.get(position);

        Picasso.get().load(mContext.getString(R.string.base_URL) + listData.getImage()).into(holder.actormain_rl_iv);
        holder.actormain_rl_tv_name.setText(listData.getName());
        holder.actormain_rl_tv_note.setText(listData.getNote());

        holder.actormain_rl_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ActorEdit.class);
                intent.putExtra("id", listData.getId()+"");// +"" This is to convert int to String type, otherwise it will report an error
                intent.putExtra("name", listData.getName());
                intent.putExtra("note", listData.getNote());
                intent.putExtra("image", listData.getImage());
                mContext.startActivity(intent);
            }
        });

        holder.actormain_rl_ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, listData.getId()+"", Toast.LENGTH_SHORT).show();
                String url = mContext.getString(R.string.base_URL) + "api/actor/" + listData.getId();
                CRUD.Delete(mContext, url);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list_data.size();
    }

}
