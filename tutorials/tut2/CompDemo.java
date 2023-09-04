import java.util.*;

class CompDemo {
  static class Point {
    public int x, y;
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class PointCmp implements Comparator<Point> {
      public int compare(Point p, Point q) {
        return (p.x + p.y) - (q.x + q.y);
      }
  }

  public static void main(String[] args) {
    List<Point> points = new ArrayList<Point>();
    points.add(new Point(4, 0));
    points.add(new Point(1, 1));
    points.add(new Point(3, 0));

    Collections.sort(points, new PointCmp());

    // Or inplace:

    Collections.sort(points, new Comparator<Point>(){
      public int compare(Point p, Point q) {
        return (p.x + p.y) - (q.x + q.y);
      }
    });

    for (Point p : points) {
      System.out.println(p.x + " " + p.y);
    }
  }
}

