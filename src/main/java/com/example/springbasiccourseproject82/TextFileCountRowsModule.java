package com.example.springbasiccourseproject82;

import org.springframework.stereotype.Component;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

@Component
public class TextFileCountRowsModule implements IModule {

    @Override
    public boolean isSupported(String path) {
        return path != null && FilenameUtils.getExtension(path).equals("txt");
    }

    @Override
    public String getDescription() {
        return "Counting the number of lines in a file";
    }

    @Override
    public void performAction(String path, PrintStream stream) {
        try {
            if(!isSupported(path)) throw new UnsupportedOperationException();

            Stream<String> stringStream = Files.lines(Paths.get(path));
            Scanner scanner = new Scanner(new File(path));

            int lines = 0;
            while(scanner.hasNextLine() && (scanner.nextLine() != null)) {
                lines++;
            }

            stream.println("File has " + lines + " lines");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}