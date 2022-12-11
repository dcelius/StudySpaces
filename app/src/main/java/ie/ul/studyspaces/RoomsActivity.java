package ie.ul.studyspaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RoomsActivity extends AppCompatActivity {

    TextView textTime;
    TextView textDate;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        textTime = findViewById(R.id.textTime);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10)
                        {
                            textTime.setText(hourOfDay + ":0" + minute);
                        } else {
                            textTime.setText(hourOfDay + ":" + minute);
                        }
                    }
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                false);
        textTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        textDate = findViewById(R.id.textDate);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        final ImageView roomImage = findViewById(R.id.imageView2);
        spinner = findViewById(R.id.spinner);
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

    public void OnClickStoreData (View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        CollectionReference reservations = db.collection("user/" + uid + "/reservations");

        String location = spinner.getSelectedItem().toString();
        String date = textDate.getText().toString();
        String time = textTime.getText().toString();
        if (date.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter a valid date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (time.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter a valid time", Toast.LENGTH_SHORT).show();
            return;
        }
        // The exclusion of location from the unique reservation code is that it can be overwritten
        // if the location changes but the date and time do not - which makes sense as a person
        // cannot use two spaces at the same time.
        String code = date.replaceAll("/","") + "-"
                + time.replaceAll(":","");
        Map<String, String> newReservation = new HashMap<>();
        newReservation.put("location", location);
        newReservation.put("date", date);
        newReservation.put("time", time);
        reservations.document(code).set(newReservation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Your reservation was successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}