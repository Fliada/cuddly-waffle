package com.example.springbasiccourseproject82;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class TextFileSizeModule implements IModule {


    @Override
    public boolean isSupported(String path) {
        return path != null && FilenameUtils.getExtension(path).equals("txt");
    }

    @Override
    public String getDescription() {
        return "Calculating file size";
    }

    @Override
    public void performAction(String path, PrintStream stream) {
        try {
            if(!isSupported(path)) throw new UnsupportedOperationException();
            stream.println("File size " + Files.size(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}