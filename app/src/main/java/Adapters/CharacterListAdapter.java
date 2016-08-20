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
import com.sumayyah.characterviewer.Views.ListFragment;

import Model.Character;
import Managers.DataManager;

/**
 * Created by sumayyah on 8/10/16.
 */
public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {

    private ListFragment.ListItemClickListener listItemClickListener;
    public boolean isList;
    private Context context;

    public CharacterListAdapter(ListFragment.ListItemClickListener listItemClickListener, Context context) {
        this.listItemClickListener = listItemClickListener;
        this.context = context;
        isList = true;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Character c = DataManager.getInstance().getList().get(position);

        //If grid view, add a profile image
        if(!isList) {
            if(c.getImageURL().length() > 0) {
                Picasso.with(context).load(c.getImageURL()).placeholder(R.drawable.placeholder_profile_pic).into(holder.profilePic);
            } else {
                Picasso.with(context).load(R.drawable.placeholder_profile_pic).placeholder(R.drawable.ic_view_grid).into(holder.profilePic);
            }
        } else {
            holder.profilePic.setVisibility(View.GONE);
        }

        //Set common information
        holder.characterName.setText(c.getName());
        holder.cardContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemClickListener.onListItemSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataManager.getInstance().getList().size();
    }


}
