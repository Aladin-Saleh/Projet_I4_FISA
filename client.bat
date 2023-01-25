cd .\client\
javac -cp -d ./build/ ./src/*.java
cd ./build/
rm Main.jar
jar cvfm Main.jar manifest.txt *.class
java -jar Main.jar
pause