package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        StringUnpacker unpacker = new StringUnpacker();

        System.out.println(unpacker.unpack("3[xyz]4[xy]z"));
        System.out.println(unpacker.unpack("2[3[x]y]"));
        String str = console.readLine();
        System.out.println(unpacker.unpack(str));


    }

}
