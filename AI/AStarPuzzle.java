package AI;
import java.util.*;

class State implements Comparable<State> {
    int[][] a = new int[3][3];
    int x, y, g, h;

    public int compareTo(State s) {
        return (this.g + this.h) - (s.g + s.h);
    }
}

public class AStarPuzzle {
    static int[][] goal = new int[3][3];

    static void print(State s) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(s.a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    static boolean isGoal(State s) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (s.a[i][j] != goal[i][j])
                    return false;
        return true;
    }

    static int heuristic(State s) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = s.a[i][j];
                if (val != 0) {
                    for (int r = 0; r < 3; r++) {
                        for (int c = 0; c < 3; c++) {
                            if (goal[r][c] == val) {
                                h += Math.abs(i - r) + Math.abs(j - c);
                            }
                        }
                    }
                }
            }
        }
        return h;
    }

    static boolean valid(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    static String getKey(State s) {
        String key = "";
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                key += s.a[i][j];
        return key;
    }

    static State inputState(Scanner sc, String msg) {
        State s = new State();
        System.out.println(msg);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter value at (" + i + "," + j + "): ");
                s.a[i][j] = sc.nextInt();
                if (s.a[i][j] == 0) {
                    s.x = i;
                    s.y = j;
                }
            }
        }
        s.g = 0;
        return s;
    }

    static void solve(State start) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        HashSet<String> vis = new HashSet<>();

        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { -1, 1, 0, 0 };

        start.h = heuristic(start);
        pq.add(start);
        vis.add(getKey(start));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            System.out.println("Current State:");
            print(cur);
            System.out.println("Number of moves: " + cur.g);
            System.out.println("Heuristic cost: " + cur.h);
            System.out.println("-------------------");

            if (isGoal(cur)) {
                System.out.println("Goal State Reached!");
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if (valid(nx, ny)) {
                    State next = new State();

                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 3; j++)
                            next.a[i][j] = cur.a[i][j];

                    int temp = next.a[cur.x][cur.y];
                    next.a[cur.x][cur.y] = next.a[nx][ny];
                    next.a[nx][ny] = temp;

                    next.x = nx;
                    next.y = ny;
                    next.g = cur.g + 1;
                    next.h = heuristic(next);

                    String key = getKey(next);
                    if (!vis.contains(key)) {
                        pq.add(next);
                        vis.add(key);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        State start = inputState(sc, "Enter initial state (0 for blank):");

        System.out.println("Enter goal state:");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                goal[i][j] = sc.nextInt();

        System.out.println("Initial State:");
        print(start);

        solve(start);
    }
}