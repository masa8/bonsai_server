package aruite.tanoshiku.bousaikunren;



import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;


public class MainActivity extends Activity {

	GoogleMap googleMap;
	
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();
            googleMap.setMyLocationEnabled(true);
            
         // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);

            LocationListener locationListener = new LocationListener() {
            	
               public void onLocationChanged(Location location) {
            	  // redraw the marker when get location update.
            	  drawMarker(location);
               }};
               
            if(location!=null){
               //PLACE THE INITIAL MARKER              
               drawMarker(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, (android.location.LocationListener) locationListener);
       
        }
    }
    
    private void drawMarker(Location location){
    	googleMap.clear();
    	LatLng currentPosition = new LatLng(location.getLatitude(),
    	location.getLongitude());
    	googleMap.addMarker(new MarkerOptions()
    	.position(currentPosition)
    	.snippet("Lat:" + location.getLatitude() + "Lng:"+ location.getLongitude())
    	.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    	.title("ME"));
    	}
}