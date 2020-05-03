package algorithm.npComplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

/*
In this assignment you will implement one or more algorithms for the traveling salesman problem,
such as the dynamic programming algorithm covered in the video lectures. Here is a data file describing a TSP instance.

tsp.txt
The first line indicates the number of cities. Each city is a point in the plane,
and each subsequent line indicates the x- and y-coordinates of a single city.

The distance between two cities is defined as the Euclidean distance --- that is,
two cities at locations (x,y)(x,y) and (z,w)(z,w) have distance \sqrt{(x-z)^2 + (y-w)^2}
(x?z)
2
 +(y?w)
2

?	  between them.

In the box below, type in the minimum cost of a traveling salesman tour for this instance, rounded down to the nearest integer.

OPTIONAL: If you want bigger data sets to play with, check out the TSP instances from around the world here.
The smallest data set (Western Sahara) has 29 cities, and most of the data sets are much bigger than that.
What's the largest of these data sets that you're able to solve --- using dynamic programming or, if you like,
a completely different method?

HINT: You might experiment with ways to reduce the data set size. For example, trying plotting the points.
Can you infer any structure of the optimal solution? Can you use that structure to speed up your algorithm?
 */
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
