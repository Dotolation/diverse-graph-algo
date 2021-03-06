export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"

#SNAP
#java -Xss1G -cp $CLASSPATHS shell.TestSNAP wikivote.txt.gz 10 400
#java -Xss1G -cp $CLASSPATHS shell.TestSNAP slashdot.txt.gz 10 400
#java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestSNAP twitter_combined.txt.gz 10 400

#DIMACS
java -Xms8G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS nyc_dist.gr.gz 10 400
java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS florida_dist.gr.gz 10 400

