package ketok.wilderness.util;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.Parameter;

public class ParameterPointBuilder {
    private static final Parameter FULL_RANGE = Parameter.span(-1, 1);

    private Parameter temperature = FULL_RANGE, humidity = FULL_RANGE, continentalness = FULL_RANGE, erosion = FULL_RANGE, depth = Parameter.point(0), weirdness = FULL_RANGE;
    private long offset = 0;

    public ParameterPointBuilder temperature(float min, float max) {
        this.temperature = Parameter.span(min, max);
        return this;
    }

    public ParameterPointBuilder humidity(float min, float max) {
        this.humidity = Parameter.span(min, max);
        return this;
    }

    public ParameterPointBuilder continentalness(float min, float max) {
        this.continentalness = Parameter.span(min, max);
        return this;
    }

    public ParameterPointBuilder erosion(float min, float max) {
        this.erosion = Parameter.span(min, max);
        return this;
    }

    public ParameterPointBuilder depth(float min, float max) {
        this.depth = Parameter.span(min, max);
        return this;
    }

    public ParameterPointBuilder weirdness(float min, float max) {
        this.weirdness = Parameter.span(min, max);
        return this;
    }

    public ParameterPointBuilder temperature(float value) {
        this.temperature = Parameter.point(value);
        return this;
    }

    public ParameterPointBuilder humidity(float value) {
        this.humidity = Parameter.point(value);
        return this;
    }

    public ParameterPointBuilder continentalness(float value) {
        this.continentalness = Parameter.point(value);
        return this;
    }

    public ParameterPointBuilder erosion(float value) {
        this.erosion = Parameter.point(value);
        return this;
    }

    public ParameterPointBuilder depth(float value) {
        this.depth = Parameter.point(value);
        return this;
    }

    public ParameterPointBuilder weirdness(float value) {
        this.weirdness = Parameter.point(value);
        return this;
    }

    public ParameterPointBuilder offset(float offset) {
        this.offset = Climate.quantizeCoord(offset);
        return this;
    }

    public Climate.ParameterPoint build() {
        return new Climate.ParameterPoint(temperature, humidity, continentalness, erosion, depth, weirdness, offset);
    }
}