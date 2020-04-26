package algorithm.npComplete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
