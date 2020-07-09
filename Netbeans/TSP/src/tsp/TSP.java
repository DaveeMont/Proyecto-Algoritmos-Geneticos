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
    

    /** Numero de ciudades o nodos a visitar */
    public static final int CITIES = 10;
    public static final double[][] CITYARRAY = new double[][] { 
        {19.776266, -99.119753}, {19.767437, -99.123009}, {19.746360, -99.097878},
        {19.752176, -99.093930}, {19.753761, -99.099530}, {19.760044, -99.091653}, 
        {19.791929, -99.082839}, {19.827808, -99.078333}, {19.824172, -99.116228}, 
        {19.776283, -99.119924}
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
            System.out.println(optimal.toString());
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
        //Formula de haversine
        IntegerGene geneB = (IntegerGene) a_to;
        IntegerGene geneA = (IntegerGene) a_from;
        //IntegerGene geneB = (IntegerGene) a_to;
        int a = geneA.intValue();
        int b = geneB.intValue();
        double x1 = CITYARRAY[a][0];
        //System.out.println("X1: "+x1);
        double y1 = CITYARRAY[a][1];
        double x2 = CITYARRAY[b][0];
        double y2 = CITYARRAY[b][1];
        //return Math.sqrt( (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        
        //public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) 
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(x2 - x1);  
        double dLng = Math.toRadians(y2 - y1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia; 
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
                genes[i] = new IntegerGene(getConfiguration(), 0, CITIES - 1);
                genes[i].setAllele(new Integer(i));
            }
            IChromosome sample = new Chromosome(getConfiguration(), genes);
            return sample;
        }
        catch (InvalidConfigurationException iex) {
            throw new IllegalStateException(iex.getMessage());
        }
    }
    
}
