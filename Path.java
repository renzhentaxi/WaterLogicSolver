import java.util.ArrayList;

/**
 * Created by Tashi on 7/10/2014.
 */
public class Path
{
    public static final int FILL  = 0;
    public static final int EMPTY = 1;
    public static final int SPLIT = 2;
    public static final int POUR  = 3;

    private ArrayList<Beaker> beakers = new ArrayList<Beaker>();
    private static int[] cmds;
    public String log;

    public Path( ArrayList<Beaker> beakers)
    {
        this.beakers.addAll(beakers);
        log = "Path Start \n";
    }

    public Path( Path p)
    {
        this(p.getBeakers());
        log = p.log;
    }

    public ArrayList<Beaker> getBeakers()
    {
        return beakers;
    }

    public static void setCommands(int[] cmds)
    {
        Path.cmds = cmds;
    }

    public ArrayList<Path> getPossiblePaths()
    {
        ArrayList<Path> paths = new ArrayList<Path>();

        for( int cmd : cmds)
        {
            if( cmd == FILL)
            {

                for(Beaker b: beakers)
                {
                    if (b.isFILLABLE(beakers))
                    {
                        Path newPath = new Path(this);
                        paths.add(newPath);
                        newPath.cmd(FILL,b);
                    }
                }
            }
            else if( cmd == EMPTY)
            {
                for(Beaker b: beakers)
                {
                    if (b.isEMPTYABLE())
                    {
                        Path newPath = new Path(this);
                        paths.add(newPath);
                        newPath.cmd(EMPTY,b);
                    }
                }
            }

            else if ( cmd == SPLIT)
            {
                for ( Beaker b: beakers)
                {
                    if(b.isSPLITTABLE())
                    {
                        Path newPath = new Path(this);
                        paths.add(newPath);
                        newPath.cmd(SPLIT,b);
                    }
                }
            }

            else if ( cmd == POUR)
            {
                for( Beaker b: beakers)
                {
                    ArrayList<Beaker> bs = new ArrayList<Beaker>();
                    bs.addAll(beakers);
                    bs.remove(b);
                    for( Beaker x: bs)
                    {
                        if( b.isPOURABLE(x))
                        {
                            Path newPath = new Path(this);
                            paths.add(newPath);
                            newPath.cmd(POUR,b,x);
                        }
                    }
                }
            }
        }
        return paths;
    }

    public void cmd(int cmd, Beaker b, Beaker b2)
    {
        Beaker newBeaker = new Beaker(b);
        int    index     = beakers.indexOf(b);
        beakers.set(index,newBeaker);
        switch (cmd)
        {
            case FILL : newBeaker.currentVolume = newBeaker.maximumVolume;
                        log += "FILL " + newBeaker +"\n";
                        break;
            case EMPTY: newBeaker.currentVolume = 0;
                        log += "EMPTY " + newBeaker + "\n";
                        break;
            case SPLIT: newBeaker.currentVolume /= 2;
                        log += "SPLIT " + newBeaker + "\n";
                        break;
            case POUR : Beaker newBeaker2 = new Beaker(b2);
                        int    index2     = beakers.indexOf(b2);
                        beakers.set(index2,newBeaker2);
                        newBeaker.pourInto(newBeaker2);
                        log += "POUR " + newBeaker + "into " + newBeaker2 + "\n";
                        break;
        }
    }

    public void cmd(int cmd, Beaker b)
    {
        cmd(cmd,b,null);
    }

    public boolean goalReached(int goal)
    {
        return beakers.get(0).currentVolume == goal;
    }
    public String toString()
    {
        return log +"path end\n\n";
    }
}
