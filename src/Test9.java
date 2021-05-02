import biuoop.GUI;

public class Test9 {
    public static void main(String[] args){
        GUI gui = new GUI("test9",800,600);
        var ds = gui.getDrawSurface();
        GameEnvironment ge = new GameEnvironment();
        ge.drawOn(ds);
        gui.show(ds);
    }
}
