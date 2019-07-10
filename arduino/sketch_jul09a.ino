#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <C:\Users\estok\Documents\Arduino\sketch_jul09a\CustomChars.h>


//pins
#define stepPinStart  2
#define dirPinStart  8
//codes
#define commandCode 47 //ascii '/'
#define endCode 100
#define substractCode 48
#define quarterTurnSteps 50

//char codes
#define csep 0
#define cidown 1
#define ciup 2
#define culbor 3
#define curbor 4
#define cdlbor 5
#define cdrbor 6
#define csepjoin 7

LiquidCrystal_I2C lcd(0x27, 20, 4);

String faces[] = {"F ", "F2", "F'", "R ", "R2", "R'", "B ", "B2", "B'", "L ", "L2", "L'", "U ", "U2", "U'", "D ", "D2", "D'"};

//current directions, needed to not output the same value twice
bool currentDirs[6];

//the indexes of the faces in order
byte faceIndexes[46];
//the mods: 0 = normal, 1 = double, 2 = prime;
byte mods[46];

int milliDelay = 1;
int solutionLengthHTM = 0;

//display variable so it doesnt have to be recalculated
byte mvsStringStart = 0;

void setup()
{

  //serial for communication
  Serial.begin(9600);

  //lcd setup
  lcd.begin();
  lcd.createChar(csep, sep);
  lcd.createChar(cidown, interDown);
  lcd.createChar(ciup, interUp);
  lcd.createChar(culbor, ulBorder);
  lcd.createChar(curbor, urBorder);
  lcd.createChar(cdlbor, dlBorder);
  lcd.createChar(cdrbor, drBorder);
  lcd.createChar(csepjoin, sepJoin);
  
  
  for (int i = 0; i < 6; i ++) {
    pinMode(stepPinStart + i, OUTPUT);
    pinMode(dirPinStart + i, OUTPUT);
  }
  initDisplay();

}
void initDisplay(){
  lcd.clear();
  lcd.home();
  lcd.print("\x03-------\x02-------\x02--\x04");
  lcd.setCursor(0, 1);
  lcd.print("\x08 temps \x08HTM mvs\x08  \x08");
  lcd.setCursor(0, 2);
  lcd.print("\x08    0ms\x08       \x07--\x06");
  lcd.setCursor(0, 3);
  lcd.print("\x05-------\x01-------\x06");

  
  
}
void updateDisplayTime(long t){
  lcd.setCursor(1, 2);
  lcd.print("     ");
  String timeMillis = String(t);
  lcd.setCursor(6-timeMillis.length(), 2);
  lcd.print(timeMillis);
}
void displayTotalMoves(int moves){
  lcd.setCursor(9, 2);
  lcd.print("       ");
  String movesString = "/"+String(moves);
  mvsStringStart = 16-movesString.length();
  lcd.setCursor(mvsStringStart, 2);
  lcd.print(movesString);
}
void displayMoveProgress(int progress){
  String progressString = String(progress);
  lcd.setCursor(mvsStringStart-progressString.length(), 2);
  lcd.print(progressString);
  
}
void displayMoveString(String moveString){
  lcd.setCursor(17,1);
  lcd.print(moveString);
}
void exec() {
  
  int progressHTM = 1;

  displayTotalMoves(solutionLengthHTM);
  

  long startTime = millis();
  for (int i = 0; faceIndexes[i] != endCode; i ++) {

    
    displayMoveProgress(progressHTM);


    int face = faceIndexes[i];
    int mod = mods[i];
    int stepPin = stepPinStart + face;
    int dirPin = dirPinStart + face;

    displayMoveString(faces[face * 3 + mod]);

    int steps = quarterTurnSteps;
    if (mod != 1) { //half turns are the same in both dirs
      if ((mod == 0) != currentDirs[face]) { //if the wanted dir != current durection, then switch the dir
        currentDirs[face] = !currentDirs[face];
        digitalWrite(dirPin, currentDirs[face]);
        delay(30);
      }
      
    }
    else {
      steps += quarterTurnSteps;
    }


    for (int j = 0; j < steps; j ++) {
      digitalWrite(stepPin, HIGH);
      delay(milliDelay);
      digitalWrite(stepPin, LOW);
      delay(milliDelay);
    }
    progressHTM ++;
    updateDisplayTime(millis()-startTime);


    
  }
  //updateDisplayTime(millis()-startTime);

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
    solutionLengthHTM = 0;


    int lastCode = -100;
    int faceCode = endCode;
    bool isFaceCode = false;
    while (started) {

      while (Serial.available() == 0) {
        delay(1);
      }
      isFaceCode = (!isFaceCode);
      lastCode = Serial.read();
      if (lastCode == commandCode) {
        faceIndexes[index] = endCode;
        break;

      }
      if (isFaceCode) {
        faceIndexes[index] = lastCode - substractCode;
      }
      else {
        mods[index] = lastCode - substractCode;
        index ++;
      }
    }
  }
  if (started) {


    for (int i = 0; faceIndexes[i] != endCode; i ++) {
      //lcd.print(faces[faceIndexes[i] * 3 + mods[i]]);
      solutionLengthHTM ++;
      if (mods[i] == 1) {
      }

    }
    exec();
  }


}
