package challenge;

import java.util.Locale;

public class CriptografiaCesariana implements Criptografia {
    public char ConvertLetter(char letter) {
        char newLetter = Character.toLowerCase(letter);
        int position = ((int) newLetter) + 3;
        return (char) position;
    }

    public char ReconvertLetter(char letter) {
        char newLetter = Character.toLowerCase(letter);
        int position = ((int) newLetter) - 3;
        return (char) position;
    }

    @Override
    public String criptografar(String texto) {
        if (texto.length() == 0) {
            throw new IllegalArgumentException("não pode ser vazio");
        }
        String newTexto = texto.toLowerCase(Locale.ROOT);
        char[] newArray = newTexto.toCharArray();
        String returnPhrase = "";
        for (char letter: newArray) {
            if (letter >= 'a' && letter <= 'z') {
                char newLetter = ConvertLetter(letter);
                returnPhrase += newLetter;
            } else {
                returnPhrase += letter;
            }
        };
        return returnPhrase;
    }

    @Override
    public String descriptografar(String texto) {
        if (texto.length() == 0) {
            throw new IllegalArgumentException("não pode ser vazio");
        }
        String newTexto = texto.toLowerCase(Locale.ROOT);
        char[] newArray = newTexto.toCharArray();
        String returnPhrase = "";
        for (char letter: newArray) {
            if (letter >= 'a' && letter <= 'z') {
                char newLetter = ReconvertLetter(letter);
                returnPhrase += newLetter;
            } else {
                returnPhrase += letter;
            }
        };
        return returnPhrase;
    }
}
