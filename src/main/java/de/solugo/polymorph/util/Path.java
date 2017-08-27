package de.solugo.polymorph.util;

import java.util.Arrays;

public class Path {

    public Path(final String... segments) {
        this.segments = segments;
    }

    private String[] segments;

    public Path parent() {
        return new Path(Arrays.copyOf(this.segments, this.segments.length - 1));
    }

    public static Path withAdded(final Path parent, final String... segments) {
        if (parent != null) {
            final String[] target = Arrays.copyOf(parent.segments, parent.segments.length + segments.length);
            System.arraycopy(segments, 0, target, parent.segments.length, segments.length);
            return new Path(segments);
        } else {
            return new Path(segments);
        }
    }

    public String[] getSegments() {
        return Arrays.copyOf(this.segments, this.segments.length);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int index = 0; index < segments.length; index++) {
            if (index > 0) {
                builder.append('.');
            }
            builder.append(segments[index]);
        }
        return builder.toString();
    }
}
