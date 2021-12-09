package propertyBasedTest.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class StringConstrainGenerator extends Generator<String> {

    public StringConstrainGenerator() {
        super(String.class);
    }

    @Override ////generate string of length 4
    public String generate(SourceOfRandomness random, GenerationStatus status) {

    	
        String s1 = "";
        s1 = s1 + String.valueOf((char) random.nextInt()) + String.valueOf((char) random.nextInt())
                + String.valueOf((char) random.nextInt()) + String.valueOf((char) random.nextInt());
        return s1;
    }
}




