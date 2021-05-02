import java.util.List;

public class UTIL {
    public static boolean DEBUG_MODE = false;
    public static void NOP(){}

    /**
     * Returns if d1 = d2 without java calculation errors
     * @param d1 first param
     * @param d2 second param
     * @return are these equals to the level of 8 digs after the decimal.
     */
    public static boolean equals(double d1, double d2){
        return (Math.abs(d1-d2) < Math.pow(10,-8));
    }

    public static void printDirectionsList(List<DIRECTION> d){
        for(DIRECTION i: d)
            System.out.println(i);
    }

    public static boolean isBetween(double val, double x1, double x2){
        boolean btn = false;
        if((val < x1) && (val > x2))
            btn = true;
        if((val > x1) && (val < x2))
            btn = true;
        return btn;
    }
}
