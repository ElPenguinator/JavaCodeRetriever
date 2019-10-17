package codesmellsstats;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.resolution.types.ResolvedType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class StatsSystem {
    public static void main(String args[]) throws URISyntaxException, IOException {

        if (args.length == 3) {
            //Path testFile = Paths.get(new URI("file:///D:/AndroidStudioProjects/androiddynamicverification/app/src/main/java/com/core/lambdaapplication/MainActivity.java"));
            Path testFile = Paths.get(new URI("file:///"+args[0]));
            CompilationUnit compilationUnit = StaticJavaParser.parse(testFile);
            //Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName("MainActivity");
            Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName(args[1]);
            /*
            switch (args[2]) {
                case "otm":
                    List<MethodDeclaration> onTrimMemory = classA.get().getMethodsByName("onTrimMemory");
                    if (!onTrimMemory.isEmpty())
                        System.out.println(onTrimMemory.get(0).toString());
                    break;
                case "olm":
                    List<MethodDeclaration> onLowMemory = classA.get().getMethodsByName("onLowMemory");
                    if (!onLowMemory.isEmpty())
                        System.out.println(onLowMemory.get(0).toString());
                    break;
                default:
                    System.out.println("unrecognized option");
                    break;
            }
            */
            List<MethodDeclaration> methodToRetrieve = classA.get().getMethodsByName(args[2]);
            if (!methodToRetrieve.isEmpty())
                System.out.println(methodToRetrieve.get(0).toString());
        }
        else {
            System.out.println("Not enough arguments. Option 0 is the path, option 1 is the class name, option 2 is the name of the function");
        }
    }
}
