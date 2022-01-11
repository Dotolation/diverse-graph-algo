export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"

#SNAP
#java -Xss1G -cp $CLASSPATHS shell.TestSNAP wikivote.txt.gz 10 400 2021
#java -Xss1G -cp $CLASSPATHS shell.TestSNAP slashdot.txt.gz 10 400 2021
#java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestSNAP twitter_combined.txt.gz 10 400 2021

#DIMACS
#java -Xms8G -Xmx32G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS nyc_dist.gr.gz 10 400 2021
#java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS florida_dist.gr.gz 10 400 2021
#java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestDIMACS co_dist.gr.gz 10 400 2021

#More SNAPS
#java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestSNAP wiki-talk.txt.gz 10 400 2021
#java -Xms8G -Xmx48G -Xmn16G -Xss1G -cp $CLASSPATHS shell.TestSNAP google.txt.gz 10 400 2021

#Grids
#java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestGrid 140 10 10 50 100

#Git stuff
mv target/test_result.csv target/grid_result.csv
git add target/grid_result.csv
git commit -m "re-conducted Grid experiment"
git push origin
