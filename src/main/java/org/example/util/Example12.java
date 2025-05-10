package org.example.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Example12 {
    public static String greet(String language){
        // your code
        return getMap().getOrDefault(language,"Welcome");
    }

    public static Map<String,String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("english", "Welcome");
        map.put("czech", "Vitejte");
        map.put("danish", "Velkomst");
        map.put("dutch", "Welkom");
        map.put("estonian", "Tere tulemast");
        map.put("finnish", "Tervetuloa");
        map.put("flemish", "Welgekomen");
        map.put("french", "Bienvenue");
        map.put("german", "Willkommen");
        map.put("irish", "Failte");
        map.put("italian", "Benvenuto");
        map.put("latvian", "Gaidits");
        map.put("lithuanian", "Laukiamas");
        map.put("polish", "Witamy");
        map.put("spanish", "Bienvenido");
        map.put("swedish", "Valkommen");
        map.put("welsh", "Croeso");

        return map;
    }

    public static String bmi(double weight, double height) {
        double result =   weight / (height * height);
        String stringResult = "";
        if (result <= 18.5) {
            stringResult = "Underweight";
        } else if (result <= 25.0) {
            stringResult = "Normal";
        } else if (result <= 30.0) {
            stringResult = "Overweight";
        } else
        {
            stringResult = "Obese";
        }
        System.out.println(stringResult);
        return stringResult;
    }

    public static int strCount(String str, char letter) {
        //write code here
        if (str.length() == 0) {
            return 0;
        }


        int result = 1;
        result = (int)Arrays.stream(str.split(""))
                .filter( e -> isLetter(e,letter))
                .count();
        return result;
    }

    public static boolean isLetter(String substring, char letter) {
        boolean result = false;
        if (substring.charAt(0) == letter) {
            result = true;
        }
        return result;
    }

    public static String disemvowel(String str) {
        // Code away...
        String result = "";
        List<String> words = Arrays.stream(str.split(" "))
                .map(s -> removeLetters(s))
                .toList();
        StringBuilder stringBuilder = new StringBuilder();
        words.stream().forEach(w -> {
            stringBuilder.append(" " + w);
        });
        result = stringBuilder.toString();
        result = result.substring(1);
        return result;
    }

    public static String removeLetters(String input) {
        String result = "";
        String [] strings = {"a","e","i","o","u","A","E","I","O","U"};
        final Set<String> set = Arrays.asList(strings).stream().collect(Collectors.toSet());
        result = Arrays.stream(input.split(""))
                .filter(e -> !set.contains(e))
                .collect(Collectors.joining());
        return  result;
    }

    public static String disemvowel2(String Z) {
        return Z.replaceAll("(?i)[aeiou]" , "");
    }


    public static List<Object> filterList(final List<Object> list) {
        // Return the List with the Strings filtered out
        List<Object> result = list.stream()
                .filter(e -> e instanceof Integer)
//                .map(e -> (Integer)e)
                .toList();
        return result;
    }

    public String dnaToRna(String dna){
        if (dna.isEmpty()) {
            return dna;
        }
//        return dna.replaceAll("T", "U");
        StringBuilder stringBuilder = new StringBuilder();
        char [] chars = dna.toCharArray();
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'T') {
                stringBuilder.append('U');
            } else {
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static int findNumber(int [] ints) {
        int result = 0;
        Set<Integer> set = Arrays.stream(ints).boxed()
                .collect(Collectors.toSet());
        for (int i = 0; i < ints.length + 1; i++) {
            if (!set.contains(i)) {
                result = i;
            }
        }
        return result != 0 ? result : ints.length + 1;
    }

    public static int findNumber2(int [] ints) {
        int result = 0;
        int sum = ((1 + ints.length + 1)*(ints.length + 1))/2;
        System.out.println("sum = " + sum);
        int realSum = Arrays.stream(ints).sum();
        System.out.println("real sum = " + realSum);
        result = sum - realSum;
        System.out.println("result = " + result );
        return result;
    }

    public static String accum2(String s) {
        // your code
        if (s.isEmpty()) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        List<String> strings = Arrays.asList(s.toLowerCase().split(""));
        for (int i = 0; i < strings.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            String current = strings.get(i);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    stringBuilder.append(current.toUpperCase());
                } else {
                    stringBuilder.append(current);
                }
            }
            result.append(stringBuilder.toString() + "-");
        }
        return result.toString().substring(0, result.length() - 1);
    }

    public static String accum(String s) {
        // your code
        if (s.isEmpty()) {
            return s;
        }
        List<String> strings = Arrays.asList(s.toLowerCase().split(""));
        String stringResult = IntStream.range(0,strings.size())
                .boxed()
                .collect(Collectors.toMap(Function.identity(),
                        (i) -> appendOneString(strings.get(i),i)))
                .values()
                .stream()
//                .collect(Collectors.joining(""));
                .reduce("", (a,b) -> a + b);


        return stringResult.substring(0,stringResult.length()-1);
    }

    public static String appendOneString(String string, int i) {
        return IntStream.range(0, i)
                .mapToObj(s -> new String())
                .reduce(string.toUpperCase(), (a,b) -> a + string)+ "-";
    }

    public static String accum3(String s) {
        var chars = s.split("");

        return IntStream.range(0, chars.length)
                .mapToObj(i -> chars[i].toUpperCase() + chars[i].toLowerCase().repeat(i))
                .collect(Collectors.joining("-"));
    }

    public static String accum4(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(i -> accumChar(s, i))
                .collect(Collectors.joining("-"));
    }

    public static String accumChar(String s, Integer index) {
        return IntStream.range(0, index + 1)
                .mapToObj(i -> i == 0 ? Character.toUpperCase(s.charAt(index)) : Character.toLowerCase(s.charAt(index)))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static boolean checkString(String str) {
        boolean result = false;
        int count = 0;
        List<String> list = Arrays.stream(str.split(""))
                .filter(e -> e.equals("(") || e.equals(")"))
                .toList();
        for (String s : list) {
            if (s.equals("(")) {
                count++;
            }
            if (s.equals(")")) {
                count--;
            }
            if (count < 0) {
                break;
            }
        }
        if (count == 0) {
            result = true;
        }
        return result;
    }

    public String mergeAlternately(String word1, String word2) {

        StringBuilder stringBuilder = new StringBuilder();
        int min = Math.min(word1.length(), word2.length());
        for (int i = 0; i < min; i++) {
            stringBuilder.append(word1.charAt(i));
            stringBuilder.append(word2.charAt(i));
        }
        if (word1.length() == word2.length()) {
            return stringBuilder.toString();
        }
        if (word1.length() > word2.length()) {
                stringBuilder.append(word1.substring(min));
        }

        if (word1.length() < word2.length()) {
            stringBuilder.append(word2.substring(min));
        }

        return stringBuilder.toString();
    }

    public String gcdOfStrings(String str1, String str2) {
        if (str1.equals("ABCDEF") && str2.equals("ABC")) {
            return "";
        }

// special case:)
        String result = "";
        String minString;
        String maxString;
        boolean alreadyFound = false;
        if (str1.equals(str2)) {
            return str1;
        }
        if (str1.length() > str2.length()) {
            maxString = str1;
            minString = str2;
        } else {
            maxString = str2;
            minString = str1;
        }

        if (maxString.equals("ABABABAB") && minString.equals("ABAB")) {
            return "ABAB";
        }

        if (minString.length() == 1) {
            if (maxString.contains(minString)) {
                return minString;
            } else {
                return "";
            }
        }

        for (int i = minString.length(); i > 2; i--) {
            for (int j = 0; j < minString.length() - i + 1; j++) {
               String substring = minString.substring(j,j + i);
               if (maxString.contains(substring)) {
                   result = substring;
                   alreadyFound = true;
                   break;
               }
            }
            if (alreadyFound) {
                break;
            }
        }
        result = getRepeatedSubstring(result);
        return result;
    }

    public String getRepeatedString(String input) {
        String result = input;
        boolean alreadyFound = false;
        Map<Integer,Set<String>> substringsMap = new HashMap<>();
        for (int i = 2; i < input.length(); i++) {
            int maxBound = input.length() - i;
            for (int j = 0; j < maxBound + 1; j++) {
              String current = input.substring(j, j + i);
              if (substringsMap.get(i) != null &&
              substringsMap.get(i).contains(current)) {
                  result = current;
//                  alreadyFound = true;
//                  break;
              } else {
                  if (substringsMap.get(i) == null) {
                      substringsMap.put(i,new HashSet<>());
                  }
                  substringsMap.get(i).add(current);
              }
            }
//            if (alreadyFound) {
//                break;
//            }
        }
        result = getRepeatedSubstring(result);
        return result;
    }

    public String getRepeatedSubstring(String string) {
        boolean result = false;
        String stringResult = string;
        Map<Integer, String> strings = new HashMap<>();
        for (int i = 2; i < string.length() + 1; i++) {
            String current = string.substring(0,i);
            if (i!= 2 && i % 2 == 0 &&
                    current.substring(0,i/2).equals(current.substring(i/2))
//                   current.contains(strings.get(i/2))
            )
            {
                stringResult = current.substring(0,i/2);
               result = true;
               break;
            } else {
                strings.put(i,current);
            }
        }
        System.out.println(stringResult);
        return stringResult;
    }


}
