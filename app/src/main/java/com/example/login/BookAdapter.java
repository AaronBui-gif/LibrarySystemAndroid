package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {

    private List<Book> listBook;
    private List<Book> listBookPrevious;

    public BookAdapter(List<Book> listBook) {
        this.listBook = listBook;
        this.listBookPrevious = listBook;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user , parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = listBook.get(position);
        if (book == null){
            return;
        }

        holder.imgUser.setImageResource(book.getImg());
        holder.tvName.setText(book.getName());
        holder.tvAuthor.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        if (listBook != null) {
            return listBook.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgUser;
        private TextView tvName;
        private TextView tvAuthor;
        private RelativeLayout item;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgBook);
            tvName = itemView.findViewById(R.id.tvName);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            item = itemView.findViewById(R.id.book);
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    listBook = listBookPrevious;
                } else {
                    List<Book> list = new ArrayList<>();
                    for (Book book: listBookPrevious) {
                        if (book.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(book);
                        }
                    }

                    listBook = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listBook;

                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listBook = (List<Book>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}
