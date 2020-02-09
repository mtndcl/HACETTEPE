

class BinarySearchTree {
 
    
    class Node {
    	String key;
        Node left, right;
 
        public Node(String key) {
            this.key = key;
            left = right = null;
        }
    }
    Node root;
  
 
    Boolean  have;
    BinarySearchTree() { 
        root = null; 
        have=false;
        
    }
 
    void insert(String key) {
    	
    	search( root,  key);
    	if(!have){
    		 root = insertNode(root, key);
    	}
    
    }
     
    public Node search(Node root, String key){
       
        if (root==null ){
        	return root;
        }else if( root.key.compareTo(key)==0){
        	
        	have=true;
        	return root;
        }
        	
        have=false; 
     
      
        if (root.key.compareTo(key) >0){
        	
            return search(root.left, key);
        }
     
       
        return search(root.right, key);
    }
    Node insertNode(Node root, String key) {
 
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key.compareTo(root.key)<0) {
        	root.left = insertNode(root.left, key);
            
        }else if (key.compareTo(root.key)>0){
        	root.right = insertNode(root.right, key);
        }
      
        return root;
    }
    void deleteKey(String key)
    {
        root = deleteNode(root, key);
    }
 
   
    Node deleteNode(Node root, String key)
    {
      
        if (root == null){
        	
        	return root;
        }  
 
    	   if (key.compareTo(root.key)<0){
               root.left = deleteNode(root.left, key);
           }
           else if (key.compareTo(root.key)>0){
               root.right = deleteNode(root.right, key);
           }
     
       
        else
        {
         
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
            
            root.key = minimunkey(root.right);
 
           
            root.right = deleteNode(root.right, root.key);
        }
 
        return root;
    }
    String minimunkey(Node root)
    {
        String min = root.key;
        while (root.left != null)
        {
            min = root.left.key;
            root = root.left;
        }
        return min;
    }

}
