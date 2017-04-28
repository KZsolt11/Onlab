import java.util.ArrayList;

public class Node{
String name;
ArrayList<Node> children;
Node parent;

	public Node(){}
	public Node(String s){name=s;}	
	public void addChild(Node n){
		if(children==null){
			children=new ArrayList<Node>();
		}
		n.parent=this;
		children.add(n);
	}
	static String pName;
	static boolean van;
	static void keres(Node root,String s){
		if(root.name.equals(s)){
			pName=root.name;
			van=true;
		}else{
			if(root.children!=null){
				for(Node child : root.children){
					keres(child,s);
				}
			}
			
		}
	}
	
	public static void findNode(Node root,String s){
		van=false;
		keres(root,s);
	}
}
