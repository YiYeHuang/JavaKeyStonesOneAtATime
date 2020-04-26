package algorithm.npComplete;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TSP {

    public TSP(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("resource/tsp.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine())-1;

        double x[] = new double[n];
        double y[] = new double[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        double srcx = Double.parseDouble(st.nextToken());
        double srcy = Double.parseDouble(st.nextToken());

        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        double dist[][] = new double[n][n];

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(i != j)
                    dist[i][j] = dist(x[i],y[i],x[j],y[j]);

        //System.out.println(n);
        double dp[][] = new double[(1<<n)][n];

        for(int curr=1; curr<(1<<n); curr++)
        {
            if((curr & (curr-1)) == 0 )
            {
                int i = 0;
                while(((1<<i) & curr) == 0) i++;
                dp[curr][i] = dist(srcx,srcy,x[i],y[i]);
                //System.out.println(curr+" "+i+" -  "+dp[curr][i]);
            }
            else
            {
                int i = 0;
                while((1<<i) <= curr)
                {
                    if(((1<<i) & curr) != 0)
                    {
                        int j = 0;
                        double res = -1;

                        while((1<<j) <= curr)
                        {
                            if(((1<<j) & curr)!= 0 && i != j)
                            {
                                if(res == -1)
                                    res = dp[curr^(1<<i)][j] + dist[j][i];
                                else
                                    res = Math.min(res, dp[curr^(1<<i)][j] + dist[j][i]);
                            }
                            j++;
                        }
                        dp[curr][i] = res;
                        //System.out.println(curr+" "+i+" "+res);
                    }
                    i++;
                }

            }
        }
        double min = Double.MAX_VALUE;

        for(int i=0; i<n; i++)
        {
            min = Math.min(min, dp[(1<<n)-1][i] + dist(srcx,srcy,x[i],y[i]));
            //System.out.println(dp[(1<<n)-1][i] +" "+dist(srcx,x[i],srcy,y[i])+" "+((1<<n)-1));
        }

        System.out.println(min);
    }

    static double dist(double x1,double y1,double x2, double y2)
    {
        //System.out.println(x1+" "+y1+" "+x2+" "+y2);
        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }

    public static void main(String[] args) throws IOException {
        TSP tsp = new TSP("resource/tsp.txt");
    }

}
