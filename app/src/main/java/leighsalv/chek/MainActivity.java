package leighsalv.chek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.regex.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText inputToDo;
    private Button addBtn;
    private ListView listToDo;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputToDo = findViewById(R.id.input_todo);
        addBtn = findViewById(R.id.add_btn);
        listToDo = (ListView)findViewById(R.id.list_todo);

        items = SaveItems.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listToDo.setAdapter(adapter);

        listToDo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                SparseBooleanArray positionChecker = listToDo.getCheckedItemPositions();
                int count = listToDo.getCount();

                for(int i = count-1; i >= 0; i--) {
                    if(positionChecker.get(i)) {
                        adapter.remove(items.get(i));
                    }
                }
                positionChecker.clear();
                adapter.notifyDataSetChanged();

                return false;
            }
        });

    }

    //Add To-Do Item
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.add_btn:
                String item = inputToDo.getText().toString();

                if(item.length()!=0) {
                    adapter.add(item);
                    inputToDo.setText("");
                    SaveItems.writeData(items, this);
                    Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                    addBtn.setClickable(false);
        }
    }
}
