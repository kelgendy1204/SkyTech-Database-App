package logic;

import java.util.Stack;

public class SizedStack<T> extends Stack<T> {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7962245097051778883L;
	private int maxSize;

    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public T push(T item) {
    	
    	while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(item);
    }
   
}