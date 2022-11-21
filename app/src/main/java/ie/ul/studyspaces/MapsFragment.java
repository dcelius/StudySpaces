package ie.ul.studyspaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        private final LatLng ANALOG_CAFE = new LatLng(52.673050, -8.569294);
        private final LatLng COMPUTER_SCIENCE_CAFE = new LatLng(52.673846, -8.575399);
        private final LatLng FOUNDATION_COMMUNAL = new LatLng(52.674414, -8.573278);
        private final LatLng HEALTH_SCIENCES_CAFE = new LatLng(52.677565, -8.569222);
        private final LatLng KEMMY_CHILL_OUT = new LatLng(52.672607, -8.576745);
        private final LatLng MEDICAL_CAFE_COMMON = new LatLng(52.678323, -8.568066);
        private final LatLng MEDICAL_ATRIUM = new LatLng(52.678456, -8.568213);
        private final LatLng PESS_CAFE = new LatLng(52.674730, -8.568300);
        private final LatLng RED_RAISIN_CAFE = new LatLng(52.673607, -8.570720);
        private final LatLng SCHRODINGER_COMMUNAL = new LatLng(52.673858, -8.567358);

        private Marker marker_analog_cafe;
        private Marker marker_computer_science_cafe;
        private Marker marker_foundation_communal;
        private Marker marker_health_sciences_cafe;
        private Marker marker_kemmy_chill_out;
        private Marker marker_medical_cafe_common;
        private Marker marker_medical_atrium;
        private Marker marker_pess_cafe;
        private Marker marker_red_raisin_cafe;
        private Marker marker_schrodinger_communal;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            marker_analog_cafe = googleMap.addMarker(new MarkerOptions()
                    .position(ANALOG_CAFE)
                    .title("Analog Building: Cafe")
                    .snippet("Capacity: 25")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            marker_computer_science_cafe = googleMap.addMarker(new MarkerOptions()
                    .position(COMPUTER_SCIENCE_CAFE)
                    .title("Computer Science Building: Cafe")
                    .snippet("Capacity: 35")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            marker_foundation_communal = googleMap.addMarker(new MarkerOptions()
                    .position(FOUNDATION_COMMUNAL)
                    .title("Foundation Building: Communal Area")
                    .snippet("Capacity: 32")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

            marker_health_sciences_cafe = googleMap.addMarker(new MarkerOptions()
                    .position(HEALTH_SCIENCES_CAFE)
                    .title("Health Sciences Building: Cafe")
                    .snippet("Capacity: 34")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            marker_kemmy_chill_out = googleMap.addMarker(new MarkerOptions()
                    .position(KEMMY_CHILL_OUT)
                    .title("Kemmy Business School: Chill-Out Area")
                    .snippet("Capacity: 30")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

            marker_medical_cafe_common = googleMap.addMarker(new MarkerOptions()
                    .position(MEDICAL_CAFE_COMMON)
                    .title("School of Medicine: Cafe / Common Area")
                    .snippet("Capacity: 28")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            marker_medical_atrium = googleMap.addMarker(new MarkerOptions()
                    .position(MEDICAL_ATRIUM)
                    .title("School of Medicine: Atrium")
                    .snippet("Capacity: 6")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

            marker_pess_cafe = googleMap.addMarker(new MarkerOptions()
                    .position(PESS_CAFE)
                    .title("PESS Building: Cafe")
                    .snippet("Capacity: 20")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            marker_red_raisin_cafe = googleMap.addMarker(new MarkerOptions()
                    .position(RED_RAISIN_CAFE)
                    .title("Main Building: Red Raisin Cafe")
                    .snippet("Capacity: 122")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            marker_schrodinger_communal = googleMap.addMarker(new MarkerOptions()
                    .position(SCHRODINGER_COMMUNAL)
                    .title("Schrodinger Building: Communal Area")
                    .snippet("Capacity: 35")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

            // [START_EXCLUDE silent]
            LatLngBounds googleMapBounds = new LatLngBounds(
                    new LatLng(52.6716019617823, -8.584351435069413), // SW bounds
                    new LatLng(52.679717617429205, -8.561421455488544)  // NE bounds
            );
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(googleMapBounds.getCenter(), 15));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}