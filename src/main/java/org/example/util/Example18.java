package org.example.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

public class Example18 {
    public static String makeReadable(int seconds) {
        // Do something
        String result = "";
        String hours = "" + seconds / 3600;
        String minutes = "" + (seconds % 3600)/ 60;
        String resultSeconds = "" + seconds % 60;
        result = editString(hours)+ ":"
                + editString(minutes) +
                ":" + editString(resultSeconds);
        System.out.println("result = " + result);
        return result;
    }

    static String editString(String input) {
        if (input.length() == 1) {
            return "0" + input;
        } else {
            return input;
        }
    }

    public static String makeReadable2(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds / 60) % 60, seconds % 60);
    }

    public static int sequence(int[] arr) {
        int result = 0;
        if (arr.length == 0) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int l = 2; l < arr.length + 1; l++) {
                for (int j = 0; j < arr.length - l + 1; j++ ) {
                    int currentSum = calculateSum(arr, j, l + j, map );
                    if (map.containsKey(l)) {
                        if (map.get(l) < currentSum ) {
                            map.put(l, currentSum);
                        }
                    } else {
                        map.put(l, currentSum);
                    }
                }
            }
            int max = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > max) {
                    max = entry.getValue();
                }
            }
            result = max;
        return result;
    }

    public static int calculateSum(int[] arr, int first, int second, Map<Integer, Integer> map) {
        int sum = 0;
        for (int i = first; i < second; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int[] removeSmallest(int[] numbers) {
        //show me the code!
        if (numbers.length == 0) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }
        Optional<Integer> r = list.stream()
                .min(Comparator.naturalOrder());
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i), r.get())) {
                index = i;
                break;
            }
        }
        list.remove(index);
        int [] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    static int[] removeSmallest2(int[] numbers) {
        int min = range(0, numbers.length).reduce((i, j) -> numbers[i] > numbers[j] ? j : i).orElse(-1);
        return range(0, numbers.length).filter(i -> i != min).map(i -> numbers[i]).toArray();
    }

    public static String howMuchILoveYou(int nb_petals) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"I love you");
        map.put(2,"a little");
        map.put(3,"a lot");
        map.put(4,"passionately");
        map.put(5,"madly");
        map.put(0,"not at all");

        String result = map.get(nb_petals % 6);
        //your code here :)
        return result;
    }

    public static String howMuchILoveYou2(int nb_petals) {
        return new HashMap<Integer, String>() {{
            put(1, "I love you");
            put(2, "a little");
            put(3, "a lot");
            put(4, "passionately");
            put(5, "madly");
            put(0, "not at all");
        }}.get(nb_petals % 6);
    }

    public static String replace(final String s) {
        String letters = "aeiouAEIOU";
        Set<String> set = Arrays.stream(letters.split("")).collect(Collectors.toSet());
        String result = Arrays.stream(s.split(""))
                .map(e -> {
                    if (set.contains(e)) {
                        return "!";
                    } else {
                        return e;
                    }
                })
                .collect(Collectors.joining());
        return result; //coding and coding....
    }

    public static String replace2(final String s) {
        return s.replaceAll("[aeiouAEIOU]", "!");
    }

    public static Map<Character, Integer> count(String str) {
        // Happy coding!
        if (str.length() == 0) {
            return new HashMap<>();
        }
        List<String> list = Arrays.stream(str.split(""))
                .collect(Collectors.toList());

        Map<Character, Long> longMap = list.stream()
                .collect(Collectors.groupingBy(e ->{
                    return e.charAt(0);
                },Collectors.counting())
        );

        Map<Character, Integer> longMap2 = Arrays.stream(str.split(""))
//                .collect(Collectors.toList()).stream()
                .collect(Collectors.groupingBy(e ->{
                            return e.charAt(0);
                        },Collectors.collectingAndThen(Collectors.counting(),
                        c -> {
                            Integer r =  Integer.valueOf(Math.toIntExact(c));
                            return r;
                        }))
                );

        System.out.println("longMap2 = " + longMap2);


//        Map<Character, Integer> longMap2 = list.stream()
//                .collect(Collectors.toMap(i -> i.charAt(0), i -> intMap.getOrDefault(i, 0L)));

        //               .collect(new Supplier<Map<Character, Long>>() {
//                                 @Override
//                                 public Map<Character, Long> get() {
//                                     HashMap<Character, Long> hashMap = new HashMap<>();
//                                     return hashMap;
//                                 },
//                                 new BiConsumer<Map<Character,Long>,T>
//
//                 );

        Map<Character, Integer> result = new HashMap<>();
        for (Map.Entry<Character, Long> entry : longMap.entrySet()) {
            Integer newValue = entry.getValue().intValue();
            result.put(entry.getKey(), newValue);
        }
        return result;
    }

    public static Map<Character, Integer> count2(String str) {
        // Happy coding!

        return str.length() == 0 ? new HashMap() :
                Arrays.stream(str.split(""))
                        .collect(Collectors.groupingBy(e ->e.charAt(0),
                                Collectors.collectingAndThen(Collectors.counting(),
                                        c ->  Integer.valueOf(Math.toIntExact(c))
                                )));

    }

    static Map<Character, Integer> count3(String str) {
        return str.chars().mapToObj(c -> (char) c).collect(toMap(c -> c, c -> 1, Integer::sum));
    }

    public static String doubleChar(String s){
        //enter your code here
        return Arrays.stream(s.split(""))
                .reduce("",(s1,s2) -> s1 + s2.repeat(2));
    }

    public static String doubleChar2(String s){
        return s.replaceAll(".", "$0$0");


    }

    public static String[] towerBuilder(int nFloors)
    {
        int stringLength = getCorrectNumber(nFloors);
        List<String> resultLst = new ArrayList<>();
        for (int i = 0; i < nFloors; i = i+ 1) {
            String current = getString(i + 1, stringLength);
            resultLst.add(current);
        }
        resultLst.forEach(System.out::println);
        return resultLst.toArray(new String[nFloors]);
    }


    public static String getString(int n, int stringLength) {
        int center = (stringLength / 2) + 1;
        String result = "*";
        for (int i = 1; i < center; i++) {
            if (i < n ) {
                result = "*" + result + "*";
            } else {
                result = " " + result + " ";
            }
        }

        return result;
    }

    public static int getCorrectNumber(int n) {
        if (n < 1) {
         return 0;
        }
        return (n -1) * 2 + 1;
    }

    public static String[] TowerBuilder2(int n) {
        String t[] = new String[n], e;

        for (int i = 0; i < n; i++)
            t[i] = (e = " ".repeat(n-i-1)) + "*".repeat(i+i+1) + e;

        return t;
    }

    public static String[] towerBuilder3(int nFloors)
    {
        return java.util.stream.IntStream.rangeClosed(1, nFloors).mapToObj(x -> " ".repeat(nFloors-x) + "*".repeat(x*2-1) + " ".repeat(nFloors-x)).toArray(String[]::new);
    }

    public static int liters(double time)  {
        //Your code goes here! Hint: You should change that -1
        int r = (int)Math.floor(time /2);
        return 0 ;
    }

    public static int[] digitize(long n) {
        // Code here
        List<Integer> list =
        Arrays.stream(String.valueOf(n).split(""))
                .map(s -> Integer.valueOf(s))
                .collect(Collectors.toList());
        Collections.reverse(list);
        int [] result = list.stream().mapToInt( i-> i).toArray();
        return result;
    }
    public static Integer [] staticPrimeNumbers = getPrimeNumbers(Integer.MAX_VALUE);
    public static String factors(int n) {
        // your code
//        System.out.println("input = " + n);
        String result = "";
        int currentNumber = n;
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for (int i = 1;
             i < staticPrimeNumbers.length && staticPrimeNumbers[i] <= currentNumber;
             i++) {
            int j = 0;
            while (currentNumber % staticPrimeNumbers[i] == 0 && currentNumber > 0) {
                currentNumber = currentNumber / staticPrimeNumbers[i];
                j = j + 1;
//                System.out.println("j = " + j + ", currentNumber = " + currentNumber);
            }
            if (j != 0) {
                map.put(staticPrimeNumbers[i], j);
            }
        }
//        System.out.println("map = " + map);
        if (currentNumber > (int)Math.floor(Math.sqrt(Integer.MAX_VALUE))) {
            map.put(currentNumber, 1);
        }
        result = convertNumbersMapToString(map);
        return  result;
    }

    public static String convertNumbersMapToString(Map<Integer,Integer> map) {
        String result = "";
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                result = result + "(" + entry.getKey() + "**" + entry.getValue() + ")";
            } else {
                if (entry.getValue() == 1) {
                    result = result + "(" + entry.getKey() + ")";
                }
            }
        }
