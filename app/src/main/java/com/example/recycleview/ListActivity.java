package com.example.recycleview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.adapter.StarAdapter;
import com.example.recycleview.beans.Star;
import com.example.recycleview.service.StarService;

public class ListActivity extends AppCompatActivity {

    private StarService service;
    private StarAdapter starAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        service = StarService.getInstance();
        init();

        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


    private void init() {
        service.create(new Star("kate bosworth", "https://images.pexels.com/photos/6895806/pexels-photo-6895806.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 3.5f));
        service.create(new Star("george clooney", "https://images.pexels.com/photos/6896185/pexels-photo-6896185.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 3));
        service.create(new Star("sharley schaplen", "https://images.pexels.com/photos/2227958/pexels-photo-2227958.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 5));
        service.create(new Star("george clooney", "https://images.pexels.com/photos/2227958/pexels-photo-2227958.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 1));
        service.create(new Star("louise bourgoin", "https://images.pexels.com/photos/1040880/pexels-photo-1040880.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 5));
        service.create(new Star("louise bourgoin", "https://images.pexels.com/photos/1040880/pexels-photo-1040880.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", 1));
    }
    //        int numberOfColumns = 2;
//
//
//        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

}
