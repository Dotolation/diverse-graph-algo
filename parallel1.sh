export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.DiverseGrid 200 200 10 &
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.KBestGrid 200 200 10 &

