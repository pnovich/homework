package org.example.util;




import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Example5 {
    StringBuilder input = new StringBuilder("Fa");
    static int bb = 11;

    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Student student = new Student("John", "Smith", "js1234");
//        Boolean result = f1.apply(student);
//        System.out.println("result = " + result);
//        Triangle triangle = new Triangle(5,10);
//        double d = f.applyAsDouble(triangle);
//        System.out.println(d);
//        IntUnaryOperator f = create(1);
//        System.out.println(f.applyAsInt(10));
//        Example5 example5 = new Example5();
//
////        System.out.println(example5.doRecursiveCurve());
////        String print = print(3);
////        System.out.println(print);
//        doWork();
////        example5.doRotateWork();
//
//        long max = maxRot(56789);
//        System.out.println("------------");
//        System.out.println("max = " + max);
//        try {
//            doAttackWithThreads();
//        } catch (InterruptedException e) {
//            System.out.println("exception in threads");
//            throw new RuntimeException(e);
//        }

    }
    public static Function<Student, Boolean> f1 = p ->
            (p.getFullName().equals("John Smith")) && (p.studentNumber.equals("js123"))
            ?true:false;//Make me a function; remember to set the types!


    public static ToDoubleFunction<Triangle> f = (r) -> {
        double result = ((double)r.height * (double)r.base)/2;
        System.out.println("result = " + result);
        r.setArea(result);
        return  result;
    };//Make me a function.
    //Don't use Function. Find the appropriate function to turn an
    //arbitrary class into a double. Remember the import.

    /*
     * Make the "create" method (public, static). It accepts one parameter (int addTo) and returns
     * a function (make sure to use the appropriate type). This function accepts an integer,
     * adds "addTo" to that integer, and returns the result as an integer (integer-to-integer function).
     */
    public static IntUnaryOperator create(int addTo) {
      return i -> i + addTo;
    }


    public String doRecursiveCurve() {
       int n = 2;
       StringBuilder input = new StringBuilder("Fa");
       StringBuilder result = recursiveCurve(n, input);
       System.out.println(result);
       String stringResult = result.toString();
       stringResult = stringResult.replace("a", "");
       stringResult = stringResult.replace("b", "");

        System.out.println("result = " + stringResult);
        return stringResult;
    }

    StringBuilder recursiveCurve(int n, StringBuilder s) {
        if (n == 0) {

            return s;
        } else {
            if (n >0) {
               s = doReplaceNeededCharacters(s);
               recursiveCurve(n - 1, s);
//               s = newString;
                System.out.println("current string "  + s  + " n = " + n);
            }
            return s;
        }
    }

    public StringBuilder doReplaceNeededCharacters(StringBuilder input) {
        String newCurrentString = new String(input);
        String []  strings = newCurrentString.split("");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("a")) {
                strings[i] = "aRbFR";
            }
            if (strings[i].equals("b")) {
                strings[i] = "LFaLb";
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(strings).forEach(s -> {
            sb.append(s);
        });
        input = sb;
        return input;
    }

    public static String print(int n) {
        // TODO your code here
        if ((n % 2 == 0)||(n <= 0)) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n/2 +1; i++) {
            for (int j = 0; j < n; j++) {
                if (isPlaceForStarHere(i,j,n)) {
                   result.append("*");
                } else if (j < n/2) {
                    result.append(" ");
                }
            }
            result.append("\n");
        }

        for (int i = n/2 -1; i > -1; i--) {
            for (int j = 0; j < n; j++) {
                if (isPlaceForStarHere(i,j,n)) {
                    result.append("*");
                } else if (j < n/2){
                    result.append(" ");
                }
            }
            result.append("\n");
        }


        return result.toString();
    }

    public static boolean isPlaceForStarHere(int i, int j, int n) {
        boolean result = ((j  >= ((n/2) - i)) && (j < (n/2 + 1))
                ||(j  <= ((n/2) + i)) && (j > (n/2 - 1))
        );
        return result;
    }

    public static String print2(int n) {
        if (n % 2  == 0 || n <= 0) {return null;}
        StringBuffer expected = new StringBuffer();
        for (int i = 1; i <= n; i+=2) {
            expected.append(" ".repeat((n - i) / 2) + "*".repeat(i) + "\n");
        }
        for (int i = n - 2; i >= 1; i-=2) {
            expected.append(" ".repeat((n - i) / 2) + "*".repeat(i) + "\n");
        }
        return expected.toString();
    }

    public static String convertString(String input) {
        String result = new String(input);
        char[] chars = input.toCharArray();
        int lastIndex = input.length() -1;
        int i = input.length() -1;
        String [] strings = input.split("");
        while (strings[i].equals(" ")) {
            lastIndex = i;
        }
        int firstLength = result.length();
        result = result.substring(0,lastIndex);
        int secondLength = result.length();
        System.out.println("first length = " + firstLength + ", second length " + secondLength);

        return result;
    }

    public int GetSum1(int a, int b)
    {
        //Good luck!
        int min;
        int max;
        int result = 0;
        if (a == b) {
            return a + b;
        }
        if (a > b) {
            min = b;
            max = a;
        } else {
            min = a;
            max = b;
        }

        for (int i  = min; i < max + 1; i++ ) {
            result = result + i;
        }


        return result;
    }

    public int GetSum(int a, int b)
    {
        //Good luck!
        int min;
        int max;
        int result = 0;
        if (a == b) {
            return a;
        }
        if (a > b) {
            min = b;
            max = a;
        } else {
            min = a;
            max = b;
        }

        result = IntStream.range(min,max + 1)
                .boxed()
                .reduce(0, Integer::sum);

        return result;
    }

    public int GetSum3(int a, int b)
    {
        return (a + b) * (Math.abs(a - b) + 1) / 2;
    }

    public int GetSum4(int a, int b) {
        int res = 0;
        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            res += i;
        }
        return a == b ? a : res;
    }

    public int GetSum5(int smaller, int bigger)
    {
        if(bigger<smaller) {
            return GetSum5(bigger,smaller);
        }
        else
        {
          /* use Euler's formula to sum up all numbers from 0 to bigger
          /  and subtract the sum of numbers from 0 to smaller (exclusive)
          */
            return (bigger+smaller)*(bigger-smaller+1)/2;
        }
    }

    public boolean isTriangle(int a, int b, int c){
        boolean isNot =
                (a >= b + c) || (b >= a + c) || (c >= a + b);
        return !isNot;
    }

    public static int mxdiflg(String[] a1, String[] a2) {
        // your code
        int result = 0;
        if (a1 ==null || a2 == null || a1.length == 0 || a2.length == 0 ) {
            return -1;
        }
        int maxX = Arrays.stream(a1)
                .max(Comparator.comparing(String::length))
                .get().length();
        int maxY = Arrays.stream(a2)
                .max(Comparator.comparing(String::length))
                .get().length();

        int minX = Arrays.stream(a1)
                .min(Comparator.comparing(String::length))
                .get().length();

        int minY = Arrays.stream(a2)
                .min(Comparator.comparing(String::length))
                .get().length();

       int max = Math.max(maxX,maxY);
       int min = Math.min(minX, minY);
       result = max - min;

        return result;
    }

    public  String findNeedle(Object[] haystack) {
        // Your code here
        for (int i = 0; i < haystack.length; i++) {
            System.out.println(haystack[i]);
        }
        final int[] result = {0};
        Map<Integer,String> map = IntStream.range(0,haystack.length)
                .boxed()
                .collect(Collectors.toMap((i) -> i,
                        i->haystack[i] != null ?haystack[i].toString():""));
        System.out.println(map);
        map.entrySet().forEach((e) -> {
            if (e.getValue().equals("needle")) {
                result[0] = e.getKey();
            }
        });
        return "found the needle at position " + result[0];
    }

    public String  mapCurrentElementToString(Integer i, Object [] array) {
      if (array[i] instanceof String) {
          return array[i].toString();
      }
      return "";
    }

    public static String getMiddle(String word) {
        //Code goes here!
        String result ="";
        String [] strings = word.split("");
        int n = word.length() / 2;
        int k = word.length() % 2;
        if (k == 0) {
            result = result + strings[word.length()/2 -1] + strings[word.length()/2];
        } else {
            result = result + strings[word.length()/ 2];
        }

        return result;
    }

    public void doWorkWithExternalData(StringBuilder a) {
        System.out.println("a before = " + a);
        a.append("1");
        System.out.println("a after = " + a);
    }

    public static void doWork() {
        Example5 example5 = new Example5();
        Integer b = 5;
        SomeObject inputObject = new SomeObject();
        inputObject.x = 4;
        int bbb = bb;
        List<Integer> input = Arrays.asList(1,2,3);
        StringBuilder string = new StringBuilder("string");
        example5.doWorkWithExternalData(string);
        System.out.println("b = " + string.toString());
    }

    public static long maxRot(long n) {
        // your code

        return doRotateWork(String.valueOf(n));
    }
    public static String rotateString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        String lastPart = string.substring(1);
        stringBuilder.append(lastPart);
        String firstPart = string.substring(0,1);
        stringBuilder.append(firstPart);
        String string1 = stringBuilder.toString();
        return string1;
    }

    public static long doRotateWork(String inputData) {
        List<String> results = new ArrayList<>();
        results.add(inputData);
        List<String> strings = new ArrayList<>();
        List<String> begins = new ArrayList<>();
        StringBuilder begin = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder(inputData);
        doRotateString(begin, stringBuilder, strings, begins);
        System.out.println(" results --------------------");
        for (int i = 0; i < inputData.length() - 1; i++) {
            System.out.println(" begin = " + begins.get(i));
            System.out.println(" string = " + strings.get(i));
            String result = begins.get(i) + strings.get(i);
            System.out.println(result);
            results.add(result);
        }
        System.out.println("results : " + results);
    results.forEach(System.out::println);
    long max = (long)results.stream()
            .map(Integer::parseInt)
//            .max(Comparator.comparingLong(k ->k))
            .max((a,b) -> (a - b))
            .get();
        System.out.println("max = " + max);
        return max;
    }
    public static void doRotateString(StringBuilder begin, StringBuilder string, List<String> strings, List<String> begins) {
        if (string.length() == 1) {
            return;
        } else {
            System.out.println("input = " + string.toString());
            String rotated = rotateString(string.toString());
            System.out.println("rotated substring = " + rotated);
            String k = rotated.substring(0,1);
            System.out.println("k = " + k);
            begin.append(k);
            System.out.println("begin = " + begin);
            StringBuilder newStringBuilder = new StringBuilder(rotated.substring(1));
            System.out.println("newStringBuilder = " + newStringBuilder);
            begins.add(begin.toString());
            strings.add(newStringBuilder.toString());
            doRotateString(begin,newStringBuilder,strings, begins);
        }
    }

    public int stringValueToInt(String input) {
        return Integer.parseInt(input);
    }

    public static String arrange(String strng) {
        // your code
        String [] strings = strng.split(" ");
        String result = Example5.moveStrings(strings);
        return result;
    }

    private static String moveStrings(String[] strings) {
        String result = "";
        for (int i = 0; i < strings.length -1; i++) {
            if (i % 2 == 0) {
                if (strings[i].length() > strings[i + 1].length()) {
                    String temp = strings[i];
                    strings[i] = strings[i + 1];
                    strings[i + 1] = temp;
                }
            } else {
                if (strings[i].length() < strings[i + 1].length()) {
                    String temp = strings[i];
                    strings[i] = strings[i + 1];
                    strings[i + 1] = temp;
                }
            }
        }
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 1) {
                strings[i] = strings[i].toUpperCase();
            } else {
                strings[i] = strings[i].toLowerCase();
            }
        }
    result = Arrays.stream(strings)
            .collect(Collectors.joining(" "));
        return result;
    }


    final static String[]                                       replacements = new String[]{"", "$2", "$1"};
    final static BiFunction<String,String,Boolean>                  lessThan = (x,y) -> x.length() < y.length();
    final static BiFunction<String,String,Boolean>               greaterThan = (x,y) -> x.length() > y.length();
    final static Map<Boolean,BiFunction<String,String,Boolean>>         func = Map.of(true, greaterThan, false, lessThan);

    public static String arrange2(String string) {
        string += " ";

        StringBuilder   resStr = new StringBuilder();
        boolean       flipFlop = true;

        Matcher mat = Pattern.compile("(\\b.+?\\b\\s+)(\\b.+?\\b(?:\\s+|$))|(^\\b.+\\b)\\s*$").matcher("");
        while (mat.reset(string).lookingAt()) {

            int     selectedGroup = 0;
            String         nxtStr = "";

            selectedGroup = mat.group(3) != null
                    ? 3
                    : func.get(flipFlop).apply(mat.group(1),mat.group(2))
                    ? 2
                    : 1;

            nxtStr        = flipFlop
                    ? mat.group(selectedGroup).toLowerCase()
                    : mat.group(selectedGroup).toUpperCase();

            resStr.append(nxtStr);
            flipFlop = !flipFlop;

            string = mat.replaceFirst(replacements[selectedGroup % 3]);
        }
        return resStr.toString();
    }

    int count = 0;
    public void recursive(){
        if(count == 6){
            return;
        }
        count++;
        recursive();
    }

    public static String vertMirror (String strng) {
        String [] strings = strng.split(",");
        String result = Arrays.stream(strings)
                .map(s ->  {
                    StringBuilder stringBuilder = new StringBuilder(s);
                    stringBuilder.reverse();
                    return stringBuilder.toString();
                })
                .collect(Collectors.joining("\r"));

        // your code
        return result;
    }
    public static String horMirror (String strng) {
        // your code
        String [] strings = strng.split(",");

            for (int i = 0; i < strings.length / 2; i ++) {
                String buffer = strings[i];
                strings[i] = strings[strings.length -1 - i];
                strings[strings.length -1 - i] = buffer;
            }

        String result = Arrays.stream(strings).collect(Collectors.joining("\r"));

        return result;
    }
    public static String oper(Function<String,String> nameOfTheFunction, String s) {
        return nameOfTheFunction.apply(s);
        // your code and complete ... before operator
    }

    public static String showSequence(int value){
        //for
        //  while
        String stringResult = "0";
        if (value == 0) {
            return value + "=0";
        }
        if (value < 0) {
            return value + "<0";
        }
        int intResult = 0;
        for (int i = 1; i < value + 1; i++) {
            intResult = intResult + i;
            stringResult = stringResult + "+" + i;
        }
        stringResult = stringResult + " = "+intResult;
        return stringResult;
    }

    public static String[][] partlist(String[] arr) {
        // your code
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("------------");
        String [] [] result = new String[arr.length - 1][2];
        for (int i = 0; i < arr.length - 1; i++) {
            String[] currentList = getListFromString(arr,i);
            result[i] = currentList;
        }
        return result;
    }

    public  static String[] getListFromString(String[] strings, int count) {
        String[] result = new String[2];
        String s1 = strings[0];
//        String [] strings = string.split("");
        for (int i = 1; i < count + 1; i++) {
            s1 = s1 + " " +  strings[i];
        }
        String s2 = strings[count + 1];
        for (int i = count + 2; i < strings.length ; i++) {
            s2 = s2 + " " + strings[i];
        }

        result[0] = s1;
        result[1] = s2;
        return  result;
    }

    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        // Your code goes here. Have fun!
        String winner = fighter2.name;
        int attackerNumber;
        int defenderNuber;
        List<Fighter> ring = new ArrayList<>();
        if (firstAttacker.equals(fighter1.name)) {
            ring.add(fighter1);
            ring.add(fighter2);
        } else {
            ring.add(fighter2);
            ring.add(fighter1);
        }
        boolean defenderIsStillFighting = true;
        int count = -1;
        while (defenderIsStillFighting) {
        count++;
        int currentFighterNumber = count % 2;
        boolean currentAttackStatus;
        if (currentFighterNumber == 0) {
            attackerNumber = 0;
            defenderNuber = 1;
        } else {
            attackerNumber = 1;
            defenderNuber = 0;
        }

        currentAttackStatus = attack(ring.get(attackerNumber), ring.get(defenderNuber));
        defenderIsStillFighting = currentAttackStatus;
        if (!defenderIsStillFighting) {
            winner = ring.get(attackerNumber).name;
            System.out.println("winner is " + winner);
        }
        }

        return winner;
    }

    public static boolean attack(Fighter attacker, Fighter defender) {
        System.out.println(attacker.name + " attacks Harry; Harry now has " + defender.health + " health.");
        defender.health = defender.health - attacker.damagePerAttack;
        if (defender.health <= 0) {
//            System.out.println("winner is " + attacker.name);
            return false;
        }
        return true;
    }

    public static int doAttackWithThreads() throws InterruptedException {
        SharedFightState state = new SharedFightState(80,80,
                20, 50,
                0, 0,
                false,0);
        ThreadFighterGame game = new ThreadFighterGame(state);
        int result = game.findWinner();
       return result;
    }



}

