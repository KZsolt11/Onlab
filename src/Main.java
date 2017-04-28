
public class Main {


	public static void main(String[] args) {
		
		SqliteDB db=new SqliteDB();
		BottomUpParser bup=new BottomUpParser(db);
		bup.elemezd("Kapcsold be a tévét a 201-es szobában");
		bup.write();

	}

}
