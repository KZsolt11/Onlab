
public class Main {


	public static void main(String[] args) {
		
		SqliteDB db=new SqliteDB();
		BottomUpParser bup=new BottomUpParser(db);
		bup.elemezd("Kapcsold be a t�v�t a 201-es szob�ban");
		bup.write();

	}

}
