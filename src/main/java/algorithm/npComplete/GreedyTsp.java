package algorithm.npComplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class GreedyTsp {
    public static void main(String[] args)  throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("resource/tsp_large.txt"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        double x[] = new double[n];
        double y[] = new double[n];

        for(int i=0; i<n; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        HashSet<Integer> set = new HashSet<Integer>();

        for(int i=1; i<n; i++)
        {
            set.add(i);
        }


        int curr = 0;
        double ans = 0;
        while(set.size() > 0)
        {
            Iterator<Integer> ir = set.iterator();
            double min = Double.MAX_VALUE;
            int mini = -1;

            while(ir.hasNext())
            {
                int j = ir.next();
                double dist = dist(x[curr],y[curr],x[j],y[j]);
                if(dist < min)
                {
                    min = dist;
                    mini = j;
                }
                else if(dist == min)
                {
                    mini = Math.min(mini, j);
                }
            }

            ans += min;
            set.remove(mini);
            curr = mini;

        }

        ans += dist(x[curr],y[curr],x[0],y[0]);
        System.out.println(ans);

    }
    static double dist(double x1,double y1,double x2, double y2)
    {

        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }
}
