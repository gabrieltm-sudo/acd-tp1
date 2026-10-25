BIN_DIR = bytecodes

run: compile
	java -cp $(BIN_DIR) Main
benchmark: compile
	java -cp $(BIN_DIR) Benchmark
compile:
	@mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) Main.java Benchmark.java TestesOrdenacao.java Vetores.java algoritmos/*.java

test: compile
	java -cp $(BIN_DIR) TestesOrdenacao

rm:
	rm -rf $(BIN_DIR)

rmcsv:
	rm -rf logs