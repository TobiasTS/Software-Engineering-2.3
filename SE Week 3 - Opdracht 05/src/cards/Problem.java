package cards;
import java.util.Scanner;

public class Problem
{
private Candidates candidates = new Candidates();
private Solution   solution   = new Solution();
private Scanner    reader = new Scanner(System.in);
    
    public void solve()
{
     //System.out.println(candidates);
     //System.out.println(solution);
     //reader.nextLine();
     int index=0;
     while (index<candidates.size())
     {
         if (solution.fits(candidates.get(index)))
         {
             solution.record(candidates.remove(index)); //move candidate to solution
             if(solution.isCorrect()) {
            	 if (solution.complete())
                 {
                     solution.show();
                     System.exit(0);
                 }
            	 else
                 {
                     solve();
                 }
             }
             else
             {
                 solve();
             }
             candidates.add(index, solution.eraseRecording()); //move candidate to candidates
  
           }
           index++;
        }
}

}
        
          
         









