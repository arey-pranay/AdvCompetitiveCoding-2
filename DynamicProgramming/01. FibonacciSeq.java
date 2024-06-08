public class Fibonacci {

public static int fibonacciBottomUp(int n) {
//Base xase
if (n <= 1) {
return n;
}
//store the older values
int[] fibArray = new int[n + 1];
//initialize the array created
fibArray[0] = 0;
fibArray[1] = 1;
fill the full array also
for (int i = 2; i <= n; i++) {
fibArray[i] = fibArray[i-1] + fibArray[i-2];
}
return fibArray[n];
}
public static void main(String[] args) {
int n = 10; // You can change this to any desired value
int result = fibonacciBottomUp(n);
System.out.println("Fibonacci(" + n + ") = " + result);
}
}
