cd client\
javac -cp build/jackson-annotations-2.14.0-rc2.jar;build/jackson-core-2.14.0-rc2.jar;build/jackson-databind-2.14.0-rc2.jar -d build/ ./src/*.java
cd build/
del Main.jar
jar cvfm Main.jar manifest.txt *.class
java -jar Main.jar
pause