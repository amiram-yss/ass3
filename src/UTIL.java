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
        return (d1-d2 < Math.pow(10,-8));
    }
}
