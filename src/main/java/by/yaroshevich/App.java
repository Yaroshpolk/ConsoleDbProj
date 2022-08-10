package by.yaroshevich;

import java.io.*;

public class App
{
    public static void main( String[] args )
    {
        Messenger.printStartMessage();

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String str;

            while (!(str = bufferedReader.readLine()).equals("exit")) {
                System.out.println(str);
            }

        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
