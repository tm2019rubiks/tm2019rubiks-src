#include <Wire.h>
#include <LiquidCrystal_I2C.h>
LiquidCrystal_I2C lcd(0x27, 20, 4);

String faces[] = {"F", "F2", "F'", "R", "R2", "R'", "B", "B2", "B'", "L", "L2", "L'", "U", "U2", "U'", "D", "D2", "D'"};
int commandCode = 47;
int stepPinStart = 2;
int DirPinStart = 8;
bool actualDirs[6];

byte commands[46];
byte mods[46];

void setup()
{
  Serial.begin(9600);
  lcd.begin();
 







}
void exec(){
  for (int i = 0; commands[i] != 100; i ++) {
    int face = commands[i];
    
  }
}

void loop()
{
  bool started = false;
  bool newSol = false;
  String solution = "";

  if (Serial.available() > 0) {
    int startCode = Serial.read();
    if (startCode == commandCode) {
      started = true;
    }

  }
  if (started) {

    int index = 0;


    int lastCode = -100;
    int faceCode = 100;
    bool isFaceCode = false;
    while (started) {

      while (Serial.available() == 0) {
        delay(1);
      }
      isFaceCode = (!isFaceCode);
      lastCode = Serial.read();
      if (lastCode == commandCode) {
        newSol = true;
        commands[index] = 100;
        break;

      }
      if (isFaceCode) {
        commands[index] = lastCode - 48;
      }
      else {
        mods[index] = lastCode - 48;
        index ++;
      }
    }
  }
  if (newSol) {
    lcd.clear();

    for (int i = 0; commands[i] != 100; i ++) {
      lcd.print(faces[commands[i]*3 + mods[i]]);
    }
  }


  //Write your code

}
