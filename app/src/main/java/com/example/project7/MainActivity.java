package com.example.project7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


public int db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new SqlHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqlHelper db = new SqlHelper(this);
/** CRUD Operations **/
// add Books
        db.addBook(new Book("Android Studio Development Essentials", "Neil Smyth", 1));
        db.addBook(new Book("Beginning Android Application Development", "Wei-Meng Lee", 2));
        db.addBook(new Book("Programming Android", "Wallace Jackson", 3));
        db.addBook(new Book("Hello, Android", "Ben Jackson", 4));


// get all books
        List<Book> list = db.getAllBooks();

// update one book
        int j = db.updateBook(list.get(3), "Hello, Android", "Ben Jackson");
// delete one book
        db.deleteBook(list.get(0));
// get all books
        db.getAllBooks();
        Log.d("Book Count", db.getIds() + "");
// get list count size
        Log.d("count", "Count test:" + list.size());
// my name
        Log.d("name", "Maksym Melnyk");
        // Spinner element
        Spinner spinner;
        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner1);
        //Create spinner item listing
        List<String> blist = new ArrayList<String>();
        blist.add("Get Highest Rated Title(s)");
        blist.add("Get Lowest Rated Title(s)");
        blist.add("Retrieve Title(s) with Android");
        blist.add("Get Record Count");
        //Sort list in Alphabetical order
        Collections.sort(blist, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        blist.add(0, "Select Analytics...");
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, blist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setWillNotDraw(false);
        spinner.setOnItemSelectedListener(this);
    } //end onCreate() method

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        switch (i) {
            case 1:
                //get query result for Highest rated title(s)
                // display query result(s) in a Toast message
                Toast.makeText(this, "Title :: " + db.getRatingMax(),
                        Toast.LENGTH_LONG).show();
                break;
            case 2:
                //get query result for lowest rated title(s)
                // display query result(s) in a Toast message
                Toast.makeText(this, "Title :: " + db.getRatingMin(),
                        Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(this, "Record Count :: " + db.getTotal(),
                        Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(this, "Title :: " + db.getBooks(),
                        Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}



