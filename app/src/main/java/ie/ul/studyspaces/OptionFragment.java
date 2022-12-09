package ie.ul.studyspaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_option);

        final ImageView imageView = findViewById(R.id.imageView);

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
                        imageView.setImageResource(R.drawable.Library);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.RedRaisinsCafe);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.CSISCafe);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.SchrodingerCommunal);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.HealthSciencesCafe);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.FoundationAtriumCommunal);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.KemmyChillOutArea);
                        break;
                    case 7:
                        imageView.setImageResource(R.drawable.MedicalSchoolCafeAndCommon);
                        break;
                    case 8:
                        imageView.setImageResource(R.drawable.AnalogCafe);
                        break;
                    case 9:
                        imageView.setImageResource(R.drawable.PESSCafe);
                        break;
                    case 10:
                        imageView.setImageResource(R.drawable.MedicalSchoolAtrium);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}