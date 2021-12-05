package propertyBasedTest.quickcheck;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class NumberConstrainGenerator extends Generator<Long> {

    public NumberConstrainGenerator() {
        super(Long.class);
    }

    @Override
    public Long generate(
            SourceOfRandomness random,
            GenerationStatus status) {
        return random.nextLong();
    }

}