//        System.out.println("actual string from map is " + result);
        return result;
    }

    public static Integer[] getPrimeNumbers(int n) {
        int highLevel = (int)Math.floor(Math.sqrt(n) + 1);

        Set<Integer> set = IntStream.range(1,highLevel)
                .boxed()
                .collect(Collectors.toSet());
//        System.out.println("part1");
//        int highMax = (int)Math.floor(Math.sqrt(Integer.MAX_VALUE));
//        System.out.println(" max int sqrt = " + highMax);
//        System.out.println(" highLevel = " + highLevel);

        for (int i = highLevel; i > 1; i--) {
            for (int j = 2; j < highLevel / i; j++) {
                set.remove(i * j);
//                System.out.println(i * j);

            }

        }
//        System.out.println("part2");
//        System.out.println(set);
        return set.toArray(new Integer[set.size()]);
    }

    public static String factors2(int n) {
        String result = "";
        int cur = n;
        for(int i = 2; i<=cur; i++){
            int ct = 0;
            while(cur%i == 0){
                ct += 1;
                cur = cur/i;
            }
            if(ct == 1)
                result  = result + "(" + i + ")";
            else if(ct > 1)
                result  = result + "(" + i + "**" + ct + ")";
        }
        return result;
    }

    public static String removeUrlAnchor(String url) {
        String [] strings = url.split("#");
        return strings[0]; // Write code here
    }

    public static String[] dirReduc(String[] arr) {
        String[] result = calulateNewArrayAfterReduction(arr, null);
        return result;
    }

    public static String[] calulateNewArrayAfterReduction(String[] arr, Set<Integer> inputSet) {
        Set<Integer> set;
        if (inputSet == null) {
           set = getNumbersToRemove(arr);
        } else {
            set = new HashSet<>(inputSet);
        }
        if (set.isEmpty())  {
           String[] result = new String[arr.length];
            for (int i = 0; i < arr.length; i++) {
                result[i] = arr[i];
            }
            return result;
        } else {
            Set<Integer> nextSet = getNumbersToRemove(arr);
            List<String> nextArrayList  = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (!nextSet.contains(i)) {
                    nextArrayList.add(arr[i]);
                }
            }
            String [] nextArray = nextArrayList.toArray(new String[nextArrayList.size()]);
            String[] result = calulateNewArrayAfterReduction(nextArray, nextSet);
            return result;
        }
    }


    public static Set<Integer> getNumbersToRemove(String[] arr ) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if ((getValueForString(arr[i]) - getValueForString(arr[i + 1]))
                    * (getValueForString(arr[i + 1]) - getValueForString(arr[i]))
                    == -1) {
                set.add(i);
                set.add(i + 1);
            }
        }
        return set;

    }

    public static Integer getValueForString(String string) {
        Map<String, Integer> map =
                new HashMap<>(){{
                    put("NORTH", 1);
                    put("SOUTH",2);
                    put("WEST",11);
                    put("EAST",12);
                }};
        if (map.containsKey(string)) {
            return map.get(string);
        }
        return null;
    }

    public static String[] dirReduc2(String[] arr) {
        final Stack<String> stack = new Stack<>();

        for (final String direction : arr) {
            final String lastElement = stack.size() > 0 ? stack.lastElement() : null;

            switch(direction) {
                case "NORTH": if ("SOUTH".equals(lastElement)) { stack.pop(); } else { stack.push(direction); } break;
                case "SOUTH": if ("NORTH".equals(lastElement)) { stack.pop(); } else { stack.push(direction); } break;
                case "EAST":  if ("WEST".equals(lastElement)) { stack.pop(); } else { stack.push(direction); } break;
                case "WEST":  if ("EAST".equals(lastElement)) { stack.pop(); } else { stack.push(direction); } break;
            }
        }
        return stack.stream().toArray(String[]::new);
    }

    public static String[] dirReduc3(String[] arr) {
        Stack<String> dist = new Stack<String>();

        for (String i: arr)
            if (dist.isEmpty() || !getOpposite(dist.lastElement()).equals(i))
                dist.push(i);
            else dist.pop();
        return dist.toArray(new String[]{});
    }

    private static String getOpposite(String str){
        switch (str){
            case "NORTH": return "SOUTH";
            case "SOUTH": return "NORTH";
            case "EAST": return "WEST";
            case "WEST": return "EAST";
        }
        return null;
    }

    private static final Map<String, Integer> dirs = new HashMap<>();
    static {
        dirs.put("NORTH", 1);
        dirs.put("SOUTH", -1);
        dirs.put("EAST", 2);
        dirs.put("WEST", -2);
    }

    public static String[] dirReduc4(String[] a) {
        Deque<String> list = new ArrayDeque<>();
        for (String d: a) {
            Integer c = dirs.get(d);
            Integer p = dirs.get(list.peekLast());
            if (p == null || p + c != 0) list.addLast(d);
            else list.removeLast();
        }
        return list.toArray(new String[list.size()]);
    }

    public static String[] dirReduc5(String[] a) {
        Stack<String> result = new Stack<String>();
        for (int i = 0; i < a.length; i++) {
            if (result.size() > 0) {
                switch (a[i]) {
                    case "NORTH":
                        if (result.peek() == "SOUTH") result.pop();
                        else result.push(a[i]);
                        break;
                    case "SOUTH":
                        if (result.peek() == "NORTH") result.pop();
                        else result.push(a[i]);
                        break;
                    case "EAST":
                        if (result.peek() == "WEST") result.pop();
                        else result.push(a[i]);
                        break;
                    case "WEST":
                        if (result.peek() == "EAST") result.pop();
                        else result.push(a[i]);
                        break;
                }
            }
            else result.push(a[i]);
        }
        return result.toArray(new String[result.size()]);
    }

    public static String[] dirReduc6(String[] arr) {
        ArrayList<String> array = new ArrayList();
        array.add("NORTHSOUTH");
        array.add("SOUTHNORTH");
        array.add("EASTWEST");
        array.add("WESTEAST");
        ArrayList<String> result = new ArrayList();
        for (int i = arr.length - 1; i > -1; --i) {
            if (!result.isEmpty() && array.contains(arr[i] + result.get(0))) {
                result.remove(0);
            } else {
                result.add(0, arr[i]);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public static String[] dirReduc7(String[] arr) {
        String[] reducedArr = arr;
        Integer reduceIndex = null;

        // Find first instance of a useless pair of directions
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == "NORTH" && arr[i] == "SOUTH"){
                reduceIndex = i;
                break;
            }
            if (arr[i - 1] == "SOUTH" && arr[i] == "NORTH"){
                reduceIndex = i;
                break;
            }
            if (arr[i - 1] == "WEST" && arr[i] == "EAST"){
                reduceIndex = i;
                break;
            }
            if (arr[i - 1] == "EAST" && arr[i] == "WEST"){
                reduceIndex = i;
                break;
            }
        }

        if (reduceIndex != null) { // if a useless pair was found...
            // build a new array without the useless pair of directions
            reducedArr = new String[arr.length - 2];
            for (int i = 0; i < arr.length; i++){
                if (i < reduceIndex - 1) {
                    reducedArr[i] = arr[i]; // Copy directions before the useless pair
                } else if (i > reduceIndex){
                    reducedArr[i - 2] = arr[i]; // Copy directions after the useless pair
                }
            }
            reducedArr = dirReduc(reducedArr); // Feed new array back into the method (Recursion!)
        }

        return reducedArr;
    }

    public static String[] dirReduc8(String[] arr) {
        String arrayString = String.join(",", arr);
        while (arrayString.matches(".*NORTH,SOUTH.?.*|.*SOUTH,NORTH.?.*|.*EAST,WEST.?.*|.*WEST,EAST.?.*")) {
            arrayString = arrayString.replaceAll("NORTH,SOUTH.?|SOUTH,NORTH.?|EAST,WEST.?|WEST,EAST.?", "");
        }
        return arrayString.length() > 0 ? arrayString.split(",") : new String[]{};
    }

    private static final Map<String, String> OPPOSITE_DIRECTION = new HashMap<String, String>() {
        {
            put("NORTH", "SOUTH");
            put("SOUTH", "NORTH");
            put("WEST", "EAST");
            put("EAST", "WEST");
        }
    };


    public static String[] dirReduc9(String[] arr) {
        Stack<String> movements = new Stack<>();
        Stream.of(arr).forEach(s -> {
            if(!movements.empty() && OPPOSITE_DIRECTION.get(s).equals(movements.peek())) {
                movements.pop();
            }else{
                movements.push(s);
            }
        });

        return movements.toArray(new String[]{});
    }

    public static String[] dirReduc10(String[] arr) {
        Deque<Direction> route = new LinkedList<>();

        for (String name : arr) {
            Direction direction = Direction.getDirectionByName(name);

            Direction lastDirection = route.size() > 0 ? route.getLast() : null;

            if (direction.isOpposite(lastDirection)) {
                route.removeLast();
            } else {
                route.addLast(direction);
            }
        }

        return route.stream().map(Direction::toString).toArray(String[]::new);
    }

    private static enum Direction {
        NORTH("NORTH") {
            public boolean isOpposite(Direction direction) {
                return SOUTH.equals(direction);
            }
        },
        SOUTH("SOUTH") {
            public boolean isOpposite(Direction direction) {
                return NORTH.equals(direction);
            }
        },
        EAST("EAST") {
            public boolean isOpposite(Direction direction) {
                return WEST.equals(direction);
            }
        },
        WEST("WEST") {
            public boolean isOpposite(Direction direction) {
                return EAST.equals(direction);
            }
        };

        public static Direction getDirectionByName(String str) {
            for (Direction direction : Direction.values()) {
                if (direction.name.equals(str)) {
                    return direction;
                }
            }
            return null;
        }

        Direction(String name) {
            this.name = name;
        }

        abstract boolean isOpposite(Direction direction);

        private final String name;
        public String toString() {
            return this.name;
        }
    }

    public static String[] dirReduc11(String[] arr) {
        String str =null;
        while (!String.join(",", arr).equals(str)) {
            str = String.join(",", arr);
            str = str.replaceAll("WEST,EAST", "");
            str = str.replaceAll("NORTH,SOUTH", "");
            str = str.replaceAll("EAST,WEST", "");
            str = str.replaceAll("SOUTH,NORTH", "");
            str = str.replaceAll("^,+", "");
            arr = str.split(",+");
        }
        return "".equals(str)?new String[]{}:arr;
    }

    public static String[] dirReduc12(String[] arr) {
        Map<String, String> opposites = new HashMap<>();
        List<String> result = new ArrayList<>();

        opposites.put("NORTH", "SOUTH");
        opposites.put("SOUTH", "NORTH");
        opposites.put("EAST", "WEST");
        opposites.put("WEST", "EAST");

        for (String direction : arr) {

            if (!result.isEmpty() && result.get(result.size() - 1).equals(opposites.get(direction))) {
                result.remove(result.size() - 1);
            } else {
                result.add(direction);
            }
        }
        return result.toArray(new String[0]);
    }

    public static String[] dirReduc13(String[] directions) {
        Stack<String> stack = new Stack<>();

        for (String direction : directions) {
            if (!stack.isEmpty() && isOpposite(stack.peek(), direction)) {
                stack.pop();
            } else {
                stack.push(direction);
            }
        }
        return stack.toArray(new String[0]);
    }

    private static boolean isOpposite(String dir1, String dir2) {
        return switch (dir1) {
            case "NORTH" -> dir2.equals("SOUTH");
            case "SOUTH" -> dir2.equals("NORTH");
            case "EAST" -> dir2.equals("WEST");
            case "WEST" -> dir2.equals("EAST");
            default -> false;
        };
    }

    public static String[] dirReduc14(String[] arr) {
        List<String> list = new ArrayList<>();
        for(String dir : arr) {
            String opposite = getOpposite14(dir);
            if(!list.isEmpty() && list.get(list.size()-1) == opposite) {
                list.remove(list.size()-1);
            } else {
                list.add(dir);
            }
        }
        return list.stream().toArray(String[]::new);
    }

    private static String getOpposite14(String dir) {
        return switch (dir) {
            case "SOUTH" -> "NORTH";
            case "NORTH" -> "SOUTH";
            case "WEST" -> "EAST";
            case "EAST" -> "WEST";
            default -> null;
        };
    }
//
    public static String[] dirReduc15(String[] arr) {
        Deque<String> deque = new ArrayDeque<>();
        for (String str: arr) {
            if (isRedundant(deque, str)) deque.removeLast();
            else deque.addLast(str);
        }
        return deque.toArray(String[]::new);
    }

    private static boolean isRedundant(Deque<String> deque, String str) {
        return !deque.isEmpty() &&
                vocab.get(deque.getLast())
                        .equals(str);
    }

    private static final Map<String, String> vocab = Map.of(
            "NORTH", "SOUTH", "SOUTH", "NORTH", "WEST", "EAST", "EAST", "WEST");

//
    public static final String NORTH = "NORTH";
    public static final String SOUTH = "SOUTH";
    public static final String EAST = "EAST";
    public static final String WEST = "WEST";

    public static String[] dirReduc16(String[] arr) {
        Stack<String> stack = new Stack<>();

        for (String s : arr) {
            if (stack.isEmpty() || !(Objects.equals(getOppositeDirection(s), stack.lastElement()))) {
                stack.push(s);
            } else {
                stack.pop();
            }
        }
        return stack.stream().toArray( value -> new String[value]);
    }

    private static String getOppositeDirection(String s) {
        switch (s) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return null;
        }
    }

    //

    private static String str;
    private static String afterStr;
    private static ArrayList<String> list = new ArrayList<String>();
    private static boolean recursion = false;


    public static String[] dirReduc17(String[] arr) {


        list = new ArrayList<String>();

        for (int i = 0; i < arr.length - 1; i++) {

            str = arr[i];
            afterStr = arr[i + 1];

            if (str == "NORTH" && afterStr == "SOUTH" || str == "SOUTH" && afterStr == "NORTH") {
                arr[i] = "";
                arr[i + 1] = "";
                recursion = true;

            }

            if (str == "WEST" && afterStr == "EAST" || str == "EAST" && afterStr == "WEST") {
                arr[i] = "";
                arr[i + 1] = "";
                recursion = true;
            }
        }

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != "") {
                list.add(arr[i]);
            }
        }

        arr = list.toArray(new String[0]);

        if (recursion) {
            recursion = false;
            return dirReduc(arr);
        }

        return arr;
    }

    public static String[] dirReduc18(String[] arr) {
        String s = Arrays.toString(arr).replaceAll("\\[|\\]|\\s","");
        String prev;
        do {
            prev = s;
            s = s.replaceAll("(NORTH,SOUTH)|(SOUTH,NORTH)|"
                    + "(EAST,WEST)|(WEST,EAST)|(^,)|(,$)", "");
            s = s.replaceAll(",{2,}",",");
        } while (!prev.equals(s));
        return s.length() > 0 ? s.split(",") : new String[0];
    }

    private static Map<String, Integer> transMap;
    static {
        transMap = new HashMap<>(4);
        transMap.put("EAST", 2);
        transMap.put("WEST", 3);
        transMap.put("SOUTH", 5);
        transMap.put("NORTH", 6);
    }

    public static String[] dirReduc19(String[] arr) {
        Stack<String> stack = new Stack<>();
        for (String n : arr) {
            entertainment(n, stack);
        }
        return stack.toArray(new String[0]);
    }

    private static void entertainment(String data, Stack<String> stack){
        if(!stack.isEmpty() && Math.abs(transMap.get(data) - transMap.get(stack.peek())) == 1){
            stack.pop();
            return;
        }
        stack.push(data);
    }

    public static String[] dirReduc20(String[] arr) {
        LinkedList<String> steps = new LinkedList<>();
        for(String current: arr) {
            if(!steps.isEmpty()) {
                String last = steps.getLast();
                if(     current.equals("NORTH") && last.equals("SOUTH") ||
                        current.equals("SOUTH") && last.equals("NORTH") ||
                        current.equals("EAST") && last.equals("WEST") ||
                        current.equals("WEST") && last.equals("EAST")) {
                    steps.removeLast();
                    continue;
                }
            }
            steps.add(current);
        }
        return steps.toArray(new String[0]);
    }

    public static boolean xor(boolean a, boolean b) {
        //your code here:
        System.out.println("a = " + a + " ,b = " + b);
        if (a == false && b == false) {
            return false;
        }
        boolean r = !a || !b;
        System.out.println("r = " + r);
        return r;
    }

    public static int[] map(int[] arr) {

        int[] result = Arrays.stream(arr).map(e -> e * 2)
                .toArray();
        return result;
    }

    public static int century(int number) {
        // your code goes here
//        if (number % 100 != 0) {
//            return  number / 100 + 1;
//        }
//        return  number / 100;
        return number % 100 == 0 ? number / 100 : number/100 + 1;
    }

    public static String warnTheSheep(String[] array) {
        //Your code)))
        int resultCount = 0;
        String result = "Pls go away and stop eating my sheep";
        Deque<String> linkedList = new LinkedList<>(Arrays.asList(array));
        int size = linkedList.size();
        for (int i = 0; i < size; i++) {
            String current = linkedList.pollLast();
            if (current.equals("wolf")) {
                resultCount = i;
            }
        }
        if (resultCount != 0) {
            result = "Oi! Sheep number " + resultCount + "! You are about to be eaten by a wolf!";
        }
        return result;
    }

    public static String warnTheSheep2(String[] array) {
        if (array == null || array.length == 0) throw new IllegalArgumentException();

        if (array[array.length - 1].equalsIgnoreCase("wolf")) {
            return "Pls go away and stop eating my sheep";
        }

        int wolfIndex = IntStream.range(0, array.length)
                .filter(i -> array[i].equals("wolf"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());

        int place = array.length - 1 - wolfIndex;

        return String.format("Oi! Sheep number %d! You are about to be eaten by a wolf!", place);
    }

    public static String mix(String s1, String s2) {
        // your code
        String result = "";
        System.out.println("string1 = " + s1);
        System.out.println("string2 = " + s2);
        if (s1.equals(s2)) {
            return "";
        }
        Map<String, Long> map1 = getMapFromString(s1);
        Map<String, Long> map2 = getMapFromString(s2);

        System.out.println("map1 = " + map1);
        System.out.println("map2 = " + map2);
        Map<String, Map<String, Long>> mapOfMaps = new HashMap<>();
        mapOfMaps.put("1",map1);
        mapOfMaps.put("2",map2);
        Map<String, Map<String, Long>> resultMap =
                createMixedStringFromMapOfMaps(mapOfMaps, new HashMap<>());
        System.out.println("map of maps : " + mapOfMaps);
        System.out.println("resultMpa : " + resultMap);
        String stringResult = getResultStringFromCombinedMap(resultMap);
        System.out.println("string result = " + stringResult);
    return stringResult;
    }

    public static String getResultStringFromCombinedMap(Map<String, Map<String, Long>> compbinedMap) {
       String result = "";
       Map<String, String> resultMapForString = new HashMap<>();

       Comparator<Map.Entry<String,String>> equalsComparator =
               new Comparator<Map.Entry<String, String>>() {
                   @Override
                   public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                       return o1.getKey().compareTo(o2.getKey());
                   }
               };
       Comparator<Map.Entry<String, String>> anotherComparator =
               new Comparator<Map.Entry<String, String>>() {
                   @Override
                   public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                       return o1.getValue().compareTo(o2.getValue());
                   }
               };
       Comparator<Map.Entry<String, String>> thirdComparator = (o1,o2) -> {
           int first = o1.getKey().toCharArray()[0];
           int second = o1.getKey().toCharArray()[0];

           if  (first > second) return -1;
           if (first < second) return 1;
           return o1.getKey().compareTo(o2.getKey());
       };
       Comparator<Map.Entry<String,String>> comparator4 =
        Comparator
                .comparing((Map.Entry<String,String> entry) ->
                        entry.getKey().length())
                .thenComparing((Map.Entry<String,String> entry)
                               -> !entry.getValue().equals("="))
                .thenComparing((Map.Entry<String,String> entry) ->
                        entry.getKey().length()).reversed()
