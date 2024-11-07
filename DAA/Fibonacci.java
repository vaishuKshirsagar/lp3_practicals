import java.util.*;
public class Fibonacci{
    static int rCount =0;
    
    public static int rFibonacci(int n){
        rCount++;
        if(n<=1) return n;
        return rFibonacci(n - 1) + rFibonacci(n - 2);
    }
    
    public static void recursiveCount(int n){
        System.out.print("Recursive : ");
        for(int i =0; i< n; i++){
            rCount = 0;
            System.out.print(rFibonacci(i) + " ");
        }
        System.out.println("\nRecursive count: " + rCount);
    }
    
    public static void iterative(int n){
        System.out.print("Iterative : ");
        int a =0;
        int b =1;
        if(n>=1) System.out.print(a + " ");
        if(n>=2) System.out.print(b + " ");
        int iCount = 0;
        for(int i =3 ; i<=n; i++){
            iCount++;
            int next = a + b;
            System.out.print(next + " " );
            a = b;
            b = next;
        }
        System.out.println();
        System.out.print("Iterative count: " + iCount);
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no: ");
        int n = sc.nextInt();
        recursiveCount(n);
        iterative(n);
    }
}