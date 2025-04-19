package com.example.rdvmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        registerForContextMenu(lvRDV);

        lvRDV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String idItem = ((TextView)view.findViewById(R.id.idRDV)).getText().toString();
                String titleItem = ((TextView)view.findViewById(R.id.Title)).getText().toString();
                String personItem = ((TextView)view.findViewById(R.id.Person)).getText().toString();
                String phoneItem = ((TextView)view.findViewById(R.id.Phone)).getText().toString();
                String dateItem = ((TextView)view.findViewById(R.id.Date)).getText().toString();
                String timeItem = ((TextView)view.findViewById(R.id.Time)).getText().toString();
                RDV pRDV = new RDV(Integer.parseInt(idItem),titleItem, dateItem, timeItem, personItem, phoneItem);
                Intent intent = new Intent(getApplicationContext(), RDVDetails.class);
                intent.putExtra("SelectedRDV",pRDV);
                intent.putExtra("fromAdd",false);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if (item.getItemId()==R.id.delete){
            myHelper.delete(info.id);
            chargeData();
            return true;
        }
        return super.onContextItemSelected(item);
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
        final String[] from = new String[] {
                DatabaseHelper._ID,
                DatabaseHelper.TITLE,
                DatabaseHelper.DATE,
                DatabaseHelper.TIME,
                DatabaseHelper.PERSON,
                DatabaseHelper.PHONE
        };
        
        final int[] to = new int[] {
                R.id.idRDV,
                R.id.Title,
                R.id.Date,
                R.id.Time,
                R.id.Person,
                R.id.Phone
        };
        
        Cursor c = myHelper.getAllRDV();
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,R.layout.rdv_item_view,c,from,to,0);
        adapter.notifyDataSetChanged();
        lvRDV.setAdapter(adapter);
    }
}