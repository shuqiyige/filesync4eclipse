/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package de.loskutov.fs.builder;

/**
 * This class is a collection of helper methods to manipulate char arrays.
 *
 * @since 2.1
 */
public final class CharOperation {

    /**
     * Constant for an empty char array
     */
    public static final char[] NO_CHAR = new char[0];

    /**
     * Constant for an empty char array with two dimensions.
     */
    static final char[][] NO_CHAR_CHAR = new char[0][];

    /**
     * Constant for an empty String array.
     * @since 3.1
     */
    public static final String[] NO_STRINGS = new String[0];


    /**
     * Answers the concatenation of the two arrays inserting the separator character between the two arrays.
     * It answers null if the two arrays are null.
     * If the first array is null, then the second array is returned.
     * If the second array is null, then the first array is returned.
     * <br>
     * <br>
     * For example:
     * <ol>
     * <li><pre>
     *    first = null
     *    second = { 'a' }
     *    separator = '/'
     *    => result = { ' a' }
     * </pre>
     * </li>
     * <li><pre>
     *    first = { ' a' }
     *    second = null
     *    separator = '/'
     *    => result = { ' a' }
     * </pre>
     * </li>
     * <li><pre>
     *    first = { ' a' }
     *    second = { ' b' }
     *    separator = '/'
     *    => result = { ' a' , '/', 'b' }
     * </pre>
     * </li>
     * </ol>
     *
     * @param first the first array to concatenate
     * @param second the second array to concatenate
     * @param separator the character to insert
     * @return the concatenation of the two arrays inserting the separator character
     * between the two arrays , or null if the two arrays are null.
     */
    public static final char[] concat(
            char[] first,
            char[] second,
            char separator) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        int length1 = first.length;
        if (length1 == 0) {
            return second;
        }
        int length2 = second.length;
        if (length2 == 0) {
            return first;
        }

