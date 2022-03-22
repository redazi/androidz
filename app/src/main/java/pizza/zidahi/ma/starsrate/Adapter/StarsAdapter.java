package pizza.zidahi.ma.starsrate.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import pizza.zidahi.ma.starsrate.R;
import pizza.zidahi.ma.starsrate.classe.Stars;
import pizza.zidahi.ma.starsrate.service.StarsService;

public class StarsAdapter extends RecyclerView.Adapter<StarsAdapter.StarViewHolder>  implements Filterable {
    private List<Stars> stars;
    private LayoutInflater inflater;
    private List<Stars> starsFilter;
    private NewFilter mfilter;

    private Context cxt;
    private static final String TAG = "StarAdapter";

    public StarsAdapter(Context context, List<Stars> stars) {
        this.stars = stars;
        this.cxt = context;
        starsFilter = new ArrayList<>();
        starsFilter.addAll(stars);
        mfilter = new NewFilter(this);


    }
    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(this.cxt).inflate(R.layout.item, viewGroup, false);
        final StarViewHolder holder = new StarViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(cxt).inflate(R.layout.star_edit_item, null,
                        false);
                final ImageView img = popup.findViewById(R.id.img);
                final RatingBar bar = popup.findViewById(R.id.ratingBar);
                final TextView idss = popup.findViewById(R.id.ids1);
                Bitmap bitmap =
                        ((BitmapDrawable) ((ImageView) v.findViewById(R.id.image)).getDrawable()).getBitmap();
                img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar) v.findViewById(R.id.stars)).getRating());
                idss.setText(((TextView) v.findViewById(R.id.ids)).getText().toString());

                AlertDialog dialog = new AlertDialog.Builder(cxt)
                        .setTitle("Notez : ")
                        .setMessage("Donner une note entre 1 et 5 :")
                        .setView(popup)
                        .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(idss.getText().toString());
                                Stars star = StarsService.getInstance().findById(ids);

                                star.setStar(s);
                                System.out.println("haaaaaaaaaaahooooooooooooooooooooowa 22222222222222222");
                                System.out.println(star.toString());
                                StarsService.getInstance().update(star);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .create();
                dialog.show();
            }
        });
        return holder;
    }
        @Override
    public void onBindViewHolder(@NonNull StarsAdapter.StarViewHolder holder, int position) {

            Log.d(TAG, "onBindView call ! "+ position);
            Glide.with(cxt)
                    .asBitmap()
                    .load(starsFilter.get(position).getImage())
                    .apply(new RequestOptions().override(100, 100))
                    .into(holder.image);
            holder.nom.setText(starsFilter.get(position).getNom().toUpperCase());
            holder.stars.setRating(starsFilter.get(position).getStar());
            holder.id.setText(starsFilter.get(position).getId()+"");


        }
    @Override
    public Filter getFilter() {
        return mfilter;
    }
    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;
        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            starsFilter.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                starsFilter.addAll(stars);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Stars p : stars) {
                    if (p.getNom().toLowerCase().startsWith(filterPattern)) {
                        starsFilter.add(p);
                    }
                }
            }
            results.values = starsFilter;
            results.count = starsFilter.size();
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Stars>) filterResults.values;
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return starsFilter.size();

    }
    public class StarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nom, id;
        ImageView image;
        RatingBar stars;
        RelativeLayout parent;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nom);
            stars = itemView.findViewById(R.id.stars);
            parent = itemView.findViewById(R.id.parent);

            id = itemView.findViewById(R.id.ids);
            image = itemView.findViewById(R.id.image);

            //  itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }


}
