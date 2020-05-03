package algorithm.npComplete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
In this assignment you will implement one or more algorithms for the 2SAT problem. Here are 6 different 2SAT instances:

2sat1.txt
2sat2.txt
2sat3.txt
2sat4.txt
2sat5.txt
2sat6.txt
The file format is as follows. In each instance, the number of variables and the number of clauses is the same,
and this number is specified on the first line of the file. Each subsequent line specifies a clause via its two literals,
with a number denoting the variable and a "-" sign denoting logical "not". For example,
the second line of the first data file is "-16808 75250", which indicates the clause ¬x16808?x75250.

Your task is to determine which of the 6 instances are satisfiable, and which are unsatisfiable.
In the box below, enter a 6-bit string, where the ith bit should be 1 if the ith instance is satisfiable, and 0 otherwise.
For example, if you think that the first 3 instances are satisfiable and the last 3 are not, then you should enter the string 111000 in the box below.

DISCUSSION: This assignment is deliberately open-ended, and you can implement whichever 2SAT algorithm you want.
For example, 2SAT reduces to computing the strongly connected components of a suitable graph
(with two vertices per variable and two directed edges per clause, you should think through the details).
This might be an especially attractive option for those of you who coded up an SCC algorithm in Part 2 of this specialization.
Alternatively, you can use Papadimitriou's randomized local search algorithm.
(The algorithm from lecture is probably too slow as stated, so you might want to make one or more simple modifications to
it --- even if this means breaking the analysis given in lecture --- to ensure that it runs in a reasonable amount of time.)
A third approach is via backtracking.
In lecture we mentioned this approach only in passing; see Chapter 9 of the Dasgupta-Papadimitriou-Vazirani book,
for example, for more details.
 */
public class SAT {

    public static List<Integer>[] adj;
    public static List<Integer>[] adjr;
    public static boolean[] visited;
    public static int[] order;
    public static int[] finish;
    public static int[] leader;
    public static int parent = 0;
    public static int time = 0;

    public static void main(String[] args)  throws IOException {
        for(int i = 1; i <= 6; i++) {
            SAT("resource/2sat" + i  + ".txt");
            parent = 0;
            time = 0;
        }
    }

    static void SAT(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        int n = Integer.parseInt(br.readLine());

        adj = new ArrayList[2*n+1];
        adjr = new ArrayList[2*n+1];

        for(int i=0; i<=2*n; i++)
        {
            adj[i] = new ArrayList<>();
            adjr[i] = new ArrayList<>();
        }


        for(int i=1; i<=n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(x > 0)
            {
                if(y > 0)
                {
                    adj[n+x].add(y); adj[n+y].add(x);
                    adjr[y].add(n+x); adjr[x].add(n+y);
                }
                else
                {
                    adj[n+x].add(n-y); adj[-y].add(x);
                    adjr[n-y].add(n+x); adjr[x].add(-y);
                }
            }
            else
            {
                if(y > 0)
                {
                    adj[-x].add(y); adj[n+y].add(n-x);
                    adjr[y].add(-x); adjr[n-x].add(n+y);
                }
                else
                {
                    adj[-x].add(n-y); adj[-y].add(n-x);
                    adjr[n-y].add(-x); adjr[n-x].add(-y);
                }
            }

        }
        visited = new boolean[2*n+1];
        order = new int[2*n+1];
        finish = new int[2*n+1];
        leader = new int[2*n+1];

        for(int i=2*n; i>0; i--)
        {
            if(!visited[i])
                dfs_reverse(i);
            order[finish[i]] = i;
        }

        visited = new boolean[2*n+1];

        for(int i=2*n; i>0; i--)
            if(!visited[order[i]])
            {
                parent = order[i];
                dfs(order[i]);
            }

        boolean satisfied = true;
        for(int i=1; i<=n; i++)
            if(stronglyConnected(i, i+n))
                satisfied = false;

        System.out.println(satisfied);

        br.close();
    }

    static void dfs_reverse(int i)
    {
        visited[i] = true;
        for(int next : adjr[i])
            if(!visited[next])
                dfs_reverse(next);
        time++;
        finish[i] = time;
    }


    static void dfs(int i)
    {
        visited[i] = true;
        leader[i] = parent;
        for(int next : adj[i])
            if(!visited[next])
                dfs(next);
    }


    static boolean stronglyConnected(int u, int v)
    {
        return leader[u] == leader[v];
    }
}
