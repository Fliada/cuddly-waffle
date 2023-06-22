package com.example.springbasiccourseproject82;

import java.io.PrintStream;

public interface IModule {
    boolean isSupported(String path);
    String getDescription();
    void performAction(String path, PrintStream stream);
}