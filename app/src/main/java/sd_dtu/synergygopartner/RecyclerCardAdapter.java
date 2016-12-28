package sd_dtu.synergygopartner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohitkumar on 25/12/16.
 */

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.RecycViewHolder>{

    Context context;
    ArrayList<CardData> arrayList = new ArrayList<CardData>();

    public RecyclerCardAdapter(Context context, ArrayList<CardData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecycViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agent_card,parent,false);
        RecycViewHolder recycViewHolder = new RecycViewHolder(view,context,arrayList);
        return recycViewHolder;
    }

    @Override
    public void onBindViewHolder(RecycViewHolder holder, int position) {
        CardData cardData = arrayList.get(position);
        holder.textView1.setText(cardData.getName());
        holder.textView2.setText(cardData.getFile());
        holder.textView3.setText(cardData.getAddtype());
        holder.textView4.setText(cardData.getAddress());
        holder.textView5.setText(cardData.getLandmark());
        holder.textView6.setText(cardData.getPcontact());
        holder.textView7.setText(cardData.getScontact());
        holder.textView8.setText(cardData.getAgentid());
        holder.textView9.setText(cardData.getUniid());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecycViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
        Context context;
        ArrayList<CardData> data = new ArrayList<CardData>();
        String s;

        public RecycViewHolder(View itemView, Context context, ArrayList<CardData> data) {
            super(itemView);
            this.data = data;
            this.context = context;
            textView1 = (TextView) itemView.findViewById(R.id.name);
            textView2 = (TextView) itemView.findViewById(R.id.file_no);
            textView3 = (TextView) itemView.findViewById(R.id.add_type);
            textView4 = (TextView) itemView.findViewById(R.id.address);
            textView5 = (TextView) itemView.findViewById(R.id.land_mark);
            textView6 = (TextView) itemView.findViewById(R.id.p_contact);
            textView7 = (TextView) itemView.findViewById(R.id.s_contact);
            textView8 = (TextView)itemView.findViewById(R.id.age);
            textView9 = (TextView)itemView.findViewById(R.id.uniid);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(textView3.getText().toString().equals("RESIDENTIAL")) {
                Intent intent = new Intent(this.context, Res1Act.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", textView1.getText().toString());
                intent.putExtra("file", textView2.getText().toString());
                intent.putExtra("addtype", textView3.getText().toString());
                intent.putExtra("address", textView4.getText().toString());
                intent.putExtra("landmark", textView5.getText().toString());
                intent.putExtra("pcontact", textView6.getText().toString());
                intent.putExtra("scontact", textView7.getText().toString());
                intent.putExtra("agent", textView8.getText().toString());
                intent.putExtra("uniid", textView9.getText().toString().trim());
                Log.d("AgentAdap", textView8.getText().toString());
                Log.d("UniqueAdap", textView9.getText().toString());
                context.startActivity(intent);
            } else if(textView3.getText().toString().equals("OFFICE")) {
                Intent intent = new Intent(this.context, Off1Act.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", textView1.getText().toString());
                intent.putExtra("file", textView2.getText().toString());
                intent.putExtra("addtype", textView3.getText().toString());
                intent.putExtra("address", textView4.getText().toString());
                intent.putExtra("landmark", textView5.getText().toString());
                intent.putExtra("pcontact", textView6.getText().toString());
                intent.putExtra("scontact", textView7.getText().toString());
                intent.putExtra("agent", textView8.getText().toString());
                intent.putExtra("uniid", textView9.getText().toString().trim());
                Log.d("AgentAdap", textView8.getText().toString());
                Log.d("UniqueAdap", textView9.getText().toString());
                context.startActivity(intent);
            } else if(textView3.getText().toString().equals(" BUSINESS")) {
                Intent intent = new Intent(this.context, Bus1Act.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", textView1.getText().toString());
                intent.putExtra("file", textView2.getText().toString());
                intent.putExtra("addtype", textView3.getText().toString());
                intent.putExtra("address", textView4.getText().toString());
                intent.putExtra("landmark", textView5.getText().toString());
                intent.putExtra("pcontact", textView6.getText().toString());
                intent.putExtra("scontact", textView7.getText().toString());
                intent.putExtra("agent", textView8.getText().toString());
                intent.putExtra("uniid", textView9.getText().toString().trim());
                Log.d("AgentAdap", textView8.getText().toString());
                Log.d("UniqueAdap", textView9.getText().toString());
                context.startActivity(intent);
            }

        }
    }
}
