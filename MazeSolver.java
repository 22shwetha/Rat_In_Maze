package Package;
   
   import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
public class MazeSolver {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter the size of the maze (n): ");
	        int n = scanner.nextInt();

	        char[][] maze = generateMaze(n);
	        printMaze(maze);

	        while (true) {
	            System.out.println("\nOptions:");
	            System.out.println("1. Print the path");
	            System.out.println("2. Generate another puzzle");
	            System.out.println("3. Exit");
	            System.out.print("Choose an option: ");

	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    char[][] solvedMaze = solveMaze(maze);
	                    printMaze(solvedMaze);
	                    break;
	                case 2:
	                    maze = generateMaze(n);
	                    printMaze(maze);
	                    break;
	                case 3:
	                    System.out.println("Exiting the game. Goodbye!");
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid option. Please choose again.");
	            }
	        }
	    }

	    private static char[][] generateMaze(int n) {
	        char[][] maze = new char[n][n];
	        Random random = new Random();

	        // Generate maze with 25% walls
	        for (char[] row : maze) {
	            Arrays.fill(row, random.nextDouble() < 0.25 ? 'W' : 'O');
	        }

	        // Set start (S) and end (E)
	        maze[0][0] = 'S';
	        maze[n - 1][n - 1] = 'E';

	        return maze;
	    }

	    private static void printMaze(char[][] maze) {
	        for (char[] row : maze) {
	            for (char cell : row) {
	                switch (cell) {
	                    case 'S':
	                        System.out.print("Start ");
	                        break;
	                    case 'E':
	                        System.out.print("End ");
	                        break;
	                    case 'W':
	                        System.out.print("Wall ");
	                        break;
	                    case 'O':
	                        System.out.print("Open ");
	                        break;
	                    case 'P':
	                        System.out.print("Path ");
	                        break;
	                }
	            }
	            System.out.println();
	        }
	    }

	    private static char[][] solveMaze(char[][] maze) {
	        int n = maze.length;
	        char[][] markedMaze = new char[n][n];

	        // Initialize markedMaze with the original maze
	        for (int i = 0; i < n; i++) {
	            System.arraycopy(maze[i], 0, markedMaze[i], 0, n);
	        }

	        // BFS for pathfinding
	        Queue<int[]> queue = new LinkedList<>();
	        queue.offer(new int[]{0, 0});

	        while (!queue.isEmpty()) {
	            int[] current = queue.poll();
	            int row = current[0];
	            int col = current[1];

	            if (row == n - 1 && col == n - 1) {
	                // Reached the end
	                break;
	            }

	            // Explore neighbors
	            exploreNeighbor(row - 1, col, queue, markedMaze, maze);
	            exploreNeighbor(row + 1, col, queue, markedMaze, maze);
	            exploreNeighbor(row, col - 1, queue, markedMaze, maze);
	            exploreNeighbor(row, col + 1, queue, markedMaze, maze);
	        }

	        return markedMaze;
	    }

	    private static void exploreNeighbor(int row, int col, Queue<int[]> queue, char[][] markedMaze, char[][] maze) {
	        int n = maze.length;
	        if (row >= 0 && row < n && col >= 0 && col < n && markedMaze[row][col] == 'O') {
	            markedMaze[row][col] = 'P';
	            queue.offer(new int[]{row, col});
	        }
	    }

}
