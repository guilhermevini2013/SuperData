package org.superdata.factory;

public class DataFactory {

    public static String validContent() {
        return """
                [
                    {
                        "nome":"guilherme",
                        "email":"guilherme@gmail.com",
                        "senha":"123456"
                    },
                    {
                        "nome":"Eduardo",
                        "email":"eduardo@gmail.com",
                        "senha":"123456"
                    }
                ]
                """;
    }

    public static String invalidContent() {
        return """
                [
                    {
                        "nome":"guilherme",
                        "email":"guilherme@gmail.com",
                        "senha":"123456"
                    }
                    {
                        "nome":"Eduardo",
                        "email":"eduardo@gmail.com",
                        "senha":"123456"
                    },
                ]
                """;
    }
}