class SharedFightState {
    int firstHealth;
    int secondHealth;
    int firstDamagePerAttack;
    int scondDamagePerAttack;
    int attackerNumber;
    int count;
    boolean gameOver;
    int winner;

    public SharedFightState(int firstHealth, int secondHealth, int firstDamagePerAttack, int scondDamagePerAttack, int attackerNumber, int count, boolean gameOver, int winner) {
        this.firstHealth = firstHealth;
        this.secondHealth = secondHealth;
        this.firstDamagePerAttack = firstDamagePerAttack;
        this.scondDamagePerAttack = scondDamagePerAttack;
        this.attackerNumber = attackerNumber;
        this.count = count;
        this.gameOver = gameOver;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "SharedFightState{" +
                "firstHealth=" + firstHealth +
                ", secondHealth=" + secondHealth +
                ", firstDamagePerAttack=" + firstDamagePerAttack +
                ", scondDamagePerAttack=" + scondDamagePerAttack +
                ", attackerNumber=" + attackerNumber +
                ", count=" + count +
                ", gameOver=" + gameOver +
                ", winner=" + winner +
                '}';
    }
}
class ThreadFighter implements Runnable {
    SharedFightState state;
    int createdFighterNumber;
    volatile boolean isOver =  false;

    public ThreadFighter(SharedFightState state, int createdFighterNumber) {
        this.state = state;
        this.createdFighterNumber = createdFighterNumber;
    }

