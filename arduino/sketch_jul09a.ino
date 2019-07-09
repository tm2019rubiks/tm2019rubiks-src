#include <Wire.h>
#include <LiquidCrystal_I2C.h>


//pins
#define stepPinStart  2
#define dirPinStart  8
//codes
#define commandCode 47
#define endCode 100
#define substractCode 48
#define quarterTurnSteps 50

LiquidCrystal_I2C lcd(0x27, 20, 4);

String faces[] = {"F", "F2", "F'", "R", "R2", "R'", "B", "B2", "B'", "L", "L2", "L'", "U", "U2", "U'", "D", "D2", "D'"};

//current directions, needed to not output the same value twice
bool currentDirs[6];

//the indexes of the faces in order
byte faceIndexes[46];
//the mods: 0 = normal, 1 = double, 2 = prime;
byte mods[46];

int milliDelay = 1;

void setup()
{

  //serial for communication
  Serial.begin(9600);
  lcd.begin();

  for(int i = 0; i < 6; i ++){
    pinMode(stepPinStart + i, OUTPUT);
    pinMode(dirPinStart + i, OUTPUT);
  }
}
void exec(){
  for (int i = 0; faceIndexes[i] != endCode; i ++) {
    int face = faceIndexes[i];
    int mod = mods[i];
    int stepPin = stepPinStart + face;
    int dirPin = dirPinStart + face;

    int steps = quarterTurnSteps;
    if(mod != 1){ //half turns are the same in both dirs
      if((mod == 0) != currentDirs[face]){ //if the wanted dir != current durection, then switch the dir
        currentDirs[face] = !currentDirs[face];
        digitalWrite(dirPin, currentDirs[face]);
        delay(30);
      }
    }
    else{
      steps += quarterTurnSteps;
    }
    

    for(int j = 0; j < steps; j ++){
      digitalWrite(stepPin, HIGH);
      delay(milliDelay);
      digitalWrite(stepPin, LOW);
      delay(milliDelay);
    }
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
    lcd.clear();

    for (int i = 0; faceIndexes[i] != endCode; i ++) {
      lcd.print(faces[faceIndexes[i]*3 + mods[i]]);
    }
    exec();
  }


}