//                .thenComparing((Map.Entry<String,String> entry) ->
//                        entry.getKey().toCharArray()[0])
                .thenComparing((Map.Entry<String,String> entry) ->
                        entry.getValue());

       Comparator<Map.Entry<String,String>> myComparator =
               Map.Entry.comparingByKey();

       Map<String, String> sortedMap = new TreeMap<String, String>(
//               myComparator
//               new Comparator<String>() {
//                   @Override
//                   public int compare(String o1, String o2) {
//                       if (o1.length() > o2.length()) {
//                           return -2;
//                       } else if (o1.equals("=")) {
//                           System.out.println("equals");
//                           return -1;
//                       }
//
//                       return 0;
//                   }
//               }
       );

       for (Map.Entry<String, Map<String, Long>> entry : compbinedMap.entrySet()) {
           String repeatedLetterString = "";
           String letter = entry.getKey();
           Long numberOfRepeats = entry.getValue().entrySet()
                   .stream()
                   .findFirst()
                   .get()
                   .getValue();
           String providerName = entry.getValue().entrySet()
                   .stream()
                   .findFirst()
                   .get()
                   .getKey();
           for (int i = 0; i < numberOfRepeats; i++) {
               repeatedLetterString = repeatedLetterString + letter;
           }
//           result = result + providerName + ":" + repeatedLetterString + "/";
           resultMapForString.put(repeatedLetterString, providerName);
       }
        System.out.println("map for string1 : " + resultMapForString);
        sortedMap.putAll(resultMapForString);
        System.out.println("sortedMap for string : " + sortedMap);
        Map<String, String> secondSortedMap = new TreeMap<String, String>(
//
//                new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        if (o1.equals("=")) {
//                            return -1;
//                        } else {
//                            return 1;
////                                    o1.compareTo(o2);
//                        }
//                    }
//                }
        );
        List<Map.Entry<String, String>> sortedEntries =
                sortedMap.entrySet()
                        .stream()
