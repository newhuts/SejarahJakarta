package anna.sejarahjakarta.model;

public class Tempat {

	private int id;
	private String nama, alamat, longt, lati, idkategori, desk;
	public static final String KEY_ID = "_id";
	public static final String KEY_NAMA = "nama";
	public static final String KEY_ALAMAT = "alamat";
	public static final String KEY_LONG = "longtitude";
	public static final String KEY_LATI = "latitude";
	public static final String KEY_IDKATEGORI = "idkategori";
	public static final String KEY_DESK = "desk";
	
	
	public Tempat(){}

	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	
	public String getNama(){
		return this.nama;
	}
	public void setNama(String nama){
		this.nama = nama;
	}
	
	public String getAlamat(){
		return this.alamat;
	}
	public void setAlamat(String alamat){
		this.alamat = alamat;
	}
	
	public String getLong(){
		return this.longt;
	}
	public void setLong(String longt){
		this.longt = longt;
	}
	
	public String getLati() {
		return this.lati;
	}
	public void setLati(String lati) {
		this.lati = lati;
	}
	
	public String getIdkategori() {
		return this.idkategori;
	}
	public void setIdkategori(String idkategori) {
		this.idkategori = idkategori;
	}

	public String getDesk() {
		return desk;
	}

	public void setDesk(String desk) {
		this.desk = desk;
	}
}

