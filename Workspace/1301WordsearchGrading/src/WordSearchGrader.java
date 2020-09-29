/**
 * Automatic grader program for Lab 13.
 * 
 * @author  Sahir Shahryar
 * @since   Sunday, April 22, 2018
 * @version 1.0.0
 */
public class WordSearchGrader {
 
    /**
     * Set this to false if your console does not support ANSI color codes
     * (i.e., instead of being colored, your board looks wacky).
     */
    private static final boolean USE_COLORS = false;
 
    public static void main(String[] args) {
        try { 
            // Seeds generated via WordSearchRubricGenerator.
            long[] seeds = {
                    -952226503406480932L, // up, down
                    3848547038311511049L,  // left, right
                    6160855838302487164L,  // up-right, down-right
                    4859007908499387336L   // up-left, down-left
            };
 
            int score = 0;
 
            for (int i = 0; i < seeds.length; ++i) {
                
            	long seed = seeds[i];
                WordBoard board = WordSearch.findWords(10, 10, seed);
 
                /**
                 * Check if the student hard-coded the dimensions by accident
                 */
                if (board.getHeight() != 10 || board.getWidth() != 10) {
                    System.out.println("WARNING: the student's board does not have " +
                            "the correct dimensions (should be 10x10, actually is " +
                            board.getHeight() + "x" + board.getWidth() + "). Please " +
                            "check their findWords() method to make sure they did not " +
                            "accidentally initialize the WordBoard with constant " +
                            "dimensions.");
                }
 
 
                /**
                 * Check if the student hard-coded the seed by accident
                 */
                if (board.getSeed() != seed) {
                    System.out.println("WARNING: the student's board does not have" +
                            " the correct seed (should be " + seed + "L, actually is " +
                            board.getSeed() + "L). Please check their findWords() " +
                            "method to make sure they did not accidentally initialize " +
                            "the WordBoard with a constant seed.");
                }
 
                /**
                 * Print the board and the score for that test case.
                 */
                System.out.println(USE_COLORS ? board.toColorizedString() : board);
                int testCaseScore = board.score();
                System.out.println("Suggested grade for test case " + (i + 1)
                        + ": " + testCaseScore + " / 25");
 
                score += testCaseScore;
            }
 
            System.out.println();
 
            /**
             * Show a final grade after all test cases.
             */
            if (score == 100) {
                System.out.println("Suggested score: 100 / 100");
                System.out.println("All test cases passed! Please check the code " +
                        "for comments / indentation / et cetera.");
            } else {
                System.out.println("Suggested score: " + score + " / 100");
                System.out.println("Please ensure that the above results reflect this " +
                        "grade.");
            }
        }
         
        catch (final ArrayIndexOutOfBoundsException e) {
            System.out.println("Student had an ArrayIndexOutOfBoundsException: ");
            e.printStackTrace();
        }
         
        catch (final Throwable t) {
            System.out.println("Unexpected exception of type " + t.getClass() + ":");
            t.printStackTrace();
        }
    }
 
}