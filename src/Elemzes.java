import java.util.ArrayList;

public class Elemzes implements Cloneable{
ArrayList<String> stack=new ArrayList<String>();
ArrayList<String> input=new ArrayList<String>();
ArrayList<String> sorhistory=new ArrayList<String>();
ArrayList<Node> tree=new ArrayList<Node>();
BottomUpParser bup;
Szabalyok szabalyok;
int nelemzo;
boolean success=false;
String historystring;
public Elemzes(ArrayList<String> in,ArrayList<String> stack,Szabalyok szabalyok,ArrayList<String> sorh,ArrayList<Node> t){
	this.stack=(ArrayList<String>) stack.clone();
	this.input=(ArrayList<String>) in.clone();
	this.sorhistory=(ArrayList<String>) sorh.clone();
	this.tree=(ArrayList<Node>) t.clone();
	this.szabalyok=szabalyok;
}
public Elemzes(){
	
}

	void elemez(){
		boolean end=false;
		ArrayList<String> r=new ArrayList<String>();
		while(end==false){

			//tryreduce and start Rulesi elemzes
			int NoR=0;
			int n2=0;
			int i2=0;
			String r2="";
			boolean elsor=false;
			for(int i=1;i<=szabalyok.maxRuleSize;i++){
				for(int n=0;n<stack.size()-i+1;n++){
					
					String[] arrayToRules=new String[i];
					for(int a=0;a<arrayToRules.length;a++){
						arrayToRules[a]=stack.get(n+a);
					}
					r=szabalyok.getSzabaly(arrayToRules);
					
					if(r!=null)
					NoR+=r.size();
					
					
					if(r!=null && r.size()>0 && elsor==true){
						for(int k=0;k<r.size();k++){
							bup.addNewElemzes(this,n,i,r.get(k));
						}
					}
					
					if(r!=null && r.size()>0 && elsor==false){
						r2=r.get(0);
						n2=n;
						i2=i;
						elsor=true;
						
						for(int k=1;k<r.size();k++){
							bup.addNewElemzes(this,n,i,r.get(k));
						}
					}
					

					
				}
			}
			
			historystring="";
			//reduce by the rule
			if(NoR>=1){
				//build the tree
				Node temp=new Node();
				temp.name=r2;
				for(int a=0;a<i2;a++){
					temp.addChild(tree.get(this.indexOf(stack.get(n2+a))));
				}
				tree.add(temp);
				
				//change the stack
				stack.set(n2, r2);
				historystring+=r2;
				for(int a=1;a<i2;a++){
					stack.remove(n2+1);
				}
				
			}
			
			
			//no rules,cant shift
			if(NoR==0 && input.size()==0){
				end=true;
				success=false;
			}
			
			//if no reduce then shift
			if(NoR==0 && input.size()>0){
				//add a node
				Node temp=new Node();
				temp.name=input.get(0);
				tree.add(temp);
				//push the stack
				stack.add(input.get(0));
				input.remove(0);
				historystring+="Shift";
			}
			
			
			//only S in stack and no input
			if(stack.get(0).equals("S") && stack.size()==1 && input.size()==0){
				end=true;
				success=true;
			}

			
			
				write(historystring);
			
			
		}
		
	}
	
	public void write(String his){
		String s="";
		s="Stack: ";
		for(int i=0;i<stack.size();i++){
			s+=stack.get(i)+" ";
		}
		
		int k=40-s.length();
		for(int i=0;i<k;i++)
			s+=" ";
		s+="Input: ";
		for(int i=0;i<input.size();i++){
			s+=input.get(i)+" ";
		}
		k=100-s.length();
		for(int i=0;i<k;i++)
			s+=" ";
		
		sorhistory.add(s+"Rule: "+his);
	}
	public int indexOf(String s){
		for(int i=0;i<tree.size();i++){
			if(tree.get(i).name.equals(s)){
				return i;
			}
		}
		return 0;
	}
}
