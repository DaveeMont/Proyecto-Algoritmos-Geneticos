package tsp;


import org.jgap.*;
import org.jgap.impl.*;
import org.jgap.impl.salesman.Salesman;

/**
 *
 * @author dave,huitzil, martin
 * 
 * -------------->Explicación del problema
 * The traveling salesman problem is the following: given a finite number of
 * 'cities' along with the cost of travel between each pair of them, find the
 * cheapest way of visiting all the cities and returning to your starting point.
 * 
 * --------------->¿Qué hace este ejemplo?
 * This simple test and example shows how to use the Salesman class.
 * The distance between the cities is assumed to be equal
 * to the difference of the assigned numbers. So, the
 * optimal solution is obviously 1 2 3 4 ... n or reverse,
 * but the system does not know this. To get the useful application, you
 * need to override at least the distance function. Also, it is recommended
 * to define a new type of gene, corresponding the data about your "city".
 * For example, it can contain the city X and Y co-ordinates, used by
 * the distance function.
 */
public class TSP extends Salesman{
    
    /** String containing the CVS revision. Read out via reflection!*/
    private static final String CVS_REVISION = "$Revision: 1.14 $";

    /** Numero de ciudades o nodos a visitar */
    public static final int CITIES = 5;
    /*
    public static final int[][] CITYARRAY = new int[][] { {2, 4}, {7, 5}, {7, 11},
        {8, 1}, {1, 6}, {5, 9}, {0, 11}
    };
    */
    public static final double[][] CITYARRAY = new double[][] { {19.776968, -99.116583}, {19.747824, -99.179816}, {19.760017, -99.091561},
        {19.827389, -99.077679}, {19.817549, -99.079310}
            
    };

    /** Método main
     *  Solve a sample task with the number of cities, defined in a CITIES
     *  constant. Print the known optimal way, sample chromosome and found
     *  solution.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            TSP t = new TSP();
            IChromosome optimal = t.findOptimalPath(null);
            System.out.println("Solution: ");
            System.out.println(optimal);
            System.out.println("Score " +
                               (Integer.MAX_VALUE / 2 - optimal.getFitnessValue()));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    //Estos dos metodos se crean por defecto al extender nuestra clase por la clase padre Salesman
    /*Para distance
        Distance is equal to the difference between city numbers, except the
       * distance between the last and first cities that is equal to 1. In this
       * way, we ensure that the optimal solution is 0 1 2 3 .. n - easy to check.
    */
    @Override
    public double distance(Gene a_from, Gene a_to) {
        DoubleGene geneB = (DoubleGene) a_to;
        DoubleGene geneA = (DoubleGene) a_from;
        //IntegerGene geneB = (IntegerGene) a_to;
        int a = (int)geneA.doubleValue();
        int b = (int)geneB.doubleValue();
        int x1 = (int) CITYARRAY[a][0];
        int y1 =(int) CITYARRAY[a][1];
        int x2 = (int)CITYARRAY[b][0];
        int y2 =(int) CITYARRAY[b][1];
        return Math.sqrt( (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    /*
        Para el método createSampleChromosome
        Create an array of the given number of integer genes. The first gene is
        * always 0, this is the city where the salesman starts the journey.
    */
    @Override
    public IChromosome createSampleChromosome(Object a_initial_data) {
        try {
            Gene[] genes = new Gene[CITIES];
            for (int i = 0; i < genes.length; i++) {
                genes[i] = new DoubleGene(getConfiguration(), 0, CITIES - 1);
                genes[i].setAllele(new Double(i));
            }
            IChromosome sample = new Chromosome(getConfiguration(), genes);
            return sample;
        }
        catch (InvalidConfigurationException iex) {
            throw new IllegalStateException(iex.getMessage());
        }
    }
    
}
