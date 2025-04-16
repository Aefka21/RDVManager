package com.example.rdvmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myHelper;
    ListView lvRDV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        myHelper=new DatabaseHelper(this);
        myHelper.open();

        lvRDV = findViewById(R.id.lvRDV);
        lvRDV.setEmptyView(findViewById(R.id.tvEmpty));

        chargeData();

        /*lvRDV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String idItem= ((TextView)view.findViewById(R.id.idRDV)).getText().toString();
                String titleItem= ((TextView)view.findViewById(R.id.Title)).getText().toString();
                String personItem= ((TextView)view.findViewById(R.id.Person)).getText().toString();
                RDV pRDV = new RDV(Integer.parseInt(idItem),titleItem,personItem);
                Intent intent = new Intent(getApplicationContext(), RDVDetails.class);
                intent.putExtra("SelectedMoment",pRDV);


                intent.putExtra("fromAdd",false);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.rdv_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_RDV) {
            Intent intent = new Intent(this,RDVDetails.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void chargeData(){
        final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.PERSON};
        final int[]to= new int[]{R.id.idRDV, R.id.Title, R.id.Person};

        Cursor c = myHelper.getAllRDV();
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,R.layout.rdv_item_view,c,from,to,0);
        adapter.notifyDataSetChanged();
        lvRDV.setAdapter(adapter);
    }
}