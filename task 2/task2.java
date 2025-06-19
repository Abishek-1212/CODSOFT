import java.util.Scanner;
class hello{
    public static void main(String args[]){
        Scanner a = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int n = a.nextInt();
        String grade;
        int totalmarks=0;
        for(int i=0;i<n;i++){
            System.out.print("enter the subject "+(i+1)+": ");
            int mark = a.nextInt();
            totalmarks+=mark;
        }
        int percentage = totalmarks/n;
        if(percentage>=80 && percentage<=100 ){
           grade="A grade";
        }
        else if(percentage>=60 && percentage<=79 ){
           grade="B grade";
        }
        else if(percentage>=35 && percentage<=59){
            grade="C grade";
        }
        else{
            grade="Fail";
        }
        System.out.println("Total marks: "+totalmarks);
        System.out.println("Percentage: "+percentage+"/100");
        System.out.println("Grade: "+grade);
        

    }

}