package analizador;

public class Token {
    private final String lexema;
    private final String patron;

    public Token(String lexeme, String pattern) {
        this.lexema = lexeme;
        this.patron = pattern;
    }

    public String getLexema() {
        return lexema;
    }

    public String getPatron() {
        return patron;
    }
}
