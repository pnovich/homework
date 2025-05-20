package org.example.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Example19 {
    public static void main(String[] args) {
        Example19 example19 = new Example19();
        Map<String,UserStats> visits1 = new HashMap<>();
        visits1.put("1",new UserStats(Optional.of(5l)));
//        visits1.put("f",new UserStats(Optional.of(10l)));
        visits1.put("3",new UserStats(Optional.of(20l)));
//        visits1.put("4", new UserStats(Optional.empty()));
        visits1.put("5",new UserStats(Optional.of(50l)));

//        visits1.put("1",new UserStats(Optional.of(15l)));

        Map<String, UserStats> visits2 = new HashMap<>();
        visits2.put("1",new UserStats(Optional.of(10l)));

        Map<Long, Long> result = example19.count(null,visits1, visits2);
        System.out.println("done " + result);

    }


    Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (visits == null || visits.length == 0) {
            return new HashMap<>();
        }

        boolean flag = true;

        Map<Long, Long> result = new HashMap<>();
        for(Map<String, UserStats> visit: visits) {
            if (visit != null && visit.size() != 0) {
                for (Map.Entry<String, UserStats> entry : visit.entrySet()) {
                    try {
                        Long id = Long.parseLong(entry.getKey());
                        Long longCount = 0l;
                        UserStats stats = entry.getValue();
                        if (stats != null ) {
//                            Optional<Long> longOptional;
                            if (stats.getVisitCount().isEmpty()) {
                                flag = false;
//                                break;
                            }
                            if (stats.getVisitCount().isPresent()) {
                                longCount = stats.getVisitCount().get();
                            }
                        }

                        if (!result.containsKey(id)) {
                            result.put(id, longCount);
                        } else {
                            result.put(id, result.get(id) + longCount);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(entry.getKey() + "is not parsable");
                    }
                }
            }
        }
        if (flag == false) {
            result = new HashMap<>();
        }
        return result;
    }

}

class UserStats {
    Optional<Long> visitCount;

    public UserStats(Optional<Long> visitCount) {
        this.visitCount = visitCount;
    }

    public Optional<Long> getVisitCount() {
        return visitCount;
    }
}