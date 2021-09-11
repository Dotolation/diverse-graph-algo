#Make sure all the classpaths are included!
export CLASSPATHS="target/classes:/home/$USER/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/$USER/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"

#SNAP
java -Xss1G -cp $CLASSPATHS shell.TestSNAP wikivote.txt.gz 10 80 7777
java -Xss1G -cp $CLASSPATHS shell.TestSNAP slashdot.txt.gz 10 80 7777
java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestSNAP twitter_combined.txt.gz 10 80 7777

#DIMACS
java -Xms24G -Xmx48G -Xmn36G -Xss1G -cp $CLASSPATHS shell.TestDIMACS nyc_dist.gr.gz 10 80 7777
java -Xms24G -Xmx48G -Xmn36G -Xss1G -cp $CLASSPATHS shell.TestDIMACS florida_dist.gr.gz 10 80 7777

#Assign other name to the default output CSV file, which is test_result.csv
mv target/test_result.csv target/sample_result.csv

