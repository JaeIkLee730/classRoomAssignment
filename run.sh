#!/bin/bash

ant
cd bin
java cr/main/Main ../data/distanceData.csv ../data/classroomMapping.csv ../data/sequence.csv ../data/assignment.csv ../data/courseTime.csv ../data/capacity.csv
