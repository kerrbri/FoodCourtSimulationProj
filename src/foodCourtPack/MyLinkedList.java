package foodCourtPack;
/**
* @author Brianne Kerr
* April 18, 2017
*/
public class MyLinkedList<T>
{
	private Node head;
	private Node tail;
	private int size;
	
	public MyLinkedList()
	{
		head = new Node(null, null, null);
		tail = new Node(head, null, null);
		head.next = tail;
		size = 0;
	}
	
	/**
	 * This method creates a new node and adds it the end of the list
	 * 
	 * @param (value) the value to be stored in the new node
	 */
	public void addToBack(T value)
	{
		Node temp = new Node(tail.prev, tail, value);
		tail.prev = temp;
		temp.prev.next = temp;
		size++;
	}
	
	/**
	 * Given the node's position in line, this method removes it and adjusts the size
	 * 
	 * @param (pos) the position of the node to be removed
	 * @return the value of the removed node
	 */
	public T removeFromPosition(int pos)
	{
		
		Node temp = head.next;
		if(pos <= size && pos > 0)
		{
			for(int i = 1; i < pos; i++)
			{
				temp = temp.next;
			}
			size--;
		}
		T value = temp.value;
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
		return value;
		
	}
	
	/**
	 * Retrieves the value of the node from a given position
	 * 
	 * @param (pos) the position of the node to be returned
	 * @return the value of the retrieved node
	 */
	public T getFromPosition(int pos)
	{
		Node temp = head.next;
		if(pos <= size && pos > 0)
		{
			for(int i = 1; i < pos; i++)
			{
				temp = temp.next;
			}
			return temp.value;
		}
		return null;
	}
	
	/**
	 * Searches the linked list for the position of the first node that holds the
	 * given value. 
	 * 
	 * @param (e) the value being searched for
	 * @return the position in the list of the desired value, or -1 if the value
	 * is not in the list
	 */
	public int getPositionOfElement(T e)
	{
		Node temp = head.next;
		
			for(int i = 1; i <= size; i++)
			{
				if(e == temp.value)
				{
					return i;
				}
				temp = temp.next;
			}
		
		return -1;
	}
	
	/**
	 * Removes the last node in the list
	 * 
	 * @return the value of the last node in the list
	 */
	public T removeFromBack()
	{
		return this.removeFromPosition(size);
	}
	
	/**
	 * This method gives the value of one node the value of another, and then
	 * removes the former node. 
	 * @param (replaced) The position in line of the node that will gain the value of
	 * the second parameter
	 * @param (replacer) The position in line of the node whose value will be put in a 
	 * new position and then removed from its original place
	 */
	public void replaceFromInLine(int replaced, int replacer)
	{
	    Node tempOld = head.next;
	    Node tempNew = head.next;
		for(int i = 1; i < replaced; i++)
		{
			tempOld = tempOld.next;
		}
		
		for(int i = 1; i < replacer; i++)
		{
			tempOld = tempNew.next;
		}
		
		tempOld.value = tempNew.value;
		this.removeFromPosition(replacer);
		
	}
	
	/**
	 * 
	 * @return the amount of nodes with values in the list
	 */
	public int getSize()
	{
		return size;
	}
	
	private class Node
	{
		T value; //Value, the element to be added to lists
		Node prev; //The node in the previous position in line
		Node next; //The node in the next position in line
		
		
		/**
		 * Initializes values
		 * 
		 * @param (prev) Initializes this.prev 
		 * @param (next) Initializes this.next
		 * @param (value) Initializes this.value
		 */
		public Node(Node prev, Node next, T value)
		{
			this.prev = prev;
			this.next = next;
			this.value = value;
		}
	}
}
