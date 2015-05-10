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
import android.widget.Toast;
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

public class Maps extends  Activity {
	  private GoogleMap map;
	  private TempatAdapter db;
	  Marker sejarah;
	  static String id;
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.maps);
	    Intent in = getIntent(); 
		id = in.getStringExtra("id");
		Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();
	    db = new TempatAdapter(this);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    List<Tempat> tempatkan = db.getSemuaByID(id);
	    for(Tempat tempat : tempatkan){
	    	ActionBar actionBar = getActionBar();
	        actionBar.setTitle(""+tempat.getNama());
	        actionBar.setHomeButtonEnabled(true);
	        actionBar.setIcon(R.drawable.map);
	    	LatLng LANGLAT = new LatLng(Double.parseDouble(tempat.getLati()), Double.parseDouble(tempat.getLong()));
	    	sejarah = map.addMarker(new MarkerOptions().position(LANGLAT)
	    	        .title(""+tempat.getNama())
	    	        .snippet("ALAMAT: "+tempat.getAlamat())
	    	        .icon(BitmapDescriptorFactory.fromResource(R.drawable.tag50)));
	    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(LANGLAT, 10));
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
	    	    View popup=inflater.inflate(R.layout.infowindow, null);

	    	    TextView tv=(TextView)popup.findViewById(R.id.title);
	    	    tv.setText(marker.getTitle());
	    	    tv=(TextView)popup.findViewById(R.id.snipet);
	    	    tv.setText(marker.getSnippet());
	    	   
	    	    return(popup);
	    	  }
	    	}
	    map.setInfoWindowAdapter(new PopupAdapter(getLayoutInflater()));
	    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
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