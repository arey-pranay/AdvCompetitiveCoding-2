public class Fibonacci {
public static int fibonacciBottomUp(int n) {
if (n <= 1) {
return n;
}
int[] fibArray = new int[n + 1];
fibArray[0] = 0;
fibArray[1] = 1;
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
