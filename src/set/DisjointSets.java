package set;

import java.util.Arrays;

public class DisjointSets {
	//array[x] stores the root of the tree (representative of the set)
	//if array[x] is less than 0, that means x is the root of the tree (representative of the set)
	private int[] array;
	
	//initial number of elements;
	//also the initial number of disjoint sets
	//since every element is initially in its own set
	public DisjointSets(int numElements) {
		array = new int [numElements];
		//since every element is the root of its tree,
		//set each element in the array to negative
		Arrays.fill(array, -1);
	}
	/**
	   *  union() unites two disjoint sets into a single set.  A union-by-rank
	   *  heuristic is used to choose the new root.  This method will corrupt
	   *  the data structure if root1 and root2 are not roots of their respective
	   *  sets, or if they're identical.
	   *
	   *  @param root1 the root of the first set.
	   *  @param root2 the root of the other set.
	   **/
	public void union(int root1, int root2) {
		if (array[root2] < array[root1]) 
			array[root1] = root2; // root2 is taller; make root2 new root
		else {
			if (array[root1] == array[root2]) 
				array[root1]--;
			array[root2] = root1; // root1 equal or taller; make root1 new root
		}
	}
	
	public int find(int x) {
		if (array[x] < 0)
			return x; //x is the root of the tree; return it
		else {
			//find the root of the tree
			array[x] = find(array[x]);
			return array[x];
		}
	}
	public static void main(String[] args) {
		int NumElements = 16;
		int NumInSameSet = 4;
		DisjointSets s = new DisjointSets(NumElements);
		int set1, set2;
		for (int k = 1; k < NumInSameSet; k *= 2) {
			for (int j = 0; j + k < NumElements; j += 2 * k) {
				set1 = s.find(j);
				System.out.println("set1: " + set1);
				set2 = s.find(j + k);
				System.out.println("set2: " + set2);
				s.union(set1, set2);
			}
		}
		for (int i = 0; i < NumElements; i++) {
			System.out.print(s.find(i) + "*");
			if (i % NumInSameSet == NumInSameSet - 1)
				System.out.println();
		}
		System.out.println();	
	}
}
