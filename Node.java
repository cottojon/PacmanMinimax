import java.util.*;

public class Node<T>
{
	private Node parent = null;
	private List<Node<T>> children = new ArrayList<Node<T>>();
	private T data = null;
	
	//constructor to pass in data
	public Node(T data)
	{
		this.data = data;
	}
	
	//constructor to pass in data and parent
	public Node(T data, Node<T> parent)
	{
		this.data = data;
		this.parent = parent;
	}
	
	public List<Node<T>> getChildren()
	{
		return children;
	}
	
	public void addChild(T data)
	{
		Node<T> child = new Node<T>(data);
		child.setParent(this);
		this.children.add(child);
	}
	
	@SuppressWarnings("unchecked")
	public void setParent(Node<T> parent)
	{
		parent.addChild((T) this);
		this.parent = parent;
	}
	
	public boolean isRoot()
	{
		return (this.parent == null);
	}
	
	public boolean isLeaf()
	{
		return this.children.size() == 0;
	}
	
	public void removeParent()
	{
		this.parent = null;
	}
	
	public T getData()
	{
		return this.data;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
	
	
	
}
