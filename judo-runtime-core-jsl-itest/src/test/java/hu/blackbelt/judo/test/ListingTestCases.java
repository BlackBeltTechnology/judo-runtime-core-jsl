package hu.blackbelt.judo.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.lang.System.out;

public class ListingTestCases {
    public static void main(String[] args) {
        out.println("Start");
        ListingTestCases listingTestCases = new ListingTestCases();
        try {
            for (Class klass : listingTestCases.getClasses(
                    "hu.blackbelt.judo.runtime.core.jsl")
            ) {
                out.println(klass.getPackage().toString() + "." + klass.getName());
                for (Method method : klass.getMethods()) {
                    if (method.isAnnotationPresent(Requirement.class)) {
                        out.println(klass.getName() + "." + method.getName());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("End");
    }

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     *
     * @param packageName
     *            The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private Iterable<Class> getClasses(String packageName) throws ClassNotFoundException, IOException
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            try {
                URI uri = new URI(resource.toString());
                //out.println(uri.getPath().toString());
                dirs.add(new File(uri.getPath()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                // skip
            }
        }
        List<Class> classes = new ArrayList<Class>();
        out.println("Dirs:");
        for (File directory : dirs)
        {
            out.println(directory.getPath());
            classes.addAll(findClasses(directory, packageName));
        }

        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     *
     * @param directory
     *            The base directory
     * @param packageName
     *            The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists())
        {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class"))
            {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }

        return classes;
    }
}
