package org.example.util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Example9 {
    public static String replaceNth(String text, int n, char oldValue, char newValue) {
        if (n < 1 || text.length() == 0) {
            return text;
        }
        String result = "";
        StringBuilder stringBuilder = new StringBuilder(text);
        String stringCharacter = oldValue + "";
        Map<Integer, Integer> map = getCharacterNumbersMap(text, stringCharacter);
        for (int i = 1; i < map.size() + 1; i = i + 1) {
            if ( i % n == 0 ) {
                int charNumberInString = map.get(i);
                stringBuilder.setCharAt(charNumberInString, newValue);
            }
        }
        result = stringBuilder.toString();
        return result;
    }

    public static Map<Integer, Integer> getCharacterNumbersMap(String string, String charOld) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<String> characters = Arrays.stream(string.split(""))
                .toList();
        int oldCharacterCount = 0;
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).equals(charOld)) {
            oldCharacterCount++;
            map.put(oldCharacterCount, i);
            }
        }
        return map;
    }

    public static char findMissingLetter(char[] array)
    {
        int intResult = 0;
        int intChar1 = array[0];;
        int intChar2 = array[array.length - 1];
        Map<Integer,Character>  charactersMap = new HashMap<>();

        for (int i = intChar1; i < intChar2 + 1; i++) {
            charactersMap.put(i, null);
        }

        for (int j = 0; j < array.length; j++) {
            Character c2 = array[j];
            int currentKey = c2;
            charactersMap.put(currentKey, array[j]);
        }

        for (int i = intChar1; i < intChar2 + 1; i++) {
            if (charactersMap.get(i) == null) {
                intResult = i;
            }
        }
        return (char) intResult;
    }

    public static char findMissingLetter2(final char[] array) {
        return IntStream.range(0, array.length - 1)
                .parallel()
                .filter(i -> array[i + 1] != array[i] + 1)
                .mapToObj(i -> (char) (array[i] + 1))
                .findAny().orElseThrow(IllegalArgumentException::new);
    }

    public static String[] streetFighterSelection(String[][] fighters, int[] position, String[] moves){
        String [] result = new String[moves.length];
        int i = position[0];
        int j = position[1];
        for (int k = 0; k < moves.length; k++) {
            String move = moves[k];
            switch (move) {
                case "up": {
                    if (i != 0) {
                        i --;
                    }
                    break;
                }
                case "down": {
                    if (i != 1) {
                        i++;
                    }
                    break;

                }
                case "left": {
                    if (j != 0) {
                        j--;
                    } else  {
                        j = fighters[0].length - 1;
                    }
                    break;
                }
                case "right": {
                    if (j != fighters[0].length - 1) {
                        j++;
                    } else {
                        j = 0;
                    }
                    break;
                }
            }
            result[k] = fighters[i][j];
        }
        for (int k = 0; k < result.length; k++) {
            System.out.println(result[k]);
        }
        return result;
    }

    public static String rot13(String str) {
        // Your code goes here.
        if (str.isEmpty()) return str;
        List<String> strings = Arrays.stream(str.split(""))
                .toList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
          char input = strings.get(i).charAt(0);
            stringBuilder.append(rotateChar(input));
        }
        return stringBuilder.toString();
    }

    public static char rotateChar(char input) {
        int intInput = input;
        if ((intInput> 64 && intInput <78) || (intInput > 96 && intInput < 110)) {
            intInput = intInput + 13;
        } else

        if (input > 76 && input < 91) {
            intInput = 65 + input - 78;

        } else if (intInput > 109 && intInput < 123) {
            intInput = 97 + input - 110;
        }

        return (char) intInput;
    }

    public static String rot132(String str) {
        return str
                .chars()
                .mapToObj(c -> {
                    if (c >= 'a' && c <= 'z') {
                        return Character.toString((c + 13) > 'z' ? (c - 13) : c + 13);
                    }
                    if (c >= 'A' && c <= 'Z') {
                        return Character.toString((c + 13) > 'Z' ? (c - 13) : c + 13);
                    }
                    return Character.toString(c);
                })
                .collect(Collectors.joining());
    }

    public static String rot133(String str) {
        return Stream.of(str.split(""))
                .filter(letter -> !letter.isEmpty())
                .map(Example9::replace)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static char replace(String letter) {
        if(letter.matches("(?i)[A-M]")) return (char) (letter.charAt(0) + 13);
        else if(letter.matches("(?i)[N-Z]")) return (char) (letter.charAt(0) - 13);
        else return letter.charAt(0);
    }


    public static void checkChars() {
        char a = 'a';
        char b = 'z';
        char c = 'A';
        char d = 'Z';
        char s = 's';

        System.out.println((int)a);
        System.out.println((int)b);
        System.out.println((int)c);
        System.out.println((int)d);
        System.out.println((int)s);


    }

    public static boolean isValid(char[] walk) {
        // Insert brilliant code here
        boolean result = false;
        int i = 0;
        int j = 0;
        int count = 0;
        for (int k = 0; k < walk.length; k++) {
            char ch = walk[k];
            switch (ch) {
                case 'w' : {
                    j = j - 1;
                    break;
                }
                case 'e' : {
                    j = j +1;
                    break;
                }
                case 'n' : {
                    i = i - 1;
                    break;
                }
                case 's' : {
                    i = i + 1;
                    break;
                }
            }
            count++;
        }
        System.out.println("count = "+count+" i = "+i+" j = "+j);
        if (count == 10 && i ==0 && j==0) {
            result = true;
        }
        return result;
    }

    public static boolean isValid2(char[] walk) {
        // Insert brilliant code here
        boolean result = false;
        Map<Character, Walk> map = new HashMap<>();
        Position position = new Position(0,0);
        map.put('n', new DoStepNorth());
        map.put('s', new DoStepSouth());
        map.put('e', new DoStepEast());
        map.put('w', new DoStepWest());
        int count = 0;
        for (int k = 0; k < walk.length; k++) {
            map.get(walk[k]).doStep().accept(position);
            count++;
        }
        System.out.println("count = "+count+" x = "+position.x+" y = "+position.y);
        if (count == 10 && position.x ==0 && position.y==0) {
            result = true;
        }
        return result;
    }
}
class Position {
    int x;
    int y;
Position(int x, int y) {
    this.x = x;
    this.y = y;
}
void moveNorth(){
    this.y = this.y + 1;
}
void moveSouth(){
    this.y = this.y - 1;
}
void moveEast(){
    this.x = this.x + 1;
}
void moveWest(){
    this.x = this.x - 1;
}

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
interface Walk {
    Consumer<Position> doStep();
}
class DoStepNorth implements Walk {

    @Override
    public Consumer<Position> doStep() {
        return new Consumer<Position>() {
            @Override
            public void accept(Position position) {
                position.moveNorth();
                System.out.println(position);
            }
        };
    }
}
class DoStepSouth implements Walk {

    @Override
    public Consumer<Position> doStep() {
        return new Consumer<Position>() {
            @Override
            public void accept(Position position) {
                position.moveSouth();
                System.out.println(position);
            }
        };
    }
}
class DoStepEast implements Walk {

    @Override
    public Consumer<Position> doStep() {
        return new Consumer<Position>() {
            @Override
            public void accept(Position position) {
                position.moveEast();
                System.out.println(position);
            }
        };
    }
}
class DoStepWest implements Walk {

    @Override
    public Consumer<Position> doStep() {
        return new Consumer<Position>() {
            @Override
            public void accept(Position position) {
                position.moveWest();
                System.out.println(position);
            }
        };
    }
}



