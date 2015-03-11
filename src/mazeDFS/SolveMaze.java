package mazeDFS;
import java.util.*;
public class SolveMaze {
	
	List<Integer> path;
	private Maze m;
	
	public SolveMaze(Maze m) {
		path = new ArrayList<Integer> ();
		this.m = m;
	}
	/**
	 * Using DFS to solve the maze
	 */
	public void solveByDFS() {
		boolean[] visited = new boolean[m.grid.length];
		//Stack<Integer> stack = new Stack<Integer> ();
		dfs(path, visited, 0);
	}
	//using recursion
	private void dfs(List<Integer> path, boolean[] visited, int curr) {
		if (curr == m.grid.length - 1) {
			visited[curr] = true; 
			path.add(curr);
			return;
		}
		path.add(curr);
		visited[curr] = true;
		int cell = m.grid[curr];
		if ((cell & Maze.LEFT) == 0 && (curr - 1) >= 0 && !visited[curr - 1] && !visited[m.grid.length - 1] && !visited[m.grid.length - 1])
			dfs(path, visited, curr - 1);
		if ((cell & Maze.RIGHT) == 0 && (curr + 1) < m.grid.length && !visited[curr + 1] && !visited[m.grid.length - 1])
			dfs(path, visited, curr + 1);
		if ((cell & Maze.UP) == 0 && (curr - m.columns) >= 0 && !visited[curr - m.columns] && !visited[m.grid.length - 1])
			dfs(path,visited, curr - m.columns);
		if ((cell & Maze.DOWN) == 0 && (curr + m.columns) < m.grid.length && !visited[curr + m.columns] && !visited[m.grid.length - 1])
			dfs(path, visited, curr + m.columns);
		if (visited[m.grid.length - 1])
			return;
		path.remove(path.size() - 1);
	}
	//using a stack
	public void solveByDFS2() {
		Stack<Integer> stack = new Stack<Integer> ();
		boolean[] visited = new boolean[m.grid.length];
		int[] distTo = new int[m.grid.length];
		int[] predecessor = new int[m.grid.length];
		Arrays.fill(distTo, Integer.MAX_VALUE);
		stack.push(0);
		visited[0] = true;
		distTo[0] = 0;
		predecessor[0] = -1;
		while (!stack.isEmpty() && !visited[m.grid.length - 1]) {
			int curr = stack.pop();
			int cell = m.grid[curr];
			if (curr == m.grid.length - 1) {
				break;
			}
			if ((cell & Maze.LEFT) == 0 && (curr - 1) >= 0 && !visited[curr - 1]) {
				stack.push(curr - 1);
				visited[curr - 1] = true;
				distTo[curr - 1] = distTo[curr] + 1;
				predecessor[curr - 1] = curr;
			}
			if ((cell & Maze.RIGHT) == 0 && (curr + 1) < m.grid.length && !visited[curr + 1]) {
				stack.push(curr + 1);
				visited[curr + 1] = true;
				distTo[curr + 1] = distTo[curr] + 1;
				predecessor[curr + 1] = curr;
			}
			if ((cell & Maze.UP) == 0 && (curr - m.columns) >= 0 && !visited[curr - m.columns]) {
				stack.push(curr - m.columns);
				visited[curr - m.columns] = true;
				distTo[curr - m.columns] = distTo[curr] + 1;
				predecessor[curr - m.columns] = curr;
			}
			if ((cell & Maze.DOWN) == 0 && (curr + m.columns) < m.grid.length && !visited[curr + m.columns]) {
				stack.push(curr + m.columns);
				visited[curr + m.columns] = true;
				distTo[curr + m.columns] = distTo[curr] + 1;
				predecessor[curr + m.columns] = curr;
			}
		}
		int x;
		for (x = m.grid.length - 1; distTo[x] != 0; x = predecessor[x]) {
			path.add(x);
		}
		path.add(0);
		Collections.reverse(path);
		
	}
	
	public void solveByBFS() {
		Queue<Integer> q = new LinkedList<Integer> ();
		boolean[] visited = new boolean[m.grid.length];
		int[] distTo = new int[m.grid.length];
		int[] predecessor = new int[m.grid.length];
		Arrays.fill(distTo, Integer.MAX_VALUE);
		q.offer(0);
		visited[0] = true;
		distTo[0] = 0;
		predecessor[0] = -1;
		while (!q.isEmpty() && !visited[m.grid.length - 1]) {
			int curr = q.poll();
			int cell = m.grid[curr];
			if (curr == m.grid.length - 1) {
				break;
			}
			if ((cell & Maze.LEFT) == 0 && (curr - 1) >= 0 && !visited[curr - 1]) {
				q.offer(curr - 1);
				visited[curr - 1] = true;
				distTo[curr - 1] = distTo[curr] + 1;
				predecessor[curr - 1] = curr;
			}
			if ((cell & Maze.RIGHT) == 0 && (curr + 1) < m.grid.length && !visited[curr + 1]) {
				q.offer(curr + 1);
				visited[curr + 1] = true;
				distTo[curr + 1] = distTo[curr] + 1;
				predecessor[curr + 1] = curr;
			}
			if ((cell & Maze.UP) == 0 && (curr - m.columns) >= 0 && !visited[curr - m.columns]) {
				q.offer(curr - m.columns);
				visited[curr - m.columns] = true;
				distTo[curr - m.columns] = distTo[curr] + 1;
				predecessor[curr - m.columns] = curr;
			}
			if ((cell & Maze.DOWN) == 0 && (curr + m.columns) < m.grid.length && !visited[curr + m.columns]) {
				q.offer(curr + m.columns);
				visited[curr + m.columns] = true;
				distTo[curr + m.columns] = distTo[curr] + 1;
				predecessor[curr + m.columns] = curr;
			}
		}
		int x;
		for (x = m.grid.length - 1; distTo[x] != 0; x = predecessor[x]) {
			path.add(x);
		}
		path.add(0);
		Collections.reverse(path);	
	}
}
