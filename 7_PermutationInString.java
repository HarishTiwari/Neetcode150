import java.util.Arrays;

class Solution {

    /**
     * Problem:
     * Given two strings s1 and s2, return true if s2 contains
     * a permutation of s1.
     *
     * In other words:
     * Check if one of s1's permutations is a substring of s2.
     *
     * Example:
     * s1 = "ab"
     * s2 = "eidbaooo"
     * Output: true
     * Explanation: "ba" is a permutation of "ab".
     *
     * ------------------------------------------------------------
     * Approach: Sliding Window (Fixed Size) + Frequency Array
     * ------------------------------------------------------------
     *
     * Idea:
     * - A permutation means same character frequency.
     * - Maintain two frequency arrays:
     *      freq1 → frequency of s1
     *      freq2 → frequency of current window in s2
     *
     * Steps:
     * 1. If s1.length() > s2.length() → return false.
     * 2. Fill freq1 using s1.
     * 3. Traverse s2 using sliding window of size s1.length().
     * 4. Update freq2:
     *      - Add current right character.
     *      - If window size exceeds s1 length → remove left character.
     * 5. Compare freq1 and freq2.
     *
     * Time Complexity: O(n * 26)
     * - For each step, Arrays.equals() compares 26 elements.
     * - Effectively O(n).
     *
     * Space Complexity: O(1)
     * - Fixed size arrays of length 26.
     */
    public boolean checkInclusion(String s1, String s2) {

        // If s1 longer than s2 → impossible
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] freq1 = new int[26]; // Frequency of s1
        int[] freq2 = new int[26]; // Frequency of current window in s2

        // Fill frequency for s1
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
        }

        int left = 0;

        // Sliding window over s2
        for (int right = 0; right < s2.length(); right++) {

            // Add current character to window
            freq2[s2.charAt(right) - 'a']++;

            // Maintain window size equal to s1 length
            if (right - left + 1 > s1.length()) {
                freq2[s2.charAt(left) - 'a']--;
                left++;
            }

            // Compare frequency arrays
            if (Arrays.equals(freq1, freq2)) {
                return true;
            }
        }

        return false;
    }
}