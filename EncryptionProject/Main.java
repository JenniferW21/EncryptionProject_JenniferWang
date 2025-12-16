class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}


  void init(){
   
    // Array1: Specific characters
    char[] letter = new char[7]; //sub
      letter[0] = 'b';
      letter[1] = 'r';
      letter[2] = 'a';
      letter[3] = 'i';
      letter[4] = 'l';
      letter[5] = 'e';
      letter[6] = '.';
 




    // Array2: Unicode characters
    char[] braillePattern = new char[7]; //sub replace with sub2
      braillePattern[0] = '\u2803';  // BRAILLE PATTERN DOTS-12(b)
      braillePattern[1] = '\u2817';  // BRAILLE PATTERN DOTS-1235(r)
      braillePattern[2] = '\u2801';  // BRAILLE PATTERN DOTS-1(a)
      braillePattern[3] = '\u280A';  // BRAILLE PATTERN DOTS-24(i)
      braillePattern[4] = '\u2807';  // BRAILLE PATTERN DOTS-123(l)
      braillePattern[5] = '\u2811';  // BRAILLE PATTERN DOTS-15(e)
      braillePattern[6] = '\u2832';  // BRAILLE PATTERN DOTS-256(.)


   
    // Encoding the original text:
    String file1 = Input.readFile("Original.txt");
    // Encode level 1 (String Manipulation - Reverse)
    String encodedMsg1 = reverse(file1);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (substitution)
    String encodedMsg2 = subEncryption(encodedMsg1,letter,braillePattern);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // Encode level 3 (Cipher elevated - round robbin shift)
    int [] roundRobin1 = {1,2,3,4};
    String encodedMsg3 = roundRobin1(encodedMsg2,roundRobin1);
    Input.writeFile("Encode3.txt", encodedMsg3);


   
    // Decoding the encoded text:
    String file2 = Input.readFile("Encode3.txt");
    // Decode level 1  (Cipher elevated - round robbin shift)
    int [] roundRobin2 = {1,2,3,4};
    String decodedMsg1 = roundRobin2(file2,roundRobin2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2 (Substitution)
    String decodedMsg2 = subEncryption(decodedMsg1, braillePattern, letter);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (String Manipulation - ReverseBack)
    String decodedMsg3 = reverse(decodedMsg2);
    Input.writeFile("Decode3.txt", decodedMsg3);


   
   
  }


  // reverse a string (encode 1)
  String reverse(String msg){
    String build ="";
    for(int x=0; x< msg.length(); x++){
      build = msg.charAt(x) + build;
    }
    return build;
  }

  // Substitution (encode2)
  String subEncryption(String msg1, char[] letter, char[] bRaille){
    String build = "";
    char ch ='\0';
    int index=0;
    for(int x=0; x<msg1.length(); x++){
      ch = msg1.charAt(x); //identify where the character position
      index = indexOf(ch,letter);
      if(index != -1){
        build += bRaille[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }




  // Round Robbin encoding (encode 3)
  String roundRobin1(String msg2, int[] rRobinshift){ //(encodedmsg1, the array of shifts)
    String build = "";
    int rRobin = 0;
    int ascii = 0;
    char ch = '\0';
   
    for(int x=0; x < msg2.length(); x++){//forloop to go through each letter of the string
      ch = msg2.charAt(x); //position of each character
      ascii = (int)ch; //takes each char and cast into integer
      ascii += rRobinshift[rRobin]; //ascii = rRobinshift[0] = +1 because its '+='
      build += (char)ascii; //convert to character
      rRobin++; //the array position goes up
      if(rRobin == 4){ //4 shifts in total{1,2,3,4} after goes back to position 0
        rRobin = 0;             //position(0,1,2,3)
      }  
    }
    return build;
  }

// Round Robbin decoding (decode 1)
  String roundRobin2(String msg3, int[] rRobinshift){ //(encodedmsg1, the array of shifts)
    String build = "";
    int rRobin = 0;
    int ascii = 0;
    char ch = '\0';
   
    for(int x=0; x < msg3.length(); x++){//forloop to go through each letter of the string
      ch = msg3.charAt(x); //position of each character
      ascii = (int)ch; //takes each char and cast into integer
      ascii -= rRobinshift[rRobin]; //ascii = rRobinshift[0] = -1 because its '-='
      build += (char)ascii; //convert to character
      rRobin++; //the array position goes up
      if(rRobin == 4){ //4 shifts in total{1,2,3,4} after goes back to position 0
        rRobin = 0;             //position(0,1,2,3)
      }  
    }
    return build;//everything is shifting back from the first round robin shift 
                 //eg. Before it was a shift of +1, now it's reversing it to -1
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


