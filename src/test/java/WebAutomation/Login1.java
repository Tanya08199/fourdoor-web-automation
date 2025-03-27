package WebAutomation;

import org.junit.Test;

public class Login1 {

/*  To find ODD and even

  public static void main (String[] args){
      int num = 0;
      if(num % 2 == 0){
System.out.println("Number is even"+ num);
      }
      else{
          System.out.println("Number is odd " +num);
      }
  } */

   /* To find prime number
     public static void main(String[] arg){
         int num = 6;


         for (int i = 2; i <= num / 2; i++) {
             if (num % i == 0) {
                 System.out.println(num + " is Not Prime");
                 return;
             }
         }

         System.out.println(num + " is Prime");
     }*/
    
    
     /*  To find the Fibonacci series
     public static void main(String[] arg){
         int first = 0 , second = 1 , next;
         for(int i = 1 ; i<= 9 ; i++){
             System.out.println(first + "");
             next = second+first;
             first = second;
             second = next;
                  }
      */

    /* Swap two number without using 3rd variable
    public static void main(String args[]) {
        int a = 12, b = 13;
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("After swapping: a = " + a + ", b = " + b);

    }
     */
    /* factoriol of number
    public static void main(String args[]) {
        int num = 5 ;
        int fact = 1 ;
        for(int i = 1 ; i<= num ; i++){
           fact = fact * i ;
        }
        System.out.println(fact);
    } */

    /* Reverse the number

    public static void main(String args[]) {
        int num = 4567;
        int rev = 0 ;
        while (num > 0) {
      int digit = num % 10;
      rev = rev * 10 + digit;
            num /= 10;
        }
        System.out.println(rev);

    }
}

*/
/* Armstrong number
    public static void main(String  args[]){
    int num = 153;
    int temp = num;
    int sum = 0;
    int r ;

        while (num > 0) {
            r = num % 10;
            num = num / 10;
            sum = sum + r * r * r;

        }
        if(sum == temp){
            System.out.println(sum + " Number is armstrong");
        }
        else {
            System.out.println(sum + " Number is not armstrong");

        }

}*/
    /* Palindrome Number
    public static void main(String  args[]){
        int num = 121 , digit ;
        int temp = num;
        int rev = 0 ;
        while (num>0){
        digit = num % 10 ;
        rev = rev * 10 + digit ;
        num = num / 10 ;

        }
        if (temp == rev){
        System.out.println(rev + " Palindrome Number");
    }
        else {
            System.out.println("not Palindrome Number ");

        }*/

    /* Sum of digit
    public static void main(String args[]){
        int num = 12345;
        int sum = 0;
        while (num>0){
            int digit = num % 10 ;
            sum = sum + digit ;
            num = num / 10 ;
        }
        System.out.println(sum);
    }*/


/*   Java program to find number of digits in given number

    public static void main(String args[]) {
        int num = 1234;
        int a = 3;
        while (num>0){
          int digit = num % 10 ;
          if(digit == a){
              System.out.println("number present");
              return;
          }
          num = num / 10 ;
        }
        System.out.println("Number not present");

    }*/

    // reverse the String

    public static void main(String args[]){
        String org = "abcdef";
        String rev ="";
        for(int i = org.length()-1; i>=0 ; ){

        }

    }






    }



































