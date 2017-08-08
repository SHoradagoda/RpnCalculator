package com.anz.rpncalc.io;

import java.util.Scanner;

/**
 * Reader from Console
 */
public class ConsoleInputReader implements InputReader {

    private Scanner scanner = new Scanner(System.in);
    @Override
    public String read() {
        return scanner.nextLine();
    }
}
