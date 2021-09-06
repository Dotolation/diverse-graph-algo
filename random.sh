export CLASSPATHS="target/classes:/home/ymmtlab/.m2/repository/org/jgrapht/jgrapht-core/1.5.1/jgrapht-core-1.5.1.jar:/home/ymmtlab/.m2/repository/org/jheaps/jheaps/0.13/jheaps-0.13.jar"

#Vertex Size  
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 50000 1000000 2020 10 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 100000 1000000 2020 10 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 250000 1000000 2020 10 100

#Overall size   
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 10000 100000 2020 10 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 25000 250000 2020 10 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 50000 500000 2020 10 100

#K count 
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 30000 500000 2020 5 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 30000 500000 2020 10 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 30000 500000 2020 15 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 30000 500000 2020 20 100

#Extras
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 500000 1000000 2020 10 100
java -Xms32G -Xmx64G -Xmn48G -Xss1G -cp $CLASSPATHS shell.TestRandom 200000 2000000 2020 10 100


mv target/test_result.csv target/random_result.csv
git add target/random_result.csv
git commit -m "Tested the random graph"
git push origin
