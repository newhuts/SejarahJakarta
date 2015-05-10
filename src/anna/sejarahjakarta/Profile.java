package anna.sejarahjakarta;

import java.util.List;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import anna.sejarahjakarta.helper.TempatAdapter;
import anna.sejarahjakarta.model.Tempat;

public class Profile extends Activity {
	
	private Button liat;
	private TempatAdapter db;
	private String id;
	TypedArray images, imgicon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.profile);
	        Intent in = getIntent();
	        id = in.getStringExtra("id");
	        
	        db = new TempatAdapter(this);
	        List<Tempat> tempatkan = db.getSemuaByID(id);
		        for(Tempat deskri : tempatkan){
		        	ActionBar actionBar = getActionBar();
		        	actionBar.setTitle(""+deskri.getNama());
		        	actionBar.setIcon(R.drawable.wisata);
		        	actionBar.setHomeButtonEnabled(true);
		           ImageView img = (ImageView)findViewById(R.id.icon);
		           images = getResources().obtainTypedArray(R.array.wisata);
		           int resourceId = images.getResourceId(deskri.getID(), -1);
		           img.setImageResource(resourceId);
		           images.recycle();
		           TextView judul = (TextView)findViewById(R.id.nama);
		           judul.setText(deskri.getNama());
		           TextView desss = (TextView)findViewById(R.id.load);
		           desss.setText(deskri.getDesk());
		           TextView ala = (TextView)findViewById(R.id.alamat);
		           ala.setText(deskri.getAlamat());
			    }
	        db.closeDB();   
	        
	        liat = (Button) findViewById(R.id.liatpeta);
	        liat.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (id.equals("0")) {
						Intent i = new Intent(Profile.this, MapsAll.class);
						db.closeDB();
				       	images.recycle();
				       	System.gc();
				       	Runtime.getRuntime().gc();
				    	finish();
						startActivity(i);
					}else{
						Intent i = new Intent(Profile.this, Maps.class);
						i.putExtra("id", id);
						db.closeDB();
				       	images.recycle();
				       	System.gc();
				       	Runtime.getRuntime().gc();
				    	finish();
						startActivity(i);
					}
					
					
				}
			});
	        ScrollView scroll = (ScrollView) findViewById(R.id.scrollView1);
	        scroll.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						liat.setVisibility(View.GONE);
					}else if (event.getAction() == MotionEvent.ACTION_UP) {
						liat.setVisibility(View.VISIBLE);
					}
					return false;
				}
			});
	 }
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	       	db.closeDB();
	       	images.recycle();
	       	System.gc();
	       	Runtime.getRuntime().gc();
	    	this.finish();
	    	
	     }
	     return super.onKeyDown(keyCode, event);
	 }
}
