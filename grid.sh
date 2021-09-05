export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"

#DIMACS
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestGrid 200 10 10 50 100