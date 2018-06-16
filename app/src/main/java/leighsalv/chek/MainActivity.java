package leighsalv.chek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
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
        listToDo = findViewById(R.id.list_todo);

//        if(inputToDo.getText().toString().equals(""))
//            addBtn.setClickable(false);
//
//        else if(!(inputToDo.getText().toString().equals("")))
//            addBtn.setClickable(true);

        items = SaveItems.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listToDo.setAdapter(adapter);

    }

    //Add or Remove button is pressed
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

//    public void onClickToDel(View d) {
//        CheckedTextView check = (CheckedTextView)d;
//        check.setChecked(!check.isChecked());
//        boolean click = !check.isChecked();
//        check.setChecked(click);
//        if(click) {
//            Toast.makeText(this, "Not Selected", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this, "Selected", Toast.LENGTH_SHORT).show();
//        }
//    }
}
