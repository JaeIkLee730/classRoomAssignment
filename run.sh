#!/bin/bash

ant
cd bin
java cr/main/Main ../data/distanceData.csv ../data/classroomMapping.csv ../data/sequence.csv ../data/assignment.csv ../data/courseTime.csv ../data/rough_room_capacity.csv ../data/rough_course_capacity.csv ../data/class_room_time.csv
