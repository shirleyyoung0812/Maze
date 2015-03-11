package mazeDFS;

public interface DisjointSet {
	int find (int item);
	int union(int item1, int item2);
	int size();

}
