import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Tashi on 7/10/2014.
 */
public class Main
{
    public static void main(String[] args)
    {
        demo();
    }

    public static void demo()
    {
        WaterLogicGame lv1 = new WaterLogicGame(7,9);
        lv1.setAvailableCommands(Path.FILL,Path.EMPTY,Path.POUR);
        lv1.setGoal(6,5,9);
        Path p = lv1.solve();
        System.out.print(p);
    }
}
