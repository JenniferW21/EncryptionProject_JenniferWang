class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
   
    // Array1: Specific characters
    char[] letter = new char[6]; //sub
    letter[0] = 'a';
    letter[1] = 'e';
    letter[2] = 'i';
    letter[3] = 'n';
    letter[4] = 'o';
    letter[5] = 't';
  

    // Array2: Unicode characters
    char[] cHess = new char[6]; //sub replace with sub2
    cHess[0] = '\u2654';  // QUARTER NOTE
    cHess[1] = '\u2655';  // EIGHTH NOTE
    cHess[2] = '\u2656';  // BEAMED EIGHTH NOTES
    cHess[3] = '\u2657';  // BEAMED SIXTEENTH NOTES
    cHess[4] = '\u2658';  // MUSIC FLAT SIGN
    cHess[5] = '\u2659';  // MUSIC NATURAL SIGN


    
    // Encoding the original text:
    String file = Input.readFile("Original.txt");
    // Encode level 1 (String Manipulation - Reverse)
    String encodedMsg1 = reverse(file);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (substitution)
    String encodedMsg2 = subEncryption(encodedMsg1,letter,cHess);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // Encode level 3 (Cipher elevated - round robbin shift)
    int [] roundRobin1 = {1,2,3,4};
    String encodedMsg3 = roundRobin1(encodedMsg2,roundRobin1);
    Input.writeFile("Encode3.txt", encodedMsg3);

    
    // Decoding the encoded text: 
    String file2 = Input.readFile("Encode3.txt");
    // Decode level 1  (Cipher elevated - round robbin shift)
    String decodedMsg1 = reverse(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2 (Substitution)
    String decodedMsg2 = subEncryption(decodedMsg1, cHess, letter);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (String Manipulation - ReverseBack)
    int [] roundRobin2 = {4,3,2,1};
    String decodedMsg3 = roundRobin2(decodedMsg2,roundRobin2);
    Input.writeFile("Decode3.txt", decodedMsg3);
    
    
  }

  // // reverse a string (encode 1)
  String reverse(String msg){
    String build ="";
    for(int x=0; x< msg.length; x++){
      build = msg.charAt(x) + build;
    }
    return build;
  }

  // Substitution (encode2)
  String subEncryption(String msg1, char[] letter, char[] Mchar){
    String build = "";
    char ch ='\0';
    int index=0;
    for(int x=0; x<msg1.length(); x++){
      ch = msg1.charAt(x); //identify where the character position
      index = indexOf(ch,letter);
      if(index != -1){
        build += Mchar[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }


  // Round Robbin +1 encoding 
  String roundRobin1(String msg2, int[] rRobinshift){ //(the text that is being encoded, the array of shifts)
    String build = "";
    int rRobin = 0;
    int ascii = 0;
    char ch = '\0';
    
    for(int x=0; x<msg2.length(); x++){
      ch = msg2.charAt(x); //position of each character
      ascii = (int)ch; //takes each char and cast into integer
      if(ch == ' '){
        build +=' ';
      }
      ascii += rRobinshift[rRobin]; // array[0] position 0 would be shift of 1
      build += (char)ascii; //convert to character 
      rRobin++; //the array position goes up
      if(rRobin == 4){ //only 4 shifts in total{1,2,3,4} after for it goes back to position 0 which is 1
        rRobin = 0;   
    }  
  }
  return build;
}


  // identifying index of char within array
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x] == ch){
        return x;
      }
    }
    return -1;
  }

  // random integer generator
  int randInt(int lower, int upper){
    int range = upper - lower + 1;
    return (int)(Math.random()*range) + lower;
  }

}