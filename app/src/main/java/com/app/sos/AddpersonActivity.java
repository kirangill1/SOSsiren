package com.app.sos;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;

        import android.Manifest;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import static com.app.sos.MainActivity.hasPermissions;

public class AddpersonActivity extends AppCompatActivity {
    private EditText contact1 , contact2 , contact3;

    private EditText name1 , name2 , name3;

    private final String  SHARED_PREF_NAME = "persons";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addperson);
        contact1 = findViewById(R.id.contact);
        contact2 = findViewById(R.id.contact2);
        contact3 = findViewById(R.id.contact3);

        name1 = findViewById(R.id.name);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);

        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        name1.setText(sp.getString("name1", ""));
        name2.setText(sp.getString("name2", ""));
        name3.setText(sp.getString("name3", ""));

        contact1.setText(sp.getString("contact1", ""));
        contact2.setText(sp.getString("contact2", ""));
        contact3.setText(sp.getString("contact3", ""));


    }

    public void save_person(View view) {
        String name_str = name1.getText().toString();
        String name2_str = name2.getText().toString();
        String name3_str = name3.getText().toString();

        String contact_str = contact1.getText().toString();
        String contact2_str = contact2.getText().toString();
        String contact3_str = contact3.getText().toString();

        SharedPreferences.Editor sp = getSharedPreferences(SHARED_PREF_NAME , MODE_PRIVATE).edit();

        sp.putString("name1" , name_str);
        sp.putString("name2" , name2_str);
        sp.putString("name3" , name3_str);

        sp.putString("contact1" , contact_str);
        sp.putString("contact2" , contact2_str);
        sp.putString("contact3" , contact3_str);

        sp.commit();

        Toast.makeText(AddpersonActivity.this , "Person contact saved" , Toast.LENGTH_SHORT).show();

    }
}


