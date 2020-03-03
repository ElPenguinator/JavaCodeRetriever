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
    private static void fetchMethod(String path, String className, String methodName) throws IOException, URISyntaxException {
        Path testFile = Paths.get(new URI("file:///" + path));
        CompilationUnit compilationUnit = StaticJavaParser.parse(testFile);
        //Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName("MainActivity");
        Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName(className);
        List<MethodDeclaration> methodToRetrieve = classA.get().getMethodsByName(methodName);
        if (!methodToRetrieve.isEmpty())
            System.out.println(methodToRetrieve.get(0).toString());
    }

    public static void main(String args[]) throws URISyntaxException, IOException {

        if (args.length == 3) {
            //Path testFile = Paths.get(new URI("file:///D:/AndroidStudioProjects/androiddynamicverification/app/src/main/java/com/core/lambdaapplication/MainActivity.java"));
            switch (args[2]) {
                case "nlmr":
                    fetchMethod(args[0], args[1], "onLowMemory");
                    break;
                case "nltr":
                    fetchMethod(args[0], args[1], "onTrimMemory");
                    break;
                case "hmu" :
                    Path testFile = Paths.get(new URI("file:///" + args[0]));
                    CompilationUnit compilationUnit = StaticJavaParser.parse(testFile);
                    //Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName("MainActivity");
                    Optional<ClassOrInterfaceDeclaration> classA = compilationUnit.getClassByName(args[1]);
                    System.out.println(classA.toString());
                    break;
                default:
                    System.out.println("unrecognized code smell");
                    break;
            }
        }
        else {
            System.out.println("Not enough arguments. Option 0 is the path, option 1 is the class name, option 2 is the name of the code smell");
        }
    }
}
