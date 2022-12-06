import java.io.*;
import java.util.List;
import java.util.ArrayList;
import com.zeroc.Ice.*;
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

            FileWriter writer = new FileWriter("Output/output.txt");

            String[] arr = solver.solve(getInput());
            if (arr.length==0){
                writer.write("El sudoku no tiene solución\n\n");
            }else{
                writer.write("El sudoku tiene "+arr.length+" Soluciones\n\n");

                for (int i = 0; i <arr.length ; i++) {
                    writer.write(i+"\n"+arr[i]+"\n");
                }
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getInput() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < 9; i++){
            out.append(br.readLine()).append("\n");

        }
        br.close();
        return out.toString();
    }
    
}