//                        .sorted(thirdComparator)
//                        .sorted(myComparator.reversed())
//                        .sorted()
//                        .sorted(anotherComparator)
                        .sorted(comparator4)
                        .collect(Collectors.toList());
        System.out.println("sorted entries list : " + sortedEntries);

//        secondSortedMap.putAll(sortedMap);
        System.out.println("secondSortedMap for string : " + secondSortedMap);

        for (Map.Entry<String, String> entry : sortedEntries) {
                       result = result + entry.getValue() + ":" + entry.getKey() + "/";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public static Map<String, Map<String, Long>> createMixedStringFromMapOfMaps(Map<String, Map<String,Long>> inputMap, Map<String,Map<String, Long>> resultMap) {
        Map<String, Map<String, Long>> result = resultMap;
        for (Map.Entry<String, Map<String, Long>> currentMapFromInput : inputMap.entrySet()) {
            Map<String,Long> currentMapFromInputValue = currentMapFromInput.getValue();
            String currentMapFromInputKey = currentMapFromInput.getKey();
            for (Map.Entry<String, Long> currentRealMapEntry : currentMapFromInputValue.entrySet()) {
                if (!result.containsKey(currentRealMapEntry.getKey())) {
                    Map<String, Long> currentPair = new HashMap<>();
                    currentPair.put(currentMapFromInputKey, currentRealMapEntry.getValue());
                    result.put(currentRealMapEntry.getKey(), currentPair);
                } else {
                    Map<String, Long> comparingMap = result.get(currentRealMapEntry.getKey());
//                    Long comparingNumber = comparingMap.get(currentMapFromInputKey);
                    Long comparingNumber = comparingMap.entrySet().stream().findFirst().get().getValue();
                    Long currentNumber = currentRealMapEntry.getValue();
                    if (currentNumber > comparingNumber) {
                        Map<String, Long> newPair = new HashMap<>();
                        newPair.put(currentMapFromInputKey, currentRealMapEntry.getValue());
                       result.put(currentRealMapEntry.getKey(), newPair);
                    } else if (currentNumber.equals(comparingNumber)) {
                        Map<String, Long> newPair = new HashMap<>();
                        newPair.put("=", currentRealMapEntry.getValue());
                        result.put(currentRealMapEntry.getKey(), newPair);
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, Long> getMapFromString(String string) {
       Map<String, Long> map = Arrays.stream(string.split(""))
               .filter(s -> s.matches("[a-z]"))
               .map(e -> e)
               .collect(Collectors.groupingBy(i-> i, Collectors.counting()));
//       map.entrySet().forEach((entry) -> {
//           if (entry.getValue() < 2) {
//               map.remove(entry);
//           }
//       });
        Map<String, Long> sortedMap =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(e -> e.getKey(),
                                e -> e.getValue(),
                                (a1,a2) -> a1,
                                LinkedHashMap::new));
        Map<String, Long> filteredMap = new HashMap<>();
        sortedMap.entrySet().forEach((entry) -> {
            if (entry.getValue() > 1) {
                filteredMap.put(entry.getKey(), entry.getValue());
            }
        });

        return filteredMap;
    }

    public static Integer mapStringToInteger(String string) {
//        Character c = string.charAt(0);
//        System.out.println(c);
//        int i = c;
//        System.out.println(i);
//        Character cc = (char) i;
//        System.out.println(cc);
//        System.out.println((int)'z');
        return (int) string.charAt(0);
    }


}
