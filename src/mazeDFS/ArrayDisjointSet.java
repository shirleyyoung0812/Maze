package mazeDFS;
import java.util.*;
public class ArrayDisjointSet implements DisjointSet {
	//roots tracks the root of each subset
	private int[] roots;
	//number of sets in the union
	private int size;
	//rank return the depth of the tree, 
	//if using up-tree to represent sets
	private int[] rank;
	/**
	 * create a new disjoint set, initially each item is the representative 
	 * of  its own set
	 * @param size:  initial number of sets
	 */
	//
	public ArrayDisjointSet(int size) {
		this.roots = new int[size];
		for (int i = 0; i < size; i++)
			this.roots[i] = i;
		this.rank = new int[size];
		Arrays.fill(rank, 1);
		this.size = size;
		//System.out.println(size);
		/*for (int i : rank)
			System.out.println(i);*/
	}
	
	@Override
	public int find(int item) {
		if (roots[item] != roots[roots[item]])
			roots[item] = find(roots[item]);
			return roots[item];
	}

	@Override
	public int union(int item1, int item2) {
		int root1 = find(item1);
		int root2 = find(item2);
		size--;
		if (root1 == root2)
			throw new IllegalArgumentException("Already in the same group!");
		//pick the root of the deeper tree as the new root
		if (rank[root1] >= rank[root2])
		{
			roots[root2] = root1;
			rank[root1] += rank[root2];
			//System.out.println("New root: " + root1);
			return root1;
			
		}
		else {
			roots[root1] = root2;
			rank[root2] += rank[root1];
			//System.out.println("New root: " + root2);
			return root2;
		}
	}
	public int size() {
		return this.size;
	}

}
