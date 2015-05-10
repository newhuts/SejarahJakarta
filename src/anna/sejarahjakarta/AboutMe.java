package anna.sejarahjakarta;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutMe extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutme);
        getActionBar().setTitle("About Me");
        getActionBar().setIcon(R.drawable.about);
	    TextView nama = (TextView)findViewById(R.id.nama);
	    nama.setText("Rosyana Haryati");
	    TextView alamat = (TextView)findViewById(R.id.alamat);
	    alamat.setText("Ga tau");
	    TextView tentang = (TextView)findViewById(R.id.keterangan);
	    tentang.setText("pokoknya gitu dah");
	}
}
