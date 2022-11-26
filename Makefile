# JAVA COMPILER
JAVAC = javac

# JAVA RUNTIME
java = java

# SERVER REPOSITORY
SERVER = server

# CLIENT REPOSITORY
CLIENT = client

# CLASS PATH
CLASSPATH = libs/*

# BUILD
BUILD = build

# SOURCE
SOURCE = src


# MAIN CLIENT
MAIN_CLIENT = $(CLIENT)Main.java

# MAIN SERVER
MAIN_SERVER = $(SERVER)Main.java


# JFLAGS SERVER
JFLAGS_SERVER = -cp $(SERVER)/$(CLASSPATH)  -d $(SERVER)/$(BUILD) -encoding "UTF-8" -sourcepath "$(SERVER)/src" -implicit:none 


# JFLAGS CLIENT
JFLAGS_CLIENT = -cp $(CLIENT)/$(CLASSPATH)  -d $(CLIENT)/$(BUILD) -encoding "UTF-8" -sourcepath "$(CLIENT)/src" -implicit:none


# SERVER RUN OPTION
SERVER_RUN_OPTION = -cp $(SERVER)/$(BUILD)/:"$(CLASSPATH)" 

# CLIENT RUN OPTION
CLIENT_RUN_OPTION = -cp $(CLIENT)/$(BUILD)/:"$(CLASSPATH)"


# COMMANDS

compile_all:
	echo "Compiling server/client ..."
	$(JAVAC) $(JFLAGS_SERVER) $(SERVER)/$(SOURCE)/*.java
	$(JAVAC) $(JFLAGS_CLIENT) $(CLIENT)/$(SOURCE)/*.java


compile_server:
	echo "Compiling server..."
	$(JAVAC) $(JFLAGS_SERVER) $(SERVER)/$(SOURCE)/*.java

compile_client:
	echo "Compiling client"
	$(JAVAC) $(JFLAGS_CLIENT) $(CLIENT)/$(SOURCE)/*.java

run_server:
	echo "Server running..."
	$(java) $(SERVER_RUN_OPTION) Main

run_client:
	echo "Client running..."
	$(java) $(CLIENT_RUN_OPTION) Main

clean:
	echo "Cleaning all repository..."
	rm -rf $(SERVER)$(BUILD)*.class
	rm -rf $(CLIENT)$(BUILD)*.class