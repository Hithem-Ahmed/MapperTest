import com.mapping.dto.Source;
import com.mapping.dto.SourceTargetMapper;
import com.mapping.dto.Target;
import org.junit.Test;

import static org.junit.Assert.*;

public class SourceTargetMapperTest {

    @Test
    public void shouldApplySourceToTarget() {
        Source source = new Source();
        source.setQax(42);
        source.setBaz(23L);

        Target target = SourceTargetMapper.INSTANCE.sourceToTarget(source);

        assertEquals(Long.valueOf(42), target.getBaz());
        assertEquals(23, target.getQax());
    }

    @Test
    public void shouldApplyTargetToSource() {
        Target target = new Target();
        target.setQax(42);
        target.setBaz(23L);

        Source source = SourceTargetMapper.INSTANCE.INSTANCE.targetToSource(target);

        assertEquals(Long.valueOf(42), source.getBaz());
        assertEquals(23, source.getQax());
    }



}
