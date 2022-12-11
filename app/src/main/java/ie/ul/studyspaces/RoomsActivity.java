package ie.ul.studyspaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class RoomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        final ImageView roomImage = findViewById(R.id.imageView2);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        roomImage.setImageResource(R.drawable.library);
                        break;
                    case 1:
                        roomImage.setImageResource(R.drawable.redraisinscafe);
                        break;
                    case 2:
                        roomImage.setImageResource(R.drawable.csiscafe);
                        break;
                    case 3:
                        roomImage.setImageResource(R.drawable.schrodingercommunal);
                        break;
                    case 4:
                        roomImage.setImageResource(R.drawable.healthsciencescafe);
                        break;
                    case 5:
                        roomImage.setImageResource(R.drawable.foundationatriumcommunal);
                        break;
                    case 6:
                        roomImage.setImageResource(R.drawable.kemmychilloutarea);
                        break;
                    case 7:
                        roomImage.setImageResource(R.drawable.medicalschoolcafeandcommon);
                        break;
                    case 8:
                        roomImage.setImageResource(R.drawable.analogcafe);
                        break;
                    case 9:
                        roomImage.setImageResource(R.drawable.pesscafe);
                        break;
                    case 10:
                        roomImage.setImageResource(R.drawable.medicalschoolatrium);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}