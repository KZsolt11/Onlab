import java.util.ArrayList;

public class BottomUpParser {

ArrayList<String> input=new ArrayList<String>();
Szabalyok sz;
ArrayList<Elemzes> elemzesek=new ArrayList<Elemzes>();
int nelemzo=0;
SqliteDB db;
ArrayList<Node> STree;
	
	public BottomUpParser(SqliteDB db){
		this.db=db;
	}

	public void elemezd(String in){
		in=in.toLowerCase();
		String [] s= in.split(" ");
		for(int i=0;i<s.length;i++)
			input.add(s[i]);
		
		sz=new Szabalyok(db,input.size());
		
		//1. elemzõ indítása:
		elemzesek.add(new Elemzes());
		elemzesek.get(0).input=input;
		elemzesek.get(0).szabalyok=sz;
		elemzesek.get(0).bup=this;
		elemzesek.get(0).nelemzo=0;
		elemzesek.get(0).elemez();
		
	}
	public void addNewElemzes(Elemzes e,int n,int i,String s){
		nelemzo++;
		Elemzes temp=new Elemzes(e.input,e.stack,e.szabalyok,e.sorhistory,e.tree);
		//build the tree
		Node Ntemp=new Node();
		Ntemp.name=s;
		for(int a=0;a<i;a++){
			Ntemp.addChild(temp.tree.get(temp.indexOf(temp.stack.get(n+a))));
		}
		temp.tree.add(Ntemp);
		
		temp.stack.set(n, s);
		for(int a=1;a<i;a++){
			temp.stack.remove(n+1);
		}
		
		temp.write(s);
		temp.bup=this;
		temp.nelemzo=nelemzo;
		elemzesek.add(temp);
		elemzesek.get(elemzesek.size()-1).elemez();
		
	}
	public void write(){
		int db=0;
		for(Elemzes e :elemzesek){
			
				System.out.println("----------------------------------------------");
				System.out.println("\n"+db+". Elemzes:\n");
				if(e.success){
					for(String s: e.sorhistory){
						System.out.println(s);
					}
					STree=e.tree;
				}
				db++;
		}
		DoCommands();
	}
	public void DoCommands(){
		if(STree!=null){
			for(Node n : STree){
				sz.DoCommand(n);
			}
		}
	}
	
	
	
}
