package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sumayyah.characterviewer.R;
import com.sumayyah.characterviewer.Views.Console;

import Data.Character;
import Data.DataManager;

/**
 * Created by sumayyah on 8/10/16.
 */
public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {

    private Context context;
    private View.OnClickListener clickListener;

    public CharacterListAdapter(Context context, View.OnClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profilePic;
        private TextView characterName;
        private LinearLayout cardContentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
            characterName = (TextView) itemView.findViewById(R.id.characterName);
            cardContentLayout = (LinearLayout) itemView.findViewById(R.id.cardContentLayout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fragment_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Character c = new DataManager().getList().get(position);
        holder.characterName.setText(c.getCharacterName());
        Picasso.with(context).load(c.getCharacterPicURL()).placeholder(R.drawable.ic_view_grid).into(holder.profilePic);

        holder.cardContentLayout.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        int size = new DataManager().getList().size();
        return size;
    }


}
