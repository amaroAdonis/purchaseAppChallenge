package parser;

import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserCsvParser implements CsvParser<User> {

    private final String filePath;
    private final String columnSymbol;

    public UserCsvParser(String filePath, String columnSymbol) {
        this.filePath = filePath;
        this.columnSymbol = columnSymbol;
    }

    @Override
    public void append(User user) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            writeUserInCsv(bw, user);
        }
    }

    @Override
    public void storeAll(List<User> users) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (var user : users) {
                writeUserInCsv(bw, user);
            }
        }
    }

    @Override
    public List<User> readAll() throws IOException {
        var userList = new ArrayList<User>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = br.lines().toList(); // ["1,Geverson","2,Adones"]
            for (var line : lines) {// "1,Geverson"
                var user = createUser(line);
                userList.add(user);
            }
        }
        return userList;
    }

    private void writeUserInCsv(BufferedWriter bw, User user) throws IOException {
        var csvLine = generateLine(user);
        bw.write(csvLine);
        bw.newLine();
    }

    private String generateLine(User user) {
        return user.getId() + "," + user.getName();
    }

    private User createUser(String line) {
        String[] column = line.split(columnSymbol);
        Integer id = Integer.valueOf(column[0]);
        User user = new User();
        user.setId(id);
        user.setName(column[1]);
        return user;
    }
}
