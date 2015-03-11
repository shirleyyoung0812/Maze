package mazeDFS;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;
public class MazeTester {

	public static void main(String[] args) {
		final Maze m = new Maze(50, 50);
		Runnable run = new Runnable() {
			public void run() {
				JFrame frame = new JFrame() {
					private static final long serialVersionUID = 1L;
					public void paint(Graphics g) {
						m.display((Graphics2D) g);
					}
				};
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				GroupLayout layout = new GroupLayout(frame.getContentPane());
				frame.getContentPane().setLayout(layout);
				layout.setHorizontalGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGap(0, 600, Short.MAX_VALUE));
				layout.setVerticalGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGap(0, 600, Short.MAX_VALUE));
				frame.pack();
				frame.setVisible(true);
			}
		};
		java.awt.EventQueue.invokeLater(run); 
		SolveMaze sm = new SolveMaze(m);
		
		System.out.println("****BFS****");
		long startTime = System.nanoTime();
		sm.solveByBFS();
		System.out.println("Running time: " + (System.nanoTime() - startTime)/1000 + "ms");
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory in bytes: " + (memory));
	
		/*System.out.println("****DFS, recursion****");
		long startTime2 = System.nanoTime();
		sm.solveByDFS();
		System.out.println("Running time: " + (System.nanoTime() - startTime2)/1000 + "ms");
		Runtime runtime2 = Runtime.getRuntime();
		runtime2.gc();
		long memory2 = runtime2.totalMemory() - runtime2.freeMemory();
		System.out.println("Used memory in bytes: " + (memory2));*/
		
		/*System.out.println("****DFS, Stack****");
		long startTime3 = System.nanoTime();
		sm.solveByDFS2();
		System.out.println("Running time: " + (System.nanoTime() - startTime3)/1000 + "ms");
		Runtime runtime3 = Runtime.getRuntime();
		runtime3.gc();
		long memory3 = runtime3.totalMemory() - runtime3.freeMemory();
		System.out.println("Used memory in bytes: " + (memory3));*/
		/*for (Integer i : sm.path) {
			System.out.println(i);
		}*/
			
	}

}
