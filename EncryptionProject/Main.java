class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
   
    // Array1: Specific characters
    char[] letter = new char[7]; //sub
    sub[0] = 'd';
    sub[1] = 'j';
    sub[2] = 'n';
    sub[3] = 'm';
    sub[4] = 'b';
    sub[5] = 'o';
    sub[6] = 's';

    // Array2: Unicode characters
    char[] Mnote = new char[7]; //sub replace with sub2
    sub2[0] = '\u2669';  // QUARTER NOTE
    sub2[1] = '\u266A';  // EIGHTH NOTE
    sub2[2] = '\u266B';  // BEAMED EIGHTH NOTES
    sub2[3] = '\u266C';  // BEAMED SIXTEENTH NOTES
    sub2[4] = '\u283D';  // MUSIC FLAT SIGN
    sub2[5] = '\u283E';  // MUSIC NATURAL SIGN
    sub2[6] = '\u283F';  // MUSIC SHARP SIGN

    
    // Encoding the plaintext:
    String file = Input.readFile("Original.txt");
    // Encode level 1 (string manipulation - reverse)
    String encodedMsg1 = subEncryption(file,letter,Mnote);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (substitution)
    String encodedMsg2 = encode(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // // Encode level 3 (Cipher elevated - round robbin shift)
    String encodedMsg3 = reverse(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

    
    // Decoding the ciphertext: 
    String file2 = Input.readFile("Encode3.txt");
    // Decode level 1  (Cipher elevated - round robbin shift)
    String decodedMsg1 = reverse(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2 (substitution)
    String decodedMsg2 = decode(decodedMsg1);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (string manipulation - reverseback)
    String decodedMsg3 = subEncryption(decodedMsg2, Mnote, letter);
    Input.writeFile("Decode3.txt", decodedMsg3);
    
    
  }

  // reverse a string (encode 1)
  String reverse(String txt){
    String build ="";
    for(int x=0; x<= txt.length()-1; x++){
      build = txt.charAt(x) + build;
    }
    return build;
  }

  // Substitution (encode2)
  String subEncryption(String s, char[] letter, char[] Mchar){
    String build = "";
    char ch ='\0';
    int index=0;
    for(int x=0; x<=s.length()-1; x++){
      ch = s.charAt(x);
      index = indexOf(ch,sub);
      if(index != -1){
        build += sub2[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }

  // Cipher +1 encoding with no wrapping
  String encode(String txt){
    String build = "";
    int ascii = 0;
    char ch = '\0';
    
    for(int x=0; x<=txt.length()-1; x++){
      ch = txt.charAt(x);
      ascii = (int)ch;
      ascii += 1;
      
      build += (char)ascii;
    }     
    return build;
  }

  // Cipher -1 encoding with no wrapping
  String decode(String txt){
    String build="";
    int ascii;
    char ch='\0';
    for(int x=0; x<=txt.length()-1; x++){
      ch=txt.charAt(x);
      ascii = (int)ch;
      ascii -= 1;
        build += (char)ascii;
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