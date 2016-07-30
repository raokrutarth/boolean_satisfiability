import java.util.Scanner;
/**
 * Created by Krutarth Rao on 10/15/2015.
 * PUID: 0027262283
 * PSO: P05
 * Collaborations: verbal discussion of solution during office hour 
 *                 with other students
 */
public class Main
{
    public static void main( String[] args )
    {
        //System.out.println("----------Boolean Satisfiability solver----------");
        Scanner in = new Scanner(System.in);
        String name = null;
        String variables = new String();
        boolean nameSet = false;

        String line;
        String nextLine;
        line = in.nextLine();
        while( in.hasNextLine() )
        {
            nextLine = in.nextLine();
            if(Test.isName(line) )
            {
                name = line;
                nameSet = true;
            }
            else if ( Test.isClause(line) )
            {
                variables += ( "\n" + line);
            }
            if ( !in.hasNextLine() || ( nameSet && in.hasNextLine() && Test.isName( nextLine ) ) )
            {
                Test t = new Test(name, variables);
                try
                {
                 t.Solve();
                 t.PrintStatement();
                }
                catch (ArrayIndexOutOfBoundsException e )
                {
                        System.err.println("Check the no. of variables (n) and the argument values.");
                }
                variables = new String();
            }
            line = nextLine;
        }
        in.close();
    }
}
