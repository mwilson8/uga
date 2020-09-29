import java.util.ArrayList;
import java.util.Random;
 
/**
 * The WordBoard class can generate and represent a board of characters for a
 * word search game.
 *
 * IMPORTANT: because this class contains a solution to the problem that the students
 * are trying to solve, it is VERY important that students receive a COMPILED JAR of
 * this file rather than the raw source code. Some students may be ingenious enough to
 * decompile the JAR and look inside; however, the solution in this class is quite
 * novel and would stick out as academic dishonesty immediately. Take a look at the
 * appearancesOf(String) method near the bottom of the class for more information.
 *
 * @author  Sahir Shahryar
 * @since   Friday, March 23, 2018
 * @version 1.1.0
 */
public class WordBoard {
 
    /**
     * An enumeration of color codes that can be used to highlight the answers
     * found inside the word search. Note that Eclipse does not support showing
     * colors inside the terminal, so in order for this to work, students will
     * need to
     *
     * Source: https://stackoverflow.com/questions/5762491/
     */
    public enum Color {
        RESET(0),
        BLACK(30),
        RED(31),
        GREEN(32),
        YELLOW(33),
        BLUE(34),
        PURPLE(35),
        CYAN(36),
        WHITE(37),
        NONE;
 
 
        /**
         * This variable is used to cycle through the colors.
         */
        private static int cycler = 0;
 
 
        /**
         * The actual ANSI color code that will be printed as part of output.
         */
        private final String code;
 
 
        /**
         * Initializes a new value in the enumeration.
         * @param code (int) the numeric portion of the color code.
         */
        Color(int code) {
            this.code = "\u001B[" + code + "m";
        }
 
 
        /**
         * Initializes the lack of a color code as an empty string.
         */
        Color() {
            this.code = "";
        }
 
 
        /**
         * Using toString() here makes these color codes very convenient when
         * programming them into strings below.
         *
         * @return (String) the full ANSI color code.
         */
        public String toString() {
            return this.code;
        }
 
 
        /**
         * Cycles through red, green, yellow, blue, purple, and cyan, returning
         * the appropriate color value.
         *
         * @return (Color) a color different from the previous one.
         */
        public static Color nextColor() {
            return Color.values()[(cycler++ % 6) + 2];
        }
    }
 
 
    /**
     * This object is used to store the location of a word in the string. With a
     * toString() method, it makes automatic input validation a cinch as long as
     * students use the highlightWord(...) and getHighlightedWords() methods.
     *
     * Students will actually never see this class; it is used entirely internally.
     */
    private static class WordLocation {
        int i1, j1, i2, j2;
        String word;
 
