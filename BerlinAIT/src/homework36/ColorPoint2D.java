package homework36;

import java.util.Objects;

public class ColorPoint2D extends Point2D {
    private String color;

    public ColorPoint2D(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColorPoint2D that = (ColorPoint2D) o;

        return Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
