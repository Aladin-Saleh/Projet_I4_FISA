cd client\
javac -cp build/jackson-annotations-2.14.0-rc2.jar;build/jackson-core-2.14.0-rc2.jar;build/jackson-databind-2.14.0-rc2.jar -d build/ ./src/*.java
cd build/
del MainMenu.jar
jar cvfm MainMenu.jar manifest-menu.txt *.class
java -jar MainMenu.jar
pause