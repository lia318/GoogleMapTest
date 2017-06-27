package kr.hs.lia318e_mirim.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    } // end of onCreate

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.818580, 15.986982), 18)); // 줌인 레벨 1~20
        googleMap.getUiSettings().setZoomControlsEnabled(true); // 확대 축소 버튼
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                loc_mark = new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.drawable.untitled)).position(latLng, 100f, 100f);
                googleMap.addGroundOverlay(loc_mark);
            }
        });
    } // end of onMapReady

    public static final int ITEM_SATELLITE = 1;
    public static final int ITEM_NORMAL = 2;
    public static final int ITEM_PROVENCE = 3;
    public static final int ITEM_COEXMALL = 4;
    public static final int ITEM_MARK_CLEAR = 5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ITEM_SATELLITE, 0, "위성 지도"); // 메뉴 구별 용도
        menu.add(0, ITEM_NORMAL, 0, "일반 지도");
        SubMenu hotMenu = menu.addSubMenu("좋아하는 장소"); // 코드 힌트 목록을 할 때 대소문자를 구분하면 원하는 목록을 볼 수 있다.
        hotMenu.add(0, ITEM_PROVENCE, 0, "프로방스");
        hotMenu.add(0, ITEM_COEXMALL, 0, "코엑스몰");
        menu.add(0, ITEM_MARK_CLEAR, 0, "위치 아이콘 제거");

        return true;
    } // end of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case ITEM_SATELLITE: googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NORMAL: googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_PROVENCE: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.791088, 126.685061), 18));
                return true;
            case ITEM_COEXMALL: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.513151, 127.057706), 18));
                return true;
            case ITEM_MARK_CLEAR: googleMap.clear();
                return true;
        } // end of switch

        return false;
    } // end of onOptionsItemSelected
} // end of MainActivity