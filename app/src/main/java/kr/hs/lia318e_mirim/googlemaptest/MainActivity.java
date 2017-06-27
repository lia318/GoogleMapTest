package kr.hs.lia318e_mirim.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    } // end of onCreate

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.818580, 15.986982), 18)); // 줌인 레벨 1~20
        googleMap.getUiSettings().setZoomControlsEnabled(true); // 확대 축소 버튼
    } // end of onMapReady

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도"); // 메뉴 구별 용도
        menu.add(0, 2, 0, "일반 지도");
        menu.add(0, 3, 0, "프로방스");

        return true;
    } // end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case 1: googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case 2: googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.791088, 126.685061), 18));
                return true;
        } // end of switch

        return false;
    } // end of onOptionsItemSelected
} // end of MainActivity