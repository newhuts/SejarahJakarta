package anna.sejarahjakarta;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import anna.sejarahjakarta.helper.TempatAdapter;
import anna.sejarahjakarta.model.Tempat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsAll extends  Activity {
	  static final LatLng DIZOOM = new LatLng(-6.208842, 106.845697);
	  private GoogleMap map;
	  private TempatAdapter db;
	  Marker sejarah;
	  static String id;
	  TextView tv,tvs;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.maps);
	    ActionBar actionBar = getActionBar();
        actionBar.setTitle("Tempat bersejarah di Jakarta");
        actionBar.setHomeButtonEnabled(true);
        //actionBar.setIcon(R.drawable.map);
	    db = new TempatAdapter(this);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    List<Tempat> tempatkan = db.getSemua();
	    for(Tempat tempat : tempatkan){
	    	LatLng LANGLAT = new LatLng(Double.parseDouble(tempat.getLati()), Double.parseDouble(tempat.getLong()));
	    	sejarah = map.addMarker(new MarkerOptions().position(LANGLAT)
	    	        .title(""+tempat.getNama())
	    	        .snippet(""+tempat.getID())
	    	        .icon(BitmapDescriptorFactory.fromResource(R.drawable.tag50)));
	    }
	    db.closeDB();
	    class PopupAdapter implements InfoWindowAdapter {
	    	  LayoutInflater inflater=null;

	    	  PopupAdapter(LayoutInflater inflater) {
	    	    this.inflater=inflater;
	    	  }

	    	  @Override
	    	  public View getInfoWindow(Marker marker) {
	    	    return(null);
	    	  }

	    	  @Override
	    	  public View getInfoContents(Marker marker) {
	    	    View popup=inflater.inflate(R.layout.infowindowall, null);
	    	    TextView tv=(TextView)popup.findViewById(R.id.titleall);
	    	    tv.setText(marker.getTitle());
	    	    tvs=(TextView)popup.findViewById(R.id.snipetall);
	    	    tvs.setText(marker.getSnippet());
	    	    tvs.setVisibility(View.GONE);
	    	    return(popup);
	    	  }
	    	}
	    map.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
	    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				Intent i = new Intent(MapsAll.this, Profile.class);
				i.putExtra("nama", ""+marker.getTitle());
				i.putExtra("id", tvs.getText().toString());
				startActivity(i);
				
				
			}
		});
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(DIZOOM, 10));
	    map.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
	  }
	  @Override
		 public boolean onKeyDown(int keyCode, KeyEvent event) {
		     if ((keyCode == KeyEvent.KEYCODE_BACK)) {
		       	db.closeDB();
		       	map.clear();
		       	this.finish();
		     }
		     return super.onKeyDown(keyCode, event);
		 }
	}