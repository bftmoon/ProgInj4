package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder res = new StringBuilder();
        Files.readAllLines(Path.of("src/test.txt")).forEach(line -> {
            String[] mas = line.split("\\|");
            String[] name = Pattern.compile("[А-Я][а-я]+").matcher(mas[0]).results().limit(2).map(MatchResult::group).toArray(String[]::new);
            res.append(name.length == 2 ? name[0] + " " + name[1] + "|" : "|");
            Pattern.compile("\\d{1,3}").matcher(mas[1]).results().limit(1).forEach(m -> res.append(m.group()));
            res.append("|");
            StringBuilder phone = new StringBuilder("+");
            Pattern.compile("\\d+").matcher(mas[2]).results().forEach(m -> phone.append(m.group()));
            if (phone.length() == 12)
//                res.append(phone);
                res.append(phone.insert(2, "(").insert(6, ")").insert(10, "-").insert(13, "-"));
            res.append("|");
            mas[3] = mas[3].replaceFirst("@+", "@").replaceFirst("\\.+", ".");
            if (mas[3].matches("\\w+@\\w+\\.[a-z]+"))
                res.append(mas[3]);
            res.append("\n");
        });
        Files.writeString(Path.of("src/res.txt"), res);
    }
}
