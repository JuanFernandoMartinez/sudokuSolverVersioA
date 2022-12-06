import java.util.List;
import java.util.ArrayList;
import com.zeroc.Ice.*;

public class Server {
    
    
    public static void main(String[] args){

        List<String> extraArgs = new ArrayList<String>();
        try(Communicator communicator = Util.initialize(args,"server.config",extraArgs)){
            if(!extraArgs.isEmpty()){
                System.out.println("Too many arguments");


                for (String x: extraArgs){
                    System.out.println(x);
                }

            }
            
            
            ObjectAdapter adapter = communicator.createObjectAdapter("Solver");
            com.zeroc.Ice.Object object = new SolverI();
            adapter.add(object, Util.stringToIdentity("SudokuSolver"));
            adapter.activate();
            communicator.waitForShutdown();
        }

    }
}