        char[] result = new char[length1 + length2 + 1];
        System.arraycopy(first, 0, result, 0, length1);
        result[length1] = separator;
        System.arraycopy(second, 0, result, length1 + 1, length2);
        return result;
    }

    /**
     * Answers the first index in the array for which the corresponding character is
     * equal to toBeFound starting the search at index start.
     * Answers -1 if no occurrence of this character is found.
     * <br>
     * <br>
     * For example:
     * <ol>
     * <li><pre>
     *    toBeFound = 'c'
     *    array = { ' a', 'b', 'c', 'd' }
     *    start = 2
     *    result => 2
     * </pre>
     * </li>
     * <li><pre>
     *    toBeFound = 'c'
     *    array = { ' a', 'b', 'c', 'd' }
     *    start = 3
     *    result => -1
     * </pre>
     * </li>
     * <li><pre>
     *    toBeFound = 'e'
     *    array = { ' a', 'b', 'c', 'd' }
     *    start = 1
     *    result => -1
     * </pre>
     * </li>
     * </ol>
     *
     * @param toBeFound the character to search
     * @param array the array to be searched
     * @param start the starting index
     * @return the first index in the array for which the corresponding character is
     * equal to toBeFound, -1 otherwise
     * @throws NullPointerException if array is null
     * @throws ArrayIndexOutOfBoundsException if  start is lower than 0
     */
    public static final int indexOf(char toBeFound, char[] array, int start) {
        for (int i = start; i < array.length; i++) {
            if (toBeFound == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Answers the last index in the array for which the corresponding character is
     * equal to toBeFound starting from the end of the array.
     * Answers -1 if no occurrence of this character is found.
     * <br>
     * <br>
     * For example:
     * <ol>
     * <li><pre>
     *    toBeFound = 'c'
     *    array = { ' a', 'b', 'c', 'd' , 'c', 'e' }
     *    result => 4
     * </pre>
     * </li>
     * <li><pre>
     *    toBeFound = 'e'
     *    array = { ' a', 'b', 'c', 'd' }
     *    result => -1
     * </pre>
     * </li>
     * </ol>
     *
     * @param toBeFound the character to search
     * @param array the array to be searched
     * @return the last index in the array for which the corresponding character is
     * equal to toBeFound starting from the end of the array, -1 otherwise
     * @throws NullPointerException if array is null
     */
    public static final int lastIndexOf(char toBeFound, char[] array) {
        for (int i = array.length; --i >= 0;) {
            if (toBeFound == array[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Answers true if the a sub-pattern matches the subpart of the given name, false otherwise.
     * char[] pattern matching, accepting wild-cards '*' and '?'. Can match only subset of name/pattern.
     * end positions are non-inclusive.
     * The subpattern is defined by the patternStart and pattternEnd positions.
     * When not case sensitive, the pattern is assumed to already be lowercased, the
     * name will be lowercased character per character as comparing.
     * <br>
     * <br>
     * For example:
     * <ol>
     * <li><pre>
     *    pattern = { '?', 'b', '*' }
     *    patternStart = 1
     *    patternEnd = 3
     *    name = { 'a', 'b', 'c' , 'd' }
     *    nameStart = 1
     *    nameEnd = 4
     *    isCaseSensitive = true
     *    result => true
     * </pre>
     * </li>
     * <li><pre>
     *    pattern = { '?', 'b', '*' }
     *    patternStart = 1
     *    patternEnd = 2
     *    name = { 'a', 'b', 'c' , 'd' }
     *    nameStart = 1
     *    nameEnd = 2
     *    isCaseSensitive = true
     *    result => false
     * </pre>
     * </li>
     * </ol>
     *
     * @param pattern the given pattern
     * @param patternStart the given pattern start
     * @param patternEnd the given pattern end
     * @param name the given name
     * @param nameStart the given name start
     * @param nameEnd the given name end
     * @param isCaseSensitive flag to know if the matching should be case sensitive
     * @return true if the a sub-pattern matches the subpart of the given name, false otherwise
     */
    public static final boolean match(
            char[] pattern,
            int patternStart,
            int patternEnd,
            char[] name,
            int nameStart,
            int nameEnd,
            boolean isCaseSensitive) {

        if (name == null)
        {
            return false; // null name cannot match
        }
        if (pattern == null)
        {
            return true; // null pattern is equivalent to '*'
        }
        int iPattern = patternStart;
        int iName = nameStart;

        if (patternEnd < 0) {
            patternEnd = pattern.length;
        }
        if (nameEnd < 0) {
            nameEnd = name.length;
        }

        /* check first segment */
        char patternChar = 0;
        while ((iPattern < patternEnd)
                && (patternChar = pattern[iPattern]) != '*') {
            if (iName == nameEnd) {
                return false;
            }
            if (patternChar
                    != (isCaseSensitive
                            ? name[iName]
                                    : Character.toLowerCase(name[iName]))
                                    && patternChar != '?') {
                return false;
            }
            iName++;
            iPattern++;
        }
        /* check sequence of star+segment */
        int segmentStart;
        if (patternChar == '*') {
            segmentStart = ++iPattern; // skip star
        } else {
            segmentStart = 0; // force iName check
        }
        int prefixStart = iName;
        checkSegment : while (iName < nameEnd) {
            if (iPattern == patternEnd) {
                iPattern = segmentStart; // mismatch - restart current segment
                iName = ++prefixStart;
                continue checkSegment;
            }
            /* segment is ending */
            if ((patternChar = pattern[iPattern]) == '*') {
                segmentStart = ++iPattern; // skip start
                if (segmentStart == patternEnd) {
                    return true;
                }
                prefixStart = iName;
                continue checkSegment;
            }
            /* check current name character */
            if ((isCaseSensitive ? name[iName] : Character.toLowerCase(name[iName]))
                    != patternChar
                    && patternChar != '?') {
                iPattern = segmentStart; // mismatch - restart current segment
                iName = ++prefixStart;
                continue checkSegment;
            }
            iName++;
            iPattern++;
        }

        return (segmentStart == patternEnd)
                || (iName == nameEnd && iPattern == patternEnd)
                || (iPattern == patternEnd - 1 && pattern[iPattern] == '*');
    }

    /**
     * Answers true if the pattern matches the filepath using the pathSepatator, false otherwise.
     *
     * Path char[] pattern matching, accepting wild-cards '**', '*' and '?' (using Ant directory tasks
     * conventions, also see "http://jakarta.apache.org/ant/manual/dirtasks.html#defaultexcludes").
     * Path pattern matching is enhancing regular pattern matching in supporting extra rule where '**' represent
     * any folder combination.
     * Special rule:
     * - foo\  is equivalent to foo\**
     * When not case sensitive, the pattern is assumed to already be lowercased, the
     * name will be lowercased character per character as comparing.
     *
     * @param pattern the given pattern
     * @param filepath the given path
     * @param isCaseSensitive to find out whether or not the matching should be case sensitive
     * @param pathSeparator the given path separator
     * @return true if the pattern matches the filepath using the pathSepatator, false otherwise
     */
    public static final boolean pathMatch(
            char[] pattern,
            char[] filepath,
            boolean isCaseSensitive,
            char pathSeparator) {

        if (filepath == null)
        {
            return false; // null name cannot match
        }
        if (pattern == null)
        {
            return true; // null pattern is equivalent to '*'
        }

        // offsets inside pattern
        int pSegmentStart = pattern[0] == pathSeparator ? 1 : 0;
        int pLength = pattern.length;
        int pSegmentEnd = CharOperation.indexOf(pathSeparator, pattern, pSegmentStart+1);
        if (pSegmentEnd < 0) {
            pSegmentEnd = pLength;
        }

        // special case: pattern foo\ is equivalent to foo\**
        boolean freeTrailingDoubleStar = pattern[pLength - 1] == pathSeparator;

        // offsets inside filepath
        int fSegmentStart, fLength = filepath.length;
        if (filepath[0] != pathSeparator){
            fSegmentStart = 0;
        } else {
            fSegmentStart = 1;
        }
        if (fSegmentStart != pSegmentStart) {
            return false; // both must start with a separator or none.
        }
        int fSegmentEnd = CharOperation.indexOf(pathSeparator, filepath, fSegmentStart+1);
        if (fSegmentEnd < 0) {
            fSegmentEnd = fLength;
        }

        // first segments
        while (pSegmentStart < pLength
                && !(pSegmentEnd == pLength && freeTrailingDoubleStar
                || (pSegmentEnd == pSegmentStart + 2
                && pattern[pSegmentStart] == '*'
                && pattern[pSegmentStart + 1] == '*'))) {

            if (fSegmentStart >= fLength) {
                return false;
            }
            if (!CharOperation
                    .match(
                            pattern,
                            pSegmentStart,
                            pSegmentEnd,
                            filepath,
                            fSegmentStart,
                            fSegmentEnd,
                            isCaseSensitive)) {
                return false;
            }

            // jump to next segment
            pSegmentEnd =
                    CharOperation.indexOf(
                            pathSeparator,
                            pattern,
                            pSegmentStart = pSegmentEnd + 1);
            // skip separator
            if (pSegmentEnd < 0) {
                pSegmentEnd = pLength;
            }

            fSegmentEnd =
                    CharOperation.indexOf(
                            pathSeparator,
                            filepath,
                            fSegmentStart = fSegmentEnd + 1);
            // skip separator
            if (fSegmentEnd < 0) {
                fSegmentEnd = fLength;
            }
        }

        /* check sequence of doubleStar+segment */
        int pSegmentRestart;
        if ((pSegmentStart >= pLength && freeTrailingDoubleStar)
                || (pSegmentEnd == pSegmentStart + 2
                && pattern[pSegmentStart] == '*'
                && pattern[pSegmentStart + 1] == '*')) {
            pSegmentEnd =
                    CharOperation.indexOf(
                            pathSeparator,
                            pattern,
                            pSegmentStart = pSegmentEnd + 1);
            // skip separator
            if (pSegmentEnd < 0) {
                pSegmentEnd = pLength;
            }
            pSegmentRestart = pSegmentStart;
        } else {
            if (pSegmentStart >= pLength)
            {
                return fSegmentStart >= fLength; // true if filepath is done too.
            }
            pSegmentRestart = 0; // force fSegmentStart check
        }
        int fSegmentRestart = fSegmentStart;
        checkSegment : while (fSegmentStart < fLength) {

            if (pSegmentStart >= pLength) {
                if (freeTrailingDoubleStar) {
                    return true;
                }
                // mismatch - restart current path segment
                pSegmentEnd =
                        CharOperation.indexOf(pathSeparator, pattern, pSegmentStart = pSegmentRestart);
                if (pSegmentEnd < 0) {
                    pSegmentEnd = pLength;
                }

                fSegmentRestart =
                        CharOperation.indexOf(pathSeparator, filepath, fSegmentRestart + 1);
                // skip separator
                if (fSegmentRestart < 0) {
                    fSegmentRestart = fLength;
                } else {
                    fSegmentRestart++;
                }
                fSegmentEnd =
                        CharOperation.indexOf(pathSeparator, filepath, fSegmentStart = fSegmentRestart);
                if (fSegmentEnd < 0) {
                    fSegmentEnd = fLength;
                }
                continue checkSegment;
            }

            /* path segment is ending */
            if (pSegmentEnd == pSegmentStart + 2
                    && pattern[pSegmentStart] == '*'
                    && pattern[pSegmentStart + 1] == '*') {
                pSegmentEnd =
                        CharOperation.indexOf(pathSeparator, pattern, pSegmentStart = pSegmentEnd + 1);
                // skip separator
                if (pSegmentEnd < 0) {
                    pSegmentEnd = pLength;
                }
                pSegmentRestart = pSegmentStart;
                fSegmentRestart = fSegmentStart;
                if (pSegmentStart >= pLength) {
                    return true;
                }
                continue checkSegment;
            }
            /* chech current path segment */
            if (!CharOperation.match(
                    pattern,
                    pSegmentStart,
                    pSegmentEnd,
                    filepath,
                    fSegmentStart,
                    fSegmentEnd,
                    isCaseSensitive)) {
                // mismatch - restart current path segment
                pSegmentEnd =
                        CharOperation.indexOf(pathSeparator, pattern, pSegmentStart = pSegmentRestart);
                if (pSegmentEnd < 0) {
                    pSegmentEnd = pLength;
                }

                fSegmentRestart =
                        CharOperation.indexOf(pathSeparator, filepath, fSegmentRestart + 1);
                // skip separator
                if (fSegmentRestart < 0) {
                    fSegmentRestart = fLength;
                } else {
                    fSegmentRestart++;
                }
                fSegmentEnd =
                        CharOperation.indexOf(pathSeparator, filepath, fSegmentStart = fSegmentRestart);
                if (fSegmentEnd < 0) {
                    fSegmentEnd = fLength;
                }
                continue checkSegment;
            }
            // jump to next segment
            pSegmentEnd =
                    CharOperation.indexOf(
                            pathSeparator,
                            pattern,
                            pSegmentStart = pSegmentEnd + 1);
            // skip separator
            if (pSegmentEnd < 0) {
                pSegmentEnd = pLength;
            }

            fSegmentEnd =
                    CharOperation.indexOf(
                            pathSeparator,
                            filepath,
                            fSegmentStart = fSegmentEnd + 1);
            // skip separator
            if (fSegmentEnd < 0) {
                fSegmentEnd = fLength;
            }
        }

        return (pSegmentRestart >= pSegmentEnd)
                || (fSegmentStart >= fLength && pSegmentStart >= pLength)
                || (pSegmentStart == pLength - 2
                && pattern[pSegmentStart] == '*'
                && pattern[pSegmentStart + 1] == '*')
                || (pSegmentStart == pLength && freeTrailingDoubleStar);
    }

    /**
     * Answers a new array which is a copy of the given array starting at the given start and
     * ending at the given end. The given start is inclusive and the given end is exclusive.
     * Answers null if start is greater than end, if start is lower than 0 or if end is greater
     * than the length of the given array. If end  equals -1, it is converted to the array length.
     * <br>
     * <br>
     * For example:
     * <ol>
     * <li><pre>
     *    array = { 'a' , 'b' }
     *    start = 0
     *    end = 1
     *    result => { 'a' }
     * </pre>
     * </li>
     * <li><pre>
     *    array = { 'a', 'b' }
     *    start = 0
     *    end = -1
     *    result => { 'a' , 'b' }
     * </pre>
     * </li>
     * </ol>
     *
     * @param array the given array
     * @param start the given starting index
     * @param end the given ending index
     * @return a new array which is a copy of the given array starting at the given start and
     * ending at the given end
     * @throws NullPointerException if the given array is null
     */
    public static final char[] subarray(char[] array, int start, int end) {
        if (end == -1) {
            end = array.length;
        }
        if (start > end) {
            return null;
        }
        if (start < 0) {
            return null;
        }
        if (end > array.length) {
            return null;
        }

        char[] result = new char[end - start];
        System.arraycopy(array, start, result, 0, end - start);
        return result;
    }

}