    public void doAttack(int number, SharedFightState state) {
        if (number == 0) {
            state.secondHealth = state.secondHealth - state.firstDamagePerAttack;
            System.out.println("fist fighter attacks; second has " + state.secondHealth);
            if (state.secondHealth <= 0) {
                state.winner = 0;
                state.gameOver = true;
                isOver = true;
            }
        } else {
            state.firstHealth = state.firstHealth - state.scondDamagePerAttack;
            System.out.println("second fighter attacks; first has " + state.firstHealth);
            if (state.firstHealth <= 0) {
                state.winner = 1;
                state.gameOver = true;
                isOver = true;
            }
        }

    }

    @Override
    public void run() {

        while (!isOver) {
            synchronized (state) {

                if (state.gameOver) {
                    System.out.println("game ovr is true ,count = " + state.count);
                    return;
                }
//            state.count++;
                boolean isActive = state.count % 2 == this.createdFighterNumber;

                if (!isActive) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                doAttack(createdFighterNumber, state);
                state.count++;
                state.notify();
            }
        }
    }
}

class ThreadFighterGame {

    SharedFightState state;
    Object gamOverLock = new Object();

    public ThreadFighterGame(SharedFightState state) {
       this.state = state;
    }

    public int findWinner() throws InterruptedException {
//        SharedFightState state = new SharedFightState(80,80,
//                20, 50,
//                0, 0,
//                false,0);
        ThreadFighter fighter0 = new ThreadFighter(this.state,0);
        ThreadFighter fighter1 = new ThreadFighter(this.state,1);
        Thread thread0 = new Thread(fighter0,"fighter0");
        Thread thread1 = new Thread(fighter1,"fighter1");
        thread0.start();
        thread1.start();

//        Thread.sleep(10000);

        thread0.join();
        thread1.join();
        System.out.println("winner is " + state.winner);
//
//        System.out.println("state is " + state);
        return state.winner;
    }
}

class Fighter {
    public String name;
    public int health, damagePerAttack;
    public Fighter(String name, int health, int damagePerAttack) {
        this.name = name;
        this.health = health;
        this.damagePerAttack = damagePerAttack;
    }
}

 class SomeObject {
    int x;
 }
 class Student {
    private final String firstName;
    private final String lastName;
    public final String studentNumber;

     public Student(String firstName, String lastName, String studentNumber) {
         this.firstName = firstName;
         this.lastName = lastName;
         this.studentNumber = studentNumber;
     }

     public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getCommaName() {
        return lastName + ", " + firstName;
    }
}

class Triangle {
    public final int height;
    public final int base;
    private double area;

    public Triangle(int height, int base) {
        this.height = height;
        this.base = base;
    }

    public void setArea(double a) {
        area = a;
    }
    public double getArea() {
        return area;
    }
}