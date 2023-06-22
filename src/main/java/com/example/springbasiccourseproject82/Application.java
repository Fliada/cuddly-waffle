package com.example.springbasiccourseproject82;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Application {

    private static Application application = new Application();
    private static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
        System.out.println("Write path to file");

        args = new String[] { new Scanner(System.in).nextLine() };

        if(args == null || args.length == 0 || args[0].length() == 0) {
            System.out.println("No arguments");
            return;
        }
        PrintStream stream = System.out;
        InputStream reader = System.in;
        String path = args[0];
        //получаем список модулей
        List<IModule> modules = application
                .getContext()
                .getBean("engine", ModuleEngine.class)
                .getSupportedModules(path);

        moduleActionsPerform(stream, reader, modules, path);
    }

    public ApplicationContext getContext() {
        return context;
    }

    //обработка модулями
    public static void moduleActionsPerform(PrintStream output, InputStream input, List<IModule> modules, String path) {
        //если не нашлось модулей для обработки jpg, png, txt
        if(modules.size() == 0) {
            output.println("No modules found");
            return;
        }

        output.println("You can choose one of " + modules.size() + " modules");
        for (IModule module : modules) {
            output.println(module.getClass().getSimpleName());
        }
        int i = -1;
        while (i < 0) {
            output.println("Please, choose the module");
            int opt = new Scanner(input).nextInt();
            i = opt > 0 && opt <= modules.size() ? opt - 1 : i;
        }

        IModule module = modules.get(i);
        output.println(module.getDescription());
        module.performAction(path, output);
    }
}