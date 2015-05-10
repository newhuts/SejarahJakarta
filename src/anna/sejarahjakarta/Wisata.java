package anna.sejarahjakarta;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import anna.sejarahjakarta.R;
import anna.sejarahjakarta.helper.TempatAdapter;
import anna.sejarahjakarta.model.Tempat;

public class Wisata extends Activity {
	
	private ListView jakarta;
	private String id, title;
	private TempatAdapter db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuutama);
		Intent in = getIntent(); 
		id = in.getStringExtra("id");
		title = in.getStringExtra("title");
		TextView hak = (TextView)findViewById(R.id.hak);
        hak.setText("Pilih Wisata");
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(""+title);
        
        jakarta = (ListView) findViewById(R.id.listmenu);
        String[] from = new String[] {"icon", "title", "id"};
        int[] to = new int[] { R.id.tempatimg, R.id.tempat, R.id.tempatid};
		db = new TempatAdapter(this);
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        List<Tempat> tempatkan = db.getWisata(id);
	    for(Tempat tempat : tempatkan){
            HashMap<String, String> map = new HashMap<String, String>();
            int maps = R.drawable.map; 
            map.put("icon", ""+maps);
            map.put("title", tempat.getNama());
            map.put("id", Integer.toString(tempat.getID()));
            fillMaps.add(map);
            
	    }
        db.closeDB();   
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.listkatewisata, from, to);
        jakarta.setAdapter(adapter);
        jakarta.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String idkt = ((TextView)view.findViewById(R.id.tempatid)).getText().toString();
				Intent in = new Intent(Wisata.this, Profile.class);
				in.putExtra("id", idkt);
				db.closeDB();
			    System.gc();
			    Runtime.getRuntime().gc();
				startActivity(in);
				
			}
		});
	 }
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	 	db.closeDB();
			    System.gc();
			    Runtime.getRuntime().gc();
			    this.finish();
	     }
	     return super.onKeyDown(keyCode, event);
	 }
}
