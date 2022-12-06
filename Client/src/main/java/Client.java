import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import com.zeroc.Ice.*;
import java.util.Scanner;
import java.util.Arrays;



public class Client {

    
    public static void main(String[] args){
        
        List<String> extraArgs = new ArrayList<>();
        try(Communicator communicator = Util.initialize(args,"client.config",extraArgs)){
            Demo.SolverPrx twoway = Demo.SolverPrx.checkedCast(
                communicator.propertyToProxy("Solver.Proxy")
            ).ice_twoway().ice_secure(false);


            Demo.SolverPrx solver = twoway;

            if (solver == null){
                throw new Error("Invalid proxy");
            }

            FileWriter writer = new FileWriter("output.txt");

            String[] arr = solver.solve(getInput());
            for(String str: arr) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();

            //System.out.println(Arrays.toString(solver.solve(getInput())));
            /* System.out.println(Arrays.toString(solver.solve(sc.nextLine()))); */

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getInput(){
        Scanner sc = new Scanner(System.in);
        String out = "";
        for(int i = 0; i < 9; i++){
            out += sc.nextLine()+"\n";
        }
        return out;
    }
    
}
