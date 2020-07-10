/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private LineSegment[] lines = new LineSegment[1];
    public BruteCollinearPoints(Point[] points) {
        ArrayList<LineSegment> lineSegmentsList = new ArrayList<>();
        Point p;
        Point q;
        Point r;
        Point s;
        for (int i = 0; i < points.length; i++) {
            p = points[i];
            for (int j = i + 1; j < points.length; j++) {
                q = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    r = points[k];
                    for (int z = k + 1; z < points.length; z++) {
                    s = points[z];
                    if (points[i].slopeTo(q) == points[i].slopeTo(r) && points[i].slopeTo(r) == points[i].slopeTo(s)) {
                        lineSegmentsList.add(new LineSegment(p, s));
                        }
                    }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println(collinear.numberOfSegments());
    }
}


