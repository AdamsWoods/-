//
// Created by WisdomZhang on 2019/11/26.
//

#include <iostream>

using namespace std;

#ifndef BASICLEARN_FACER_H
#define BASICLEARN_FACER_H

class Facer {
public:
    Facer(const string &top="#", const string &bottom="#", const string &brow="~", const string &eyes=".");
    ~Facer();
public:
    string top;
    string bottom;
    string brow;
    string eyes;
public:
    void printFace() ;
    string getFace();
};

#endif //BASICLEARN_FACER_H
