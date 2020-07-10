/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

// does copies all subsegments
public class FastCollinearPoints {
    private LineSegment[] lines = new LineSegment[1];
    public FastCollinearPoints(Point[] points) {
        ArrayList<LineSegment> lineSegmentsList = new ArrayList<>();
        Point p;
        Point s;
        int validSlopes = 1;
        double pqSlope = 0;
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            p = points[i];
            validSlopes = 1;
            if( i > points.length - 3) {
                break;
            }
            for (int j = i + 2; j < points.length; j++) {
                s = points[j];
                if (validSlopes == 1) {
                    pqSlope = points[i].slopeTo(s);
                    validSlopes++;
                }
                if (pqSlope == points[i].slopeTo(s)) {
                    validSlopes++;
                    if (validSlopes >= 3 && j == points.length -1) {
                        lineSegmentsList.add(new LineSegment(p, points[j]));
                        break;
                    }
                }
                else if (pqSlope != points[i].slopeTo(s) && validSlopes >= 3) {
                    lineSegmentsList.add(new LineSegment(p, points[j - 1]));
                }
            }
        }
        if (lineSegmentsList.size() > 0) {
            this.lines = new LineSegment[lineSegmentsList.size()];
            this.lines = lineSegmentsList.toArray(this.lines);
        }
    }
    public int numberOfSegments() {
        return this.lines.length;
    }

    public LineSegment[] segments() {
        return this.lines;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }
}
