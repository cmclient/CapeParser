package me.nedori.capeparser;

import me.nedori.capeparser.app.CapeParserApplication;

public class Bootstrap {

    public static void main(String[] args) {
        new Thread(() -> new CapeParserApplication(args), "CapeParserThread").start();
    }
}
