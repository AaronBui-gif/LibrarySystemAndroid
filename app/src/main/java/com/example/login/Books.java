package com.example.login;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {

    private RecyclerView recyclerBook;
    private BookAdapter bookAdapter;
    private SearchView searchView;
    private BookDatabase bookDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        bookDatabase = new BookDatabase(this);
//        recyclerBook = findViewById(R.id.rcvBooks);
//        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
//        recyclerBook.setLayoutManager(linearLayout);
//
//        bookAdapter = new BookAdapter(getListBooks());
//        recyclerBook.setAdapter(bookAdapter);
//
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        recyclerBook.addItemDecoration(itemDecoration);
    }

    private List<Book> getListBooks(){
        List<Book> list = new ArrayList<>();
        list.add(new Book("HARRY POTTER", "Adventure", "J.K.Rowling", R.drawable.harrypotterandsorcerer));
        if (bookDatabase.checkName("Harry Potter")){
            bookDatabase.insertData("Harry Potter", "J.K.Rowling");
        }
        list.add(new Book("Bear", "Kids", "Stephanie Banert", R.drawable.bear));
        if (bookDatabase.checkName("Bear")){
            bookDatabase.insertData("Bear", "Stephanie Banert");
        }
        return list;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_books, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                bookAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed(){
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}