import java.util.Scanner;
import java.util.Random ;
class hello{  
    
    public static void main(String args[]){
        Random rand = new Random();
        Scanner a = new Scanner(System.in);
        int randomnumber = rand.nextInt(100);
        int noofattempts = 10;
        int attempts = 10;
        int count = 0;
        boolean playagain = true;

        System.out.println("welcome to Guess number game");  
        System.out.println("You have totally 10 attempts");

        while(playagain){
        for(int i=0;i<noofattempts;i++){
            System.out.print("enter the number between (1-100): ");
            int n = a.nextInt();
            if(randomnumber==n){
                System.out.println("you won");
                System.out.println("no of attempts: "+count);
                break;
            }
            else if(count==9){
                System.out.println("sorry bro you completly lost all the attempts and the random number generated is "+randomnumber);                
            }
            else{
                if(n<randomnumber){
                    System.out.println("too low");
                    count++;
                }
                
                else if(n>randomnumber){
                    System.out.println("too high");
                    count++;
                }
                System.out.println("remaining no of attempts "+(--attempts));
            }
        }
        a.nextLine();
        System.out.print("are you want to play again:(YES / NO) : ");
        String response = a.nextLine();
        if(response.equals("yes") || response.equals("YES")){
            playagain = true;
        }
        else if(response.equals("NO")||response.equals("no")){
            playagain = false;
            System.out.println("thanks for playing");
        }
        else{
            System.out.println("Invalid input");
        }
    }
    } 
}
