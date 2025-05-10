package org.example.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationLimiter {
    private List<User> users;
    private int writeLimit;
    private int readLimit;
    private Map<String, Integer> readMap = new HashMap<String, Integer>();
    private Map<String, Integer> writeMap = new HashMap<String, Integer>();

    public OperationLimiter(List<User> users, int writeLimit, int readLimit) {
        this.users = users;
        this.writeLimit = writeLimit;
        this.readLimit = readLimit;
        this.initMaps();
    }

    private void initMaps() {
        for (User user : users) {
            readMap.put(user.getName(), 0);
            writeMap.put(user.getName(), 0);
        }
    }

    public boolean isOperationAllowed(Operation operation) {
        boolean allow = true;
        if (operation.getOperatioType().equals(OperatioType.READ)) {
            if (readMap.get(operation.getUserId()) >= readLimit) {
                return false;
            }
        }
        if (operation.getOperatioType().equals(OperatioType.WRITE)) {
            if (writeMap.get(operation.getUserId()) >= writeLimit) {
                return false;
            }
        }
        return allow;
    }
    public void doOperation(Operation operation) {
        if (!this.isOperationAllowed(operation)) {
            return;
        }
        if (operation.getOperatioType().equals(OperatioType.READ)) {
            readMap.put(operation.getUserId(), readMap.get(operation.getUserId()) + 1);
        } else if (operation.getOperatioType().equals(OperatioType.WRITE)) {
            writeMap.put(operation.getUserId(), writeMap.get(operation.getUserId()) + 1);
        }
    }

    public void printMaps() {
        System.out.println("readMap = " + readMap);
        System.out.println("writeMap = " + writeMap);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getWriteLimit() {
        return writeLimit;
    }

    public void setWriteLimit(int writeLimit) {
        this.writeLimit = writeLimit;
    }

    public int getReadLimit() {
        return readLimit;
    }

    public void setReadLimit(int readLimit) {
        this.readLimit = readLimit;
    }
}
class Operation {
    private String userId;
    private OperatioType operatioType;

    public Operation(String userId, OperatioType operatioType) {
        this.userId = userId;
        this.operatioType = operatioType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OperatioType getOperatioType() {
        return operatioType;
    }

    public void setOperatioType(OperatioType operatioType) {
        this.operatioType = operatioType;
    }
}
class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
enum OperatioType {
    READ,
    WRITE
}