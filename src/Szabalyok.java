import java.util.ArrayList;

public class Szabalyok {

	
	ArrayList<String[]> allRules=new ArrayList<String[]>();
	int maxRuleSize=3;
	/*						Kapcsolo Szabályok:
							{"U","T","S1"},
							{"U","T","S2"},
							{"TR","T","TR"},
							{"TR","T","T"},
							{"S2","H","S"},
							{"KAPCS2","K","U2"},
							//{"a","lámpát","T"},
							//{"a","tévét","T"},
							//{"a","rádiót","T"},


							{"S1","S"},
							{"U1","U"},
							{"U2","U"},
							{"KAPCS1","U1"},
							{"kapcsold","KAPCS1"},
							{"kapcsold","KAPCS2"},
							{"be","K"},
							{"ki","K"},
							{"le","K"},
							{"fel","K"},

							 
							 
						
							
*/

	ArrayList<String> Targyak=new ArrayList<String>();
	ArrayList<String> TargyakN=new ArrayList<String>();
	ArrayList<String> RoomNumbers=new ArrayList<String>();
	ArrayList<String> TargyNevelo=new ArrayList<String>();
	ArrayList<String> RoomNevelo=new ArrayList<String>();
	
	public Szabalyok(SqliteDB db,int maxR){
			this.maxRuleSize=maxR;
			Targyak=db.getDeviceTNames();
			TargyakN=db.getDeviceNames();
			RoomNumbers=db.getRoomNumbers();
			TargyNevelo=db.getDeviceNevelo();
			RoomNevelo=db.getRoomNevelo();
			createSzabalyok();
	}
	public void createSzabalyok(){
		
		allRules.add(new String[]{"S1","S"});
		allRules.add(new String[]{"U1","U"});
		allRules.add(new String[]{"U2","U"});
		allRules.add(new String[]{"KAPCS1","U1"});
		allRules.add(new String[]{"kapcsold","KAPCS1"});
		allRules.add(new String[]{"kapcsold","KAPCS2"});
		allRules.add(new String[]{"be","K"});
		allRules.add(new String[]{"ki","K"});
		allRules.add(new String[]{"le","K"});
		allRules.add(new String[]{"fel","K"});
		allRules.add(new String[]{"mindenhol","H"});
		
		allRules.add(new String[]{"U","T","S1"});
		allRules.add(new String[]{"U","T","S2"});
		allRules.add(new String[]{"S2","H","S"});
		allRules.add(new String[]{"KAPCS2","K","U2"});

		
		for(int i=0;i<Targyak.size();i++){
			String s=Targyak.get(i).toLowerCase();
			allRules.add(new String[]{TargyNevelo.get(i),s,"T"});
			allRules.add(new String[]{TargyNevelo.get(i),s,"TR"});
			allRules.add(new String[]{"TR","T","T"});
			allRules.add(new String[]{"TR","T","TR"});
		}
		

		for(int i=0;i<TargyakN.size();i++){
			String s=TargyakN.get(i).toLowerCase();
			allRules.add(new String[]{TargyNevelo.get(i),s,"TN"});
		}
		

		for(int i=0;i<RoomNumbers.size();i++){
			allRules.add(new String[]{RoomNevelo.get(i),RoomNumbers.get(i),"szobában","H"});
			allRules.add(new String[]{RoomNevelo.get(i),RoomNumbers.get(i),"szobába","H"});
		}
		
		//kerdesek:
		allRules.add(new String[]{"KSZ","TN","H","S"});
		allRules.add(new String[]{"K","van","kapcsolva","KSZ"});
		
		allRules.add(new String[]{"mi","van","MV"});
		allRules.add(new String[]{"mik","vannak","MV"});
		allRules.add(new String[]{"MV","H","S"});
		
		
	}
	
	public ArrayList<String> getSzabaly(String[] s){		
		ArrayList<String> res=null;
			for(String[] str : allRules){
				if(str.length==s.length+1){
					boolean ruleMatch=true;
					for(int i=0;i<s.length;i++){
						if(!s[i].equals(str[i])){
							ruleMatch=false;
						}
					}
					if(ruleMatch==true){
						if(res==null)
							res=new ArrayList<String>();
						res.add(str[str.length-1]);
					}
				}
			}
			
			
		return res;
	}
	
	public void DoCommand(Node n){
		if(n.name.equals("U")){
			Node.findNode(n, "ki");
			if(Node.van){
				System.out.println("ki van!");
			}
			Node.findNode(n, "be");
			if(Node.van){
				System.out.println("be van!");
			}
		}
		if(n.name.equals("T")){
			Node.findNode(n, "tévét");
			if(Node.van){
				System.out.println("tévét van!");
			}
			Node.findNode(n, "rádiót");
			if(Node.van){
				System.out.println("rádiót van!");
			}
		}
		
	}
}
