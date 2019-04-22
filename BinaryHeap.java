import java.util.*;

public class BinaryHeap {

	private int[] hdata;
	private int size;
	
	public BinaryHeap() {
		hdata =  new int[10];
		size = 0;
	}

	// add integers to a function in the form of a complete tree
	public void add(int item) { // O(nlgn) // 1. place in last position on last level, 2. compare vs its parent, swap if necessary, 3. recursively apply 2.

		if(hdata.length == (size+1)){
            doublearr();
        }

		hdata[size++] = item;
		int current = size-1;
		int parent = (current-1)/2;

		while ((current != 0) && (hdata[current] < hdata[parent])) {
			swap(hdata, current, parent);
			current = parent;
			parent = (parent-1)/2;
		}
	}

	// removes the root of the tree
	public int remove() { // 1. store the root into a variable so we can return that later, 2. swap the rightmost child on last level to root, 3. compare root vs higher priority child and swap if necessary, 4. recursively apply 3, 5. return value.
		if (size == 0) {
			return -1;
		}
		swap(hdata, 0, size-1);
		--size;
		if(size > 0) {
			shiftdown(0);
		}
		return hdata[size];
	}


	public void shiftdown(int pos) {
		int smallest;
		while(!(isLeaf(pos))){
			smallest = leftchild(pos);
			if((smallest < size-1) && (hdata[smallest] > hdata[smallest+1])){
				smallest = smallest+1;
			}
			if(hdata[pos] <= hdata[smallest]) {
				return;
            }
			swap(hdata, pos, smallest);
			pos = smallest;
		}	
	}

	// doubles array
	private void doublearr(){
        hdata = Arrays.copyOf(hdata, hdata.length*2);
    }

    // swaps integers in the array
	private void swap(int[] a, int left, int right) {
		int temp = a[left];
		a[left] = a[right];
		a[right] = temp;
	}

	// obtains the leftmost child
	private int leftchild(int pos) {
		return 2*pos+1;
	}

	// checks to see whether it is a leaf or not
	private boolean isLeaf(int pos) {
		return ((pos >= (size)/2) && (pos < size));
	}
}