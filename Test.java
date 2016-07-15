/**
 * Created by Krutarth Rao on 10/15/2015.
 * PUID: 0027262283
 * PSO: P05
 * * Collaborations: verbal discussion of solution during office hour 
 *                 with other students
 */
public class Test
{
    String name;
    String variables;
    boolean printed = false;
    String[][] toPrint = null;

    public Test( String name, String variables )
    {
        this.name = name;
        this.variables = variables;
    }    
    public void RecursiveSol(boolean[] assignments, int pos)
    {
    	if( SolveTest(toPrint, assignments) && !printed && pos == assignments.length)
    	{
    		printed = true;
    		System.out.println("Satisfiable");
    		System.out.println(Printing1DBooleanArray( assignments ) );
    		return;
    	}    
    	else if ( pos < assignments.length)
    	{
            //System.out.println(Printing1DBooleanArray( assignments ) + "= false" );
    		assignments[pos] = true;
    		RecursiveSol(assignments, pos+1);
    		assignments[pos] = false;
    		RecursiveSol(assignments, pos+1);
    	}
    	else
    		return;
    }
    public static boolean SolveTest(String[][] a, boolean[] assignments)
    {
        boolean[] rowVal = new boolean[a.length];
        boolean totalVal = true;
        for(int i =0; i < a.length; i ++)
        {
            for(int k = 0; k < a[i].length && a[i][k]!=null; k++)
            {
                int n = Integer.parseInt( a[i][k] );
                if( n > 0)
                       rowVal[i] = rowVal[i] || assignments[n-1];           
                else
                    rowVal[i] = rowVal[i] || !assignments[(n*-1)-1];
            }
        }
        for(int y = 0; y < rowVal.length; y++)
           totalVal = totalVal && rowVal[y];     
        return totalVal;
    }
    public void Solve()
    {
        
        String[] vars = variables.split("\n");
        String[] ags = vars[1].split(" ");
        int variables = Integer.parseInt(ags[0]);
        int clauses = Integer.parseInt( ags[1] );
        
        System.out.println(name + ": " + variables + " Variable(s) " + clauses + " Clause(s)");
        
        String[][] x = new String[clauses][variables];
        for(int i = 0; i < clauses; i++)
        {
            String[] vrs = vars[i+2].split(" ");
            if( !is3DCopy(vrs, x) )
                x[i] = vrs;            
        }
        toPrint = copy2DArray(x, variables);    
        Print2DStringArray(toPrint);   
        boolean[] assignments = new boolean[variables]; 
        RecursiveSol(assignments, 0); //CALLING THE RECURSIVE METHOD
        if(!printed)
            System.out.println("Unsatisfiable");
    }

    public void PrintStatement()
    {
    	String[][] x = toPrint;
    	String result = new String();
    	for(int i = 0; i < x.length; i++)
        {
    		result+="( ";
            for( int j = 0; j < x[i].length && x[i][j] != null; j++)
            {
            	int n = Integer.parseInt( x[i][j] );
            	if( n > 0)
            		if(j != x[i].length-1)
            			result += "x" + Math.abs(n) + " | " ;
            		else
            			result += "x" + Math.abs(n) ;
            	
            	else
            		if(j != x[i].length-1)
            			result += "-x" + Math.abs(n) + " | " ;
            		else
            			result += "-x" + Math.abs(n) ;
            }
    		result+=" )";
    		if(i != x.length -1)
    			result+=" & ";
        }
    	System.out.println(result );
    }
/*--------------------------------------------------------------------------------*/
public static String[][] copy2DArray(String [][] e, int cols)
    {
        int rows = 0;
        for(int i = 0; i < e.length-1 && e[i][0] != null; i++)
        {
            rows++;
            if(e[i+1][0] == null)
                break;
        }            
        String[][] res = new String[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            res[i] = e[i];
        } 
        return res;
    }
    public static String printingArray(String[] arrayS)
    {
        String result = "";
        for (int i = 0 ; i < arrayS.length ; i++)
        {
            if ( i == arrayS.length-1)
            {
                result = result + arrayS[i];
            }
            else
            {
                result = result + arrayS[i] + "\n";
            }
        }
        return result;
    }
    public static String Printing1DBooleanArray(boolean[] array)
    {
        String result = "";
        for (int i = 0 ; i < array.length ; i++)
        {
            if ( i == array.length-1)
            {
                result = result + array[i];
            }
            else
            {
                result = result + array[i] + " ";
            }
        }
        return result;
    }
     
    public static void Print2DStringArray( String[][] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            for( int j = 0; j < array[i].length && array[i][j] != null; j++)
            {
                System.out.print(" | " + array[i][j] + " | ");
            }
            if(array[i][0] != null)
                System.out.println();
        }
    }
    public static boolean is3DCopy( String[] e, String[][] x)
    {
        for(int i = 0; i < x.length && x[i]!=null; i++)
        {
            if( e.length != x[i].length )
                return false;
            else if ( is2DCopy(e, x[i]) )
                return true;
        }
        return false;
    }
    public static boolean is2DCopy(String[] e, String[] xi )
    {
        for(int i = 0; i < e.length && xi[i]!=null; i++)
        {
            if( e[i].compareTo(xi[i]) != 0 )
                return false;
        }
        return true;
    }
    
    public static boolean isClause( String line )
    {
        for(int i =0; i < line.length(); i++)
        {
            if(line.charAt(i) == '-')
            {
                if( (line.charAt(i+1) + "").matches("-?\\d+(\\.\\d+)?"))
                    continue;
                else
                    return false;
            }
            else if (line.charAt(i) == ' ')
                continue;
            else if( (line.charAt(i) + "").matches("-?\\d+(\\.\\d+)?") )
                continue;
            else
                return false;
        }
        return true;
    }
    public static boolean isName(String line)
    {
        if( !isClause( line ) || line.contains("Test") || line.contains("test") ||
                line.contains("Problem") || line.contains("problem") )
            return true;
        return false;
    }
}
