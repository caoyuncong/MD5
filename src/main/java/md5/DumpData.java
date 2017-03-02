package md5;

import com.mysql.jdbc.Driver;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/15.
 */
public class DumpData {
    private static final String CSDN_URL = "jdbc:mysql:///db_csdn";
    private static final String USER = "root";
    private static final String PASSWORD = "system";
    private static final String SELECT = "SELECT * FROM db_csdn.user_new";
    private static Set<String> passwords = new HashSet<>();

    public static void main(String[] args) throws SQLException {
        new Driver();
        Connection connection = DriverManager.getConnection(CSDN_URL, USER, PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
        long start = System.currentTimeMillis();
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("total time 1: " + (System.currentTimeMillis() - start) / 1000 + "seconds.");
        while (resultSet.next()) {
            passwords.add(resultSet.getString("password"));
        }
        passwords.clear();
        System.out.println(passwords.size());
        System.out.println("total time2: " + (System.currentTimeMillis() - start) / 1000 + "seconds.");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new BufferedWriter(new FileWriter("data")))) {
            for (String string : passwords) {
                bufferedWriter.write(string + "\t" + DigestUtils.md5Hex(string) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("total time 3: " + (System.currentTimeMillis() - start)/1000+"seconds.");
    }
}
/*
*
* Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.jar.Manifest$FastInputStream.<init>(Manifest.java:332)
	at java.util.jar.Manifest$FastInputStream.<init>(Manifest.java:327)
	at java.util.jar.Manifest.read(Manifest.java:195)
	at java.util.jar.Manifest.<init>(Manifest.java:69)
	at java.util.jar.JarFile.getManifestFromReference(JarFile.java:199)
	at java.util.jar.JarFile.getManifest(JarFile.java:180)
	at sun.misc.URLClassPath$JarLoader$2.getManifest(URLClassPath.java:944)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:450)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:73)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:368)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:362)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:361)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:411)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.util.ResourceBundle$RBClassLoader.loadClass(ResourceBundle.java:503)
	at java.util.ResourceBundle$Control.newBundle(ResourceBundle.java:2640)
	at java.util.ResourceBundle.loadBundle(ResourceBundle.java:1501)
	at java.util.ResourceBundle.findBundle(ResourceBundle.java:1465)
	at java.util.ResourceBundle.getBundleImpl(ResourceBundle.java:1361)
	at java.util.ResourceBundle.getBundle(ResourceBundle.java:890)
	at sun.util.resources.LocaleData$1.run(LocaleData.java:167)
	at sun.util.resources.LocaleData$1.run(LocaleData.java:163)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.util.resources.LocaleData.getBundle(LocaleData.java:163)
	at sun.util.resources.LocaleData.getCurrencyNames(LocaleData.java:87)
	at sun.util.locale.provider.LocaleResources.getCurrencyName(LocaleResources.java:217)
	at sun.util.locale.provider.CurrencyNameProviderImpl.getString(CurrencyNameProviderImpl.java:122)
	at sun.util.locale.provider.CurrencyNameProviderImpl.getSymbol(CurrencyNameProviderImpl.java:90)
	at java.util.Currency$CurrencyNameGetter.getObject(Currency.java:642)

Process finished with exit code 1
*/