        /**
         * Initializes the location.
         *
         * @param i1 (int) the starting i-coordinate.
         * @param j1 (int) the starting j-coordinate.
         * @param i2 (int) the finishing i-coordinate.
         * @param j2 (int) the finishing j-coordinate.
         * @param word (String) the word that has been found there.
         */
        public WordLocation(int i1, int j1, int i2, int j2, String word) {
            this.i1 = i1;
            this.j1 = j1;
            this.i2 = i2;
            this.j2 = j2;
            this.word = word;
        }
 
 
        /**
         * Returns the details of this word location in a standardized format.
         * @return (String) this, as a string
         */
        public String toString() {
            return "Word '"+ this.word +
                    "' from (" + i1 + ", " + j1 + ") to (" + i2 + ", " + j2 + ")";
        }
 
 
        /**
         * Checks if the given location is "the same" as this one, just with its
         * start and end indices reversed.
         *
         * @param other (WordLocation) the other location.
         *
         * @return (boolean) true if swapping (i1, j1) with (i2, j2) would make
         *         the two word locations equivalent.
         */
        public boolean isReversed(WordLocation other) {
            return this.i1 == other.i2 && this.j1 == other.j2
                    && this.i2 == other.i1 && this.j2 == other.j1;
        }
 
 
        /**
         * Checks if two locations are equivalent.
         *
         * @param other (WordLocation) the other location.
         *
         * @return (boolean) true if the i1-, j1-, i2-, and j2-values of both
         *         WordLocations are equal to each other; false otherwise.
         */
        public boolean equals(Object other) {
            if (!(other instanceof WordLocation)) {
                return false;
            }
 
            WordLocation loc = (WordLocation) other;
 
            return this.i1 == loc.i1 && this.j1 == loc.j1
                    && this.i2 == loc.i2 && this.j2 == loc.j2;
        }
    }
 
 
    /**
     * This array contains a list of words that the program should look for.
     *
     * WARNING: PLEASE READ!
     * CHANGING THE CONTENTS OF THIS ARRAY WILL AFFECT THE BOARDS THAT THE
     * generateBoard() METHOD GENERATES. IF YOU CHANGE THIS ARRAY IN THE COMPILED JAR
     * FILE THAT STUDENTS RECEIVE, YOU SHOULD UPDATE THE BOARDS THAT ARE SHOWN INSIDE
     * THE LAB PDF. FAILURE TO DO SO WILL RESULT IN A LOT OF QUESTIONS FROM CONFUSED
     * STUDENTS BECAUSE THEY WILL NOT RECEIVE THE BOARDS THAT ARE SHOWN ON THE LAB PDF,
     * EVEN IF THEY TYPE IN THE CORRECT SEED. YOU HAVE BEEN WARNED.
     *
     * THE ORIGINAL CONTENTS OF THIS ARRAY WERE "bug", "double", "false", "float",
     * "int", "object", "ram", AND "true".
     */
    public static final String[] DICTIONARY =
            { "bug", "double", "false", "float",
                    "int", "object", "ram", "true" };
 
 
    /**
     * When this is true, constructing a WordBoard object with a seed between
     * 0 and TESTING_BOARDS.length will use the hard-coded board at
     * TESTING_BOARDS[seed]. Set this to true when grading if you want to use
     * hard-coded boards.
     */
    public static final boolean ENABLE_TESTING_BOARDS = false;
 
 
    /**
     * This array of 2D arrays contains a series of constant boards that the student
     * can use when testing.
     *
     * @author Austin Hardaway
     */
    public static final char[][][] TESTING_BOARDS = {
            // Athens-themed
            {
                    {'a','t','h','e','n','s'},
                    {'u','r',' ',' ','c','l'},
                    {' ','g','c',' ','l','c'},
                    {' ',' ','a','h','m',' '}
            },
 
            // Computer science-themed
            {
                    {' ',' ','m',' ','b',' '},
                    {'j','a','v','a','y',' '},
                    {' ','d','j',' ','t','e'},
                    {' ',' ','k',' ','e','d'},
                    {'t','c','e','j','b','o'},
                    {' ',' ','r',' ',' ','c'},
                    {' ',' ',' ','s',' ',' '}
            },
 
            // Athens landmarks
            {
                    {'w','u','x','t','r','y',' ',' ',' '},
                    {' ',' ',' ','r','e','v','l',' ','s'},
                    {'y','l','a','z','y',' ',' ','a','a'},
                    {'k','a','m','i','c','i','i',' ','n'},
                    {'s',' ',' ',' ','d','l',' ','o','d'},
                    {'e',' ',' ','a','o',' ','h','r','b'},
                    {'u',' ','o','n',' ','a',' ','t','a'},
                    {'l','r','g',' ','w',' ',' ','n','r'},
                    {'b','a','g','r','i','l','l','e',' '},
                    {'m','h','e','d','g','e','s','c',' '}
            },
 
            // Large CS board
            {
                    {' ',' ',' ',' ','b','e','r','n','e','r','s',' ','h'},
                    {' ',' ',' ',' ',' ','c',' ',' ',' ',' ',' ',' ','o'},
                    {'t','a','h','d','e','r',' ',' ',' ',' ',' ','s','p'},
                    {'i','p',' ',' ',' ','u','b','u','n','t','u','y','p'},
                    {'m','a',' ',' ',' ','o',' ',' ','i','e',' ','s','e'},
                    {' ','c',' ',' ','h','s','a','b','k','c',' ','g','r'},
                    {' ','h','k','e','r','n','e','l','e','a',' ','n',' '},
                    {' ','e',' ','x',' ','e',' ',' ',' ','r','v','i','m'},
                    {' ',' ','u','i',' ','p',' ',' ',' ','g',' ','t',' '},
                    {'x','t',' ','n','s','o','s',' ',' ','n',' ','a',' '},
                    {' ','u','u','u',' ',' ','c',' ','b','i','n','r',' '},
                    {' ',' ','n',' ','e',' ','a',' ',' ','r',' ','e',' '},
                    {' ','i','g','i','e',' ','m',' ',' ','u',' ','p',' '},
                    {'l',' ',' ',' ','l',' ','e',' ',' ','t',' ','o',' '}
            }
    };
 
 
    /**
     * This array of String arrays contains the dictionaries that accompany each of
     * the above testing boards.
     *
     * @author Austin Hardaway
     */
    public static final String[][] TESTING_DICTIONARIES = {
            // Athens-themed
            { "arch", "athens", "mlc", "slc", "uga" },
 
            // Computer science-themed
            { "byte", "code", "java", "jdk", "jvm", "object", "src" },
 
            // Athens landmarks
            { "amici", "bluesky", "broad", "centro", "grill", "hedges",
                    "lazy", "magnolias", "sandbar", "waho", "wuxtry" },
 
            // Large CS board
            { "apache", "berners", "bin", "dijkstra", "emacs", "gnu", "grace",
                    "hopper", "kernel", "lee", "linus", "linux", "nike",
                    "opensource", "operatingsys", "redhat", "tim", "turing",
                    "tux", "ubuntu", "unix", "vim" }
    };
 
 
    /**
     * When you let a computer randomly generate words, it's statistically inevitable
     * that every so often (however rarely), a bad word will show up inside the
     * generated array. It might be funny when the computer randomly generates a board
     * that contains "fuck", but there are some words that are so seriously wrong that
     * they should never, EVER appear on the board, even by accident.
     *
     * Roughly based on [https://en.wiktionary.org/wiki/Category:English_swear_words].
     *
     * WARNING: PLEASE READ!
     * UPDATING THE LIST OF SWEAR WORDS MAY AFFECT THE WAY BOARDS ARE GENERATED. IF
     * YOU CHANGE IT, PLEASE ENSURE THAT ALL OF THE BOARDS ON THE LAB PDF ARE THE
     * SAME WHEN GENERATED WITH THE UPDATED LIST OF SWEAR WORDS. FAILURE TO DO SO
     * MAY RESULT IN A LOT OF QUESTIONS FROM CONFUSED STUDENTS BECAUSE THEY WILL
     * NOT RECEIVE THE BOARDS THEY SEE ON THE LAB PDF, EVEN THOUGH THEY ARE TYPING
     * IN THE CORRECT SEED. YOU HAVE BEEN WARNED.
     */
    private static final String[] FORBIDDEN_WORDS =  { "arse", "ass", "bastard",
            "bitch", "christ", "crap", "cunt", "damn", "fag", "fuck", "hell",
            "holy", "jesus", "nigga", "nigger", "pussy", "shit", "twat", "whore" };
 
 
    /**
     * This double represents the probability that any random word from the dictionary
     * will be selected to be inserted into the board.
     *
     * WARNING: PLEASE READ!
     * CHANGING THIS VALUE WILL AFFECT THE BOARDS THAT THE generateBoard() METHOD
     * GENERATES. IF YOU CHANGE THIS VALUE IN THE COMPILED JAR FILE THAT STUDENTS
     * RECEIVE, YOU SHOULD UPDATE THE BOARDS THAT ARE SHOWN INSIDE THE LAB PDF.
     * FAILURE TO DO SO WILL RESULT IN A LOT OF QUESTIONS FROM CONFUSED STUDENTS
     * BECAUSE THEY WILL NOT RECEIVE THE BOARDS THAT ARE SHOWN ON THE LAB PDF, EVEN
     * IF THEY TYPE IN THE CORRECT SEED. YOU HAVE BEEN WARNED.
     *
     * THE ORIGINAL VALUE USED WHEN WRITING THE LAB PDF WAS 0.75.
     */
    private static final double WORD_INCLUSION_PROBABILITY = 0.75;
 
 
    /**
     * This value determines whether the output from toString() (no parameters) is
     * colorized or not.
     */
    private static final boolean COLORIZE_BY_DEFAULT = false;
 
 
    /**
     * This 2D character array represents the game board.
     */
    private char[][] board;
 
 
    /**
     * This 2D array is used to determine which spaces the student's program has
     * located. As colors are changed from Color.NONE to any other value, the program
     * is made aware that the student's program has found a word along those spaces.
     *
     * The student will NOT modify this array directly; it will be done for the student.
     */
    private Color[][] boardColoration;
 
 
    /**
     * This is the list of words that will be used to populate the board.
     */
    private final String[] dictionary;
 
 
    /**
     * Represents the height (i-coordinate) of the board.
     */
    private final int rows;
 
 
    /**
     * Represents the width (j-coordinate) of the board.
     */
    private final int columns;
 
 
    /**
     * Represents the seed used to generate the table. In order to combat randomness
     * and help students debug their code, this class uses a seed which is passed on
     * to the random number generator. The getSeed() method can be used to fetch the
     * seed, and a constructor where the student can specify the seed has been made
     * available.
     *
     * Of course, changing the dimensions of the board will change how a particular
     * seed operates, so the student needs to maintain the same board size in order
     * to consistently produce the same board with a given seed.
     */
    private final long seed;
 
 
    /**
     * This list represents all of the words that were successfully incorporated into
     * the board. It is more for grading than anything else; the student should be
     * checking the board for words that may not even exist. As such, the JAR that
     * students receive should remove the getter for this list.
     */
    private ArrayList<String> wordsIncluded = new ArrayList<>();
 
 
    /**
     * This list represents the correct locations for each word that was included in
     * the array. This is used to check the user's highlighted words to see if they're
     * all correct (and if some are missing).
     */
    private ArrayList<WordLocation> wordLocationsAnswerKey = new ArrayList<>();
 
 
    /**
     * This list represents all of the words that the student's program has marked
     * as found. The getter for this list should remain available to the students.
     */
    private ArrayList<WordLocation> wordLocations = new ArrayList<>();
 
 
    /**
     * Initializes a new WordBoard object with the given 2D character array
     * and the default dictionary.
     *
     * @param board (char[][]) the 2D character array.
     */
    public WordBoard(char[][] board) {
        this(board, WordBoard.DICTIONARY);
    }
 
 
    /**
     * Initializes a new WordBoard object with the given 2D character array
     * and a custom dictionary.
     *
     * @param board      (char[][]) the 2D character array.
     * @param dictionary (String[]) the words that should populate the board.
     */
    public WordBoard(char[][] board, String[] dictionary) {
        this(board, -1, dictionary);
 
        if (this.isBoardJagged()) {
            System.out.println("Warning: your board is jagged (some rows are not " +
                    this.columns + " elements long)! You are not expected to " +
                    "implement a word search for a jagged array.");
            return;
        }
 
        /**
         * Make sure the given board is at least semi-valid.
         */
        boolean hasWord = false;
        for (String word : dictionary) {
            word = word.toLowerCase();
            int appearances = this.appearancesOf(word, true);
 
            if (appearances > 0) {
                this.wordsIncluded.add(word);
                hasWord = true;
            }
 
            if (appearances > 1) {
                System.out.println("Warning: the word '" + word + "' appears " +
                        appearances + " times inside the given board! You " +
                        "should use a board where no word appears more than once.");
            }
        }
 
        if (!hasWord) {
            System.out.println("Warning: none of the words in the dictionary appear " +
                    "in the given board! You should use a board which contains at " +
                    "least one of the words in the dictionary.");
        }
    }
 
 
    /**
     * Initializes a new WordBoard object with the given dimensions and seed,
     * alongside the default dictionary. This should be used if the student
     * encounters an error with a specific board and needs to debug on that board.
     *
     * Providing the seed will make the program produce the same board consistently.
     *
     * @param rows    (int) the number of rows.
     * @param columns (int) the number of columns.
     * @param seed    (int) the seed to generate the board.
     */
    public WordBoard(int rows, int columns, long seed) {
        this(rows, columns, seed, WordBoard.DICTIONARY);
    }
 
 
    /**
     * Initializes a new WordBoard object with the given dimensions, seed, and
     * dictionary. This should be used if the student encounters an error with
     * a specific board and needs to debug on that board. Providing the seed
     * will make the program produce the same board consistently.
     *
     * @param rows       (int) the number of rows.
     * @param columns    (int) the number of columns.
     * @param seed       (int) the seed to generate the board.
     * @param dictionary (String[]) the words that should populate the board.
     */
    public WordBoard(int rows, int columns, long seed, String[] dictionary) {
        this(new char[rows][columns], seed, dictionary);
 
        if (seed >= 0 && seed < WordBoard.TESTING_BOARDS.length) {
            return;
        }
 
        Random random = new Random(this.seed);
 
        do {
            this.generateBoard(random);
        } while (this.boardGenerationIncorrect() || this.containsProfanity());
    }
 
 
    /**
     * Initializes a new WordBoard object with the given dimensions, a random
     * seed, and the default dictionary.
     *
     * @param rows    (int) the number of rows.
     * @param columns (int) the number of columns.
     */
    public WordBoard(int rows, int columns) {
        this(rows, columns, WordBoard.DICTIONARY);
    }
 
 
    /**
     * Initializes a new WordBoard object with the given dimensions, a random
     * seed, and a custom dictionary.
     *
     * @param rows       (int) the number of rows.
     * @param columns    (int) the number of columns.
     * @param dictionary (String[]) the words that should populate the board.
     */
    public WordBoard(int rows, int columns, String[] dictionary) {
        this(new char[rows][columns], (new Random()).nextLong(), dictionary);
 
        Random random = new Random(this.seed);
        do {
            this.generateBoard(random);
        } while (this.boardGenerationIncorrect() || this.containsProfanity());
    }
 
 
    /**
     * Private initializer which does most of the field-setting we need.
     *
     * @param board      (char[][]) the board.
     * @param seed       (int) the seed for the board.
     * @param dictionary (String[]) the words that should populate the board.
     */
    private WordBoard(char[][] board, long seed, String[] dictionary) {
        if (ENABLE_TESTING_BOARDS && seed >= 0
                && seed < WordBoard.TESTING_BOARDS.length) {
            this.board = WordBoard.TESTING_BOARDS[(int) seed];
            this.dictionary = WordBoard.TESTING_DICTIONARIES[(int) seed];
 
            for (String word : this.dictionary) {
                this.wordsIncluded.add(word);
            }
        } else {
            this.board = board;
            this.dictionary = dictionary;
        }
 
        if (board.length == 0 || board[0].length == 0) {
            System.out.println("Invalid board generation (0 rows or 0 columns)!");
            throw new RuntimeException();
        }
 
        this.rows = this.board.length;
        this.columns = this.board[0].length;
        this.seed = seed;
        this.boardColoration = new Color[this.rows][this.columns];
 
        this.fillSpaces();
 
        /**
         * Populate the array of letter colors with Color.NONE.
         */
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                this.boardColoration[i][j] = Color.NONE;
            }
        }
    }
 
 
    /**
     * Gives the student a copy of the 2D character array that represents the
     * game board.
     *
     * @return (char[][]) a copy of the game board.
     */
    public char[][] getBoard() {
        char[][] board = new char[this.rows][this.columns];
 
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                board[i][j] = this.board[i][j];
            }
        }
 
        return board;
    }
 
 
    /**
     * Gives the student a copy of the dictionary, which contains a list of words
     * that their program should look for. Note that not all of the words contained
     * in this dictionary may actually exist on the board.
     *
     * @return (String[]) a copy of the dictionary.
     */
    public String[] getDictionary() {
        String[] dictionary = new String[this.dictionary.length];
 
        for (int i = 0; i < dictionary.length; ++i) {
            dictionary[i] = this.dictionary[i];
        }
 
        return dictionary;
    }
 
 
    /**
     * Gets the number of rows in the board.
     *
     * @return (int) the number of rows in the board.
     */
    public int getHeight() {
        return this.rows;
    }
 
 
    /**
     * Gets the number of columns in the board.
     *
     * @return (int) the number of columns in the board.
     */
    public int getWidth() {
        return this.columns;
    }
 
 
    /**
     * A writeup for this lab assignment should make explicit reference to this
     * method and the possibility of using the seed to consistently generate the
     * same board. The student should print out the seed number when debugging their
     * code so that, if something unexpected happens, they can produce the same
     * board over and over.
     *
     * @return (long) the seed that has been used to generate the board.
     */
    public long getSeed() {
        return this.seed;
    }
 
 
    /**
     * Prints the contents of the game board with indices and user-found words
     * capitalized.
     *
     * @return (String) the game board.
     */
    public String toString() {
        return this.toString(WordBoard.COLORIZE_BY_DEFAULT, true, true);
    }
 
 
    /**
     * For anyone with access to a colorized terminal, this is available to spice
     * things up. Using this colorizes the user-found words.
     *
     * @return (String) the game board, now in Technicolor.
     */
    public String toColorizedString() {
        return this.toString(true, true, true);
    }
 
 
    /**
     * This is a generic method for drawing the game board to the terminal.
     *
     * @param colorize    (boolean) whether or not the board should have colors.
     * @param capitalize  (boolean) whether or not the found words should be capitalized.
     * @param showIndices (boolean) whether or not to show indices on the top and left.
     *
     * @return (String) the game board drawn as configured.
     */
    public String toString(boolean colorize, boolean capitalize, boolean showIndices) {
        StringBuilder output = new StringBuilder();
 
        /**
         * If the board is more than 10 rows wide, the indices will occupy 2 characters
         * (or more for even larger boards). Thus we need to figure out the correct
         * amount of space between each letter, as well as how much padding is needed on
         * the left, so that all of the board's letters line up neatly.
         */
        int horizontalSpacing = 1, leftPadding = 0;
 
        /**
         * If we're showing indices, generate a column indicator at the top first.
         */
        if (showIndices) {
            horizontalSpacing = Math.max((int) Math.log10(this.columns - 1) + 1, 1);
            leftPadding = Math.max((int) Math.log10(this.rows - 1) + 1, 1);
 
            StringBuilder firstRow = new StringBuilder(generateSpaces(leftPadding));
 
            for (int j = 0; j < this.columns; ++j) {
                firstRow.append(padRightAlign(j + "", horizontalSpacing + 1));
            }
 
            if (colorize) output.append(Color.RED);
            output.append(firstRow);
            if (colorize) output.append(Color.RESET);
 
            output.append('\n');
        }
 
        /**
         * The string "spacer" will come after each letter.
         *
         * Now we actually iterate through the board.
         */
        String spacer = generateSpaces(horizontalSpacing);
        for (int i = 0; i < this.rows; ++i) {
 
            /**
             * If we're showing indices, add the row number to the left.
             */
            if (showIndices) {
                if (colorize) output.append(Color.RED);
                output.append(padRightAlign(i + "", leftPadding)).append(spacer);
                if (colorize) output.append(Color.RESET);
            }
 
            /**
             * Now enter each row to print it out.
             */
            for (int j = 0; j < this.columns; ++j) {
                char letter = this.board[i][j];
                Color color = this.boardColoration[i][j];
 
                if (colorize) output.append(color);
 
                if (color != Color.NONE && capitalize) {
                    output.append(Character.toUpperCase(letter));
                } else {
                    output.append(letter);
                }
 
                if (colorize) output.append(Color.RESET);
 
                if (j + 1 < this.columns) {
                    output.append(spacer);
                }
            }
 
            if (i + 1 < this.rows) {
                output.append('\n');
            }
        }
 
        return output.toString();
    }
 
 
    /**
     * This method returns the game board array formatted as a syntactically-valid
     * Java array. This is an alternative to using the seed to generate the same
     * board; instead, a student can have the line
     *
     * System.out.println(board.toCopyableArray());
     *
     * and then copy the output straight into their code as an array. This can save
     * a lot of time when trying to bug-fix, as the student does not have to manually
     * transcribe a large array into Java by hand.
     *
     * @return (String) the contents of the game board formatted to be syntactically
     *         valid in Java.
     */
    public String toCopyableArray() {
        return this.toCopyableArray(false);
    }
 
    /**
     * This method returns the game board array formatted as a syntactically-valid
     * Java array. This is an alternative to using the seed to generate the same
     * board; instead, a student can have the line
     *
     * System.out.println(board.toCopyableArray());
     *
     * and then copy the output straight into their code as an array. This can save
     * a lot of time when trying to bug-fix, as the student does not have to manually
     * transcribe a large array into Java by hand.
     *
     * @return (String) the contents of the game board formatted to be syntactically
     *         valid in Java.
     */
    public String toCopyableArray(boolean showSpaces) {
        String output = "char[][] board = { \n";
 
        for (int i = 0; i < this.rows; ++i) {
            output += "    {";
            for (int j = 0; j < this.columns; ++j) {
                char add = this.board[i][j];
 
                if (showSpaces && this.boardColoration[i][j] == Color.NONE) {
                    add = ' ';
                }
 
                output += (j == 0) ? " " : ", ";
                output += "'" + add + "'";
            }
            output += " }" + ((i + 1 == this.rows) ? "" : ",") + "\n";
        }
 
        output += "};";
        return output;
    }
 
 
    /**
     * Gets the character at a specific position on the board. In the student version of
     * this, this method should probably be removed in favor of having the students
     * copy and access the array directly.
     *
     * @param i (int) the desired row.
     * @param j (int) the desired column.
     * @return (char) the character at position (i, j).
     */
    public char charAt(int i, int j) {
        if (i + 1 > this.rows) {
            System.out.println("Tried to access row #" + i +
                    ", which is out-of-bounds!");
            return ' ';
        }
 
        if (j + 1 > this.board[i].length) {
            System.out.println("Tried to access column #" + j
                    + " in row #" + i + ", which is out-of-bounds!");
            if (this.isBoardJagged()) {
                System.out.println("The 2D array you used for this WordBoard " +
                        "was jagged. You do not have to make your word " +
                        "search work on jagged arrays.");
            }
 
            return ' ';
        }
 
        return this.board[i][j];
    }
 
 
    /**
     * Gets an array containing the words that the program was able to fit onto the
     * board. This is computed when the board is generated, and so cannot give a
     * real answer for when the WordBoard object was created with a hard-coded array.
     *
     * @return (String[]) an array containing the words that exist on the board to be
     *         found.
     */
    private String[] getHiddenWords() {
        if (this.seed == -1 && this.wordsIncluded.isEmpty()) {
            return new String[] { "Board was not generated algorithmically" };
        }
 
        String[] result = new String[this.wordsIncluded.size()];
        return this.wordsIncluded.toArray(result);
    }
 
 
    /**
     * Gets an array containing the highlighted positions of the words that the program
     * has found. This is where the WordLocation class's data gets turned into a String;
     * as such, the student does not even have to be aware of WordLocation's existence.
     *
     * Note: the output of this method is not a list of correct answers, but rather what
     * the student has put into the program themselves. This should therefore be an aid
     * in debugging their code.
     *
     * @return (String[]) a list of the words that the student's program has found.
     */
    public String[] getDiscoveredWords() {
        String[] result = new String[this.wordLocations.size()];
 
        for (int i = 0; i < result.length; ++i) {
            result[i] = this.wordLocations.get(i).toString();
        }
 
        return result;
    }
 
 
    /**
     * Highlights the given range of characters on the board with the next available
     * color (see the Color enum's nextColor() method above). If the student has
     * discovered a word correctly, then calling this method will highlight the
     * answer straight away.
     *
     * @param i1 (int) the starting i-coordinate (row).
     * @param j1 (int) the starting j-coordinate (column).
     * @param i2 (int) the finishing i-coordinate (row).
     * @param j2 (int) the finishing j-coordinate (column).
     */
    public void highlightWord(int i1, int j1, int i2, int j2) {
        this.highlightWord(i1, j1, i2, j2, Color.nextColor());
    }
 
 
    /**
     * Highlights the given range of characters on the board with the specified
     * color.
     *
     * @param i1 (int) the starting i-coordinate (row).
     * @param j1 (int) the starting j-coordinate (column).
     * @param i2 (int) the finishing i-coordinate (row).
     * @param j2 (int) the finishing j-coordinate (column).
     * @param color (Color) the color to assign to the characters in that range.
     */
    private void highlightWord(int i1, int j1, int i2, int j2, Color color) {
        /**
         * Technically, the two if-statements below could be one long one, but I think
         * it looks neater this way.
         */
        if (i1 < 0 || j1 < 0 || i2 < 0 || j2 < 0) {
            return;
        }
 
        if (i1 > this.rows || i2 > this.rows || j1 > this.columns || j2 > this.columns) {
            return;
        }
 
        /**
         * signumI and signumJ represent the direction of vertical (i) and horizontal
         * (j) movement, respectively. Each can have a value of either -1, 0, or 1,
         * where -1 represents upward or leftward motion, 0 represents no motion along
         * that axis, and 1 represents downward or rightward motion.
         *
         * len represents the length of the word being found, and is used as the
         * upper bound for our for-loop.
         *
         * The code to iterate across the given region works on the same principle as
         * the algorithm to perform the word search -- except here, we're determining
         * each signum manually instead of iterating over all combinations.
         */
        int signumI, signumJ, len = 0;
 
        /**
         * If i1 == i2, then there was no change in i, meaning that the word was either
         * straight forwards or backwards. Otherwise, determine whether the change in
         * i was positive or negative by seeing whether the starting or ending point
         * is larger.
         */
        if (i1 == i2) {
            signumI = 0;
            len = Math.abs(j2 - j1) + 1;
        } else {
            signumI = (i1 < i2) ? 1 : -1;
        }
 
        /**
         * The principle outlined in the comment above also applies for this block.
         */
        if (j1 == j2) {
            signumJ = 0;
            len = Math.abs(i2 - i1) + 1;
        } else {
            signumJ = (j1 < j2) ? 1 : -1;
        }
 
        /**
         * This code is reached if both i1 == i2 and j1 == j2 are false (i.e., when
         * we have a word arranged diagonally). Both signumI and signumJ have been
         * determined, but len is still undetermined. Thankfully there's nothing too
         * fancy to be done here -- just determine the difference between the values
         * on either axis. If the other one doesn't match, then the indices provided
         * are invalid and the method should exit.
         */
        if (len == 0) {
            len = Math.abs(i2 - i1) + 1;
 
            if (Math.abs(j2 - j1) + 1 != len) {
                return;
            }
        }
 
        /**
         * Construct the word in order to save it into a new WordLocation object.
         * As we pass each character on the way, set the respective position in
         * the color grid to a new color.
         */
        StringBuilder word = new StringBuilder();
        for (int dist = 0; dist < len; ++dist) {
            int currI = i1 + (signumI * dist),
                    currJ = j1 + (signumJ * dist);
 
            word.append(this.board[currI][currJ]);
            if (this.boardColoration[currI][currJ] == Color.NONE) {
                this.boardColoration[currI][currJ] = color;
            }
        }
 
        WordLocation location = new WordLocation(i1, j1, i2, j2, word.toString());
 
 
        /**
         * If the student's program finds the exact same word twice, notify them.
         */
        if (!this.wordLocations.contains(location)) {
            this.wordLocations.add(location);
        } else {
            System.out.println("Warning: this is not the first time you have located " +
                    "this exact word:");
            System.out.println(location);
        }
    }
 
 
    /**
     * MAKE PRIVATE (maybe)
     *
     * This method allows the user to know how many words actually exist on the board.
     * This method isn't really necessary for students, as they won't know *which* words
     * to look for based purely on this value, but it is useful in generating rubric
     * boards, as the boards on the rubric should have at least 5 words apiece.
     *
     * @return (int) the number of words that were put on this board when it was
     *               generated.
     */
    public int wordsOnBoard() {
        return this.wordsIncluded.size();
    }
 
 
    /**
     * This method is provided as a simple automatic verifier for the students'
     * algorithm. As long as the student is using the highlightWords() method to mark
     * the locations of words, this method can check their work and show where issues
     * are popping up. It can identify reversed words (i.e., the student put in the
     * parameters for highlightWords() backwards) and lets the student know if a word
     * they found was or was not in the dictionary.
     */
    public void checkAnswers() {
        this.score();
    }
 
 
    /**
     * MAKE PRIVATE
     *
     * This internal method should be made private when given to students. It is used
     * to grade rubric boards out of 25 points:
     *
     * 25 baseline
     *   -5 for each missing word
     *   -1 for each reversed word (indices were backwards)
     *   -2 for each unknown word (not in the dictionary)
     *
     * The method prints out its findings to standard output.
     *
     * @return (int) the score the user earned out of 25.
     */
    public int score() {
        /**
         * If, for some reason, the answer key doesn't contain any entries, we can't
         * really do any automatic grading for the student. Let them know about this.
         *
         * Note: the answer key will not exist only if a hard-coded board is passed to
         * the program with an empty (or incorrect) dictionary.
         */
        if (this.wordLocationsAnswerKey.isEmpty()) {
            /**
             * If there was nothing and the student found nothing, then that's
             * technically correct!
             */
            if (this.wordLocations.isEmpty()) {
                System.out.println("No words on board: result correct!");
            } else {
                System.out.println("No answer key exists to grade the board, please " +
                        "do so manually!");
            }
 
            System.out.println("Seed: " + this.getSeed() + "L");
            return 25;
        }
 
        /**
         * Create categories for correct answers, correct-just-backward answers,
         * and incorrect answers.
         */
        ArrayList<WordLocation> correctAnswers = new ArrayList<>(),
                reversedAnswers = new ArrayList<>(),
                incorrectAnswers = new ArrayList<>();
 
        /**
         * Create a copy of the answer key. Once all of the student's answers have been
         * iterated over, the remaining items in this list are those that the student
         * should have found, but haven't.
         */
        ArrayList<WordLocation> answerKey = new ArrayList<>(this.wordLocationsAnswerKey);
 
        for (WordLocation userAnswer : this.wordLocations) {
            if (answerKey.contains(userAnswer)) {
                correctAnswers.add(userAnswer);
                answerKey.remove(userAnswer);
            } else {
                /**
                 * Check if it's a reversed answer.
                 */
                WordLocation remove = null;
                for (WordLocation answer : answerKey) {
                    if (userAnswer.isReversed(answer)) {
                        remove = answer;
                        break;
                    }
                }
 
                if (remove != null) {
                    reversedAnswers.add(userAnswer);
                    answerKey.remove(remove);
                } else {
                    incorrectAnswers.add(userAnswer);
                }
            }
        }
 
        /**
         * If all of the student's answers were correct, then make the output simple.
         */
        if (correctAnswers.size() == this.wordLocationsAnswerKey.size()
                && incorrectAnswers.isEmpty() && reversedAnswers.isEmpty()) {
            System.out.println("All answers correct!");
            System.out.println("Seed: " + this.getSeed() + "L");
            return 25;
        }
 
        /**
         * Otherwise, go into some detail about where they went wrong.
         *
         * WordLocation#toString() does some convenient formatting for us here;
         * converting its output into lowercase makes it look grammatically nice, too.
         */
        else {
            int score = 25;
            for (WordLocation correct : correctAnswers) {
                System.out.println("Correctly found "
                        + correct.toString().toLowerCase());
            }
 
            for (WordLocation missing : answerKey) {
                System.out.println("Missing " + missing.toString().toLowerCase());
                score -= 5;
            }
 
            for (WordLocation reversed : reversedAnswers) {
                System.out.println("Reversed " + reversed.toString().toLowerCase());
                score -= 1;
            }
 
            for (WordLocation incorrect : incorrectAnswers) {
                System.out.println("Unknown " + incorrect.toString().toLowerCase());
                score -= 2;
            }
 
            System.out.println("Seed: " + this.getSeed() + "L");
            return Math.max(score, 0);
        }
    }
 
 
    // THE REMAINING METHODS ARE PRIVATE.
 
 
    /**
     * @return (boolean) true if the board is a jagged array; false otherwise.
     */
    private boolean isBoardJagged() {
        for (int i = 1; i < this.board.length; ++i) {
            if (this.board[i].length != this.board[0].length) {
                return false;
            }
        }
 
        return true;
    }
 
 
    /**
     * Generates a new board using a random number generator.
     *
     * WARNING: PLEASE READ!
     * CHANGING THE WAY THAT THE BOARD GENERATION ALGORITHM WORKS WILL CHANGE
     * THE BOARDS THAT THE ALGORITHM GENERATES. NOW THAT THE OBVIOUS IS OUT OF THE
     * WAY, PLEASE BE AWARE THAT THE BOARDS ON THE LAB PDF WERE ALL GENERATED USING
     * THIS METHOD. IF YOU CHANGE THE ALGORITHM BELOW, YOU MUST UPDATE THE LAB PDF
     * TO USE BOARDS GENERATED BY THE UPDATED ALGORITHM. FAILURE TO DO SO WILL RESULT
     * IN A LOT OF QUESTIONS FROM CONFUSED STUDENTS BECAUSE THEY WILL NOT RECEIVE THE
     * BOARDS THEY SEE ON THE LAB PDF, EVEN THOUGH THEY ARE TYPING IN THE CORRECT
     * SEED. YOU HAVE BEEN WARNED.
     *
     * @param random (Random) the random number generator. This must be passed in
     *               because initializing it with the seed INSIDE the method will
     *               cause it to generate the same board over and over, even if
     *               the board that was generated is invalid.
     */
    private void generateBoard(Random random) {
        if (this.seed >= 0 && this.seed < WordBoard.TESTING_BOARDS.length) {
            return;
        }
 
 
        /**
         * First, clear the list of included words and the answer key,
         * just in case this isn't the first time that generateBoard() is being called.
         */
        this.wordsIncluded.clear();
        this.wordLocationsAnswerKey.clear();
 
 
        /**
         * Next, populate the board with random lowercase letters (ASCII 97-122). If the
         * seed is -1, generate a board entirely consisting of lowercase a's.
         */
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                char nextChar = (this.seed == -1) ? 'a'
                        : (char) (97 + random.nextInt(26));
 
                this.board[i][j] = nextChar;
            }
        }
 
 
        /**
         * This 2D array of booleans is a way to keep track of where words have been
         * inserted into the board. If spacesOccupied[i][j] is true for some i, j within
         * bounds, then the character is part of a word in the dictionary.
         *
         * The loop directly below is simply populating the array with 'false', since
         * no words have been added to the board yet.
         */
        boolean[][] spacesOccupied = new boolean[this.rows][this.columns];
        for (int i = 0; i < spacesOccupied.length; ++i) {
            for (int j = 0; j < spacesOccupied[i].length; ++j) {
                spacesOccupied[i][j] = false;
            }
        }
 
 
        /**
         * Now we iterate over each word on the dictionary, trying to add it.
         *
         * The boolean value prevWordWanted is used to account for cases where
         * the random number generator "picks" a given word but it cannot be included
         * because the word is too big to fit (or could not be inserted in a
         * non-intrusive way within 10 attempts). In that case, we just insert
         * the next word. This should hopefully result in a relatively even
         * density of words based on WORD_INCLUSION_PROBABILITY.
         */
        boolean prevWordWanted = false;
        for (String possibleWord : this.dictionary) {
            if (!prevWordWanted) {
                if (random.nextFloat() > WordBoard.WORD_INCLUSION_PROBABILITY) {
                    continue;
                }
            }
 
            possibleWord = possibleWord.toLowerCase();
            int len = possibleWord.length();
 
            /**
             * If the current word can't fit, try to fit the next word.
             */
            if (len > this.rows && len > this.columns) {
                prevWordWanted = true;
                continue;
            }
 
            /**
             * Make sure to reset prevWordWanted to false.
             */
            prevWordWanted = false;
 
            /**
             * Like the search algorithm, we use two simple variables, signumI
             * and signumJ, to determine the direction that the word will go.
             * Consider the following (crudely-drawn) diagram for an explanation.
             *
             * The ordered pairs contain (signumI, signumJ).
             *
             *      (-1,-1)  (-1,0)  (-1,1)
             *           ^     ^     ^
             *             \   |   /
             *              \  |  /
             *               \ | /
             * (0,-1) < ------ + ------ > (0,1)
             *               / | \
             *              /  |  \
             *             /   |   \
             *           V     V     V
             *       (1,-1)  (1,0)   (1,1)
             */
            int signumI, signumJ;
 
            /**
             * Determine the direction of the word at random, but do it in such a way
             * that the word can FIT inside the array.
             */
            do {
                signumI = random.nextInt(3) - 1;
                signumJ = random.nextInt(3) - 1;
            } while ((signumI == 0 && signumJ == 0)
                    || (Math.abs(signumI) * len > this.rows
                    && Math.abs(signumJ) * len > this.columns));
 
 
            /**
             * Now attempt to locate a place where the word can fit. A maximum of ten
             * attempts will be made before giving up and moving on to the next word.
             * (An attempt is counted whenever insertion of the word is obstructed by
             * another word.)
             */
            int startI = -1, startJ = -1, terminalI = -1, terminalJ = -1;
            int attempts = 0;
            while (attempts < 10) {
                startI = random.nextInt(this.rows);
                startJ = random.nextInt(this.columns);
 
                /**
                 * Determine the ending position of the word along this
                 * direction. We use len - 1 because the starting position
                 * already represents the first character in the array, so only
                 * len - 1 characters in a given direction are required to fit
                 * the rest of the word.
                 *
                 * Once that position has been determined, test to ensure that
                 * the ending position is within bounds.
                 */
                terminalI = startI + (signumI * (len - 1));
                terminalJ = startJ + (signumJ * (len - 1));
 
                if (terminalI >= this.rows || terminalI < 0
                        || terminalJ >= this.columns || terminalJ < 0) {
                    continue;
                }
 
                /**
                 * Now test to see if the word can be placed in the suggested position
                 * without overwriting part of another word.
                 */
                boolean wordObstructed = false;
                for (int dist = 0; dist < len; ++dist) {
                    int currI = startI + (signumI * dist),
                            currJ = startJ + (signumJ * dist);
 
                    /**
                     * A character from another word has been encountered. If it's the
                     * same character as the one from the word we're currently trying
                     * to insert, then it's no problem; otherwise, consider it
                     * obstructed.
                     */
                    if (spacesOccupied[currI][currJ]) {
                        if (possibleWord.charAt(dist) != this.board[currI][currJ]) {
                            wordObstructed = true;
                            break;
                        }
                    }
                }
 
                if (!wordObstructed) {
                    break;
                }
 
                ++attempts;
            }
 
            /**
             * This word could not be inserted within a reasonable number of attempts,
             * so try again with the next word.
             */
            if (attempts == 10) {
                prevWordWanted = true;
                continue;
            }
 
            /**
             * Now that we know it can actually be added to the board, perform the
             * insertion and mark the used spaces as occupied.
             */
            for (int dist = 0; dist < len; ++dist) {
                int currI = startI + (signumI * dist),
                        currJ = startJ + (signumJ * dist);
 
                this.board[currI][currJ] = possibleWord.charAt(dist);
                spacesOccupied[currI][currJ] = true;
            }
 
            /**
             * Add the word to the list of words that are on the board.
             */
            this.wordsIncluded.add(possibleWord);
            this.wordLocationsAnswerKey.add(
                    new WordLocation(startI, startJ, terminalI, terminalJ, possibleWord)
            );
        }
    }
 
 
    /**
     * Fills a passed array's empty spaces with random characters.
     */
    private void fillSpaces() {
        /**
         * If it's a randomly-generated board, no need to fill empty spaces.
         */
        if (this.seed < -1 || this.seed > WordBoard.TESTING_BOARDS.length) {
            return;
        }
 
        Random random = new Random();
 
        int[] signaI = {  0,  1,  1,  0, -1, -1,  1, -1 },
                signaJ = {  1,  0,  1, -1,  0, -1, -1,  1 };
 
        /**
         * Now iterate over the board. We'll use a slighly different approach than
         * normal here - here, we keep picking letters until we know that it doesn't
         * create a duplicate of a word that's already on the board, rather than
         * doing a do-over of the entire board.
         */
        for (int i = 0; i < this.board.length; ++i) {
            for (int j = 0; j < this.board[i].length; ++j) {
                if (this.board[i][j] == ' ') {
                    /**
                     * Generate the words that branch out from this character
                     * in each direction. Here we exclude the character at i,j
                     * so that we can continually check for valid letter selection
                     * without having to regenerate this entire array over and over.
                     */
                    String[] makeableWords = new String[8];
 
                    /**
                     * Iterate over the 8 directions using the signaI and signaJ array,
                     * just like the original word search algorithm.
                     */
                    for (int k = 0; k < 8; ++k) {
                        int dirI = signaI[k], dirJ = signaJ[k];
                        StringBuilder wordInDirection = new StringBuilder();
 
                        /**
                         * Iterate for a maximum distance of the width of the entire
                         * board. There are checks in place to stop the string from
                         * overextending beyond the bounds of the board.
                         */
                        for (int dist = 1;
                             dist < Math.max(this.rows, this.columns); ++dist) {
 
                            int currI = i + (dist * dirI),
                                    currJ = j + (dist * dirJ);
 
                            /**
                             * Out-of-bounds? Stop!
                             */
                            if (currI < 0 || currI + 1 > this.rows) {
                                break;
                            }
 
                            if (currJ < 0 || currJ + 1 > this.columns) {
                                break;
                            }
 
                            /**
                             * Otherwise, append away!
                             */
                            wordInDirection.append(this.board[i][j]);
                        }
 
                        makeableWords[k] = wordInDirection.toString();
                    }
 
                    /**
                     * Statistically, there is just no way that this loop iterates
                     * 100 times without having covered every letter in the alphabet.
                     */
                    for (int k = 0; k < 100; ++k) {
                        char candidate = (char) (97 + random.nextInt(26));
                        boolean valid = true;
 
                        for (String wordInDirection : makeableWords) {
                            /**
                             * Check if the generated word is in the dictionary...
                             */
                            for (String word : this.dictionary) {
                                if ((candidate + wordInDirection).startsWith(word)) {
                                    valid = false;
                                    break;
                                }
                            }
 
                            if (!valid) {
                                break;
                            }
 
                            /**
                             * ...or, of course, in the list of swear words.
                             */
                            for (String swear : FORBIDDEN_WORDS) {
                                if ((candidate + wordInDirection).startsWith(swear)) {
                                    valid = false;
                                    break;
                                }
                            }
 
                            if (!valid) {
                                break;
                            }
                        }
 
                        /**
                         * Found a letter that can fit!
                         */
                        if (valid) {
                            this.board[i][j] = candidate;
                            break;
                        }
                    }
 
                    if (this.board[i][j] == ' ') {
                        System.out.println("Warning: no letter in the alphabet is " +
                                "able to fill this position without causing a word to " +
                                "appear twice in the dictionary. Please remove some " +
                                "words from the dictionary.");
                    }
                }
            }
        }
 
        /**
         * Add the words in the board to the dictionary for automatic error-checking.
         */
        for (String word : this.dictionary) {
            this.appearancesOf(word, true);
        }
    }
 
 
    /**
     * Counts the number of times a given word appears on the board, without adding
     * appearances of that word to the board's answer key.
     *
     * @param word (String) the word to look for.
     *
     * @return (int) the number of times it appears.
     */
    private int appearancesOf(String word) {
        return this.appearancesOf(word, false);
    }
 
 
    /**
     * This method counts the number of times a given word appears on the board.
     * This is the implementation for searching which I originally implemented before
     * starting work on this object as a whole.
     *
     * @param word (String) the word to look for.
     * @param addToAnswerKey (boolean) whether or not the found word should be added to
     *                       the board's answer key.
     *
     * @return (int) the number of times the given word appears.
     */
    private int appearancesOf(String word, boolean addToAnswerKey) {
        int count = 0;
        word = word.toLowerCase();
 
        char search = word.charAt(0);
        int len = word.length();
 
        /**
         * For this method, I simply implemented how a human does word search - hunt
         * and peck for the first letter of each word, then check all surrounding
         * directions to see if we can find the word.
         */
        for (int i = 0; i < this.rows; ++i) {
            for (int j = 0; j < this.columns; ++j) {
                /**
                 * There's no way that the word we're looking for can start at this
                 * position if the first character isn't even the same.
                 */
                if (this.board[i][j] != search) {
                    continue;
                }
 
                /**
                 * This is the solution I came up with for iterating across eight
                 * possible directions as neatly as possible. The loop below iterates
                 * across both signaI and signaJ in parallel. The value from signaI
                 * represents how much i should change on each iteration, and the
                 * value from signaJ does the same for j.
                 *
                 * Both i and j can have three values; -1 (upward or leftward movement),
                 * 0 (no movement along that variable), or 1 (downward or rightward
                 * movement). Thus the code for checking in all eight directions is
                 * rather straightforward.
                 *
                 * I would consider this a fairly novel solution. It's hard enough to
                 * understand without comments that someone who decompiles this class to
                 * look at the code would not be able to understand what is going on.
                 * Hence, if someone copies it, it will most likely be a direct copy,
                 * which is really easy to catch.
                 */
                int[] signaI = {  0,  1,  1,  0, -1, -1,  1, -1 },
                        signaJ = {  1,  0,  1, -1,  0, -1, -1,  1 };
 
                /**
                 * Iterate along the 8 possible directions we can search.
                 */
                for (int k = 0; k < 8; ++k) {
                    int signumI = signaI[k], signumJ = signaJ[k];
                    StringBuilder wordInDirection = new StringBuilder();
 
                    /**
                     * Determine the ending position of the word if we go along this
                     * direction. Again, using len - 1 because the character we're
                     * at is already the first character of the word, so we only need
                     * to find len - 1 characters along the direction of travel.
                     */
                    int terminalI = i + (signumI * (len - 1)),
                            terminalJ = j + (signumJ * (len - 1));
 
                    /**
                     * If the word ends out-of-bounds, it can't lie in that direction.
                     * Try another direction.
                     */
                    if (terminalI >= this.rows || terminalI < 0
                            || terminalJ >= this.columns || terminalJ < 0) {
                        continue;
                    }
 
                    /**
                     * Now assemble the word along this direction. We're about four
                     * loops deep right now (five if this were the solver program).
                     */
                    for (int dist = 0; dist < word.length(); ++dist) {
                        int currI = i + (signumI * dist),
                                currJ = j + (signumJ * dist);
                        wordInDirection.append(this.board[currI][currJ]);
                    }
 
                    if (wordInDirection.toString().toLowerCase().startsWith(word)) {
                        ++count;
                        if (addToAnswerKey) {
                            this.wordLocationsAnswerKey.add(
                                    new WordLocation(i, j, terminalI, terminalJ, word)
                            );
                        }
                    }
                } // (for int k)
            } // (for int j)
        } // (for int i)
 
        return count;
    }
 
 
    /**
     * WARNING: PLEASE READ!
     * CHANGING THE WAY THAT THIS VALIDATOR WORKS MAY CHANGE THE BOARDS THAT THE
     * ALGORITHM GENERATES. IF YOU CHANGE THE WAY THIS METHOD WORKS, PLEASE MAKE
     * SURE THAT ALL OF THE BOARDS ON THE LAB PDF ARE THE SAME AFTER MAKING THE
     * CHANGE. FAILURE TO DO SO WILL RESULT IN A LOT OF QUESTIONS FROM CONFUSED
     * STUDENTS BECAUSE THEY WILL NOT RECEIVE THE BOARDS THEY SEE ON THE LAB PDF,
     * EVEN THOUGH THEY ARE TYPING IN THE CORRECT SEED. YOU HAVE BEEN WARNED.
     *
     * @return (boolean) true if the board doesn't actually contain every word it says
     *         it does, or if it contains multiple copies of the same word by accident;
     *         false otherwise.
     */
    private boolean boardGenerationIncorrect() {
        for (String word : this.dictionary) {
            int appearances = this.appearancesOf(word);
 
            /**
             * BUG FIX: Check if a word that was in the dictionary was
             * unintentionally generated and we don't know about it.
             */
            if (!this.wordsIncluded.contains(word)) {
                if (appearances == 0) {
                    continue;
                } else if (appearances == 1) {
                    this.appearancesOf(word, true);
                    this.wordsIncluded.add(word);
                }
            }
 
            if (this.appearancesOf(word) != 1) {
                return true;
            }
        }
 
        return false;
    }
 
 
    /**
     * Checks if the board contains any words from the list WordBoard.FORBIDDEN_WORDS.
     * There are some words that should just not appear on the board under any
     * circumstances.
     *
     * @return (boolean) true if any word in the array of censored words is found on
     *         the board. Remember that only one of these words has to be found
     *         anywhere in the board; so, if the word "damned" is somehow present in
     *         the board, then the rule against "damn" will catch it.
     */
    private boolean containsProfanity() {
        for (String swear : WordBoard.FORBIDDEN_WORDS) {
            if (this.appearancesOf(swear) > 0) {
                return true;
            }
        }
 
        return false;
    }
 
 
    /**
     * This helper method helps align the indices on the left side of each
     * row if the board is more than 10 rows tall.
     *
     * @param input (String) the text that should be right-aligned.
     * @param width (int) the total width of the resulting string.
     *
     * @return (String) spaces to the left of the original input, such that
     *         the resulting output has a length equivalent to 'spaces'.
     */
    private static String padRightAlign(String input, int width) {
        return generateSpaces(width - input.length()) + input;
    }
 
 
    /**
     * This helper method generates a string consisting entirely of spaces.
     *
     * @param spaces (int) the number of spaces to generate.
     * @return (String) a string containing only spaces with a length of 'spaces'.
     */
    private static String generateSpaces(int spaces) {
        String result = "";
 
        for (int i = 0; i < spaces; ++i) {
            result += " ";
        }
 
        return result;
    }
 
}