cd server\
javac -cp libs/jackson-annotations-2.14.0-rc2.jar;libs/jackson-core-2.14.0-rc2.jar;libs/jackson-databind-2.14.0-rc2.jar -d build/ ./src/*.java
cd build/
del Main.jar
jar cvfm Main.jar manifest.txt *.class
java -jar Main.jar
pause