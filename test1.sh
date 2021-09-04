export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"

#GridGraphs
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.DiverseGrid 150 250 10 
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.KBestGrid 150 150 10 
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.DiverseGrid 200 200 10 
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.KBestGrid 200 200 10
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.DiverseGrid 250 250 10 
java -Xms16G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.KBestGrid 250 250 10

#SNAP
java -Xss1G -cp $ClASSPATHS shell.TestSNAP wikivote.txt.gz 400
java -Xss1G -cp $CLASSPATHS shell.TestSNAP slashdot.txt.gz 400
java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestSNAP twitter_combined.txt.gz 400

#DIMACS
java -Xms8G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS nyc_dist.gz 400
java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS florida_dist.gz 400

