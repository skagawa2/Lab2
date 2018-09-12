import edu.illinois.cs.cs125.lib.mazemaker.Maze;

/**
 * Solve a randomly-generated maze.
 *
 * @see <a href="https://github.com/cs125-illinois/mazemaker">Mazemaker on GitHub</a>
 * @see <a href="https://cs125-illinois.github.io/mazemaker/">Mazemaker Documentation</a>
 * @see <a href="https://cs125.cs.illinois.edu/lab/2/#maze">Lab 2 Writeup</a>
 */
@SuppressWarnings("checkstyle:emptyblock")
public class SolveMaze {

    /**
     * Implement your maze solving algorithm in the main method below.
     *
     * @param unused unused input arguments
     */

    public static void main(final String[] unused) {
        /*
         * Create a new 10 x 10 maze. Feel free to change these values.
         */
        Maze maze = new Maze(200, 200);

        /*
         * Pick (0, 0), the bottom left corner, as the starting point.
         * Put the end in the top right corner.
         */
        maze.startAtZero();
        maze.endAtTopRight();

        double maxSteps = 1e7;

        // Deterministic (hug right wall)
        long startTime = System.currentTimeMillis();
        int step = 0;
        while (++step < maxSteps && !maze.isFinished()) {
            deterministic(maze);
        }

        if (maze.isFinished()) {
            System.out.println("Deterministic solved the " + maze.getxDimension() + " by " + maze.getyDimension()
                    + " maze in " + step + " steps. (Took "
                    + (System.currentTimeMillis() - startTime) + " ms to complete.)");
        } else {
            System.out.println("Deterministic was not able to solve the maze in " + step + " steps.");
        }


        // Reset maze
        maze.startAtZero();

        // Random Walk
        startTime = System.currentTimeMillis();
        step = 0;
        while (++step < maxSteps && !maze.isFinished()) {
            randomWalk(maze);
        }

        if (maze.isFinished()) {
            System.out.println("RandomWalk solved the " + maze.getxDimension() + " by " + maze.getyDimension()
                    + " maze in " + step + " steps. (Took "
                    + (System.currentTimeMillis() - startTime) + " ms to complete.)");
        } else {
            System.out.println("RandomWalk was not able to solve the maze in " + step + " steps.");
        }
    }

    private static void deterministic(final Maze maze) {
        maze.turnRight();
        if (!maze.canMove()) {
            while (!maze.canMove()) {
                maze.turnLeft();
            }
        }
        maze.move();
    }

    private static void randomWalk(final Maze maze) {
        if (!maze.canMove()) {
            for (int i = 0; i < (int)(Math.random() * 4); i++) {
                if (Math.random() < 0.5) {
                    maze.turnLeft();
                } else {
                    maze.turnRight();
                }
            }
        }
        maze.move();
    }
}
