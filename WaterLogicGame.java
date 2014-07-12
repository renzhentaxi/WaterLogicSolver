import java.util.ArrayList;

/**
 * Created by Tashi on 7/12/2014.
 */
public class WaterLogicGame
{
    private int[] availableCommands;
    private ArrayList<Beaker> beakers = new ArrayList<Beaker>();
    private int best;
    private int goal;

    public WaterLogicGame(int... beakerSizes)
    {
        for(int i: beakerSizes)
        {
            beakers.add( new Beaker(i));
        }
    }

    public void setAvailableCommands(int... paraAvailableCommands)
    {
        availableCommands = paraAvailableCommands;
    }

    public void setGoal(int best, int goal, int targetBeakerSize)
    {
        for( Beaker b : beakers)
        {
            if (b.maximumVolume == targetBeakerSize)
            {
                Beaker temp = beakers.get(0);
                int bIndex  = beakers.indexOf(b);
                beakers.set(0,b);
                beakers.set(bIndex,temp);
            }
        }

        setGoal(best, goal);
    }

    public void setGoal(int best, int goal)
    {
        this.best = best-1;
        this.goal = goal;
    }

    public Path solve()
    {
        ArrayList<Path> paths = new ArrayList<Path>();
        ArrayList<Path> nextPaths = new ArrayList<Path>();
        Path.setCommands(availableCommands);

        for(Beaker b: beakers)
        {
            Path path = new Path( beakers);
            path.cmd(Path.FILL,b);
            paths.add(path);

        }

        for(int i = 0; i < best; i+=1)
        {
            for(Path path: paths)
            {
                nextPaths.addAll(path.getPossiblePaths());
            }

            paths.clear();
            paths.addAll(nextPaths);
            nextPaths.clear();
        }
        System.out.print(paths.size());
        for(Path p: paths)
        {
            if(p.goalReached(goal))
            {
                return p;
            }
        }
        return null;
    }
}
