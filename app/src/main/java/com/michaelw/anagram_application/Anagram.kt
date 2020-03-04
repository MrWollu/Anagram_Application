package com.michaelw.anagram_application
import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


object Anagram{
    private val DICT: String = "dict"
    private val LOADED: Boolean = false
    private val WORDS: List<String> = ArrayList()

    /*
    * public Boolean isLoaded(){
    *   return true
    * }
    * */

    fun isLoaded(): Boolean{
        return LOADED
    }

    /*
    BufferedReader buf = null;

      try {
       buf = new BufferedReader(new InputStreamReader(context.getAssets().open(DICT)));

       String line = null;

       // we read file line by line to store words
       while ((line = buf.readLine()) != null) {
        // we set words in upper case to make
        // things easier when searching anagrams for entered letters
        WORDS.add(line.toUpperCase());
       }

       LOADED = true;

      } catch (IOException ioe) {
       // ...
      } finally {
       if (buf != null) {
        try {
         buf.close();
        } catch (IOException e) {
         // ...
        }
       }
      }
     }
    * */
    fun loadWords(context: Context){
        val TAG: String = "myAnagram";
        try {
            val inputStream: InputStream = context.assets.open(DICT)
            val inputStreamReader = InputStreamReader(inputStream)
            val sb = StringBuilder()
            var line: String?
            val br = BufferedReader(inputStreamReader)
            line = br.readLine()
            while (br.readLine() != null) {
                sb.append(line)
                line = br.readLine()
            }
            br.close()
            Log.d(TAG,sb.toString())
        } catch (e:Exception){
            Log.d(TAG, e.toString())
        }
    }

    /*
    *  // Method for comparing two strings and returning true if they have same letters
         public static boolean sameLetters(String a, String b) {
          if (a == null)
           return b == null;
          if (b == null)
           return false;

          char[] left = a.toCharArray();
          char[] right = b.toCharArray();

          // we sort characters of each String
          Arrays.sort(left);
          Arrays.sort(right);

          // we compare both sorted arrays
          return Arrays.equals(left, right);
         }
    * */
    fun sameLetters(a:String,b:String): Boolean{
        if(a == null)
            return b == null
        if(b == null)
            return false

        var left = a.toCharArray()
        var right = b.toCharArray()

        left.sort()
        right.sort()

        return left contentEquals right
    }

    /*
    *  // Method returning all anagrams for entered letters
     public static List < String > listWords(String letters) {
      List < String > list = new ArrayList < > ();

      for (String word: WORDS) {
       if (sameLetters(word, letters))
        list.add(word);
      }


      return list;
     }
    * */
    fun listWords(letters: String): List<String>{
        var list: MutableList<String> = ArrayList()
        for (word in WORDS){
            if (sameLetters(word, letters))
                list.add(word)
        }
        return list
    }
    /*Question on array list (ArrayList, MutableList, List)? when would I use these List*/
}
