//package com.zed.next.ui.search;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import com.zed.next.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.MovieHolder> implements Filterable {
//
//    private Context context;
//    private List<Movies> movies;
//    private List<Movies> allMovies;
//
//    public SearchViewAdapter(Context context, ArrayList<Movies> movies) {
//        this.context = context;
//        this.movies = movies;
//        allMovies = new ArrayList<>(movies);
//
//    }
//
//    @Override
//    public Filter getFilter() {
//        return exampleFilter;
//    }
//
//    @NonNull
//    @Override
//    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
//        return new MovieHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
//        Movies movie = movies.get(position);
//        holder.setDetails(movie);
//    }
//
//    @Override
//    public int getItemCount() {
//        return movies.size();
//    }
//
//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Movies> filteredList = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(allMovies);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Movies item : allMovies) {
//                    if (item.getPlanetName().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//            return results;
//        }
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            movies.clear();
//            movies.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };
//
//    class MovieHolder extends RecyclerView.ViewHolder{
//        public MovieHolder(@NonNull View itemView) {
//            super(itemView);
//            txtName = itemView.findViewById(R.id.txtName);
//            txtDistance = itemView.findViewById(R.id.txtDistance);
//            txtGravity = itemView.findViewById(R.id.txtGravity);
//            txtDiameter = itemView.findViewById(R.id.txtDiameter);
//        }
//        private TextView txtName, txtDistance, txtGravity, txtDiameter;
//
//        void setDetails(Movies planet) {
//            txtName.setText(planet.getPlanetName());
//            txtDistance.setText(String.format(Locale.US, "Distance from Sun : %d Million KM", planet.getDistanceFromSun()));
//            txtGravity.setText(String.format(Locale.US, "Surface Gravity : %d N/kg", planet.getGravity()));
//            txtDiameter.setText(String.format(Locale.US, "Diameter : %d KM", planet.getDiameter()));
//        }
//    }
//}
//
//
