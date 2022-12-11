package ie.ul.studyspaces;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReservationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ReservationsFragment extends Fragment {

    // creating variables for our list view.
    private ListView reservationsList;
    ArrayList<String> reservationsArrayList;


    public static ReservationsFragment newInstance(String param1, String param2) {
        ReservationsFragment fragment = new ReservationsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ReservationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reservationsList = view.findViewById(R.id.listReservations);
        reservationsArrayList = new ArrayList<String>();
        getListItems();
    }

    private void getListItems() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, reservationsArrayList);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/reservations").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            return;
                        } else {
                            for (DocumentSnapshot doc : documentSnapshots.getDocuments()) {
                                StringBuilder sb = new StringBuilder();
                                for (Object value : doc.getData().values()) {
                                    sb.append((String)value + " ");
                                }
                                String concatenatedString = sb.toString();
                                reservationsArrayList.add(concatenatedString);
                            }
                            reservationsList.setAdapter(adapter);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Error fetching data", Toast.LENGTH_LONG).show();
                        }
                });
    }

}