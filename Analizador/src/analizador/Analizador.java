
package analizador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {

    static List<Token> tokenize(String input, boolean type) {
        List<Token> tokens = new ArrayList<>();
        Set<String> tokenSet = new HashSet<>(); // Utilizacion para evitar duplicados

        // Inicializacion de Tokens, para poder leer los patrones del codigo
        String[] tokenPatterns = {
            "\\bclass\\b", "Palabra reservada class",
            "\\{", "Símbolo llave abre",
            "\\}", "Símbolo llave cierra",
            "\\(", "Símbolo parentesis abre",
            "\\)", "Símbolo parentesis cierra",
            "\\;", "Símbolo punto y coma",
            "\\:", "Símbolo dos puntos",
            "\\//", "Comentario",
            "private", "Palabra reservada private",
            "public", "Palabra reservada public",
            "set", "Palabra reservada set",
            "get", "Palabra reservada get",
            "if", "Palabra reservada if",
            "for", "Palabra reservada for",
            "while", "Palabra reservada while",
            "do while", "Palabra reservada do while",
            "case", "Palabra reservada case",
            "new", "Palabra reservada new",
            "void", "Palabra reservada void",
            "System", "Palabra reservada System",
            "out", "Palabra reservada out",
            "print", "Palabra reservada print",
            "println", "Palabra reservada println",
            "next", "Palabra reservada next",
            "nextint", "Palabra reservada nextint",
            "Scanner", "Palabra reservada Scanner",
            "int", "Palabra reservada int",
            "float", "Palabra reservada float",
            "double", "Palabra reservada double",
            "boolean", "Palabra reservada boolean",
            "String", "Palabra reservada string",
            "decimal", "Palabra reservada decimal",
            "null", "Palabra reservada null",
            "this", "Palabra reservada this",
            "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b", "Identificador"
        };

        for (int i = 0; i < tokenPatterns.length; i += 2) {
            Pattern pattern = Pattern.compile(tokenPatterns[i]);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String lexeme = matcher.group();
                String patternStr = tokenPatterns[i + 1];
                if (type) {
                    // Revisa si el token ya existe dentro del programa
                    if (!tokenSet.contains(lexeme)) {
                        tokens.add(new Token(lexeme, patternStr));
                        tokenSet.add(lexeme);
                    }
                } else {
                    tokens.add(new Token(lexeme, patternStr));
                }
            }
        }
        return tokens;
    }
    //tokens para encontrar el nombre de la clase o diferenciarlo
    static String findClassName(List<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if ("Identificador".equals(tokens.get(i).getPatron()) && !"Identificador".equals(tokens.get(i - 1).getLexema())) {
                return tokens.get(i).getLexema();
            }
        }
        return "No se encontró el nombre de la clase.";
    }
    
    static List<Atributo> getAtributos(List<Token> tokens) {
        List<Atributo> atributos = new ArrayList<>();
        
        for (int i = 0; i < tokens.size()-2; i++) {
            String token = tokens.get(i).getLexema();
            String token2 = tokens.get(i+1).getLexema();
            String token3 = tokens.get(i+2).getLexema();
            String token4 = tokens.get(i+2).getPatron();

            if (("private".equals(token) || "public".equals(token))
                    && ("int".equals(token2) || "String".equals(token2) || "decimal".equals(token2) || "Scanner".equals(token2)
                    || "double".equals(token2) || "float".equals(token2))
                    && ("Identificador".equals(token4))) {
                String accesibilidad = token;
                String tipo = token2; // tipo de dato sigue al nombre del atributo
                String nombre = token3; // nombre del atributo para el modificador de acceso
                
                atributos.add(new Atributo(accesibilidad,tipo,nombre));
            }
        }
        return atributos;
    }
}
