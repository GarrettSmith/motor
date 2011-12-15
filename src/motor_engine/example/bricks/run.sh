#!/bin/sh
java \
	-cp .:lib/*:lib/lwjgl-2.7.1/jar/* \
	-Djava.library.path=lib/lwjgl-2.7.1/native/linux/ \
	motor_engine.example.bricks.ExampleGame
