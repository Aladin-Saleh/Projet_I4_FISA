cd .\server\
javac -cp ./libs/* -d ./build/ ./src/*.java
cd ./build/
jar cvfm Main.jar manifest.txt *.class
java -jar Main.jar
