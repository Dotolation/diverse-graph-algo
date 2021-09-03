export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"
java -Xms16G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS nyc_dist.gr.gz 10 340 &
java -Xms16G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS florida_dist.gr.gz 10 340 &
