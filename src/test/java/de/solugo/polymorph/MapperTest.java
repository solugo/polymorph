package de.solugo.polymorph;

import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MapperTest {

    @Test
    public void testClass() {
       final Mapper mapper = Mapper.createDefault();

       assertThat(mapper.map("1", Integer.class), is(1));
       assertThat(mapper.map("1", Long.class), is(1L));
       assertThat(mapper.map("1", Double.class), is(1D));


       final SourceItem sourceItem = new SourceItem();
       sourceItem.getValues().put("1", Arrays.asList(1));
       final TargetItem targetItem = mapper.map(sourceItem, TargetItem.class);


    }

    @Data
    public static class SourceItem {
        private final Map<String, List<Integer>> values = new HashMap<>();
    }

    @Data
    public static class TargetItem {
        private final Map<Integer, List<String>> values = new HashMap<>();
    }
}
