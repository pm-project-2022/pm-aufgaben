package reg_exp;
import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;

public final class Lexer {
    private final List<Token> tokenizer = new ArrayList<>();

    public Lexer() {
        //Keywords
        tokenizer.add(new Token(Pattern.compile("\\bpackage\\b"),0, Color.YELLOW));
        tokenizer.add(new Token(Pattern.compile("\\bimport\\b"),0, Color.BLUE));
        tokenizer.add(new Token(Pattern.compile("\\bclass\\b"),0, Color.DARK_GRAY));
        tokenizer.add(new Token(Pattern.compile("\\bpublic\\b"),0, Color.GRAY));
        tokenizer.add(new Token(Pattern.compile("\\bprivate\\b"),0, Color.GREEN));
        tokenizer.add(new Token(Pattern.compile("\\bfinal\\b"),0, Color.LIGHT_GRAY));
        tokenizer.add(new Token(Pattern.compile("\\breturn\\b"),0, Color.PINK));
        tokenizer.add(new Token(Pattern.compile("\\bnull\\b"),0, Color.CYAN));
        tokenizer.add(new Token(Pattern.compile("\\bnew\\b"),0, Color.MAGENTA));

        //Annotation
        tokenizer.add(new Token(Pattern.compile("@[A-Za-z-]+"),0, Color.ORANGE));

        //Einzeiliger Kommentar
        tokenizer.add(new Token(Pattern.compile("/{2}.*"),0, Color.RED));

        //Mehrzeilger Kommentar
        tokenizer.add(new Token(Pattern.compile("\\/\\*(.*?)\\*\\/"),1, Color.BLACK));

        //Javadoc
        tokenizer.add(new Token(Pattern.compile("/\\*{2}((\\n*.*?)*)\\*/"),1, new Color(255, 100, 255)));

        //Strings
        tokenizer.add(new Token(Pattern.compile("\"(.*?)\""),1, new Color(100, 100, 100)));

        //CharacterContent
        tokenizer.add(new Token(Pattern.compile("\'(.{1})\'"),1, new Color(10, 200, 50)));
    }

    public List<MyMatchResult> tokenize(String s) {
        List<MyMatchResult> results = new ArrayList<>();
        tokenizer.forEach(t -> results.addAll(t.test(s)));
        return results;
    }

    public static void main(String[] args) {
        LexerUI lexerUI = new LexerUI();
    }
}
