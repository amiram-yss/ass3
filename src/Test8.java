public class Test8 {
    public static void main(String[] args){
        Block b = new Block(new Rectangle(new Point(0,0),100,100));
        Point u = new Point(50,0);
        Point r = new Point(100,40);
        Point ur = new Point(100,0);
        Point dr = new Point(100,100);

        var l1 = b.getCollisionRectangle().directionOfEdgeWithThePoint(r);
        var l2 = b.getCollisionRectangle().directionOfEdgeWithThePoint(u);
        var l3 = b.getCollisionRectangle().directionOfEdgeWithThePoint(ur);
        var l4 = b.getCollisionRectangle().directionOfEdgeWithThePoint(dr);

        UTIL.printDirectionsList(l1);
        System.out.println("***");
        UTIL.printDirectionsList(l2);
        System.out.println("***");
        UTIL.printDirectionsList(l3);
        System.out.println("***");
        UTIL.printDirectionsList(l4);
    }
}
